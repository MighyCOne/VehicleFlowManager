<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="v-style.css"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<!-- Must use internal CSS for Spring MVC-->

<!--CSS: my_style.css-->
<!-- <link type="text/css" rel="stylesheet" href="v-style.css" /> -->
<!--Bootstrap  -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- <link rel="stylesheet" type="text/css" href="v-style.css"> -->
</head>
<body>

<br>
Web Service WSDL: http://localhost:7001/VehicleFlowManagerProjectWebService/VehicleInventoryWsService?WSDL
<br>

	<div class="container">
		<div class="login-css">
		<h3>Vehicle Flow Manager</h3>
			<h3>Login</h3>
			<form:form method="POST" modelAttribute="credentials"
				action="${pageContext.request.contextPath}/vlogin/check">

				<div class="form-group form-group-sm">

					<div class="col-sm-12">
						<div class="center">
							<%-- <form:form method="GET" modelAttribute="credentials"> --%>
							<form:input type="text" class="form-control" path="username"
								placeholder="Username" />
							<br>
							<form:input type="password" class="form-control" path="password"
								placeholder="Password" />
							<br> <input type="submit" class="btn btn-primary btn-block"
								value="Login" />
						</div>
					</div>
				</div>

				<form:errors path="username" cssClass="errors"></form:errors>
				<br>
				<form:errors path="password" cssClass="errors"></form:errors>
			</form:form>
		</div>
	</div>
	
</body>
</html>