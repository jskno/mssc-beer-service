package jskno.micro.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

@JsonTest
@ActiveProfiles("snake")
public class BeerDtoSnakeTest extends BaseTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializeDtoSnakeNamingStrategy() throws JsonProcessingException {
        BeerDto beerDto = getDto();

        String jsonString = objectMapper.writeValueAsString(beerDto);

        System.out.println(jsonString);
    }

    @Test
    void testDeserializeJsonSnakeNamingStrategy() throws IOException {
        String beerJson = "{\"beerId\":\"7d982e12-19a3-4eee-a5ab-0641ec2f35d0\",\"version\":null,\"created_date\":\"2021-02-16T12:46:15+0100\",\"last_modified_date\":\"2021-02-16T12:46:15+0100\",\"beer_name\":\"BeerName\",\"beer_style\":\"ALE\",\"upc\":123123123123,\"price\":12.99,\"quantity_on_hand\":50}";

        BeerDto beerDto = objectMapper.readValue(beerJson, BeerDto.class);

        System.out.println(beerDto);
    }

}
