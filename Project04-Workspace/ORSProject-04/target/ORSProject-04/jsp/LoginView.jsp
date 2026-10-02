<%@ page import="in.co.rays.proj4.util.DataUtility"%>
<%@ page import="in.co.rays.proj4.controller.LoginCtl"%>
<%@ page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page import="in.co.rays.proj4.util.MessageSource" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<title>Login-ORS</title>

</head>

<!-- Light Background -->

<body class="bg-light">

	<%@ include file="Header.jsp"%>

	<%

	String _suc = ServletUtility.getSuccessMessage(request);

	String _err = ServletUtility.getErrorMessage(request);
	

	%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.UserBean"
		scope="request">

	</jsp:useBean>

	<form action="<%=ORSView.LOGIN_CTL%>" method="post">

		<div class="container py-5">

			<div class="row justify-content-center">

				<div class="col-lg-5 col-md-7 col-sm-10">

					<!-- Login Card -->

					<div class="card shadow-lg border-0 rounded-4">

						<div class="card-body p-4">

							<!-- ORS Icon -->

							<div class="text-center mb-3">

								<i
									class="bi bi-mortarboard-fill text-primary display-4"></i>

							</div>

							<!-- Heading -->

							<h3 class="text-center fw-bold text-primary mb-1">
								<%=ms.get("login.welcome")%>
							</h3>

							<p class="text-center text-muted mb-4">
								<%=ms.get("login.description")%>
							</p>


							<!-- Success Message -->

							<%

							if (_suc != null && !_suc.isEmpty()) {

							%>

							<div
								class="alert alert-success alert-dismissible fade show d-flex align-items-center">

								<i class="bi bi-check-circle-fill"></i>

								<%=_suc%>

								<button type="button" class="btn-close"
									data-bs-dismiss="alert"
									aria-label="<%=ms.get("common.close")%>"></button>

							</div>

							<%

							}

							%>


							<!-- Error Message -->

							<%

							if (_err != null && !_err.isEmpty()) {

							%>

							<div
								class="alert alert-danger alert-dismissible fade show d-flex align-items-center"
								role="alert">

								<i class="bi bi-x-circle-fill me-2"></i>

								<div>

									<%=_err%>

								</div>

								<button type="button" class="btn-close"
									data-bs-dismiss="alert"
									aria-label="<%=ms.get("common.close")%>"></button>

							</div>

							<%

							}

							%>


							<!-- Login -->

							<div class="mb-3">

								<label class="form-label fw-bold">

									<i class="bi bi-person-fill text-primary"></i>

									<%=ms.get("login.userid")%>

									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-light">

										<i class="bi bi-person"></i>

									</span>

									<input type="text" name="login"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getLogin())%>"
										placeholder="<%=ms.get("login.userid.placeholder")%>">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("login", request)%>

								</div>

							</div>


							<!-- Password -->

							<div class="mb-3">

								<label class="form-label fw-bold">

									<i class="bi bi-lock-fill text-primary"></i>

									<%=ms.get("login.password")%>

									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-light">

										<i class="bi bi-lock"></i>

									</span>

									<input type="password" name="password"
										value="<%=DataUtility.getStringData(bean.getPassword())%>"
										class="form-control"
										placeholder="<%=ms.get("login.password.placeholder")%>">

									<button type="button"
										class="btn btn-outline-secondary"
										onclick="togglePassword()">

										<i class="bi bi-eye" id="eyeIcon"></i>

									</button>

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("password", request)%>

								</div>

							</div>


							<!-- Login Button -->

							<div class="d-grid mt-4">

								<button type="submit" name="operation"
									value="<%=LoginCtl.OP_SIGN_IN%>"
									class="btn btn-primary btn-lg">

									<i class="bi bi-box-arrow-in-right"></i> &nbsp;

									<%=ms.get("login.button")%>

								</button>

							</div>


							<!-- Forgot Password Link -->

							<div class="text-center mt-3">

								<a href="<%=ORSView.FORGET_PASSWORD_CTL%>"
									class="text-decoration-none text-primary">

									<i class="bi bi-key-fill"></i>

									<%=ms.get("login.forgot")%>

								</a>

							</div>


						</div>

					</div>

				</div>

			</div>

		</div>

	</form>


	<%@ include file="Footer.jsp"%>


	<!-- Password Show / Hide -->

	<script>

	function togglePassword() {

		var password = document.getElementById("password");

		var eyeIcon = document.getElementById("eyeIcon");

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