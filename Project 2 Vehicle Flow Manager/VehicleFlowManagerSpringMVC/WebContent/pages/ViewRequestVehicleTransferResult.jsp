<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.eintern.orm.entity.Vehicle"%>
<%@ page import="com.eintern.orm.entity.Employee"%>
<%@ page import="com.eintern.orm.entity.Location"%>
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
	<%@ include file="v-style.css"%>
	<%@include file="cssSkeleton.jsp"%>
	Are you sure you'd like to request the following vehicles?
	<center>
		<form:form method="POST"
			action="${pageContext.request.contextPath}/pwe/sendRequests">
			<%-- <form:form method="POST" modelAttribute="credentials"
					action="${pageContext.request.contextPath}/pwm">--%>

			<br>

			<!-- <h3>View All Storage Units:</h3> -->

			<table border="1" class="table table-striped">
				<tr>
					<th colspan="4">Vehicle Request Selection</th>
				</tr>
				<tr>
					<th>ID</th>
					<th>V.Name</th>
					<th>Location ID</th>
					<th>Location Name</th>
				</tr>
				<%
					for (Vehicle vehicle : (List<Vehicle>) session.getAttribute("result")) {
				%>

				<tr>
					<td><%=vehicle.getVehicleId()%></td>
					<td><%=vehicle.getVehicleName()%></td>
					<td><%=vehicle.getLocation().getLocId()%></td>
					<td><%=vehicle.getLocation().getLocName()%></td>
				</tr>
				<%
					}
				%>
			</table>
			<input type="submit" class="btn btn-primary" value="Send Request(s)" />

		</form:form>
		<form:form method="POST"
			action="${pageContext.request.contextPath}/vlogin/cancel">
			<input type="submit" class="btn btn-success" value="Cancel" />
		</form:form>
	</center>
</body>
</html>