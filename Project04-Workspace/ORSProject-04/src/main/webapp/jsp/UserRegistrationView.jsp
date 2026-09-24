<%@ page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@ page import="in.co.rays.proj4.util.DataUtility"%>
<%@ page import="in.co.rays.proj4.controller.UserRegistrationCtl"%>
<%@ page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page import="in.co.rays.proj4.util.MessageSource" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<title>SignUp-ORS</title>

</head>

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

map.put("male", ms.get("gender.male"));

map.put("female", ms.get("gender.female"));

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

						<h3 class="text-center fw-bold text-primary mb-1">
							<%=ms.get("signup.create")%>
						</h3>

						<p class="text-center text-muted mb-4">
							<%=ms.get("signup.description")%>
						</p>


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

								<label class="form-label fw-bold">

									<i class="bi bi-person-fill text-primary"></i>

									<%=ms.get("signup.firstName")%>

									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-light">

										<i class="bi bi-person"></i>

									</span>

									<input type="text" name="firstName"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getFirstName()) %>"
										placeholder="<%=ms.get("signup.firstName.placeholder")%>">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("firstName", request)%>

								</div>

							</div>


							<!-- Last Name -->

							<div class="col-md-6 mb-3">

								<label class="form-label fw-bold">

									<i class="bi bi-person-fill text-primary"></i>

									<%=ms.get("signup.lastName")%>

									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-light">

										<i class="bi bi-person"></i>

									</span>

									<input type="text" name="lastName"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getLastName()) %>"
										placeholder="<%=ms.get("signup.lastName.placeholder")%>">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("lastName", request)%>

								</div>

							</div>

						</div>


						<!-- Login -->

						<div class="mb-3">

							<label class="form-label fw-bold">

								<i class="bi bi-envelope-fill text-primary"></i>

								<%=ms.get("signup.login")%>

								<span class="text-danger">*</span>

							</label>

							<div class="input-group">

								<span class="input-group-text bg-light">

									<i class="bi bi-envelope"></i>

								</span>

								<input type="text" name="login"
									class="form-control"
									value="<%=DataUtility.getStringData(bean.getLogin()) %>"
									placeholder="<%=ms.get("signup.login.placeholder")%>">

							</div>

							<div class="text-danger small mt-1">

								<%=ServletUtility.getErrorMessage("login", request)%>

							</div>

						</div>


						<!-- Password + Confirm Password -->

						<div class="row">

							<!-- Password -->

							<div class="col-md-6 mb-3">

								<label class="form-label fw-bold">

									<i class="bi bi-lock-fill text-primary"></i>

									<%=ms.get("signup.password")%>

									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-light">

										<i class="bi bi-lock"></i>

									</span>

									<input type="password" name="password"
										id="password"
										value="<%=DataUtility.getStringData(bean.getPassword()) %>"
										class="form-control"
										placeholder="<%=ms.get("signup.password.placeholder")%>">

									<button type="button"
										class="btn btn-outline-secondary"
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

								<label class="form-label fw-bold">

									<i class="bi bi-shield-lock-fill text-primary"></i>

									<%=ms.get("signup.confirmPassword")%>

									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-light">

										<i class="bi bi-shield-lock"></i>

									</span>

									<input type="password" name="confirmPassword"
										value="<%=DataUtility.getStringData(bean.getPassword()) %>"
										id="confirmPassword"
										class="form-control"
										placeholder="<%=ms.get("signup.confirmPassword.placeholder")%>">

									<button type="button"
										class="btn btn-outline-secondary"
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

								<label class="form-label fw-bold">

									<i class="bi bi-gender-ambiguous text-primary"></i>

									<%=ms.get("signup.gender")%>

									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-light">

										<i class="bi bi-person-vcard"></i>

									</span>

									<%=HTMLUtility.getList("gender",
											DataUtility.getStringData(bean.getGender()), map) %>

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("gender", request)%>

								</div>

							</div>


							<!-- DOB -->

							<div class="col-md-6 mb-3">

								<label class="form-label fw-bold">

									<i class="bi bi-calendar-date-fill text-primary"></i>

									<%=ms.get("signup.dob")%>

									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-light">

										<i class="bi bi-calendar-date"></i>

									</span>

									<input type="date" name="dob"
										class="form-control">

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

								<i class="bi bi-person-plus-fill"></i> &nbsp;

								<%=ms.get("signup.button")%>

							</button>

						</div>


					</div>

				</div>


				<!-- Card Footer -->

				<div class="card-footer bg-light text-center">

					<span class="text-muted">
						<%=ms.get("signup.alreadyAccount")%>
					</span>

					<a href="<%=ORSView.LOGIN_CTL%>"
						class="text-decoration-none fw-bold">

						<%=ms.get("signup.loginLink")%>

					</a>

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