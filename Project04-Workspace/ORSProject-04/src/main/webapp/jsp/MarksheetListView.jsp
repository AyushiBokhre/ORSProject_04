<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.bean.MarksheetBean"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<%@ include file="Header.jsp"%>
	<%
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = ((pageNo - 1) * pageSize) + 1;
	List<MarksheetBean> list = ServletUtility.getList(request);
	Iterator<MarksheetBean> it = list.iterator();
	String _suc = ServletUtility.getSuccessMessage(request);
	String _err = ServletUtility.getErrorMessage(request);
	%>

	<form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">
		<div align="center">

			<h1>Marksheet List</h1>

			<h3 style="color: green"><%=_suc != null ? _suc : ""%></h3>
			<h3 style="color: red"><%=_err != null ? _err : ""%></h3>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> 
			<input type="hidden" name="pageSize" value="<%=pageSize%>">

			<table>
				<tr>
					<td><input type="text" name="name" value=""
						placeholder="search by name"></td>
					<td><input type="text" name="rollNo" value=""
						placeholder="search by roll no"></td>
					<td><input type="submit" name="operation"
						value="<%=BaseCtl.OP_SEARCH%>"></td>
				</tr>
			</table>

			<table border="1px" width="100%">

				<tr style="background-color: skyblue">
					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)"></th>
					<th>S.No</th>
					<th>Roll No</th>
					<th>Name</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Physics</th>
				</tr>

				<%
				while (it.hasNext()) {
					MarksheetBean bean = it.next();
				%>
				<tr align="center" style="background-color: lightgrey">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getRollNo()%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getPhysics()%></td>
					<td><%=bean.getChemistry()%></td>
					<td><%=bean.getMaths()%></td>
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
</body>
</html>