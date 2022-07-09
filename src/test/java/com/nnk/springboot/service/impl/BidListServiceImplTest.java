package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
import com.nnk.springboot.utility.ConvertTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
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
@Tag("BidListServiceImplTest")
@DisplayName("bidList service implementation class")
public class BidListServiceImplTest  {

  @Mock
  private BidListRepository bidListRepository;

  private BidListService bidListService;

  private BidList bidList;

  @BeforeEach
  public void setUp() {
    bidListService = new BidListServiceImpl(bidListRepository);

    bidList = new BidList("Account Test", "Type Test", 10d);
  }

  @Test
  @DisplayName("getAllBidList, should return a list of bidList")
  public void getAllBidList_ShouldReturnListOfBidList() {
    // GIVEN
    when(bidListRepository.findAll()).thenReturn(List.of(bidList));

    // WHEN
    Iterable<BidList> results = bidListService.getAllBidList();

    // THEN
    verify(bidListRepository, times(1)).findAll();
    assertThat(results).contains(bidList);
  }

  @Test
  @DisplayName("getOneBidList, should return a bidList")
  public void getOneBidList_ShouldReturnBidList() {
    // GIVEN
    Integer id = 1;
    when(bidListRepository.findById(id)).thenReturn(Optional.ofNullable(bidList));

    // WHEN
    Optional<BidList> result = bidListService.getOneBidList(id);

    // THEN
    verify(bidListRepository, times(1)).findById(id);
    assertThat(result.get()).isEqualTo(bidList);
  }

  @Test
  @DisplayName("deleteBidList, should delete a bidList")
  public void deleteBidList_ShouldDeleteBidList() {
    // GIVEN
    doNothing().when(bidListRepository).delete(bidList);

    // WHEN
    bidListService.deleteBidList(bidList);

    // THEN
    assertThat(bidListService.getAllBidList()).doesNotContain(bidList);
  }

  @Test
  @DisplayName("saveBidList, should save a bidList")
  public void saveBidList() {
    // GIVEN
    when(bidListRepository.save(bidList)).thenReturn(bidList);

    // WHEN
    BidList result = bidListService.saveBidList(ConvertTo.convertToBidListDto(bidList));

    // THEN
    assertThat(result).isEqualTo(bidList);
  }
}