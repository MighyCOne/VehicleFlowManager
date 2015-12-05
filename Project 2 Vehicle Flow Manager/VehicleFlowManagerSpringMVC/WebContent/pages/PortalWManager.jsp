<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page errorPage="ErrorOccurredPage.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!--CSS: my_style.css-->
<!-- <link type="text/css" rel="stylesheet" href="resources/v-style.css" /> -->
<!--Bootstrap  -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

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

	<table>
		<tr>
			<td><form:form method="GET"
					action="${pageContext.request.contextPath}/pwm/ViewRequests">
					<input type="submit" class="btn btn-primary" value="View Requests" />
				</form:form></td>

			<td><form:form method="GET"
					action="${pageContext.request.contextPath}/pwm/ViewAllMyVehicles">
					<input type="submit" class="btn btn-success"
						value="View Stored Vehicles" />

				</form:form></td>
		</tr>
	</table>

</body>
</html>