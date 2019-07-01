package com.media.restaurant.service;

import java.util.List;
import java.util.Map;

import com.media.restaurant.domain.ChefMenuVo;
import com.media.restaurant.domain.ItemsTransaction;
import com.media.restaurant.domain.ItemsType;
import com.media.restaurant.domain.Users;
import com.media.restaurant.exception.GlobalException;

public interface RestaurantApiService {

  public Long addItem(ItemsType itemType) throws GlobalException;
  public Long addUser(Users user) throws GlobalException;
  public void deleteItem(ItemsType itemType) throws GlobalException;
  public ItemsType downloadImage(Long id) throws GlobalException;
  public List<ItemsType> getAllItems() throws GlobalException;
  public String saveItemsTransaction(Map<String, String> inputMap) throws GlobalException;
  public List<ChefMenuVo> getReceivedItemsTransaction() throws GlobalException;
  public List<ChefMenuVo> getPendingItemsTransaction() throws GlobalException;
  public void saveOrderStatus(String selectedSequences, String userId) throws GlobalException;
  public Users findUsersByUserName(String userName) throws GlobalException;
  public boolean checkPendingOrderForUser(String userId) throws GlobalException;
  public List<Users> getAllUsers() throws GlobalException;
}