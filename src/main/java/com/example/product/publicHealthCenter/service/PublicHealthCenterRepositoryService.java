package com.example.product.publicHealthCenter.service;

import com.example.product.publicHealthCenter.entity.PublicHealthCenter;
import com.example.product.publicHealthCenter.repository.PublicHealthCenterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicHealthCenterRepositoryService {

    private final PublicHealthCenterRepository publicHealthCenterRepository;

    @Transactional
    public void updateAddress(Long id, String address){
        PublicHealthCenter entity = publicHealthCenterRepository.findById(id).orElse(null);

        if(Objects.isNull(entity)) {
            log.error("[publicHealthCenterRepository updateAddress] not found id: {}", id);
            return;
        }

        entity.changePublicHealthCenterAddress(address);
    }

    // for test
    public void updateAddressTransactionTest(Long id, String address){
        PublicHealthCenter entity = publicHealthCenterRepository.findById(id).orElse(null);

        if(Objects.isNull(entity)) {
            log.error("[publicHealthCenterRepository updateAddress] not found id: {}", id);
            return;
        }

        entity.changePublicHealthCenterAddress(address);
    }

    @Transactional(readOnly = true)
    public List<PublicHealthCenter> findAll() {
        return publicHealthCenterRepository.findAll();
    }
}
