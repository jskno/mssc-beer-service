package jskno.micro.msscbeerservice.web.mappers;

import jskno.micro.msscbeerservice.domain.Beer;
import jskno.micro.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
