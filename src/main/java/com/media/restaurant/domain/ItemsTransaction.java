package com.media.restaurant.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name="ITEMS_TRANSACTION")
@SequenceGenerator(
		  name = "ITEMS_TRANSACTION_GENERATOR",
		  sequenceName = "ITEMS_TRANSACTION_SEQ",
		  initialValue = 1, allocationSize = 1)
public class ItemsTransaction {

	@Id
	@Column(name="items_transaction_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEMS_TRANSACTION_GENERATOR")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="items_type_id")
	private ItemsType ItemsType;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users users;
	
	@Column(name="table_no")
	private String tableNumber;
	
	@Column(name="total_quantity")
	private String totalQuantity;
	
	@Column(name="order_status")
	private String orderStatus;
	
	@Column(name="CREATED_TS")
	private Timestamp createdTs;
	
	@Column(name="MODIFIED_TS")
	private Timestamp modifiedTs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemsType getItemsType() {
		return ItemsType;
	}

	public void setItemsType(ItemsType itemsType) {
		ItemsType = itemsType;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Timestamp getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Timestamp createdTs) {
		this.createdTs = createdTs;
	}

	public Timestamp getModifiedTs() {
		return modifiedTs;
	}

	public void setModifiedTs(Timestamp modifiedTs) {
		this.modifiedTs = modifiedTs;
	}
}
