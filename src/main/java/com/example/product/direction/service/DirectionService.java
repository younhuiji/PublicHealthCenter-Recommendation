package com.example.product.direction.service;

import com.example.product.api.dto.DocumentDto;
import com.example.product.direction.entity.Direction;
import com.example.product.publicHealthCenter.dto.PublicHealthCenterDto;
import com.example.product.publicHealthCenter.service.PublicHealthCenterSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectionService {


    private static final int MAX_SEARCH_COUNT = 3; // 약국 최대 검색 갯수
    private static final double RADIUS_KM = 10.0;  // 반경 10 km

    private final PublicHealthCenterSearchService publicHealthCenterSearchService;

    public List<Direction> buildDirectionList(DocumentDto documentDto){

        if(Objects.isNull(documentDto)) return Collections.emptyList();

        // 약국 데이터 조회
        // 거리 계산 알고리즘을 이용하여, 고객과 보건소 사이의 거리를 계산하고 sort
        return publicHealthCenterSearchService.searchPublicHealthCenterDtoList()
                .stream().map(publicHealthCenterDto ->
                Direction.builder()
                        .inputAddress(documentDto.getAddressName())
                        .inputLatitude(documentDto.getLatitude())
                        .inputLongitude(documentDto.getLongitude())
                        .targetAddress(publicHealthCenterDto.getPublicHealthCenterAddress())
                        .targetPublicHealthCenterName(publicHealthCenterDto.getPublicHealthCenterName())
                        .targetLatitude(publicHealthCenterDto.getLatitude())
                        .targetLongitude(publicHealthCenterDto.getLongitude())
                        .distance(
                                calculateDistance(documentDto.getLatitude(), documentDto.getLongitude(),
                                        publicHealthCenterDto.getLatitude(), publicHealthCenterDto.getLongitude())
                        )
                        .build())
                .filter(direction -> direction.getDistance() <= RADIUS_KM)
                .sorted(Comparator.comparing(Direction::getDistance))
                .limit(MAX_SEARCH_COUNT)
                .collect(Collectors.toList());

    }

    // Haversine formula
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double earthRadius = 6371; //Kilometers
        return earthRadius * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
    }

}
