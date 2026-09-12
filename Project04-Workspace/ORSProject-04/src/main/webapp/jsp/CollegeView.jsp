<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>

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
	/* CollegeBean bean = (CollegeBean) request.getAttribute("bean"); */
	%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.CollegeBean"
		scope="request"></jsp:useBean>

	<form action="<%=ORSView.COLLEGE_CTL%>" method="post">

		<input type="hidden" name="id"
			value="<%=DataUtility.getStringData(bean.getId())%>">
	
		<div align="center">

			<h1>
				<%=bean != null && bean.getId() > 0 ? "Update College" : "Add College"%>
			</h1>

			<h3 style="color: green"><%=_suc != null ? _suc : ""%></h3>
			<h3 style="color: red"><%=_err != null ? _err : ""%></h3>

			<table>

				<tr>
					<th>Name<font color="red">*</font></th>
					<td><input type="text" name="name"  value="<%=DataUtility.getStringData(bean.getName())%>"
						placeholder="enter college name"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("name", request)%></td>
				</tr>

				<tr>
					<th>Address<font color="red">*</font></th>
					<td><input type="text" name="address"  value="<%=DataUtility.getStringData(bean.getAddresss())%>"
						placeholder="enter Address"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("address", request)%></td>
				</tr>
				<tr>
					<th>State<font color="red">*</font></th>
					<td><input type="text" name="state"  value="<%=DataUtility.getStringData(bean.getState())%>"
						placeholder="enter state"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("state", request)%></td>
				</tr>
				<tr>
					<th>City<font color="red">*</font></th>
					<td><input type="text" name="city"  value="<%=DataUtility.getStringData(bean.getCity())%>"
						placeholder="enter city"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("city", request)%></td>
				</tr>
				<tr>
					<th>Phone No<font color="red">*</font></th>
					<td><input type="text" name="phoneNo"  value="<%=DataUtility.getStringData(bean.getPhoneNo())%>"
						placeholder="enter phoneNo"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("phoneNo", request)%></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=bean != null && bean.getId() > 0 ? "Update" : BaseCtl.OP_SAVE%>"></td>
				</tr>

			</table>

		</div>

	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>