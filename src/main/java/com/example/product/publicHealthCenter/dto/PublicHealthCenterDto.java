package com.example.product.publicHealthCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublicHealthCenterDto {

    private Long id;
    private String publicHealthCenterName;
    private String publicHealthCenterAddress;
    private double latitude;
    private double longitude;

}
