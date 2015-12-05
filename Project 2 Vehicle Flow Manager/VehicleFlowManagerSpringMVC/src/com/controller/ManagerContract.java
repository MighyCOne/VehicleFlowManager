package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pwm")
public interface ManagerContract {

	@RequestMapping(method = RequestMethod.GET)
	String successfulReturn(HttpServletRequest request, HttpServletResponse response);
	@RequestMapping(value = "ViewRequests", method = RequestMethod.GET)
	String buttonB(HttpServletRequest request, HttpServletResponse response, HttpSession session);
	@RequestMapping(value = "ViewApproval", method = RequestMethod.POST)
	String answerRequests(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model);
	@RequestMapping(value = "Decision", method = RequestMethod.POST)
	String decision(String approval, HttpSession session, HttpServletRequest request, HttpServletResponse response);
	@RequestMapping(value = "ViewAllMyVehicles", method = RequestMethod.GET)
	String viewWarehouseVehicles(HttpServletRequest request, HttpServletResponse response, HttpSession session);

}
