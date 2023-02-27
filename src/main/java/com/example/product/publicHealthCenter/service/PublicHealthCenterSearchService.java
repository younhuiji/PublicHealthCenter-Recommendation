package com.example.product.publicHealthCenter.service;

import com.example.product.publicHealthCenter.dto.PublicHealthCenterDto;
import com.example.product.publicHealthCenter.entity.PublicHealthCenter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicHealthCenterSearchService {

    private final PublicHealthCenterRepositoryService publicHealthCenterRepositoryService;

        public List<PublicHealthCenterDto> searchPublicHealthCenterDtoList() {

            // redis

            // db

            return  publicHealthCenterRepositoryService.findAll()
                    .stream()
                    .map(this::convertToPublicHealthCenterDto) // option +  enter : map(entity -> convertToPublicHealthCenterDto(entity))
                    .collect(Collectors.toList());
        }

            private PublicHealthCenterDto convertToPublicHealthCenterDto(PublicHealthCenter publicHealthCenter){

                return PublicHealthCenterDto.builder()
                        .id(publicHealthCenter.getId())
                        .publicHealthCenterAddress(publicHealthCenter.getPublicHealthCenterAddress())
                        .publicHealthCenterName(publicHealthCenter.getPublicHealthCenterName())
                        .latitude(publicHealthCenter.getLatitude())
                        .longitude(publicHealthCenter.getLongitude())
                        .build();

        }
}
