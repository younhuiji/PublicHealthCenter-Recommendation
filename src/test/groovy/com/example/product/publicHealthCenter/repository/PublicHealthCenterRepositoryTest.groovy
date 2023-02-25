package com.example.product.publicHealthCenter.repository

import com.example.product.AbstractIntegrationContainerBaseTest
import com.example.product.publicHealthCenter.entity.PublicHealthCenter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDateTime

class PublicHealthCenterRepositoryTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private PublicHealthCenterRepository publicHealthCenterRepository

    // 각 test 시작 전에 db 정리해주기
    def setup() {
        publicHealthCenterRepository.deleteAll()
    }

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

    def "PublicHealthCenterRepository saveAll()"(){
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
        publicHealthCenterRepository.saveAll(Arrays.asList(publicHealthCenter))
        def result = publicHealthCenterRepository.findAll()

        then:
        result.size() == 1
    }

    def "BaseTimeEntity 등록"(){
        given:
        LocalDateTime now = LocalDateTime.now()
        String address = "서울 특별시 성북구 종암동"
        String name = "은혜 약국"

        def publicHealthCenter = PublicHealthCenter.builder()
                    .publicHealthCenterAddress(address)
                    .publicHealthCenterName(name)
                    .build()

        when:
        publicHealthCenterRepository.save(publicHealthCenter)
        def result = publicHealthCenterRepository.findAll()

        then:
        result.get(0).getCreatedDate().isAfter(now)
        result.get(0).getModifiedDate().isAfter(now)

    }

}
