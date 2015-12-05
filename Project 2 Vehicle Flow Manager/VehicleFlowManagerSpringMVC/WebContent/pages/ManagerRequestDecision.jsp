<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.eintern.orm.entity.Request"%>
<%@ page import="com.eintern.orm.entity.Vehicle"%>
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
	<%
		Request req = (Request) session.getAttribute("this_request");
	%>
	<div align=center>
	<br>
		<form:form method="POST"
			action="${pageContext.request.contextPath}/pwm/Decision">

			<table border=1 class="table table-striped">
				<tr>
					<th colspan="5">Request Resolution</th>
				</tr>
				<tr>
					<th>Date</th>
					<th>Request Id</th>
					<th>Vehicle Id</th>
					<th>Vehicle Name</th>
					<th>Location Id</th>
					<th>Approve</th>
					<th>Deny</th>
				</tr>

				<tr>
					<td><%=req.getReq_date()%></td>
					<td><%=req.getReqId()%></td>
					<td><%=req.getVehicle().getVehicleId()%></td>
					<td><%=req.getVehicle().getVehicleName()%></td>
					<td><%=req.getVehicle().getLocation().getLocId()%></td>
					<td><input type="radio" name="approval" value="Approved" /></td>
					<!-- "CHECKED" is an example of HTML validation -->
					<td><input type="radio" name="approval" value="Denied" CHECKED /></td>
				</tr>
			</table>
			<input type="submit" class="btn btn-primary" value="Choose" />

		</form:form>
		<form:form method="POST"
			action="${pageContext.request.contextPath}/vlogin/cancel">
			<input type="submit" class="btn btn-success" value="Cancel" />
		</form:form>
	</div>
</body>
</html>