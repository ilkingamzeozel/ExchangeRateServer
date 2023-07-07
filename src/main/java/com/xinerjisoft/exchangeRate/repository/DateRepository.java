package com.xinerjisoft.exchangeRate.repository;

import com.xinerjisoft.exchangeRate.model.Date;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateRepository extends JpaRepository<Date, Long> {
}