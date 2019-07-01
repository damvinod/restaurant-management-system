
package com.media.restaurant.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

@Entity(name="ITEMS_TYPE")
@SequenceGenerator(
		  name = "ITEMS_TYPE_GENERATOR",
		  sequenceName = "ITEMS_TYPE_SEQ",
		  initialValue = 1, allocationSize = 1)
public class ItemsType {
	
	@Id
	@Column(name="items_type_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEMS_TYPE_GENERATOR")
	private Long id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private String price;
	
	@Column(name="duration")
	private String duration;
	
	@Column(name="image")
	@Lob
	private byte[] image;
	
	@Column(name="currency_type")
	private String currencyType;
	
	@Column(name="ingredients")
	private String ingredients;
	
	@Column(name="active_flag")
	private String activeFlag;

	@Column(name="CREATED_TS")
	private Timestamp createdTs;
	
	@Column(name="MODIFIED_TS")
	private Timestamp modifiedTs;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Override
	public String toString() {
		return "ItemsType [id=" + id + ", description=" + description
				+ ", price=" + price + ", duration=" + duration
				+ ", currencyType=" + currencyType + ", ingredients="
				+ ingredients + ", activeFlag=" + activeFlag + "]";
	}
	
}
