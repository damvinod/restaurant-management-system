package com.media.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.media.restaurant.domain.ItemsTransaction;
import com.media.restaurant.domain.ItemsType;
import com.media.restaurant.domain.Users;

@Repository
public interface ItemsTransactionRepository extends JpaRepository<ItemsTransaction, Long> {

	public List<ItemsTransaction> findAllByOrderStatus(String orderSatus);
	
	public List<ItemsTransaction> findAllByOrderStatusAndUsers(String orderSatus, Users users);
}
