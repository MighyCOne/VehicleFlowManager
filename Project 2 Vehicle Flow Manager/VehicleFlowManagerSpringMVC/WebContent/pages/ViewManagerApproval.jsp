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
	<%@include file="cssSkeleton.jsp"%>
	<%@ include file="v-style.css"%>

	<div align=center>
		<form:form method="POST"
			action="${pageContext.request.contextPath}/pwm/ViewApproval">
<br>


			<table border="1" class="table table-striped">
				<tr>
					<th colspan="4">Approval Required For the Following
						Transactions:</th>
				</tr>
				<tr>
					<th>Date</th>
					<th>Request Id</th>
					<th>Vehicle Id</th>
					<th>Requesting Location Id</th>
					<th>Select</th>

				</tr>
				<%
					for (Request req : (List<Request>) session.getAttribute("r")) {
							if (req.getShipping_location_id() == (int) session.getAttribute("locationId")) {
				%>

				<tr>
					<td><%=req.getReq_date()%></td>
					<td><%=req.getReqId()%></td>
					<td><%=req.getVehicle().getVehicleId()%></td>
					<td><%=req.getLocation().getLocId()%></td>
					<td>
						<%-- <input type="hidden" name="oper" value= "<%=req.getReqId()%>"> --%>
						<button type="submit" name="oper" class="btn btn-primary"
							value="<%=req.getReqId()%>">Select</button>
					</td>

					<%
						
					%>
				</tr>
				<%
					}
						}
				%>
			</table>

			<form:errors path="username" cssClass="errors"></form:errors>
		</form:form>
		<form:form method="POST"
			action="${pageContext.request.contextPath}/vlogin/cancel">
			<input type="submit" class="btn btn-success" value="Cancel" />
		</form:form>
	</div>
</body>
</html>