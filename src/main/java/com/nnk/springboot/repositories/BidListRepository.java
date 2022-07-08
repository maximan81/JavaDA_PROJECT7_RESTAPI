package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BidListRepository. Interface that allows to access and
 * persist data between BidList object and poseidenDb database
 */
@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
