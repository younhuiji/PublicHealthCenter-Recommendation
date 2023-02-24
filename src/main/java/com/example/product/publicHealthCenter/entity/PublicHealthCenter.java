package com.example.product.publicHealthCenter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "publicHealthCenter")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicHealthCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String publicHealthCenterName;
    private String publicHealthCenterAddress;
    private double latitude;
    private double longitude;

}
