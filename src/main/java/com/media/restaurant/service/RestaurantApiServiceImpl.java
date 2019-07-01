package com.media.restaurant.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.media.restaurant.constant.CommonConstant;
import com.media.restaurant.domain.ChefMenuVo;
import com.media.restaurant.domain.ItemsTransaction;
import com.media.restaurant.domain.ItemsType;
import com.media.restaurant.domain.Users;
import com.media.restaurant.exception.GlobalException;
import com.media.restaurant.repository.ItemsTransactionRepository;
import com.media.restaurant.repository.ItemsTypeRepository;
import com.media.restaurant.repository.UsersRepository;

@Service
public class RestaurantApiServiceImpl implements RestaurantApiService {

	@Autowired
	private ItemsTypeRepository itemsTypeRepository;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private ItemsTransactionRepository itemsTransactionRepository;

	@Override
	public Long addItem(ItemsType itemType) throws GlobalException {

		itemType.setDescription(itemType.getDescription().toUpperCase());
		itemType.setCurrencyType(itemType.getCurrencyType().toUpperCase());
		itemType.setIngredients(itemType.getIngredients().toUpperCase());
		
		itemType.setActiveFlag(CommonConstant.ACTIVE_FLAG_Y);

		itemType.setCreatedTs(createTimestamp());
		itemType.setModifiedTs(createTimestamp());

		itemsTypeRepository.save(itemType);

		return itemType.getId();
	}

	@Override
	public Long addUser(Users user) throws GlobalException {

		user.setPassword(CommonConstant.DEFAULT_PASSWORD);
		user.setRole(user.getRole() == null ? CommonConstant.CHEF : CommonConstant.ADMIN);
		user.setUserName(user.getUserName().toUpperCase());
		
		user.setActiveFlag(CommonConstant.ACTIVE_FLAG_Y);
		
		user.setCreatedTs(createTimestamp());
		user.setModifiedTs(createTimestamp());

		usersRepository.save(user);
		
		return user.getUserId();
	}

	@Override
	public void deleteItem(ItemsType itemType) throws GlobalException {

		ItemsType itemTypeTemp = itemsTypeRepository.findById(itemType.getId()).orElse(null);

		if (itemTypeTemp != null) {
			itemTypeTemp.setActiveFlag(CommonConstant.ACTIVE_FLAG_N);
			itemsTypeRepository.save(itemTypeTemp);
		}
	}

	@Override
	public ItemsType downloadImage(Long id) throws GlobalException {
		ItemsType itemType = itemsTypeRepository.findById(id).orElse(null);
		return itemType;
	}

	@Override
	public List<ItemsType> getAllItems() throws GlobalException {
		return itemsTypeRepository.findAllByActiveFlag(CommonConstant.ACTIVE_FLAG_Y);
	}

	private Timestamp createTimestamp() throws GlobalException {
		return new Timestamp(System.currentTimeMillis());
	}

	@Override
	public String saveItemsTransaction(Map<String, String> inputMap) throws GlobalException {

		List<String> itemsSelectedList = inputMap.keySet().stream().filter(s -> s.contains(CommonConstant.ITEM))
				.collect(Collectors.toList());

		for (String itemsSelected : itemsSelectedList) {

			String itemsQuantity = inputMap.get(itemsSelected);

			if (!"".equalsIgnoreCase(itemsQuantity) && !"0".equalsIgnoreCase(itemsQuantity)) {

				ItemsTransaction itemsTransaction = new ItemsTransaction();

				ItemsType itemsType = new ItemsType();
				itemsTransaction.setItemsType(itemsType);

				itemsType.setId(Long.valueOf(itemsSelected.split("_")[1]));

				itemsTransaction.setTotalQuantity(itemsQuantity);
				itemsTransaction.setTableNumber(inputMap.get(CommonConstant.TABLE_NO));
				itemsTransaction.setOrderStatus(CommonConstant.ORDER_RECEIVED);
				itemsTransaction.setCreatedTs(createTimestamp());
				itemsTransaction.setModifiedTs(createTimestamp());

				itemsTransactionRepository.save(itemsTransaction);
			}
		}

		return "success";
	}

	@Override
	public List<ChefMenuVo> getReceivedItemsTransaction() throws GlobalException {

		List<ItemsTransaction> itemsTransactionList = itemsTransactionRepository
				.findAllByOrderStatus(CommonConstant.ORDER_RECEIVED);

		Map<Long, Integer> idQunatityMap = new HashMap<>();
		Map<Long, List<String>> idItemsTransactionSeqIdMap = new HashMap<>();
		Map<Long, String> userIdNameMap = new HashMap<>();
		Map<Long, ItemsType> itemDetailsMap = new HashMap<>();

		List<ChefMenuVo> chefMenuVoList = new LinkedList<>();

		for (ItemsTransaction itemsTransaction : itemsTransactionList) {

			ItemsType itemsType = itemsTransaction.getItemsType();
			Users users = itemsTransaction.getUsers();

			Integer quantity = Integer.valueOf(itemsTransaction.getTotalQuantity());
			Long itemId = itemsType.getId();

			if (!itemDetailsMap.containsKey(itemId))
				itemDetailsMap.put(itemId, itemsType);

			if (idQunatityMap.containsKey(itemId)) {
				quantity = idQunatityMap.get(itemId) + quantity;

				idQunatityMap.put(itemId, quantity);
				idItemsTransactionSeqIdMap.get(itemId).add(String.valueOf(itemsTransaction.getId()));
			} else {
				List<String> newList = new ArrayList<>();
				newList.add(String.valueOf(itemsTransaction.getId()));

				idQunatityMap.put(itemId, quantity);
				idItemsTransactionSeqIdMap.put(itemId, newList);
				userIdNameMap.put(itemId, users != null ? users.getUserName() : "");
			}

		}

		for (Map.Entry<Long, Integer> entry : idQunatityMap.entrySet()) {

			ChefMenuVo chefMenuVo = new ChefMenuVo();

			ItemsType itemsType = itemDetailsMap.get(entry.getKey());

			chefMenuVo.setDuration(itemsType.getDuration());
			chefMenuVo.setItemDescription(itemsType.getDescription());

			chefMenuVo.setItemId(String.valueOf(entry.getKey()));
			chefMenuVo.setTotalQuantity(String.valueOf(entry.getValue()));

			chefMenuVo.setSelectedSequences(String.join(",", idItemsTransactionSeqIdMap.get(entry.getKey())));
			chefMenuVo.setUserName(userIdNameMap.get(entry.getKey()));

			chefMenuVoList.add(chefMenuVo);
		}

		return chefMenuVoList;
	}

	@Override
	public void saveOrderStatus(String selectedSequences, String userId) throws GlobalException {

		List<String> selectedSeq = Arrays.asList(selectedSequences.split(","));

		for (String s : selectedSeq) {

			ItemsTransaction itemsTransaction = itemsTransactionRepository.findById(Long.valueOf(s)).orElse(null);
			String orderStatus = "";
			if(userId != null)
			{
				Users users = new Users();
				users.setUserId(Long.valueOf(userId));
				itemsTransaction.setUsers(users);
				
				orderStatus = CommonConstant.ORDER_PENDING;
			}
			else
			{
				orderStatus = CommonConstant.ORDER_CLOSED;
			}

			itemsTransaction.setModifiedTs(createTimestamp());
			itemsTransaction.setOrderStatus(orderStatus);

			itemsTransactionRepository.save(itemsTransaction);
		}
	}

	@Override
	public List<ChefMenuVo> getPendingItemsTransaction() throws GlobalException {

		List<ItemsTransaction> itemsTransactionList = itemsTransactionRepository
				.findAllByOrderStatus(CommonConstant.ORDER_PENDING);

		Map<Long, Integer> idQunatityMap = new HashMap<>();
		Map<Long, List<String>> idItemsTransactionSeqIdMap = new HashMap<>();
		Map<Long, Users> itemIdUsersMap = new HashMap<>();
		Map<Long, ItemsType> itemDetailsMap = new HashMap<>();

		List<ChefMenuVo> chefMenuVoList = new LinkedList<>();
		
		for (ItemsTransaction itemsTransaction : itemsTransactionList) {

			ItemsType itemsType = itemsTransaction.getItemsType();
			Users users = itemsTransaction.getUsers();
			
			Integer quantity = Integer.valueOf(itemsTransaction.getTotalQuantity());
			Long itemId = itemsType.getId();
			
			if (!itemDetailsMap.containsKey(itemId))
				itemDetailsMap.put(itemId, itemsType);

			if (idQunatityMap.containsKey(itemId)) {
				quantity = idQunatityMap.get(itemId) + quantity;

				idQunatityMap.put(itemId, quantity);
				idItemsTransactionSeqIdMap.get(itemId).add(String.valueOf(itemsTransaction.getId()));
			} else {
				List<String> newList = new ArrayList<>();
				newList.add(String.valueOf(itemsTransaction.getId()));

				idQunatityMap.put(itemId, quantity);
				idItemsTransactionSeqIdMap.put(itemId, newList);
				itemIdUsersMap.put(itemId, users);
			}
		}
		
		for (Map.Entry<Long, Integer> entry : idQunatityMap.entrySet()) {

			ChefMenuVo chefMenuVo = new ChefMenuVo();

			ItemsType itemsType = itemDetailsMap.get(entry.getKey());

			chefMenuVo.setDuration(itemsType.getDuration());
			chefMenuVo.setItemDescription(itemsType.getDescription());

			chefMenuVo.setItemId(String.valueOf(entry.getKey()));
			chefMenuVo.setTotalQuantity(String.valueOf(entry.getValue()));

			chefMenuVo.setSelectedSequences(String.join(",", idItemsTransactionSeqIdMap.get(entry.getKey())));
			chefMenuVo.setUserName(itemIdUsersMap.get(entry.getKey()).getUserName());
			chefMenuVo.setUserId(String.valueOf(itemIdUsersMap.get(entry.getKey()).getUserId()));
			
			chefMenuVoList.add(chefMenuVo);
		}
		
		return chefMenuVoList;
	}

	@Override
	public Users findUsersByUserName(String userName) throws GlobalException {

		Users users = usersRepository.findByUserName(userName);
		
		return users;
	}

	@Override
	public boolean checkPendingOrderForUser(String userId) throws GlobalException {

		Users users = new Users();
		users.setUserId(Long.valueOf(userId));
		
		List<ItemsTransaction> list = itemsTransactionRepository.findAllByOrderStatusAndUsers(CommonConstant.ORDER_PENDING, users);
		
		return list != null && list.size() > 0;
	}

	@Override
	public List<Users> getAllUsers() throws GlobalException {
		return usersRepository.findAllByActiveFlag(CommonConstant.ACTIVE_FLAG_Y);
	}
	
	
}