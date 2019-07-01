package com.media.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.media.restaurant.domain.ItemsType;
import com.media.restaurant.domain.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

	public List<Users> findAllByActiveFlag(String activeFlag);
	public Users findByUserName(String userName);
}
