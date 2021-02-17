package jskno.micro.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jskno.micro.msscbeerservice.services.BeerService;
import jskno.micro.msscbeerservice.web.model.BeerDto;
import jskno.micro.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "dev.springframework.guru", uriPort = 80)
@WebMvcTest(BeerController.class)
//@ComponentScan(basePackages = "jskno.micro.msscbeerservice.web.mappers")
public class BeerRestDocControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception {
        given(beerService.getById(ArgumentMatchers.any(), ArgumentMatchers.any())).willReturn(BeerDto.builder().build());
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                .param("iscold", "yes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("v1/beer-get",
                        RequestDocumentation.pathParameters(
                            RequestDocumentation.parameterWithName("beerId")
                                    .description("UUID of desired beer to get")
                ),
                        RequestDocumentation.requestParameters(
                                RequestDocumentation.parameterWithName("iscold")
                                    .description("Is Beer Cold Query param")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("beerId").description("Id of Beer"),
                                PayloadDocumentation.fieldWithPath("version").description("Version number"),
                                PayloadDocumentation.fieldWithPath("createdDate").description("Date Created"),
                                PayloadDocumentation.fieldWithPath("lastModifiedDate").description("Date Updated"),
                                PayloadDocumentation.fieldWithPath("beerName").description("Beer Name"),
                                PayloadDocumentation.fieldWithPath("beerStyle").description("Beer Style"),
                                PayloadDocumentation.fieldWithPath("upc").description("UPC of Beer"),
                                PayloadDocumentation.fieldWithPath("price").description("Price"),
                                PayloadDocumentation.fieldWithPath("quantityOnHand").description("Quantity On hand")
                        )));
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        ConstrainedFields fields = new ConstrainedFields((BeerDto.class));

        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andDo(MockMvcRestDocumentation.document("v1/beer-post",
                        PayloadDocumentation.requestFields(
                                fields.withPath("beerId").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").ignored(),
                                fields.withPath("lastModifiedDate").ignored(),
                                fields.withPath("beerName").description("Name of the beer"),
                                fields.withPath("beerStyle").description("Style of Beer"),
                                fields.withPath("upc").description("Beer UPC").attributes(),
                                fields.withPath("price").description("Beer Price"),
                                fields.withPath("quantityOnHand").ignored()
                        )));
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(RestDocumentationRequestBuilders.put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    private BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .id(null)
                .beerName("Beer Name")
                .beerStyle(BeerStyleEnum.ALE)
                .upc("123456789012")
                .price(new BigDecimal("12.58"))
                .build();
    }

    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return PayloadDocumentation.fieldWithPath(path).attributes(Attributes.key("constraints").value(
                    StringUtils.collectionToDelimitedString(
                        this.constraintDescriptions.descriptionsForProperty(path), ". ")));
        }
    }
}