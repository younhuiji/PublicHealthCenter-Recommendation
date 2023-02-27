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
        String modifiedAddress = "부산광역시 서구 부용로 30"
        String name = "중구"

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
        String modifiedAddress = "부산광역시 서구 부용로 30"
        String name = "중구"

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
