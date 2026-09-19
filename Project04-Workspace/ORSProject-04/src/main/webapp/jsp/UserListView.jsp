<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@page import="in.co.rays.proj4.bean.RoleBean"%>
<%@page import="in.co.rays.proj4.model.RoleModel"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>User List</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
	rel="stylesheet">

</head>

<body class="bg-light">

	<%@include file="Header.jsp"%>

	<%
	int pageNo = ServletUtility.getPageNo(request);

	int pageSize = ServletUtility.getPageSize(request);

	int index = ((pageNo - 1) * pageSize) + 1;

	List<UserBean> list = ServletUtility.getList(request);

	Iterator<UserBean> it = list.iterator();

	String _suc = ServletUtility.getSuccessMessage(request);

	String _err = ServletUtility.getErrorMessage(request);
	%>


	<form action="<%=ORSView.USER_LIST_CTL%>" method="post">

		<div class="container py-4">

			<div class="card border-0 shadow-lg rounded-4">

				<div class="card-body p-4">


					<!-- Heading -->

					<div class="text-center mb-4">

						<div class="mb-2">

							<i class="bi bi-people-fill text-primary"
								style="font-size: 42px;"></i>

						</div>

						<h3 class="fw-bold text-dark mb-0">User List</h3>

					</div>

					<!-- PDF Button - Top Right -->
					
					<div class="position-absolute top-0 end-0 mt-2 me-3">
					
						<a href="<%=ORSView.USER_REPORT_CTL%>?type=pdf"
							class="btn btn-outline-danger btn-sm px-3">
							
							 <i class="bi bi-file-earmark-pdf me-1"></i> PDF
							 
						</a>
						
					</div>
					
					
					<!-- Success Message -->

					<%
					if (_suc != null && !_suc.trim().isEmpty()) {
					%>

					<div class="alert alert-success text-center py-2">

						<i class="bi bi-check-circle me-1"></i>

						<%=_suc%>

					</div>

					<%
					}
					%>


					<!-- Error Message -->

					<%
					if (_err != null && !_err.trim().isEmpty()) {
					%>

					<div class="alert alert-danger text-center py-2">

						<i class="bi bi-exclamation-circle me-1"></i>

						<%=_err%>

					</div>

					<%
					}
					%>


					<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
						type="hidden" name="pageSize" value="<%=pageSize%>">


					<!-- Search Section -->

					<div class="bg-light rounded-4 p-3 mb-4">

						<div class="row g-3 align-items-center">

							<div class="col-md-5">

								<div class="input-group">

									<span class="input-group-text bg-white border-end-0"> <i
										class="bi bi-person text-primary"></i>

									</span> <input type="text" name="firstName" value=""
										class="form-control border-start-0"
										placeholder="Search by Name">

								</div>

							</div>


							<div class="col-md-5">

								<div class="input-group">

									<span class="input-group-text bg-white border-end-0"> <i
										class="bi bi-person text-primary"></i>

									</span> <input type="text" name="login" value=""
										class="form-control border-start-0"
										placeholder="Search by login">

								</div>

							</div>


							<div class="col-md-2">

								<input type="submit" name="operation"
									class="btn btn-primary btn-sm px-3"
									value="<%=BaseCtl.OP_SEARCH%>">

							</div>

						</div>

					</div>


					<!-- User Table -->

					<div class="table-responsive">

						<table
							class="table table-bordered table-hover align-middle text-center mb-0">

							<thead class="table-primary">

								<tr>

									<th><input type="checkbox" class="form-check-input"
										onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">

									</th>

									<th>S.No</th>

									<th>Photo</th>

									<th>FirstName</th>

									<th>LastName</th>

									<th>Login</th>

									<th>DOB</th>

									<th>Gender</th>

									<th>RoleName</th>

									<th>Edit</th>

								</tr>

							</thead>


							<tbody>

								<%
								while (it.hasNext()) {

									UserBean bean = it.next();

									RoleModel rmodel = new RoleModel();

									RoleBean rbean = rmodel.findByPK(bean.getRoleId());
								%>


								<tr>

									<td><input type="checkbox" class="form-check-input"
										name="ids" value="<%=bean.getId()%>"></td>


									<td><%=index++%></td>


									<td><img
										src="<%=ORSView.UPLOAD_PHOTO_CTL%>?id=<%=bean.getId()%>"
										onerror="this.style.display='none';" alt="User Photo"
										width="50" height="50" class="rounded-circle border"
										style="object-fit: cover;"></td>


									<td><%=bean.getFirstName()%></td>


									<td><%=bean.getLastName()%></td>


									<td><%=bean.getLogin()%></td>


									<td><%=bean.getDob()%></td>


									<td><%=bean.getGender()%></td>



									<td><%=rbean.getName()%></td>


									<td><a
										href="<%=ORSView.USER_CTL + "?id=" + bean.getId()%>"
										class="btn btn-sm btn-outline-primary"> <i
											class="bi bi-pencil-square me-1"></i> Edit

									</a></td>

								</tr>


								<%
								}
								%>

							</tbody>

						</table>

					</div>


					<!-- Pagination / Actions -->

					<div class="border-top mt-4 pt-3 pb-5"">

						<div
							class="d-flex justify-content-center align-items-center gap-2">

							<input type="submit" name="operation"
								class="btn btn-primary btn-sm px-3"
								<%=pageNo == 1 ? "disabled" : ""%>
								value="<%=BaseCtl.OP_PREVIOUS%>"> <input type="submit"
								name="operation" class="btn btn-danger btn-sm px-3"
								value="<%=BaseCtl.OP_DELETE%>"> <input type="submit"
								name="operation" class="btn btn-primary btn-sm px-3"
								<%=list.size() < 10 ? "disabled" : ""%>
								value="<%=BaseCtl.OP_NEXT%>">

						</div>

					</div>


				</div>

			</div>

		</div>

	</form>


	<%@include file="Footer.jsp"%>

</body>

</html>