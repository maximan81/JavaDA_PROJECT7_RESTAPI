package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TradeRepository. Interface that allows to access and
 * persist data between Trade object and poseidenDb database
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
