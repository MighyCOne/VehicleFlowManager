<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.eintern.orm.entity.Location"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!--Bootstrap  -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

</head>
<body>
	<%@include file="v-style.css"%>
	<%-- <nav class="navbar">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Welcome <%=session.getAttribute("name")%>
				<%=session.getAttribute("locationType")%> #<%=session.getAttribute("locationId")%>:
				<%=session.getAttribute("locationName")%></a>
		</div>
		<div>
			<!--  <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">Page 1</a></li>
        <li><a href="#">Page 2</a></li> 
        <li><a href="#">Page 3</a></li> 
      </ul> -->
			<ul class="nav navbar-nav navbar-right">
				<!--  <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li> -->

				<li><form:form method="GET"
							action="${pageContext.request.contextPath}/vlogin/logout"><span class="glyphicon glyphicon-log-in"></span>
						Login</form:form></li>
			</ul>
		</div>
	</div>
	</nav> --%>
		<div class="navbar navbar-default">

		
				<b>Welcome <%=session.getAttribute("name")%> </b><div class="right">
						<form:form method="GET"
							action="${pageContext.request.contextPath}/vlogin/logout">
							<input type="submit" class="btn btn-primary" value="Logout" />
						</form:form>
					</div>
				<br>
				<b> <%=session.getAttribute("locationType")%>
					#<%=session.getAttribute("locationId")%>: <%=session.getAttribute("locationName")%>
				</b>
					
					
					
				<div class="center-text"><h4><b><%=session.getAttribute("empType") %> Portal</b></h4></div>
				
				
				
	</div>


	<!-- <div class="left-bar">
This is the left-bar.
</div>
<div class="right-bar">
This is the right-bar.
</div> -->
	<br>
</body>
</html>