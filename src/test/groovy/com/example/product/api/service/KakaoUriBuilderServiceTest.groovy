package com.example.product.api.service

import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.shaded.org.bouncycastle.util.test.Test
import spock.lang.Specification

import java.nio.charset.StandardCharsets

class KakaoUriBuilderServiceTest extends Specification {

    private KakaoUriBuilderService kakaoUriBuilderService;

    def setup() {
        kakaoUriBuilderService = new KakaoUriBuilderService()
    }

    def "buildUriByAddressSearch - 한글 파라미터의 경우 정상적으로 인코딩"(){
        given:
        String address = "부산광역시 연제구 연제로 30"
        def charset = StandardCharsets.UTF_8

        when:
        def uri = kakaoUriBuilderService.builderByAddressSearch(address)
        def decodedResult = URLDecoder.decode(uri.toString(), charset)

        then:
        decodedResult == "https://dapi.kakao.com/v2/local/search/address.json?query=부산광역시 연제구 연제로 30"
    }
}
