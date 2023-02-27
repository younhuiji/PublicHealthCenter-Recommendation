package com.example.product.direction.entity;

import com.example.product.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "direction")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Direction extends BaseTimeEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // 고객
        private String inputAddress;
        private double inputLatitude;
        private double inputLongitude;

        // 보건소
        private String targetPublicHealthCenterName;
        private String targetAddress;
        private double targetLatitude;
        private double targetLongitude;

        // 고객 주소 와 보건소 주소 사이의 거리
        private double distance;
}
