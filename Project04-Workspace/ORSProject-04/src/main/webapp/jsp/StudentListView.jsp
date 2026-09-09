<%@page import="in.co.rays.proj4.bean.CollegeBean"%>
<%@page import="in.co.rays.proj4.bean.StudentBean"%>
<%@page import="in.co.rays.proj4.model.CollegeModel"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<%
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = ((pageNo - 1) * pageSize) + 1;
	List<StudentBean> list = ServletUtility.getList(request);
	Iterator<StudentBean> it = list.iterator();
	String _suc = ServletUtility.getSuccessMessage(request);
	String _err = ServletUtility.getErrorMessage(request);
	%>

	<form action="<%=ORSView.USER_LIST_CTL%>" method="post">
		<div align="center">

			<h1>Student List</h1>

			<h3 style="color: green"><%=_suc%></h3>
			<h3 style="color: red"><%=_err%></h3>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table>
				<tr>
					<td><input type="text" name="firstName" value=""
						placeholder="search by firstName"></td>
					<td><input type="text" name="collegeName" value=""
						placeholder="search by collegeName"></td>
					<td><input type="submit" name="operation"
						value="<%=BaseCtl.OP_SEARCH%>"></td>
				</tr>
			</table>

			<table border="1px" width="100%">

				<tr style="background-color: skyblue">
					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)"></th>
					<th>S.No</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>College Name</th>
					<th>Email</th>
					<th>DOB</th>
					<th>Mobile No</th>
				</tr>

				<%
				while (it.hasNext()) {
					StudentBean bean = it.next();
					CollegeModel cmodel = new CollegeModel();
					CollegeBean cbean = cmodel.findByPK(bean.getCollegeId());
				%>
				<tr align="center" style="background-color: lightgrey">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=cbean.getName()%></td>
					<td><%=bean.getEmail()%></td>
					<td><%=bean.getDateOfBirth()%></td>
					<td><%=bean.getMobileNo()%></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>

		<table width="100%">
			<tr>
				<td><input type="submit" name="operation"
					<%=pageNo == 1 ? "disabled" : ""%> value="<%=BaseCtl.OP_PREVIOUS%>"></td>
				<td align="center"><input type="submit" name="operation"
					value="<%=BaseCtl.OP_DELETE%>"></td>
				<td align="right"><input type="submit" name="operation"
					<%=list.size() < 10 ? "disabled" : ""%>
					value="<%=BaseCtl.OP_NEXT%>"></td>
			</tr>
		</table>
	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>