package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eintern.business.Emailer;
import com.eintern.orm.dao.HibernateGenericDAO;
import com.eintern.orm.dao.RequestDAO;
import com.eintern.orm.entity.Employee;
import com.eintern.orm.entity.Location;
import com.eintern.orm.entity.Request;
import com.eintern.orm.entity.Vehicle;

@Controller
@RequestMapping("/pwe")
public class EmployeeController {

	@SuppressWarnings("rawtypes")
	@Inject
	private HibernateGenericDAO genDaoImpl;

	@Inject
	private RequestDAO reqDaoImpl;

	@RequestMapping(method = RequestMethod.GET)
	public String successfulReturnPWE() {

		return "PortalWEmp";
	}

	@RequestMapping(value = "/ple", method = RequestMethod.GET)
	public String successfulReturnPLE() {

		return "PortalLEmp";
	}

	@RequestMapping(value = "VehicleRequestSelection", method = RequestMethod.GET)
	public String vehicleRequestSelection(HttpSession session) {
		
		List vlist = reqDaoImpl.retrieveVehiclesNotAtLocation(Vehicle.class, (int)session.getAttribute("locationId"));

		session.setAttribute("v", vlist);
		
			System.out.println(vlist);

		List pendinglist = reqDaoImpl.retrieveRequestsByStatus(Request.class, "Pending");
		
		session.setAttribute("pl", pendinglist);

		return "VehicleSelection";
	}

	@RequestMapping(value = "VehicleRequestReview", method = RequestMethod.POST)
	public String viewRequestList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String[] idArray = request.getParameterValues("vId");

		if (idArray != null) {
			List<Vehicle> vchoice = new ArrayList<Vehicle>();
			int num_id = 0;
			for (int i = 0; i < idArray.length; i++) {

				num_id = Integer.parseInt(idArray[i]);
				vchoice.add((Vehicle) genDaoImpl.retrieveById(Vehicle.class, num_id));
			}

			session.setAttribute("result", vchoice);

			return "ViewRequestVehicleTransferResult";
		} else {
			return "FailedVehicleSelection";
		}
	}

	// Replace success page
	@RequestMapping(value = "/sendRequests", method = RequestMethod.POST)
	public String sendRequests(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		for (Vehicle vehicle : (List<Vehicle>) session.getAttribute("result")) {

			Request req = new Request();

			Date date = new Date();
			SimpleDateFormat sDate = new SimpleDateFormat("MM-dd-yyyy 'at' hh:mm aaa");
			String formatedDate = sDate.format(date);

			req.setLocation((Location) session.getAttribute("emp_location"));
			req.setStatus("Pending");
			req.setShipping_location_id(vehicle.getLocation().getLocId());
			req.setReq_date(formatedDate);
			req.setVehicle(vehicle);
			req.setLast_update_date(formatedDate);

			genDaoImpl.insert(req);
		}

		if (session.getAttribute("empType").equals("Lot Employee")) {
			return "successPLE";
		} else if (session.getAttribute("empType").equals("Warehouse Employee")) {
			return "successPWE";
		} else {
			return "GeneralFailure";
		}
	}

	@RequestMapping(value = "/ViewInTransit", method = RequestMethod.GET)
	public String viewInTransitToMyID(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List transitList = reqDaoImpl.retrieveRequestsByStatus(Request.class, "Transit");

		session.setAttribute("transit_to_warehouse", transitList);

		// send emails method here
		return "MarkVehicleAsReceived";
	}

	@RequestMapping(value = "/MarkAsSent", method = RequestMethod.GET)
	public String markSent(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		System.out.println("Mark As Sent");
		
		List reqList = reqDaoImpl.retrieveRequestsByStatus(Request.class, "Approved");

		session.setAttribute("approved_list", reqList);

		return "WarehouseEmployeeViewApproved";
	}

	@RequestMapping(value = "/SentReview", method = RequestMethod.POST)
	public String sentReview(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String[] reqArray = request.getParameterValues("sentReqId");

		List<Request> receivedList = new ArrayList<Request>();
		if (reqArray != null) {
			int num_id = 0;
			for (int i = 0; i < reqArray.length; i++) {

				num_id = Integer.parseInt(reqArray[i]);
				receivedList.add((Request) genDaoImpl.retrieveById(Request.class, num_id));
			}

			session.setAttribute("sent_result", receivedList);

			return "ViewWarehouseEmployeeViewApprovedResult";
		} else {
			return "FailedSentReview";
		}

	}

	@RequestMapping(value = "/SentReviewTransit", method = RequestMethod.POST)
	public String sentVehicle(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		for (Request req : (List<Request>) session.getAttribute("sent_result")) {

			Date date = new Date();
			SimpleDateFormat sDate = new SimpleDateFormat("MM-dd-yyyy 'at' hh:mm aaa");
			String formatedDate = sDate.format(date);

			req.setStatus("Transit");
			req.setLast_update_date(formatedDate);
			genDaoImpl.update(req);

for (Employee employee : req.getLocation().getEmployees()) {
				
				Location loc = (Location) genDaoImpl.retrieveById(Location.class, req.getShipping_location_id());
				
				Emailer email = new Emailer();
				
				email.sendHTMLMail(req.getStatus(), employee.getEmpEmail(), "cjrcoley@gmail.com", req.getReqId(),
						req.getVehicle().getVehicleId(), req.getVehicle().getVehicleName(),
						req.getLocation().getLocId(), loc.getLocName(), req.getShipping_location_id(),
						req.getLocation().getLocName(), req.getLast_update_date());

			}

		}

		/*
		 * genDaoImpl.delete(Request.class, entityId) genDaoImpl genDaoImpl
		 */

		if (session.getAttribute("empType").equals("Lot Employee")) {
			return "successPLE";
		} else if (session.getAttribute("empType").equals("Warehouse Employee")) {
			return "successPWE";
		} else {
			return "GeneralFailure";
		}
	}

	@RequestMapping(value = "/MarkReceived", method = RequestMethod.POST)
	public String markRequests(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String[] reqArray = request.getParameterValues("reqId");

		List<Request> receivedList = new ArrayList<Request>();

		if (reqArray != null) {
			int num_id = 0;
			for (int i = 0; i < reqArray.length; i++) {
				System.out.println("soda");
				num_id = Integer.parseInt(reqArray[i]);
				receivedList.add((Request) genDaoImpl.retrieveById(Request.class, num_id));
			}

			session.setAttribute("mark_result", receivedList);

			return "ViewMarkedReceivedResult";
		} else {
			System.out.println("pizza");
			return "FailedMarkedVehicleAsReceived";
		}
	}

	@RequestMapping(value = "/VehicleReceived", method = RequestMethod.POST)
	public String receiveVehicleResult(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<Request> reqList = (List<Request>) session.getAttribute("mark_result");

		for (Request req : reqList) {
			int requestId = req.getReqId();

			Date date = new Date();
			SimpleDateFormat sDate = new SimpleDateFormat("MM-dd-yyyy 'at' hh:mm aaa");
			String formatedDate = sDate.format(date);

			Location location = req.getLocation();
			Vehicle vehicle = req.getVehicle();
			vehicle.setLocation(location);
			genDaoImpl.update(vehicle);

			/*
			 * req.setLocation(null); req.setVehicle(null);
			 * genDaoImpl.update(req); genDaoImpl.delete(Request.class,
			 * requestId);
			 */

			req.setStatus("Received");
			req.setLast_update_date(formatedDate);
			genDaoImpl.update(req);

			for (Employee employee : req.getLocation().getEmployees()) {
				
				Location loc = (Location) genDaoImpl.retrieveById(Location.class, req.getShipping_location_id());
				
				Emailer email = new Emailer();
				
				email.sendHTMLMail(req.getStatus(), employee.getEmpEmail(), "cjrcoley@gmail.com", req.getReqId(),
						req.getVehicle().getVehicleId(), req.getVehicle().getVehicleName(),
						req.getLocation().getLocId(), loc.getLocName(), req.getShipping_location_id(),
						req.getLocation().getLocName(), req.getLast_update_date());

			}

		}

		if (session.getAttribute("empType").equals("Lot Employee")) {
			return "successPLE";
		} else if (session.getAttribute("empType").equals("Warehouse Employee")) {
			return "successPWE";
		} else {
			return "GeneralFailure";
		}
	}

	@RequestMapping(value = "/ManageRequests", method = RequestMethod.GET)
	public String manageMyRequests(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		List<Request> req = genDaoImpl.retrieveAll(Request.class);

		session.setAttribute("all_requests", req);

		return "EmployeeManageRequests";
	}

	@RequestMapping(value = "/ManageRequestsReview", method = RequestMethod.POST)
	public String manageMyRequestsReview(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String[] reqArray = request.getParameterValues("myReqsId");

		List<Request> receivedList = new ArrayList<Request>();
		if (reqArray != null) {
			int num_id = 0;
			for (int i = 0; i < reqArray.length; i++) {

				num_id = Integer.parseInt(reqArray[i]);
				receivedList.add((Request) genDaoImpl.retrieveById(Request.class, num_id));
			}

			session.setAttribute("delete_review", receivedList);

			return "EmployeeManageRequestsReview";
		} else {
			return "FailedManageRequests";
		}
	}

	@RequestMapping(value = "/ManageRequestsResult", method = RequestMethod.POST)
	public String manageMyRequestsResult(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		List<Request> reqList = (List<Request>) session.getAttribute("delete_review");
		for (Request req : reqList) {
			int requestId = req.getReqId();

			req.setLocation(null);
			req.setVehicle(null);
			genDaoImpl.update(req);

			genDaoImpl.delete(Request.class, requestId);
		}

		if (session.getAttribute("empType").equals("Lot Employee")) {
			return "successPLE";
		} else if (session.getAttribute("empType").equals("Warehouse Employee")) {
			return "successPWE";
		} else {
			return "GeneralFailure";
		}
	}

}