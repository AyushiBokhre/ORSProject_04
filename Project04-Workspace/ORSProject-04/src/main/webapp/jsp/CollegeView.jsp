<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<%
	String _suc = ServletUtility.getSuccessMessage(request);
	String _err = ServletUtility.getErrorMessage(request);
	%>

	<form action="<%=ORSView.COLLEGE_CTL%>" method="post">

		<div align="center">

			<h1>Add College</h1>

			<h3 style="color: green"><%=_suc != null ? _suc : ""%></h3>
			<h3 style="color: red"><%=_err != null ? _err : ""%></h3>

			<table>

				<tr>
					<th>Name<font color="red">*</font></th>
					<td><input type="text" name="name" value=""
						placeholder="enter college name"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("name", request)%></td>
				</tr>

				<tr>
					<th>Address<font color="red">*</font></th>
					<td><input type="text" name="address" value=""
						placeholder="enter Address"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("address", request)%></td>
				</tr>
				<tr>
					<th>State<font color="red">*</font></th>
					<td><input type="text" name="state" value=""
						placeholder="enter state"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("state", request)%></td>
				</tr>
				<tr>
					<th>City<font color="red">*</font></th>
					<td><input type="text" name="city" value=""
						placeholder="enter city"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("city", request)%></td>
				</tr>
				<tr>
					<th>Phone No<font color="red">*</font></th>
					<td><input type="text" name="phoneNo" value=""
						placeholder="enter phoneNo"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("phoneNo", request)%></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=BaseCtl.OP_SAVE%>"></td>
				</tr>

			</table>

		</div>

	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>