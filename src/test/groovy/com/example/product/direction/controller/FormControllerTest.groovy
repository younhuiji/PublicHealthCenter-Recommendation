package com.example.product.direction.controller

import com.example.product.direction.dto.OutputDto
import com.example.product.publicHealthCenter.service.PublicHealthCenterRecommendationService
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class FormControllerTest extends Specification {

    private MockMvc mockMvc
    private PublicHealthCenterRecommendationService publicHealthCenterRecommendationService = Mock()
    private List<OutputDto> outputDtoList

    def setup (){
        // FormController MockMvc 객체로 만든다.
        mockMvc = MockMvcBuilders.standaloneSetup(new FormController(publicHealthCenterRecommendationService))
        .build()

        outputDtoList = new ArrayList<>()
        outputDtoList.addAll(
                OutputDto.builder()
                        .publicHealthCenterName("publicHealthCenter1")
                        .build(),
                OutputDto.builder()
                        .publicHealthCenterName("publicHealthCenter2")
                        .build()
        )
    }

    def "GET /"(){
        expect:
        // FormController 의 "/" URI를 get방식으로 호출
        mockMvc.perform(get("/"))
        .andExpect(handler().handlerType(FormController.class))
        .andExpect(handler().methodName("main"))
        .andExpect(status().isOk())
        .andExpect(view().name("main"))
        .andDo (log())
    }

    def "POST /search"(){

        given:
        String inputAddress = "부산 강서구 강동송백5길 55"

        when:
        def resultActions = mockMvc.perform(post("/search")
                .param("address", inputAddress))

        then:
        1 * publicHealthCenterRecommendationService.recommendPublicHealthCenterList(argument -> {
            assert argument == inputAddress // mock 객체의 argument 검증
        }) >> outputDtoList

        resultActions
                .andExpect(status().isOk())
                .andExpect(view().name("output"))
                .andExpect(model().attributeExists("outputFormList")) // model에 outputFormList라는 key가 존재하는지 확인
                .andExpect(model().attribute("outputFormList", outputDtoList))
                .andDo(print())

    }
}
