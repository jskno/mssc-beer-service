package jskno.micro.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@JsonTest
class BeerDtoTest extends BaseTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializeDto() throws JsonProcessingException {
        BeerDto beerDto = getDto();

        String jsonString = objectMapper.writeValueAsString(beerDto);

        System.out.println(jsonString);
    }

    @Test
    void testDeserializeJson() throws IOException {
        String beerJson = "{\"beerId\":\"36624816-2060-4276-9cc4-a4b82384968a\",\"version\":null,\"createdDate\":\"2021-02-16T12:44:28+0100\",\"lastModifiedDate\":\"2021-02-16T12:44:28+0100\",\"beerName\":\"BeerName\",\"beerStyle\":\"ALE\",\"upc\":123123123123,\"price\":12.99,\"quantityOnHand\":50}";

        BeerDto beerDto = objectMapper.readValue(beerJson, BeerDto.class);

        System.out.println(beerDto);
    }

}