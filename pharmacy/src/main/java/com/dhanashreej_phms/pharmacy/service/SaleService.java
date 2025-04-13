package com.dhanashreej_phms.pharmacy.service;

import com.dhanashreej_phms.pharmacy.domain.Sale;
import com.dhanashreej_phms.pharmacy.repository.SaleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepo repo;

    public List<Sale> getAll() {
        return repo.findAll();
    }

    public void save(Sale sale) {
        repo.save(sale);
    }
    public double getTotalSales() {
        return repo.findAll()
                   .stream()
                   .mapToDouble(sale -> sale.getTotalPrice()) // unboxed from Double
                   .sum();
    }
    
    
}
