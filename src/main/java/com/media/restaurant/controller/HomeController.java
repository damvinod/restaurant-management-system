package com.media.restaurant.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.media.restaurant.exception.GlobalException;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(Map<String, Object> model) throws GlobalException {
		return "home";
	}
}
