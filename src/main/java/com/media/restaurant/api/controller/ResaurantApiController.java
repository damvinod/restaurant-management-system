package com.media.restaurant.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.media.restaurant.constant.CommonConstant;
import com.media.restaurant.domain.ChefMenuVo;
import com.media.restaurant.domain.ItemsType;
import com.media.restaurant.domain.Users;
import com.media.restaurant.exception.GlobalException;
import com.media.restaurant.service.RestaurantApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = CommonConstant.API_VALUE, tags = CommonConstant.API_DESC)
public class ResaurantApiController {

  @Autowired
  private RestaurantApiService restaurantApiService;

  /**
   * This return the id of added menu items.
   *
   * @return Id of the added item
   * @throws GlobalException - when any exception occurs it is thrown
   */
  @ApiOperation(value = CommonConstant.API_ADD_IEMS_VALUE, nickname = CommonConstant.GET_ADD_ITEMS)
  @RequestMapping(path = CommonConstant.ADD_ITEMS_URI, method = RequestMethod.POST)
  public Long addItems(ItemsType itemType) throws GlobalException {
    return restaurantApiService.addItem(itemType);
  }

  /**
   * This return the id of added user.
   *
   * @return Id of the added user
   * @throws GlobalException - when any exception occurs it is thrown
   */
  @ApiOperation(value = CommonConstant.ADDING_NEW_USERS, nickname = CommonConstant.ADD_USER)
  @RequestMapping(path = CommonConstant.ADD_USER_URI, method = RequestMethod.POST)
  public Long addUser(Users user) throws GlobalException {
    return restaurantApiService.addUser(user);
  }

  /**
   * This return the id of deleted user.
   *
   * @return Id of the deleted user
   * @throws GlobalException - when any exception occurs it is thrown
   */
  @ApiOperation(value = CommonConstant.DELETING_AN_EXISTING_MENU_ITEMS,
      nickname = CommonConstant.DELETE_ITEM)
  @RequestMapping(path = CommonConstant.DELETE_ITEM_URI, method = RequestMethod.POST)
  public Long deleteItem(ItemsType itemType) throws GlobalException {
    long referenceId = itemType.getId();
    restaurantApiService.deleteItem(itemType);
    return referenceId;
  }

  /**
   * This return the List of all menu items.
   *
   * @return List of all menu items
   * @throws GlobalException - when any exception occurs it is thrown
   */
  @ApiOperation(value = CommonConstant.GET_ALL_EXISTING_MENU_ITEMS,
      nickname = CommonConstant.GET_ALL_ITEMS)
  @RequestMapping(path = CommonConstant.ALL_ITEMS_URI, method = RequestMethod.GET)
  public List<ItemsType> getAllItems(ItemsType itemType) throws GlobalException {
    return restaurantApiService.getAllItems();
  }

  /**
   * This return the List of all users.
   *
   * @return List of all users
   * @throws GlobalException - when any exception occurs it is thrown
   */
  @ApiOperation(value = CommonConstant.GET_ALL_EXISTING_USERS,
      nickname = CommonConstant.GET_ALL_USERS)
  @RequestMapping(path = CommonConstant.ALL_USERS_URI, method = RequestMethod.GET)
  public List<Users> getAllUsers() throws GlobalException {
    return restaurantApiService.getAllUsers();
  }

  /**
   * This return the List of all pending transactions.
   *
   * @return List of all pending transactions
   * @throws GlobalException - when any exception occurs it is thrown
   */
  @ApiOperation(value = CommonConstant.GET_ALL_PENDING_TRANSACTIONS,
      nickname = CommonConstant.GET_PENDING_ITEMS_TRANSACTION)
  @RequestMapping(path = CommonConstant.PEN_ITEMS_TRXS_URI, method = RequestMethod.POST)
  public List<ChefMenuVo> getPendingItemsTransaction() throws GlobalException {
    List<ChefMenuVo> chefMenuVoList = restaurantApiService.getPendingItemsTransaction();
    return chefMenuVoList;
  }

  /**
   * This return the List of all received transactions.
   *
   * @return List of all received transactions
   * @throws GlobalException - when any exception occurs it is thrown
   */
  @ApiOperation(value = CommonConstant.GET_ALL_RECEIVED_TRANSACTIONS,
      nickname = CommonConstant.GET_RECEIVED_ITEMS_TRANSACTION)
  @RequestMapping(path = CommonConstant.RECEIVED_ITEMS_TRXS_URI, method = RequestMethod.POST)
  public List<ChefMenuVo> getReceivedItemsTransaction() throws GlobalException {
    List<ChefMenuVo> chefMenuVoList = restaurantApiService.getReceivedItemsTransaction();
    return chefMenuVoList;
  }
}
