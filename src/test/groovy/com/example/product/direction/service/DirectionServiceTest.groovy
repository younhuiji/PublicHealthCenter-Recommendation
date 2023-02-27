package com.example.product.direction.service

import com.example.product.api.dto.DocumentDto
import com.example.product.publicHealthCenter.dto.PublicHealthCenterDto
import com.example.product.publicHealthCenter.service.PublicHealthCenterSearchService
import spock.lang.Specification

class DirectionServiceTest extends Specification {

    private PublicHealthCenterSearchService publicHealthCenterSearchService = Mock()

    private DirectionService directionService = new DirectionService(publicHealthCenterSearchService)

    private List< PublicHealthCenterDto> publicHealthCenterList

    def setup() {
        publicHealthCenterList = new ArrayList<>()
        publicHealthCenterList.addAll(
                PublicHealthCenterDto.builder()
                        .id(1L)
                        .publicHealthCenterName("중구")
                        .publicHealthCenterAddress("주소1")
                        .latitude(35.10650760)
                        .longitude(129.0321071)
                        .build(),

                PublicHealthCenterDto.builder()
                        .id(2L)
                        .publicHealthCenterName("서구")
                        .publicHealthCenterAddress("주소2")
                        .latitude(35.10753860)
                        .longitude(129.0159046)
                        .build()
        )
    }

    def "buildDirectionList - 결과 값이 거리 순으로 정렬이 되는지 확인" () {
        given:
        def addressName= "부산광역시 연제구 연제로 30"
        double inputLatitude = 35.1795543
        double inputLongitude = 129.0756416

        def documentDto = DocumentDto.builder()
                .addressName(addressName)
                .latitude(inputLatitude)
                .longitude(inputLongitude)
                .build()

        when:
        publicHealthCenterSearchService.searchPublicHealthCenterDtoList() >> publicHealthCenterList

        def result = directionService.buildDirectionList(documentDto)

        then:
        result.size() == 2
        result.get(0).targetPublicHealthCenterName == "중구"
        result.get(1).targetPublicHealthCenterName == "서구"
    }

    def "buildDirectionList - 정해진 반경 10km 내에 검색이 되는지 확인" () {
        given:
        def addressName= "부산광역시 연제구 연제로 30"
        double inputLatitude = 35.1795543
        double inputLongitude = 129.0756416

        publicHealthCenterList.add(
                PublicHealthCenterDto.builder()
                        .id(3L)
                        .publicHealthCenterName("포천시보건소")
                        .publicHealthCenterAddress("주소3")
                        .latitude(37.900572152844)
                        .longitude(127.20113755295)
                        .build()
        )

        def documentDto = DocumentDto.builder()
                .addressName(addressName)
                .latitude(inputLatitude)
                .longitude(inputLongitude)
                .build()

        when:
        publicHealthCenterSearchService.searchPublicHealthCenterDtoList() >> publicHealthCenterList

        def result = directionService.buildDirectionList(documentDto)

        then:
        result.size() == 2
        result.get(0).targetPublicHealthCenterName == "중구"
        result.get(1).targetPublicHealthCenterName == "서구"

    }

}
