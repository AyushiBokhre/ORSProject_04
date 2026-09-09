<%@page import="in.co.rays.proj4.bean.CollegeBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.controller.UserCtl"%>
<%@page import="in.co.rays.proj4.controller.LoginCtl"%>
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
	List<CollegeBean> collegeList = (List) request.getAttribute("collegeList");
	%>

	<form action="<%=ORSView.STUDENT_CTL%>" method="post">

		<div align="center">

			<h1>Add Student</h1>

			<h3 style="color: green"><%=_suc%></h3>
			<h3 style="color: red"><%=_err%></h3>

			<table>

				<tr>
					<th>FirstName<font color="red">*</font></th>
					<td><input type="text" name="firstName" value=""
						placeholder="enter your firstName"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("firstName", request)%></td>
				</tr>

				<tr>
					<th>LastName<font color="red">*</font></th>
					<td><input type="text" name="lastName" value=""
						placeholder="enter your lastName"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("lastName", request)%></td>
				</tr>

				<tr>
					<th>Email<font color="red">*</font></th>
					<td><input type="email" name="email" value=""
						placeholder="enter an emial"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("email", request)%></td>
				</tr>

				<tr>
					<th>College<font color="red">*</font></th>
					<td><select class='form-control' name='collegeId'>
							<option selected value=''>-------------Select------------</option>
								<%
							for (CollegeBean cbean : collegeList) {
							%>
							<option value='<%=cbean.getKey()%>'><%=cbean.getValue()%></option>
							<%
							}
							%>
					</select></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("collegeId", request)%></td>
				</tr>

				

				<tr>
					<th>DOB<font color="red">*</font></th>
					<td><input type="date" name="dob" value=""></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("dob", request)%></td>
				</tr>
				
				<tr>
					<th>Mobile No<font color="red">*</font></th>
					<td><input type="text" name="mobileNo" value=""></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></td>
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