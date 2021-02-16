package jskno.micro.msscbeerservice.services;

import jskno.micro.msscbeerservice.domain.Beer;
import jskno.micro.msscbeerservice.exceptions.EntityNotFoundException;
import jskno.micro.msscbeerservice.repositories.BeerRepository;
import jskno.micro.msscbeerservice.web.mappers.BeerMapper;
import jskno.micro.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDto(
                beerRepository.findById(beerId)
                    .orElseThrow(() -> new EntityNotFoundException()));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(
                beerRepository.save(
                        beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId)
                .orElseThrow(() -> new EntityNotFoundException());

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(
                beerRepository.save(beer));
    }
}
