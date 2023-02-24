package com.example.product.publicHealthCenter.repository

import com.example.product.AbstractIntegerationContainerBaseTest
import com.example.product.publicHealthCenter.entity.PublicHealthCenter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

class PublicHealthCenterRepositoryTest extends AbstractIntegerationContainerBaseTest {

    @Autowired
    private PublicHealthCenterRepository publicHealthCenterRepository

    def "PublicHealthCenterRepository save"() {
        given:
        String address = "서울 특별시 성북구 종암동"
        String name = "은혜 약국"
        double latitude = 36.11
        double longitude = 128.11

        def publicHealthCenter = PublicHealthCenter.builder()
        .publicHealthCenterAddress(address)
        .publicHealthCenterName(name)
        .latitude(latitude)
        .longitude(longitude)
        .build()

        when:
        def result = publicHealthCenterRepository.save(publicHealthCenter)

        then:
        result.getPublicHealthCenterAddress() == address
        result.getPublicHealthCenterName() == name
        result.getLatitude() == latitude
        result.getLongitude() == longitude
    }

}
