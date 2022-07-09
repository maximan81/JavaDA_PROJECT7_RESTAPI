package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * TradeServiceImpl. class that implement
 * Trade business logic
 */
@Service
public class TradeServiceImpl implements TradeService {
  private final TradeRepository tradeRepository;

  public TradeServiceImpl(TradeRepository tradeRepository) {
    this.tradeRepository = tradeRepository;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<Trade> getTrades() {
    return tradeRepository.findAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Trade saveTrade(Trade trade) {
    return tradeRepository.save(trade);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Trade> getTrade(Integer id) {
    return tradeRepository.findById(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteTrade(Trade trade) {
    tradeRepository.delete(trade);
  }
}
