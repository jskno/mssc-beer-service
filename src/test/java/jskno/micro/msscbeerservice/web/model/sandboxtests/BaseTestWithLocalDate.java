package jskno.micro.msscbeerservice.web.model.sandboxtests;

import jskno.micro.msscbeerservice.web.model.BeerDto;
import jskno.micro.msscbeerservice.web.model.BeerStyleEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BaseTestWithLocalDate {

    BeerDtoWithLocalDate getDtoWithLocalDate() {
        return BeerDtoWithLocalDate.builder()
                .id(UUID.randomUUID())
                .beerName("BeerName")
                .beerStyle(BeerStyleEnum.ALE)
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .price(new BigDecimal("12.99"))
                .upc(123123123123L)
                .quantityOnHand(50)
                .myLocalDate(LocalDate.now())
                .build();
    }
}
