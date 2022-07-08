package com.nnk.springboot.repository;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Tag("BidListRepositoryTest")
@DisplayName("bidList repository implementation class")
@SpringBootTest
public class BidListRepositoryTest {

  @Autowired
  private BidListRepository bidListRepository;

  private BidList bidList;

  @BeforeEach
  public void setUp() {
    bidList = new BidList("Account Test", "Type Test", 10d);
    bidListRepository.save(bidList);
  }

  @Test
  @DisplayName("saveBidList, should save a bidList")
  public void saveBidList_ShouldSaveBidList() {
    // GIVEN
    BidList bid = new BidList("Account Test 2", "Type Test 2", 20d);

    // WHEN
    BidList result = bidListRepository.save(bid);

    // THEN
    Assert.assertNotNull(result.getBidListId());
    Assert.assertEquals(result.getBidQuantity(), 20d, 20d);
  }

  @Test
  @DisplayName("getAllBidList, should return bidList list")
  public void getAllBidList_ShouldReturnBidListList() {
    // GIVEN
    // WHEN
    List<BidList> listResult = bidListRepository.findAll();

    // THEN
    Assert.assertTrue(listResult.size() > 0);
  }

  @Test
  @DisplayName("getOneBidList, should return a bidList")
  public void getOneBidList_ShouldReturnBidList() {
    // GIVEN
    Integer id = bidList.getBidListId();
    // WHEN
    Optional<BidList> bidList = bidListRepository.findById(id);

    // THEN
    Assert.assertTrue(bidList.isPresent());

  }

  @Test
  @DisplayName("deleteBidList, should delete a bidList")
  public void deleteBidList_ShouldDeleteBidList() {
    // GIVEN
    Integer id = bidList.getBidListId();

    // WHEN
    bidListRepository.delete(bidList);
    Optional<BidList> bidList = bidListRepository.findById(id);

    // THEN
    Assert.assertFalse(bidList.isPresent());
  }

  @Test
  @DisplayName("updateBidList, should update a bidList")
  public void updateBidList_ShouldUpdateBidList() {
    // GIVEN
    bidList.setBidQuantity(40d);

    // WHEN
    bidList = bidListRepository.save(bidList);

    // THEN
    Assert.assertEquals(bidList.getBidQuantity(), 40d, 40d);
  }


  @Test
  public void bidListTest() {
    BidList bid = new BidList("Account Test", "Type Test", 10d);

    // Save
    bid = bidListRepository.save(bid);
    Assert.assertNotNull(bid.getBidListId());
    Assert.assertEquals(bid.getBidQuantity(), 10d, 10d);

    // Update
    bid.setBidQuantity(20d);
    bid = bidListRepository.save(bid);
    Assert.assertEquals(bid.getBidQuantity(), 20d, 20d);

    // Find
    List<BidList> listResult = bidListRepository.findAll();
    Assert.assertTrue(listResult.size() > 0);

    // Delete
    Integer id = bid.getBidListId();
    bidListRepository.delete(bid);
    Optional<BidList> bidList = bidListRepository.findById(id);
    Assert.assertFalse(bidList.isPresent());
  }
}