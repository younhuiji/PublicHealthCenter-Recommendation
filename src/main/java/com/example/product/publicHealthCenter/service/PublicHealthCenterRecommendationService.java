package com.example.product.publicHealthCenter.service;

import com.example.product.api.dto.DocumentDto;
import com.example.product.api.dto.KakaoApiResponseDto;
import com.example.product.api.service.KakaoAddressSearchService;
import com.example.product.direction.dto.OutputDto;
import com.example.product.direction.entity.Direction;
import com.example.product.direction.service.Base62Service;
import com.example.product.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublicHealthCenterRecommendationService {

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;
    private final Base62Service base62Service;
    private static final String ROAD_VIEW_BASE_URL = "https://map.kakao.com/link/roadview/";

    @Value("${publicHealthCenter.recommendation.base.url}")
    private String baseURl;

    public List<OutputDto> recommendPublicHealthCenterList(String address){

        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if(Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentList())) {
            log.error("[PublicHealthCenterRecommendationService recommendPublichealthCenterList fail : Input address: {} ]", address);
            return Collections.emptyList();
        }

        // 공공기관 보건소 데이터 및 거리계산 알고리즘 이용
        DocumentDto documentDto = kakaoApiResponseDto.getDocumentList().get(0);

        // kakao 카테고리를 이용한 장소 검색 api 이용
        List<Direction> directionList = directionService.buildDirectionList(documentDto);

        List result = directionService.saveAll(directionList)
                .stream()
                .map(this::convertToOutputDto) // t -> convertToOutDto(t)
                .collect(Collectors.toList());

        return directionService.saveAll(directionList)
                .stream()
                .map(this::convertToOutputDto) // t -> convertToOutDto(t)
                .collect(Collectors.toList());

    }

    private OutputDto convertToOutputDto(Direction direction) {

     return OutputDto.builder()
             .publicHealthCenterName(direction.getTargetPublicHealthCenterName())
             .publicHealthCenterAddress(direction.getTargetAddress())
             .directionUrl(baseURl + base62Service.encodingDirectionId(direction.getId()))
             .roadViewUrl(ROAD_VIEW_BASE_URL + direction.getTargetLatitude() + "," + direction.getTargetLongitude())
             .distance(String.format("%.2f km", direction.getDistance()))
             .build();
    }

}
