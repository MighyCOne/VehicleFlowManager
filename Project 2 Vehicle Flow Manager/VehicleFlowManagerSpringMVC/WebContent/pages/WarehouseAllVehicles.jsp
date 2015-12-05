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
<%@ include file="v-style.css" %>
<%@include file="cssSkeleton.jsp"%>

		<br>

		<div align=center>
			<table border="1" class="table table-striped">
				<tr>
					<th colspan="3">All Vehicles Stored at Warehouse <%=session.getAttribute("locationId") %></th>
				</tr>
				<tr>
					<th>Vehicle Id</th>
					<th>Vehicle Name</th>
					<th>Number of Open Requests</th>
				</tr>
				<%
					for (Vehicle vehicle : (List<Vehicle>) session.getAttribute("all_w_vehicles")) {
						if (vehicle.getLocation().getLocId()==(int)session.getAttribute("locationId")){
						
				%>

				<tr>
					<td><%=vehicle.getVehicleId()%></td>
					<td><%=vehicle.getVehicleName()%></td>
					<td><%= vehicle.getRequests().size()%></td>
					<%
						
					%>
				</tr>
				<%
				}
					}
				%>
			</table>
			<br>
			<form:form method="POST"
			action="${pageContext.request.contextPath}/vlogin/cancel">
			<input type="submit" class="btn btn-success" value="Cancel" />
		</form:form>
		</div>



</body>
</html>