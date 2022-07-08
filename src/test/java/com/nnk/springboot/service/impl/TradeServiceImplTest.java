package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;
import com.nnk.springboot.utility.ConvertTo;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@Tag("TradeServiceImplTest")
@DisplayName("trade service implementation class")
public class TradeServiceImplTest {

  @Mock
  private TradeRepository tradeRepository;

  private TradeService tradeService;

  private Trade trade;

  @BeforeEach
  public void setUp() {
    tradeService = new TradeServiceImpl(tradeRepository);

    trade = new Trade("Trade Account", "Type", 10d);
  }

  @Test
  @DisplayName("getAllTrade, should return a list of trade")
  public void getAllTrade_ShouldReturnListOfTrade() {
    // GIVEN
    when(tradeRepository.findAll()).thenReturn(List.of(trade));

    // WHEN
    Iterable<Trade> results = tradeService.getTrades();

    // THEN
    verify(tradeRepository, times(1)).findAll();
    assertThat(results).contains(trade);
  }

  @Test
  @DisplayName("getOneTrade, should return a trade")
  public void getOneTrade_ShouldReturnTrade() {
    // GIVEN
    Integer id = 1;
    when(tradeRepository.findById(id)).thenReturn(Optional.ofNullable(trade));

    // WHEN
    Optional<Trade> result = tradeService.getTrade(id);

    // THEN
    verify(tradeRepository, times(1)).findById(id);
    assertThat(result.get()).isEqualTo(trade);
  }

  @Test
  @DisplayName("deleteTrade, should delete a trade")
  public void deleteTrade_ShouldDeleteTrade() {
    // GIVEN
    doNothing().when(tradeRepository).delete(trade);

    // WHEN
    tradeService.deleteTrade(trade);

    // THEN
    assertThat(tradeService.getTrades()).doesNotContain(trade);
  }

  @Test
  @DisplayName("saveTrade should save a trade")
  public void saveBidList() {
    // GIVEN
    when(tradeRepository.save(trade)).thenReturn(trade);

    // WHEN
    Trade result = tradeService.saveTrade(trade);

    // THEN
    assertThat(result).isEqualTo(trade);
  }
}