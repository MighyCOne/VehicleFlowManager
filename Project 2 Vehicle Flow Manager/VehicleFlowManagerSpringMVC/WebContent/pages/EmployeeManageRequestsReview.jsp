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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
	<%@ include file="v-style.css"%>

	<%@include file="cssSkeleton.jsp"%>
	<center>
		<form:form method="POST"
			action="${pageContext.request.contextPath}/pwe/ManageRequestsResult">

	Delete the following requests?

		<!-- <h3>View All Storage Units:</h3> -->

			<table class="table table-striped" border="1">
				<tr>
					<th colspan="5">Your Open Requests</th>
				</tr>
				<tr>
					<th>Request ID</th>
					<th>Vehicle ID</th>
					<th>Vehicle Name</th>
					<th>Shipping From(ID)</th>
					<th>Shipping From(Name)</th>
					<th>Status</th>

				</tr>
				<%
					//This page should display a list of vehicles (id,where they came from,etc.) whose request where transit and their destination location was to my location
						for (Request req : (List<Request>) session.getAttribute("delete_review")) {
				%>
				<tr>
					<td><%=req.getReqId()%></td>
					<td><%=req.getVehicle().getVehicleId()%></td>
					<td><%=req.getVehicle().getVehicleName()%></td>
					<td><%=req.getVehicle().getLocation().getLocId()%></td>
					<td><%=req.getVehicle().getLocation().getLocName()%></td>
					<td><%=req.getStatus()%></td>
				</tr>
				<%
					}
				%>
			</table>
			<input type="submit" class="btn btn-primary" value="Delete" />

		</form:form>

		<form:form method="POST"
			action="${pageContext.request.contextPath}/vlogin/cancel">
			<input type="submit" class="btn btn-success" value="Cancel" />
		</form:form>
	</center>
</body>
</html>