<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.bean.RoleBean"%>
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

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>User</title>

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

	String _suc = ServletUtility.getSuccessMessage(request);

	String _err = ServletUtility.getErrorMessage(request);

	List<RoleBean> roleList = (List) request.getAttribute("roleList");

	HashMap<String, String> map = new HashMap<String, String>();

	map.put("Male", "male");

	map.put("Female", "female");

	%>

	<jsp:useBean id="bean"
		class="in.co.rays.proj4.bean.UserBean"
		scope="request">
	</jsp:useBean>


	<div class="container py-4">

		<div class="card border-0 shadow-lg rounded-4 mx-auto"
			style="max-width: 850px;">

			<div class="card-body p-4">

				<!-- Heading -->
				<div class="text-center mb-4">

					<div class="mb-2">
						<i class="bi bi-person-circle text-primary"
							style="font-size: 42px;"></i>
					</div>

					<h3 class="fw-bold text-dark mb-0">

						<%=bean != null && bean.getId() > 0 ? "Update User" : "Add User"%>

					</h3>

				</div>


				<!-- Success / Error Message -->
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


				<!-- Photo Upload -->
				<%
				if (bean.getId() > 0) {
				%>

				<div class="bg-light rounded-4 p-3 mb-4">

					<div class="text-center">

						<h6 class="fw-bold text-dark mb-3">
							<i class="bi bi-camera me-1 text-primary"></i>
							Profile Photo
						</h6>

						<form action="<%=ORSView.UPLOAD_PHOTO_CTL%>"
							method="POST"
							enctype="multipart/form-data">

							<input type="hidden"
								name="id"
								value="<%=bean.getId()%>">

							<div class="mb-3">

								<img
									src="<%=ORSView.UPLOAD_PHOTO_CTL%>?id=<%=bean.getId()%>"
									onerror="this.style.display='none';"
									alt="User Photo"
									width="80"
									height="80"
									class="rounded-circle border shadow-sm"
									style="object-fit: cover;">

							</div>

							<div class="mb-3">

								<input
									type="file"
									name="photo"
									accept="image/*"
									class="form-control">

							</div>

							<input
								type="submit"
								value="Upload Photo"
								class="btn btn-outline-primary btn-sm px-3">

						</form>

					</div>

				</div>

				<%
				}
				%>


				<!-- User Form -->

				<form action="<%=ORSView.USER_CTL%>" method="post">

					<input type="hidden"
						name="id"
						value="<%=DataUtility.getStringData(bean.getId())%>">


					<div class="bg-light rounded-4 p-3">

						<div class="row g-3">


							<!-- First Name -->
							<div class="col-md-6">

								<label class="form-label fw-semibold">
									First Name <span class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">
										<i class="bi bi-person text-primary"></i>
									</span>

									<input
										type="text"
										name="firstName"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getFirstName())%>"
										placeholder="Enter your firstName">

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("firstName", request)%>
								</div>

							</div>


							<!-- Last Name -->
							<div class="col-md-6">

								<label class="form-label fw-semibold">
									Last Name <span class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">
										<i class="bi bi-person text-primary"></i>
									</span>

									<input
										type="text"
										name="lastName"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getLastName())%>"
										placeholder="Enter your lastName">

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("lastName", request)%>
								</div>

							</div>


							<!-- Login -->
							<div class="col-md-6">

								<label class="form-label fw-semibold">
									Login <span class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">
										<i class="bi bi-envelope text-primary"></i>
									</span>

									<input
										type="text"
										name="login"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getLogin())%>"
										placeholder="Enter an emial">

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("login", request)%>
								</div>

							</div>


							<!-- Role -->
							<div class="col-md-6">

								<label class="form-label fw-semibold">
									Role <span class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">
										<i class="bi bi-person-badge text-primary"></i>
									</span>

									<%=HTMLUtility.getList("roleId",
										DataUtility.getStringData(bean.getRoleId()),
										roleList)%>

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("roleId", request)%>
								</div>

							</div>


							<!-- Password -->
							<div class="col-md-6">

								<label class="form-label fw-semibold">
									Password <span class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">
										<i class="bi bi-lock text-primary"></i>
									</span>

									<input
										type="password"
										name="password"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getPassword())%>"
										placeholder="Enter an password">

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("password", request)%>
								</div>

							</div>


							<!-- Confirm Password -->
							<div class="col-md-6">

								<label class="form-label fw-semibold">
									Confirm Password <span class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">
										<i class="bi bi-lock-fill text-primary"></i>
									</span>

									<input
										type="password"
										name="confirmPassword"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getPassword())%>"
										placeholder="Re-enter your password">

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("confirmPassword", request)%>
								</div>

							</div>


							<!-- Gender -->
							<div class="col-md-6">

								<label class="form-label fw-semibold">
									Gender <span class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">
										<i class="bi bi-gender-ambiguous text-primary"></i>
									</span> <%=HTMLUtility.getList("gender", DataUtility.getStringData(bean.getGender()), map) %>

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("gender", request)%>
								</div>

							</div>


							<!-- DOB -->
							<div class="col-md-6">

								<label class="form-label fw-semibold">
									DOB <span class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">
										<i class="bi bi-calendar-date text-primary"></i>
									</span>

									<input
										type="date"
										name="dob"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getDob())%>">

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("dob", request)%>
								</div>

							</div>


							<!-- Save Button -->
                            <div class="col-12 text-center mt-3">

                                <input
                                    type="submit"
                                    name="operation"
                                    value="<%=bean != null && bean.getId() > 0
                                            ? "Update"
                                            : BaseCtl.OP_SAVE%>"
                                    class="btn btn-primary btn-sm px-4">

                            </div>
						</div>

					</div>

				</form>

			</div>

		</div>

	</div>


	<%@include file="Footer.jsp"%>

</body>

</html>
