package com.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eintern.orm.dao.AuthenticateUserDAO;
import com.eintern.orm.dao.HibernateGenericDAO;
import com.eintern.orm.dao.RequestDAO;
import com.eintern.orm.entity.Credentials;
import com.eintern.orm.entity.Location;
import com.eintern.orm.entity.Request;
import com.eintern.orm.entity.Vehicle;
import com.eintern.testing.Tester;

@Controller
@RequestMapping("/vlogin")
public class LoginController {

	@Inject
	private AuthenticateUserDAO<Credentials> authDaoImpl;

	@SuppressWarnings("rawtypes")
	@Inject
	private HibernateGenericDAO genDaoImpl;

	@Inject
	private RequestDAO reqDaoImpl;

	@RequestMapping(method = RequestMethod.GET)
	public String displayLoginForm(Model model) {
		System.out.println("hello");
		model.addAttribute(("credentials"), new Credentials());

		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut(HttpSession session) {

		session.invalidate();
		/* return "logout"; */
		return "logout";
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String checkCredentials(@Valid Credentials c, BindingResult result, HttpSession session) {

		String username = c.getUsername();
		String password = c.getPassword();

		// Inserts 3 locations with connected Vehicles,Employees, and
		// Credentials
		/*Tester test = new Tester(); 
		 test.insertW1(genDaoImpl);
		 test.insertW2(genDaoImpl); 
		 	test.insertL1(genDaoImpl);
		 */

		if (result.hasErrors()) {
			// System.out.println("error");
			return "login";
		}
		// System.out.println(username);
		// System.out.println(password);
		if (authDaoImpl.authenticateUser(username, password, Credentials.class)) {

			Credentials cred = (Credentials) genDaoImpl.retrieveByUsername(Credentials.class, username);
			String type = cred.getEmployee().getEmpType();
			int locId = cred.getEmployee().getLocation().getLocId();

			String locname = cred.getEmployee().getLocation().getLocName();
			String loctype = cred.getEmployee().getLocation().getLocType();
			Location empLocation = cred.getEmployee().getLocation();
			
			session.setAttribute("user", cred);

			session.setAttribute("emp_location", empLocation);

			session.setAttribute("empType", type);

			session.setAttribute("name", cred.getEmployee().getEmpName());
			session.setAttribute("locationId", locId);
			session.setAttribute("locationName", locname);
			session.setAttribute("locationType", loctype);

			if (type.equals("Warehouse Manager")) {
				return "PortalWManager";
			} 
			else if (type.equals("Warehouse Employee")) {
				return "PortalWEmp";
			} 
			else if (type.equals("Lot Employee")) {
				return "PortalLEmp";
			}

		}
		return "failedlogin";
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public String cancelAndGoHome(@Valid Credentials credentials, BindingResult result, HttpSession session) {

		if (session.getAttribute("empType").equals("Warehouse Manager")) {

			return "PortalWManager";
		} else if (session.getAttribute("empType").equals("Warehouse Employee")) {

			return "PortalWEmp";
		} else if (session.getAttribute("empType").equals("Lot Employee")) {

			return "PortalLEmp";
		}

		return "GeneralFailure";
	}

	/*
	 * @RequestMapping(value = "/pwm", method = RequestMethod.POST) public
	 * String answerRequests(@RequestParam("approval") String approval,
	 * HttpServletRequest request, HttpServletResponse response) { //List
	 * reqList=request.getParameterValues("approval') System.out.println( "Test:
	 * " +approval); //send emails here return "success"; }
	 */

}
