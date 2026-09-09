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

	<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">

		<div align="center">

			<h1>Add Marksheet</h1>

			<h3 style="color: green"><%=_suc != null ? _suc : ""%></h3>
			<h3 style="color: red"><%=_err != null ? _err : ""%></h3>

			<table>
				<tr>
					<th>Roll No<font color="red">*</font></th>
					<td><input type="text" name="rollNo" value=""
						placeholder="enter roll no"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("rollNo", request)%></td>
				</tr>
				<tr>
					<th>Name<font color="red">*</font></th>
					<td><input type="text" name="name" value=""
						placeholder="enter role name"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("name", request)%></td>
				</tr>

				<tr>
					<th>Physics<font color="red">*</font></th>
					<td><input type="text" name="physics" value=""
						placeholder="enter physics marks "></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("physics", request)%></td>
				</tr>
				<tr>
					<th>Chemistry<font color="red">*</font></th>
					<td><input type="text" name="chemistry" value=""
						placeholder="enter chemistry marks"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("chemistry", request)%></td>
				</tr>
				<tr>
					<th>Maths<font color="red">*</font></th>
					<td><input type="text" name="maths" value=""
						placeholder="enter maths marks"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("maths", request)%></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=BaseCtl.OP_SAVE%>"></td>
				</tr>

			</table>

		</div>

	</form>
</body>
</html>