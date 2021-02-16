package jskno.micro.msscbeerservice.services;

import jskno.micro.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {

    BeerDto getById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beer);

    BeerDto updateBeer(UUID beerId, BeerDto beer);
}
