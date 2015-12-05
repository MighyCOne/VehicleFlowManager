<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.eintern.orm.entity.Vehicle"%>
<%@ page import="com.eintern.orm.entity.Request"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

</head>
<body>
<%@ include file="v-style.css" %>
<%@include file="cssSkeleton.jsp"%>

	The following vehicles will be marked as received:
	
	<form:form method="POST"
		action="${pageContext.request.contextPath}/pwe/VehicleReceived">

		<br>

		<!-- <h3>View All Storage Units:</h3> -->
		<center>
			<table border="1" class="table table-striped">
				<tr>
					<th colspan="5">Vehicles in Transit to Your Location</th>
				</tr>
				<tr>
					<th>Request ID</th>
					<th>Vehicle ID</th>
					<th>Vehicle Name</th>
					<th>Shipping From(ID)</th>
					<th>Shipping From(Name)</th>
				</tr>
				<%
					//This page should display a list of vehicles (id,where they came from,etc.) whose request where transit and their destination location was to my location
						for (Request req : (List<Request>) session.getAttribute("mark_result")) {
				%>
				<tr>
					<td><%=req.getReqId()%></td>
					<td><%=req.getVehicle().getVehicleId()%></td>
					<td><%=req.getVehicle().getVehicleName()%></td>
					<td><%=req.getVehicle().getLocation().getLocId()%></td>
					<td><%=req.getVehicle().getLocation().getLocName()%></td>
					<%
						
					%>
				</tr>
				<%
					
						}
				%>
			</table>
			<input type="submit" class="btn btn-primary" value="Submit" />
		</center>
	</form:form>


</body>
</html>