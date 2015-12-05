package com.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eintern.orm.dao.AuthenticateUserDAO;
import com.eintern.orm.dao.HibernateGenericDAO;
import com.eintern.orm.dao.RequestDAO;
import com.eintern.orm.entity.Credentials;
import com.eintern.orm.entity.Request;
import com.eintern.orm.entity.Vehicle;

@Controller
@RequestMapping("/pwm")
public class ManagerController{

	@Inject
	private AuthenticateUserDAO<Credentials> authDaoImpl;

	@SuppressWarnings("rawtypes")
	@Inject
	private HibernateGenericDAO genDaoImpl;

	@Inject
	private RequestDAO reqDaoImpl;

	@RequestMapping(method = RequestMethod.GET)
	public String successfulReturn(HttpServletRequest request, HttpServletResponse response) {

		// return "ViewManagerApproval";
		return "PortalWManager";
	}
	//@Before("redirectPage()")
	@RequestMapping(value = "ViewRequests", method = RequestMethod.GET)
	public String buttonB(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List list = reqDaoImpl.retrieveRequestsByStatus(Request.class, "Pending");

		session.setAttribute("r", list);

		return "ViewManagerApproval";
		// return "PortalWManager";
	}

	@RequestMapping(value = "ViewApproval", method = RequestMethod.POST)
	public String answerRequests(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {

		model.addAttribute(("request"), new Request());
		//System.out.println("hi");
		int reqId = Integer.parseInt(request.getParameter("oper"));

		Request req = (Request) genDaoImpl.retrieveById(Request.class, reqId);
		// session.setAttribute("reqId", reqId);

		session.setAttribute("this_request", req);
		return "ManagerRequestDecision";

	}

	// the parameter "@RequestParam("") Type whatever" is used to get the value
	// of a checkbox/button on a jsp
	@RequestMapping(value = "Decision", method = RequestMethod.POST)
	public String decision(@RequestParam("approval") String approval, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		String myDecision = approval;
		System.out.println(myDecision);
		/*
		 * if (result.hasErrors()) {
		 * 
		 * return "ManagerRequestDecision"; }
		 */
		// String myDecision = request.getParameter("approval");

		Request req = (Request) session.getAttribute("this_request");

		if (myDecision.equals("Approved")) {

			for (Request reqother : (List<Request>) session.getAttribute("r")) {
				if (reqother.getVehicle().getVehicleId() == req.getVehicle().getVehicleId()) {
					reqother.setStatus("Denied");
					genDaoImpl.update(reqother);
				}
			}
			req.setStatus(myDecision);
			genDaoImpl.update(req);
		}
		if (myDecision.equals("Denied")) {

			req.setStatus(myDecision);
			genDaoImpl.update(req);
			// System.out.println("Test: "+approval);

		}
		return "successPWM";
	}
	@RequestMapping(value = "ViewAllMyVehicles", method = RequestMethod.GET)
	public String viewWarehouseVehicles(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		List list = genDaoImpl.retrieveAll(Vehicle.class);

		session.setAttribute("all_w_vehicles", list);

		return "WarehouseAllVehicles";

	}
}
