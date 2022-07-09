package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;

import java.util.Optional;

/**
 * TradeService interface structure the business logic
 * of Trade.
 */
public interface TradeService {

  /**
   * getTrades. Method that get all Trade from database.
   *
   * @return Iterable Trade
   */
  Iterable<Trade> getTrades();

  /**
   * saveTrade. Method that save given Trade in database.
   *
   * @param trade an Trade
   * @return an Trade
   */
  Trade saveTrade(Trade trade);

  /**
   * getTrade. Method that get one Trade from database.
   *
   * @param id a Trade id
   * @return optional Trade
   */
  Optional<Trade> getTrade(Integer id);

  /**
   * deleteTrade. Method that delete given Trade in database.
   *
   * @param trade a Trade
   */
  void deleteTrade(Trade trade);
}
