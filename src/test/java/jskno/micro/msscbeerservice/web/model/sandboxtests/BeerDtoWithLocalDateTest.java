package jskno.micro.msscbeerservice.web.model.sandboxtests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jskno.micro.msscbeerservice.web.model.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;

@JsonTest
class BeerDtoWithLocalDateTest extends BaseTestWithLocalDate {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializeDto() throws JsonProcessingException {
        BeerDtoWithLocalDate beerDto = getDtoWithLocalDate();

        String jsonString = objectMapper.writeValueAsString(beerDto);

        System.out.println(jsonString);
    }

    @Test
    void testDeserializeJson() throws IOException {
        String beerJson = "{\"version\":null,\"createdDate\":\"2021-02-16T12:19:10+0100\",\"lastModifiedDate\":\"2021-02-16T12:19:10.7667307+01:00\",\"beerName\":\"BeerName\",\"beerStyle\":\"ALE\",\"upc\":123123123123,\"price\":\"12.99\",\"quantityOnHand\":50,\"myLocalDate\":\"20210216\",\"beerId\":\"24f56bcb-6f04-42b7-bf7a-8d0659e57d83\"}";

        BeerDtoWithLocalDate beerDto = objectMapper.readValue(beerJson, BeerDtoWithLocalDate.class);

        System.out.println(beerDto);
    }

}