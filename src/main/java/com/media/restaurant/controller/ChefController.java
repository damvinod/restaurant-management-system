package com.media.restaurant.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.media.restaurant.constant.CommonConstant;
import com.media.restaurant.domain.Users;
import com.media.restaurant.exception.GlobalException;
import com.media.restaurant.service.RestaurantApiService;

@Controller
public class ChefController {

	private static Logger LOG = LoggerFactory.getLogger(ChefController.class);
	
	@Autowired
	private RestaurantApiService restaurantApiService;
	
	@RequestMapping("/chef")
	public ModelAndView chef(Map<String, Object> model) throws GlobalException {
		
		ModelAndView modelAndView = new ModelAndView("chef");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
		Users users = restaurantApiService.findUsersByUserName(auth.getName());
		
		modelAndView.addObject("userName",users.getUserName());
		modelAndView.addObject("userId",users.getUserId());
		
		return modelAndView;
	}
	
	@RequestMapping(path = "/saveOrderStatusToPending", method = RequestMethod.POST)
	public @ResponseBody String saveOrderStatusToPending(@RequestParam("selectedSequences") String selectedSequences, @RequestParam("userId") String userId) throws GlobalException {
		
		boolean passport = restaurantApiService.checkPendingOrderForUser(userId);
		
		if(! passport)
			restaurantApiService.saveOrderStatus(selectedSequences, userId);
		else
			LOG.info("Job is pending for this user " + userId);
		
		return passport ? CommonConstant.ORDER_EXIST : "";
	}
	
	@RequestMapping(path = "/saveOrderStatusToClosed", method = RequestMethod.POST)
	public @ResponseBody String saveOrderStatusToClosed(@RequestParam("selectedSequences") String selectedSequences) throws GlobalException {
		restaurantApiService.saveOrderStatus(selectedSequences, null);
		return "";
	}
}
