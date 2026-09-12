<%@page import="in.co.rays.proj4.bean.CollegeBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.controller.UserCtl"%>
<%@page import="in.co.rays.proj4.controller.LoginCtl"%>
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
	List<CollegeBean> collegeList = (List) request.getAttribute("collegeList");
	/* FacultyBean bean = (FacultyBean) request.getAttribute("bean"); */
	%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.FacultyBean"
		scope="request"></jsp:useBean>

	<form action="<%=ORSView.FACULTY_CTL%>" method="post">

		<input type="hidden" name="id"
			value="<%=DataUtility.getStringData(bean.getId())%>">
	
		<div align="center">

			<h1>
				<%=bean != null && bean.getId() > 0 ? "Update Faculty" : "Add Faculty"%>
			</h1>

			<h3 style="color: green"><%=_suc%></h3>
			<h3 style="color: red"><%=_err%></h3>

			<table>
				<tr>
					<th>College Name<font color="red">*</font></th>
					<%-- <td><select class='form-control' name='collegeId'>
							<option selected value=''>-------------Select------------</option>
								<%
							for (CollegeBean cbean : collegeList) {
							%>
							<option value='<%=cbean.getKey()%>'><%=cbean.getValue()%></option>
							<%
							}
							%>
					</select></td>--%>
					<td><%=HTMLUtility.getList("collegeId",DataUtility.getStringData(bean.getCollegeId()) ,collegeList) %></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("collegeId", request)%></td>
				</tr>
			
				<tr>
					<th>FirstName<font color="red">*</font></th>
					<td><input type="text" name="firstName"  value="<%=DataUtility.getStringData(bean.getFirstName())%>"
						placeholder="enter your firstName"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("firstName", request)%></td>
				</tr>

				<tr>
					<th>LastName<font color="red">*</font></th>
					<td><input type="text" name="lastName"  value="<%=DataUtility.getStringData(bean.getLastName())%>"
						placeholder="enter your lastName"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("lastName", request)%></td>
				</tr>

				<tr>
					<th>Email<font color="red">*</font></th>
					<td><input type="email" name="email" value="<%=DataUtility.getStringData(bean.getEmail())%>"
						placeholder="enter an emial"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("email", request)%></td>
				</tr>

				<tr>
					<th>Mobile No<font color="red">*</font></th>
					<td><input type="text" name="mobileNo"  value="<%=DataUtility.getStringData(bean.getMobileNo())%>" placeholder="enter mobile no"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></td>
				</tr>
				
				<tr>
					<th>Address<font color="red">*</font></th>
					<td><input type="text" name="address" value="<%=DataUtility.getStringData(bean.getAddress())%>"  placeholder="enter address"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("address", request)%></td>
				</tr>
				
				<tr>
					<th>Gender<font color="red">*</font></th>
					<td><select class='form-control' name='gender'>
							<option selected value=''>-------------Select------------</option>
							<option value='female'>female</option>
							<option value='male'>male</option>
					</select></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("gender", request)%></td>
				</tr>
				
				<tr>
					<th>DOB<font color="red">*</font></th>
					<td><input type="date" name="dob" value="<%=DataUtility.getStringData(bean.getDob())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("dob", request)%></td>
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