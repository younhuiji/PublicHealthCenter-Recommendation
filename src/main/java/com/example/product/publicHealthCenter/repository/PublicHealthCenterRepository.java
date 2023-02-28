package com.example.product.publicHealthCenter.repository;

import com.example.product.publicHealthCenter.entity.PublicHealthCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PublicHealthCenterRepository extends JpaRepository<PublicHealthCenter, Long> {
}
