package com.example.product.publicHealthCenter.service

import com.example.product.AbstractIntegrationContainerBaseTest
import com.example.product.publicHealthCenter.entity.PublicHealthCenter
import com.example.product.publicHealthCenter.repository.PublicHealthCenterRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

class PublicHealthCenterRepositoryServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private PublicHealthCenterRepositoryService publicHealthCenterRepositoryService

    @Autowired
    private PublicHealthCenterRepository publicHealthCenterRepository

    // db 정리
    def setup() {
        publicHealthCenterRepository.deleteAll()
    }

    def "PublicHealthCenterRepository update - drity checking success"(){
        given:
        String inputAddress = "서울 특별시 성북구 종암동"
        String modifiedAddress = "서울 광진구 구의동"
        String name = "은혜 약국"

        def publicHealthCenter = PublicHealthCenter.builder()
                                .publicHealthCenterAddress(inputAddress)
                                .publicHealthCenterName(name)
                                .build()

        when:
        def entity = publicHealthCenterRepository.save(publicHealthCenter)
        publicHealthCenterRepositoryService.updateAddress(entity.getId(), modifiedAddress)

        def result = publicHealthCenterRepository.findAll()

        then:
        result.get(0).getPublicHealthCenterAddress() == modifiedAddress
    }

    def "PublicHealthCenterRepository update - drity checking fail"(){
        given:
        String inputAddress = "서울 특별시 성북구 종암동"
        String modifiedAddress = "서울 광진구 구의동"
        String name = "은혜 약국"

        def publicHealthCenter = PublicHealthCenter.builder()
                .publicHealthCenterAddress(inputAddress)
                .publicHealthCenterName(name)
                .build()

        when:
        def entity = publicHealthCenterRepository.save(publicHealthCenter)
        publicHealthCenterRepositoryService.updateAddressTransactionTest(entity.getId(), modifiedAddress)

        def result = publicHealthCenterRepository.findAll()

        then:
        result.get(0).getPublicHealthCenterAddress() == inputAddress
    }
}
