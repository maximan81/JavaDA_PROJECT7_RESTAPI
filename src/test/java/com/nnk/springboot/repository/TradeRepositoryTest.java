package com.nnk.springboot.repository;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Tag("TradeRepositoryTest")
@DisplayName("trade repository implementation class")
@SpringBootTest
public class TradeRepositoryTest {

  @Autowired
  private TradeRepository tradeRepository;

  private Trade trade;

  @BeforeEach
  public void setUp() {
    trade = new Trade("Trade Account", "Type", 30d);
    tradeRepository.save(trade);
  }

  @Test
  @DisplayName("saveTrade, should save a trade")
  public void saveTrade_ShouldSaveTrade() {
    // GIVEN
    Trade saveTrade = new Trade("Trade Account", "Type", 12d);

    // WHEN
    Trade result = tradeRepository.save(saveTrade);

    // THEN
    Assert.assertNotNull(result.getTradeId());
    Assert.assertEquals(result.getAccount(), "Trade Account", "Trade Account");
  }

  @Test
  @DisplayName("getAllTrade, should return trade list")
  public void getAllTrade_ShouldReturnTradeList() {
    // GIVEN
    // WHEN
    List<Trade> listResult = tradeRepository.findAll();

    // THEN
    Assert.assertTrue(listResult.size() > 0);
  }

  @Test
  @DisplayName("getOneTrade, should return a trade")
  public void getOneTrade_ShouldReturnTrade() {
    // GIVEN
    Integer id = trade.getTradeId();
    // WHEN
    Optional<Trade> trade = tradeRepository.findById(id);

    // THEN
    Assert.assertTrue(trade.isPresent());

  }

  @Test
  @DisplayName("deleteTrade, should delete a trade")
  public void deleteTrade_ShouldDeleteTrade() {
    // GIVEN
    Integer id = trade.getTradeId();

    // WHEN
    tradeRepository.delete(trade);
    Optional<Trade> trade = tradeRepository.findById(id);

    // THEN
    Assert.assertFalse(trade.isPresent());
  }

  @Test
  @DisplayName("updateTrade, should update a trade")
  public void updateTrade_ShouldUpdateTrade() {
    // GIVEN
    trade.setAccount("Trade Account update");

    // WHEN
    trade = tradeRepository.save(trade);

    // THEN
    Assert.assertEquals(trade.getAccount(), "Trade Account update", "Trade Account update");
  }
  
}