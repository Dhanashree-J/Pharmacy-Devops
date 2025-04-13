package com.dhanashreej_phms.pharmacy.repository;

import com.dhanashreej_phms.pharmacy.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepo extends JpaRepository<Sale, Long> {
}