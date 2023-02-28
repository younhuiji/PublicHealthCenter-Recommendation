package com.example.product.publicHealthCenter.service;

import com.example.product.publicHealthCenter.entity.PublicHealthCenter;
import com.example.product.publicHealthCenter.repository.PublicHealthCenterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicHealthCenterRepositoryService {

    private final PublicHealthCenterRepository publicHealthCenterRepository;

    // self invocation test
    public void bar(List<PublicHealthCenter> publicHealthCenter) {
        log.info("bar CurrentTransactionName: "+ TransactionSynchronizationManager.getCurrentTransactionName());
        foo(publicHealthCenter);
    }

    // self invocation test
    @Transactional
    public void foo(List<PublicHealthCenter> publicHealthCenterList) {
        log.info("foo CurrentTransactionName: "+ TransactionSynchronizationManager.getCurrentTransactionName());
        publicHealthCenterList.forEach(pharmacy -> {
            publicHealthCenterRepository.save(pharmacy);
            throw new RuntimeException("error");
        });
    }

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

    // read only test
    @Transactional(readOnly = true)
    public void startReadOnlyMethod(Long id) {
        publicHealthCenterRepository.findById(id).ifPresent(pharmacy ->
                pharmacy.changePublicHealthCenterAddress("부산광역시 서구 부용로 30"));
    }

    @Transactional(readOnly = true)
    public List<PublicHealthCenter> findAll() {
        return publicHealthCenterRepository.findAll();
    }
}
