<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.bean.CourseBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
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
	List<CourseBean> courseList = (List) request.getAttribute("courseList");

	%>

	<form action="<%=ORSView.SUBJECT_CTL%>" method="post">

		<div align="center">

			<h1>Add Subject</h1>

			<h3 style="color: green"><%=_suc != null ? _suc : ""%></h3>
			<h3 style="color: red"><%=_err != null ? _err : ""%></h3>

			<table>
				<tr>
					<th>Course<font color="red">*</font></th>
					<%-- <td><select class='form-control' name='courseId'>
							<option selected value=''>-------------Select------------</option>
								<%
							for (CourseBean cbean : courseList) {
							%>
							<option value='<%=cbean.getKey()%>'><%=cbean.getValue()%></option>
							<%
							}
							%>
					</select></td>--%>
					<td><%=HTMLUtility.getList("courseId", "" ,courseList) %></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("courseId", request)%></td>
				</tr>
		
				<tr>
					<th>Name<font color="red">*</font></th>
					<td><input type="text" name="name" value=""
						placeholder="enter subject name"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("name", request)%></td>
				</tr>

				<tr>
					<th>Description<font color="red">*</font></th>
					<td><input type="text" name="description" value=""
						placeholder="enter subject description"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("description", request)%></td>
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