<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.UserRegistrationCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Sign Up - ORS</title>

<!-- Bootstrap 5 -->

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Bootstrap Icons -->

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
	rel="stylesheet">

</head>

<!-- Light Background -->

<body class="bg-light">
<jsp:useBean id="bean"
		class="in.co.rays.proj4.bean.UserBean"
		scope="request">
	</jsp:useBean>

	<%@include file="Header.jsp"%>

	<%
	String _suc = ServletUtility.getSuccessMessage(request);
	String _err = ServletUtility.getErrorMessage(request);
	HashMap<String, String> map = new HashMap<String, String>();

	map.put("male", "male");

	map.put("female", "female");

	%>

	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">

		<div class="container py-5">

			<div class="row justify-content-center">

				<div class="col-lg-6 col-md-8 col-sm-10">

					<!-- Signup Card -->
					<div class="card shadow-lg border-0 rounded-4">

						<div class="card-body p-4">

							<!-- ORS Icon -->
							<div class="text-center mb-3">

								<i class="bi bi-person-plus-fill text-primary display-4"></i>

							</div>

							<!-- Heading -->
							<h3 class="text-center fw-bold text-primary mb-1">Create
								Account</h3>

							<p class="text-center text-muted mb-4">Register for your ORS
								account</p>


							<!-- Success Message -->
							<%
							if (_suc != null && !_suc.isEmpty()) {
							%>

							<div class="alert alert-success">

								<i class="bi bi-check-circle-fill"></i>

								<%=_suc%>

							</div>

							<%
							}
							%>


							<!-- Error Message -->
							<%
							if (_err != null && !_err.isEmpty()) {
							%>

							<div class="alert alert-danger">

								<i class="bi bi-exclamation-triangle-fill"></i>

								<%=_err%>

							</div>

							<%
							}
							%>


							
							<!-- First Name + Last Name -->
							<div class="row">

								<!-- First Name -->
								<div class="col-md-6 mb-3">

									<label class="form-label fw-bold"> <i
										class="bi bi-person-fill text-primary"></i> First Name <span
										class="text-danger">*</span>
									</label>

									<div class="input-group">

										<span class="input-group-text bg-light"> <i
											class="bi bi-person"></i>
										</span> <input type="text" name="firstName" class="form-control" value="<%=DataUtility.getStringData(bean.getFirstName()) %>"
											placeholder="Enter your first name">

									</div>

									<div class="text-danger small mt-1">
										<%=ServletUtility.getErrorMessage("firstName", request)%>
									</div>

								</div>


								<!-- Last Name -->
								<div class="col-md-6 mb-3">

									<label class="form-label fw-bold"> <i
										class="bi bi-person-fill text-primary"></i> Last Name <span
										class="text-danger">*</span>
									</label>

									<div class="input-group">

										<span class="input-group-text bg-light"> <i
											class="bi bi-person"></i>
										</span> <input type="text" name="lastName" class="form-control" value="<%=DataUtility.getStringData(bean.getLastName()) %>"
											placeholder="Enter your last name">

									</div>

									<div class="text-danger small mt-1">
										<%=ServletUtility.getErrorMessage("lastName", request)%>
									</div>

								</div>

							</div>


							<!-- Login -->
							<div class="mb-3">

								<label class="form-label fw-bold"> <i
									class="bi bi-envelope-fill text-primary"></i> Login <span
									class="text-danger">*</span>
								</label>

								<div class="input-group">

									<span class="input-group-text bg-light"> <i
										class="bi bi-envelope"></i>
									</span> <input type="text" name="login" class="form-control" value="<%=DataUtility.getStringData(bean.getLogin()) %>"
										placeholder="Enter your email">

								</div>

								<div class="text-danger small mt-1">
									<%=ServletUtility.getErrorMessage("login", request)%>
								</div>

							</div>


							<!-- Password + Confirm Password -->
							<div class="row">

								<!-- Password -->
								<div class="col-md-6 mb-3">

									<label class="form-label fw-bold"> <i
										class="bi bi-lock-fill text-primary"></i> Password <span
										class="text-danger">*</span>
									</label>

									<div class="input-group">

										<span class="input-group-text bg-light"> <i
											class="bi bi-lock"></i>
										</span> <input type="password" name="password" id="password" value="<%=DataUtility.getStringData(bean.getPassword()) %>"
											class="form-control" placeholder="Enter password">

										<button type="button" class="btn btn-outline-secondary"
											onclick="togglePassword('password', 'eyeIcon1')">

											<i class="bi bi-eye" id="eyeIcon1"></i>

										</button>

									</div>

									<div class="text-danger small mt-1">
										<%=ServletUtility.getErrorMessage("password", request)%>
									</div>

								</div>


								<!-- Confirm Password -->
								<div class="col-md-6 mb-3">

									<label class="form-label fw-bold"> <i
										class="bi bi-shield-lock-fill text-primary"></i> Confirm
										Password <span class="text-danger">*</span>
									</label>

									<div class="input-group">

										<span class="input-group-text bg-light"> <i
											class="bi bi-shield-lock"></i>
										</span> <input type="password" name="confirmPassword" value="<%=DataUtility.getStringData(bean.getPassword()) %>"
											id="confirmPassword" class="form-control"
											placeholder="Re-enter password">

										<button type="button" class="btn btn-outline-secondary"
											onclick="togglePassword('confirmPassword', 'eyeIcon2')">

											<i class="bi bi-eye" id="eyeIcon2"></i>

										</button>

									</div>

									<div class="text-danger small mt-1">
										<%=ServletUtility.getErrorMessage("confirmPassword", request)%>
									</div>

								</div>

							</div>


							<!-- Gender + DOB -->
							<div class="row">

								<!-- Gender -->
								<div class="col-md-6 mb-3">

									<label class="form-label fw-bold"> <i
										class="bi bi-gender-ambiguous text-primary"></i> Gender <span
										class="text-danger">*</span>
									</label>

									<div class="input-group">

										<span class="input-group-text bg-light"> <i
											class="bi bi-person-vcard"></i>
										</span> <%=HTMLUtility.getList("gender", DataUtility.getStringData(bean.getGender()), map) %>
									</div>

									<div class="text-danger small mt-1">
										<%=ServletUtility.getErrorMessage("gender", request)%>
									</div>

								</div>


								<!-- DOB -->
								<div class="col-md-6 mb-3">

									<label class="form-label fw-bold"> <i
										class="bi bi-calendar-date-fill text-primary"></i> Date of
										Birth <span class="text-danger">*</span>
									</label>

									<div class="input-group">

										<span class="input-group-text bg-light"> <i
											class="bi bi-calendar-date"></i>
										</span> <input type="date" name="dob" class="form-control">

									</div>

									<div class="text-danger small mt-1">
										<%=ServletUtility.getErrorMessage("dob", request)%>
									</div>

								</div>

							</div>


							<!-- Signup Button -->
							<div class="d-grid mt-2">

								<button type="submit" name="operation"
									value="<%=UserRegistrationCtl.OP_SIGN_UP%>"
									class="btn btn-primary btn-lg">

									<i class="bi bi-person-plus-fill"></i> &nbsp; Sign Up

								</button>

							</div>
						


						</div>


					</div>


					<!-- Card Footer -->
					<div class="card-footer bg-light text-center">

						<span class="text-muted"> Already have an account? </span> <a
							href="<%=ORSView.LOGIN_CTL%>"
							class="text-decoration-none fw-bold"> Login </a>

					</div>


				</div>

			</div>

		</div>


	</form>


	<%@include file="Footer.jsp"%>


	<!-- Password Show / Hide -->
	<script>
		function togglePassword(fieldId, iconId) {

			var password = document.getElementById(fieldId);

			var eyeIcon = document.getElementById(iconId);

			if (password.type === "password") {

				password.type = "text";

				eyeIcon.classList.remove("bi-eye");

				eyeIcon.classList.add("bi-eye-slash");

			} else {

				password.type = "password";

				eyeIcon.classList.remove("bi-eye-slash");

				eyeIcon.classList.add("bi-eye");

			}

		}
	</script>

</body>

</html>
