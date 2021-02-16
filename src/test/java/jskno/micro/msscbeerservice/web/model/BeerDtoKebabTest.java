package jskno.micro.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

@JsonTest
@ActiveProfiles("kebab")
public class BeerDtoKebabTest extends BaseTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializeDtoKebabNamingStrategy() throws JsonProcessingException {
        BeerDto beerDto = getDto();

        String jsonString = objectMapper.writeValueAsString(beerDto);

        System.out.println(jsonString);
    }

    @Test
    void testDeserializeJsonKebabNamingStrategy() throws IOException {
        String beerJson = "{\"beerId\":\"496529a5-b43b-4226-a813-d3dfd7b31491\",\"version\":null,\"created-date\":\"2021-02-16T12:46:53+0100\",\"last-modified-date\":\"2021-02-16T12:46:53+0100\",\"beer-name\":\"BeerName\",\"beer-style\":\"ALE\",\"upc\":123123123123,\"price\":12.99,\"quantity-on-hand\":50}";

        BeerDto beerDto = objectMapper.readValue(beerJson, BeerDto.class);

        System.out.println(beerDto);
    }

}
