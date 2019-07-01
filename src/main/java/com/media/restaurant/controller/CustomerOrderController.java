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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.media.restaurant.exception.GlobalException;
import com.media.restaurant.service.RestaurantApiService;

@Controller
public class CustomerOrderController {

	@Autowired
	private RestaurantApiService restaurantApiService;
	
	@RequestMapping("/customerOrder")
	public String customerOrder(Map<String, Object> model) throws GlobalException {
		return "customerOrder";
	}

	@RequestMapping(path = "/confirmOrder", method = RequestMethod.POST)
	public @ResponseBody String confirmOrder(@RequestParam("jsonString") String jsonString, HttpServletRequest request)
			throws IOException, GlobalException {
		ObjectMapper mapper = new ObjectMapper();

		Map<String, String> inputMap = new HashMap<String, String>();

		inputMap = mapper.readValue(jsonString, new TypeReference<Map<String, String>>() {
		});

		String a = restaurantApiService.saveItemsTransaction(inputMap);
		
		return a;
	}
}
