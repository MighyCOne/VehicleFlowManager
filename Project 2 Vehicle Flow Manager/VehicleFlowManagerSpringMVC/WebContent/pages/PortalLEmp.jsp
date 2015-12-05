<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="ErrorOccurredPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="v-style.css">

</head>
<body>
	<%@ include file="v-style.css"%>
	<%
		//This caching code prevents someone who has gone to another page(like logging out) from returning using back.
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<%@include file="cssSkeleton.jsp"%>

	<br>
	<table>
		<tr>
			<td><form:form method="GET"
					action="${pageContext.request.contextPath}/pwe/ViewInTransit">

					<input type="submit" class="btn btn-info"
						value="View Incoming Vehicles" />

				</form:form></td>

			<td><form:form method="GET"
					action="${pageContext.request.contextPath}/pwe/ManageRequests">

					<input type="submit" class="btn btn-primary"
						value="Manage My Requests" />

				</form:form></td>

			<td><form:form method="GET"
					action="${pageContext.request.contextPath}/pwe/VehicleRequestSelection">

					<input type="submit" class="btn btn-success"
						value="Request Vehicles" />

				</form:form></td>
	</table>
</body>
</html>