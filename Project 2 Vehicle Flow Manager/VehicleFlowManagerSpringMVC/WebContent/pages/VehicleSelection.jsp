<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.eintern.orm.entity.Vehicle"%>
<%@ page import="com.eintern.orm.entity.Request"%>
<%@ page import="com.eintern.orm.entity.Location"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">




</head>
<body>
<%-- 	<%
		if (session.getAttribute("user") == null) {

    		response.sendRedirect("login"); // Not logged in, redirect to login page.
		}
   
	%> --%>

	
	
	<%@ include file="v-style.css"%>
	<%@include file="cssSkeleton.jsp"%>
	<div align=center>
		<form:form method="POST"
			action="${pageContext.request.contextPath}/pwe/VehicleRequestReview">

			<br>


			<table border="1" class="table table-striped">
				<tr>
					<th colspan="5">Vehicle Request Selection</th>
				</tr>
				<tr>
					<th>V.ID</th>
					<th>V.Name</th>
					<th>Location ID</th>
					<th>Location Name</th>
					<th>Select</th>
				</tr>
				<tr>
					<%
						for (Vehicle vehicle : (List<Vehicle>) session.getAttribute("v")) {

								if (!vehicle.getLocation().getLocType().equals("Lot")) {

									if (vehicle.getRequests().isEmpty()) {
					%>

					<td><%=vehicle.getVehicleId()%></td>
					<td><%=vehicle.getVehicleName()%></td>
					<td><%=vehicle.getLocation().getLocId()%></td>
					<td><%=vehicle.getLocation().getLocName()%></td>
					<td><input type="checkbox" name="vId"
						value="<%=vehicle.getVehicleId()%>" /></td>
				</tr>
				
				<%} else {
					//I only want to see vehicles that I dont already have requests out for
									//I need to check each Vehicle's requests to see if it was sent from my location		

									for (Request req : vehicle.getRequests()) {
										if (req.getLocation().getLocId() != (int) session.getAttribute("locationId")) {
				%>
				<tr>
					<td><%=vehicle.getVehicleId()%></td>
					<td><%=vehicle.getVehicleName()%></td>
					<td><%=vehicle.getLocation().getLocId()%></td>
					<td><%=vehicle.getLocation().getLocName()%></td>
					<td><input type="checkbox" name="vId"
						value="<%=vehicle.getVehicleId()%>" /></td>
				</tr>

				<%
					}
									}
								}
							}
						}
				%>
			</table>

			<!-- <input action="action" type="button" value="Back"
				onclick="window.history.go(-1); return false;" /> -->
			<input type="submit" class="btn btn-primary"
				value="Request Vehicle(s)" />


		</form:form>
		<form:form method="POST"
			action="${pageContext.request.contextPath}/vlogin/cancel">
			<input type="submit" class="btn btn-success" value="Cancel" />
		</form:form>
	</div>
</body>
</html>