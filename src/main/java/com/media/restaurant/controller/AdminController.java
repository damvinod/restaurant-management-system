package com.media.restaurant.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.media.restaurant.constant.CommonConstant;
import com.media.restaurant.domain.ItemsType;
import com.media.restaurant.exception.GlobalException;
import com.media.restaurant.service.RestaurantApiService;

@Controller
public class AdminController {

	@Autowired
	private RestaurantApiService restaurantApiService;

	@RequestMapping(path = "/addItemsWithImage", method = RequestMethod.POST)
	public @ResponseBody Long addItems(@RequestParam("itemType") String itemType,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException, GlobalException {
		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		ItemsType itemsType = mapper.readValue(itemType, ItemsType.class);

		itemsType.setImage(file.getBytes());

		Long id = restaurantApiService.addItem(itemsType);

		return id;
	}

	@RequestMapping("/admin")
	public String admin(Map<String, Object> model) throws GlobalException {
		return "admin";
	}

	@RequestMapping(path = "/downloadImage", method = RequestMethod.POST)
	public @ResponseBody ItemsType downloadImage(@RequestParam("referenceId") String referenceId) throws GlobalException {
		ItemsType itemType = restaurantApiService.downloadImage(Long.valueOf(referenceId));
		return itemType;
	}
}