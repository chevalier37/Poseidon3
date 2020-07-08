package com.nnk.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nnk.springboot.domain.BidList;

public interface BidListRepository extends JpaRepository<BidList, Integer> {

	@Query("select u from BidList u where u.bidListId = ?1")
	Optional<BidList> findByBidListId(int bidListId);

}
