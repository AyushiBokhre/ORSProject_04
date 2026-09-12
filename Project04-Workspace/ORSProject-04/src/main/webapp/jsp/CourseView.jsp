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
	/* CourseBean bean = (CourseBean) request.getAttribute("bean"); */
	%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.CourseBean"
		scope="request"></jsp:useBean>

	<form action="<%=ORSView.COURSE_CTL%>" method="post">

		<input type="hidden" name="id"
			value="<%=DataUtility.getStringData(bean.getId())%>">
	
		<div align="center">

			<h1>
				<%=bean != null && bean.getId() > 0 ? "Update Course" : "Add Course"%>
			</h1>

			<h3 style="color: green"><%=_suc != null ? _suc : ""%></h3>
			<h3 style="color: red"><%=_err != null ? _err : ""%></h3>

			<table>

				<tr>
					<th>Name<font color="red">*</font></th>
					<td><input type="text" name="name"  value="<%=DataUtility.getStringData(bean.getName())%>"
						placeholder="enter course name"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("name", request)%></td>
				</tr>

				<tr>
					<th>Description<font color="red">*</font></th>
					<td><input type="text" name="description"  value="<%=DataUtility.getStringData(bean.getDescription())%>"
						placeholder="enter course description"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("description", request)%></td>
				</tr>
				<tr>
					<th>Duration<font color="red">*</font></th>
					<td><input type="text" name="duration"  value="<%=DataUtility.getStringData(bean.getDuration())%>"
						placeholder="enter course duration"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("duration", request)%></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=bean != null && bean.getId() > 0 ? "Update" : BaseCtl.OP_SAVE%>"></td>
				</tr>

			</table>

		</div>

	</form>
</body>
</html>