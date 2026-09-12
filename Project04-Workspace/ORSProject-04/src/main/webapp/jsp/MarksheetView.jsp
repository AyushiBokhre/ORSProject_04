<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.bean.StudentBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
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
	List<StudentBean> studentList = (List) request.getAttribute("studentList");
	/* MarksheetBean bean = (MarksheetBean) request.getAttribute("bean"); */
	%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.MarksheetBean"
		scope="request"></jsp:useBean>
	<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">

		<input type="hidden" name="id"
			value="<%=DataUtility.getStringData(bean.getId())%>">
	
		<div align="center">

			<h1>
				<%=bean != null && bean.getId() > 0 ? "Update Marksheet" : "Add Marksheet"%>
			</h1>

			<h3 style="color: green"><%=_suc != null ? _suc : ""%></h3>
			<h3 style="color: red"><%=_err != null ? _err : ""%></h3>

			<table>
				<tr>
					<th>Roll No<font color="red">*</font></th>
					<td><input type="text" name="rollNo" value="<%=DataUtility.getStringData(bean.getRollNo())%>"
						placeholder="enter roll no"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("rollNo", request)%></td>
				</tr>
				<tr>
					<th>Name<font color="red">*</font></th>
					<%--<td><select class='form-control' name='studentId'>
							<option selected value=''>-------------Select------------</option>
								<%
							for (StudentBean sbean : studentList) {
							
							<option value='<%=sbean.getKey()%>'><%=sbean.getValue()%></option>
							<%
							}
							%>
					</select></td>--%>
					 <td><%=HTMLUtility.getList("studentId", DataUtility.getStringData(bean.getStudentId()) ,studentList) %></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("studentId", request)%></td>
				</tr>

				<tr>
					<th>Physics<font color="red">*</font></th>
					<td><input type="text" name="physics"  value="<%=DataUtility.getStringData(bean.getPhysics())%>"
						placeholder="enter physics marks "></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("physics", request)%></td>
				</tr>
				<tr>
					<th>Chemistry<font color="red">*</font></th>
					<td><input type="text" name="chemistry"  value="<%=DataUtility.getStringData(bean.getChemistry())%>"
						placeholder="enter chemistry marks"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("chemistry", request)%></td>
				</tr>
				<tr>
					<th>Maths<font color="red">*</font></th>
					<td><input type="text" name="maths"  value="<%=DataUtility.getStringData(bean.getMaths())%>"
						placeholder="enter maths marks"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("maths", request)%></td>
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