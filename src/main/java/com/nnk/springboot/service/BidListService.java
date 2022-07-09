package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDto;

import java.util.Optional;

/**
 * BidListService interface structure the business logic
 * of BidList.
 */
public interface BidListService {

  /**
   * saveBidList. Method that save given bidList in database.
   *
   * @param bidListDto an bidListDto
   * @return an bidList
   */
  BidList saveBidList(BidListDto bidListDto);

  /**
   * getAllBidList. Method that get all bidList from database.
   *
   * @return Iterable bidList
   */
  Iterable<BidList> getAllBidList();

  /**
   * getOneBidList. Method that get one bidList from database.
   *
   * @param id an bidList id
   * @return optional bidList
   */
  Optional<BidList> getOneBidList(Integer id);

  /**
   * deleteBidList. Method that delete given bidList in database.
   *
   * @param bidList a bidList
   */
  void deleteBidList(BidList bidList);
}
