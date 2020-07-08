package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {

	@Autowired
	private BidListRepository bidListRepository;

	public List<BidList> findAllBidList() {
		return bidListRepository.findAll();
	}

	public BidList addBid(BidList bid) {
		return bidListRepository.save(bid);
	}

	public Optional<BidList> getBidListBybidListId(int id) {
		return bidListRepository.findByBidListId(id);
	}

	public void deleteBidList(int id) {
		bidListRepository.deleteById(id);
	}

}
