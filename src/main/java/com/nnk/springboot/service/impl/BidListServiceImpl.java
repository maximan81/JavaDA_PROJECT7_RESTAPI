package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
import com.nnk.springboot.utility.ConvertTo;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * BidListServiceImpl. class that implement
 * bidList business logic
 */
@Service
public class BidListServiceImpl implements BidListService {

  private final BidListRepository bidListRepository;

  public BidListServiceImpl(BidListRepository bidListRepository) {
    this.bidListRepository = bidListRepository;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<BidList> getAllBidList() {
    return bidListRepository.findAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<BidList> getOneBidList(Integer id) {
    return bidListRepository.findById(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteBidList(BidList bidList) {
    bidListRepository.delete(bidList);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BidList saveBidList(BidListDto bidListDto) {
    return bidListRepository.save(ConvertTo.convertToBidList(bidListDto));
  }

}
