package com.example.product.publicHealthCenter.service;

import com.example.product.api.dto.DocumentDto;
import com.example.product.api.dto.KakaoApiResponseDto;
import com.example.product.api.service.KakaoAddressSearchService;
import com.example.product.direction.entity.Direction;
import com.example.product.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublicHealthCenterRecommendationService {

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    public void recommendPublicHealthCenterList(String address){

        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if(Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentList())) {
            log.error("[PublicHealthCenterRecommendationService recommendPublichealthCenterList fail : Input address: {} ]", address);
            return;
        }

        DocumentDto documentDto = kakaoApiResponseDto.getDocumentList().get(0);

        List<Direction> directionList = directionService.buildDirectionList(documentDto);

        directionService.saveAll(directionList);

    }

}
