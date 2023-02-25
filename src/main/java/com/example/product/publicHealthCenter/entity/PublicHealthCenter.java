package com.example.product.publicHealthCenter.entity;

import com.example.product.BaseTimeEntity;
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
public class PublicHealthCenter extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String publicHealthCenterName;
    private String publicHealthCenterAddress;
    private double latitude;
    private double longitude;

    public void changePublicHealthCenterAddress(String address){
        this.publicHealthCenterAddress = address;
    }
}
