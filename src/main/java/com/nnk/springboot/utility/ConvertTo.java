package com.nnk.springboot.utility;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDto;

public class ConvertTo {
  public static BidList convertToBidList(BidListDto bidListDto) {
    return new BidList(bidListDto.getBidListId(), bidListDto.getAccount(), bidListDto.getType(), bidListDto.getBidQuantity());
  }

  public static BidListDto convertToBidListDto(BidList bidList) {
    return new BidListDto(bidList.getBidListId(), bidList.getAccount(), bidList.getType(), bidList.getBidQuantity());
  }
}
