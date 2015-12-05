<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<link type="text/css" rel="stylesheet" href="v-style.css" />

</head>
<body>
	<%@include file="cssSkeleton.jsp"%>
	<center>
		<form:form method="POST"
			action="${pageContext.request.contextPath}/pwe/ManageRequestsReview">

			<br>

			<!-- <h3>View All Storage Units:</h3> -->

			<table class="table table-striped" border="1">
				<tr>
					<th colspan="7">Your Open Requests</th>
				</tr>
				<tr>
					<th>Request ID</th>
					<th>Vehicle ID</th>
					<th>Vehicle Name</th>
					<th>Shipping From(ID)</th>
					<th>Shipping From(Name)</th>
					<th>Last Update</th>
					<th>Status</th>
					<th>Delete</th>
				</tr>
				<%
					//This page should display a list of vehicles (id,where they came from,etc.) whose request where transit and their destination location was to my location
						for (Request req : (List<Request>) session.getAttribute("all_requests")) {
							if (req.getLocation().getLocId() == (int) session.getAttribute("locationId")) {
				%>
				<tr>
					<td><%=req.getReqId()%></td>
					<td><%=req.getVehicle().getVehicleId()%></td>
					<td><%=req.getVehicle().getVehicleName()%></td>
					<td><%=req.getVehicle().getLocation().getLocId()%></td>
					<td><%=req.getVehicle().getLocation().getLocName()%></td>
					<td><%=req.getLast_update_date()%></td>
					<td><%=req.getStatus()%></td>
					<%
						if (!req.getStatus().equals("Transit")) {
					%>
					<td><input type="checkbox" name="myReqsId"
						value="<%=req.getReqId()%>" /></td>
					<%
						}
					%>
				</tr>
				<%
					}
						}
				%>
			</table>
			<input type="submit" class="btn btn-primary" value="Submit" />

		</form:form>
		<form:form method="POST"
			action="${pageContext.request.contextPath}/vlogin/cancel">
			<input type="submit" class="btn btn-success" value="Cancel" />
		</form:form>

		</center>
</body>
</html>