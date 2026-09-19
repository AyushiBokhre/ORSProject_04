<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<meta name="viewport"
 content="width=device-width, initial-scale=1.0">

<title>Role</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link
	rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">

</head>

<body class="bg-light">

<%@include file="Header.jsp"%>

<%
String _suc = ServletUtility.getSuccessMessage(request);
String _err = ServletUtility.getErrorMessage(request);
%>

<jsp:useBean id="bean"
	class="in.co.rays.proj4.bean.RoleBean"
	scope="request">
</jsp:useBean>

<form action="<%=ORSView.ROLE_CTL%>" method="post">

	<input type="hidden"
		name="id"
		value="<%=DataUtility.getStringData(bean.getId())%>">

	<div class="container pt-4 pb-5 mb-5">

		<div class="card border-0 shadow-lg rounded-4 mx-auto"
			style="max-width: 650px;">

			<div class="card-body p-4">

				<!-- Heading -->

				<div class="text-center mb-4">

					<div class="mb-2">

						<i class="bi bi-person-badge-fill text-primary"
							style="font-size: 42px;"></i>

					</div>

					<h3 class="fw-bold text-dark mb-0">

						<%=bean != null && bean.getId() > 0
								? "Update Role"
								: "Add Role"%>

					</h3>

				</div>


				<!-- Success Message -->

				<%
				if (_suc != null && !_suc.trim().isEmpty()) {
				%>

				<div class="alert alert-success text-center py-2">

					<i class="bi bi-check-circle-fill me-2"></i>

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

					<i class="bi bi-exclamation-triangle-fill me-2"></i>

					<%=_err%>

				</div>

				<%
				}
				%>


				<div class="bg-light rounded-4 p-3">

					<!-- Name -->

					<div class="mb-3">

						<label class="form-label fw-semibold">

							Name <span class="text-danger">*</span>

						</label>

						<div class="input-group">

							<span class="input-group-text bg-white">

								<i class="bi bi-person-badge text-primary"></i>

							</span>

							<input type="text"
								class="form-control"
								name="name"
								value="<%=DataUtility.getStringData(bean.getName())%>"
								placeholder="Enter role name">

						</div>

						<div class="text-danger small mt-1">

							<%=ServletUtility.getErrorMessage("name", request)%>

						</div>

					</div>


					<!-- Description -->

					<div class="mb-3">

						<label class="form-label fw-semibold">

							Description <span class="text-danger">*</span>

						</label>

						<div class="input-group">

							<span class="input-group-text bg-white">

								<i class="bi bi-card-text text-primary"></i>

							</span>

							<input type="text"
								class="form-control"
								name="description"
								value="<%=DataUtility.getStringData(bean.getDescription())%>"
								placeholder="Enter role description">

						</div>

						<div class="text-danger small mt-1">

							<%=ServletUtility.getErrorMessage("description", request)%>

						</div>

					</div>

				</div>


				<!-- Save / Update Button -->

				<div class="col-12 text-center mt-3">

					<input type="submit"
						name="operation"
						value="<%=bean != null && bean.getId() > 0
								? "Update"
								: BaseCtl.OP_SAVE%>"
						class="btn btn-primary btn-sm px-4">

				</div>

			</div>

		</div>

	</div>

</form>

<%@include file="Footer.jsp"%>

</body>

</html>
