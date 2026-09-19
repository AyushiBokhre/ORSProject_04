<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.LoginCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Login - ORS</title>

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

	<%@ include file="Header.jsp"%>

	<%
	String _suc = ServletUtility.getSuccessMessage(request);
	String _err = ServletUtility.getErrorMessage(request);
	%>
	
	<jsp:useBean id="bean"
		class="in.co.rays.proj4.bean.UserBean"
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
									class="bi bi-mortarboard-fill
									text-primary display-4"></i>

							</div>

							<!-- Heading -->
							<h3 class="text-center fw-bold text-primary mb-1">Welcome
								Back!</h3>

							<p class="text-center text-muted mb-4">Login to your ORS
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


							<!-- Login -->
							<div class="mb-3">

								<label class="form-label fw-bold"> <i
									class="bi bi-person-fill text-primary"></i> <%=ms.get("login.userid")%>

									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-light"> <i
										class="bi bi-person"></i>
									</span> <input type="text" name="login" class="form-control" value="<%=DataUtility.getStringData(bean.getLogin())%>"
										placeholder="Enter your login">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("login", request)%>

								</div>

							</div>


							<!-- Password -->
							<div class="mb-3">

								<label class="form-label fw-bold"> <i
									class="bi bi-lock-fill text-primary"></i> <%=ms.get("login.password")%>

									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-light"> <i
										class="bi bi-lock"></i>
									</span> <input type="password" name="password"  value="<%=DataUtility.getStringData(bean.getPassword())%>"
										class="form-control" placeholder="Enter your password">

									<button type="button" class="btn btn-outline-secondary"
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
									value="<%=LoginCtl.OP_SIGN_IN%>" class="btn btn-primary btn-lg">

									<i class="bi bi-box-arrow-in-right"></i> &nbsp; Login

								</button>

							</div>

							<!-- Forgot Password Link -->
							<div class="text-center mt-3">

								<a href="<%=ORSView.FORGET_PASSWORD_CTL%>"
									class="text-decoration-none text-primary">
									<i class="bi bi-key-fill"></i> Forgot Password?

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