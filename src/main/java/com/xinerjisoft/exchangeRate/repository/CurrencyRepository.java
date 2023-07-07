package com.xinerjisoft.exchangeRate.repository;

import com.xinerjisoft.exchangeRate.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    // özel sorguları buraya ekleyebilirsiniz


}