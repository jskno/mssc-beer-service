package jskno.micro.msscbeerservice.web.controller;

import jskno.micro.msscbeerservice.repositories.BeerRepository;
import jskno.micro.msscbeerservice.web.mappers.BeerMapper;
import jskno.micro.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/repository/beer")
@RequiredArgsConstructor
public class BeerWithRepositoryController {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {
        return new ResponseEntity<>(beerMapper.beerToBeerDto(beerRepository.findById(beerId).get()),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beerDto) {
        beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable UUID beerId,
                                         @RequestBody @Validated BeerDto beerDto) {
        beerRepository.findById(beerId).ifPresent(beer -> {
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle().name());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());

            beerRepository.save(beer);
        });
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
