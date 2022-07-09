package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.utility.ConvertTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
  /*
      @Autowired
      private BidListRepository bidListRepository;*/
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
	/*	BidList bidList1 = new BidList("account 1", "type 1", 1d);
		bidListRepository.save(bidList1);
		BidList bidList2 = new BidList("account 2", "type 2", 2d);
		bidListRepository.save(bidList2);
		BidList bidList3 = new BidList("account 3", "type 3", 3d);
		bidListRepository.save(bidList3);
		BidList bidList4 = new BidList("account 4", "type 4", 4d);
		bidListRepository.save(bidList4);
		BidList bidList5 = new BidList("account 5", "type 5", 5d);
		bidListRepository.save(bidList5);*/
  }
}
