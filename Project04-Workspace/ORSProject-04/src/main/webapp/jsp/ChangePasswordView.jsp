<%@page import="in.co.rays.proj4.util.MessageSource"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Change Password - ORS</title>

<!-- Bootstrap 5 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Bootstrap Icons -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
	rel="stylesheet">

</head>

<body class="bg-light">

	<%@include file="Header.jsp"%>

	<%
		String _suc = ServletUtility.getSuccessMessage(request);
		String _err = ServletUtility.getErrorMessage(request);
	%>

	<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">

		<div class="container py-5">

			<div class="row justify-content-center">

				<div class="col-lg-5 col-md-7 col-sm-10">

					<!-- Change Password Card -->
					<div class="card shadow-lg border-0 rounded-4">

						<div class="card-body p-4">

							<!-- Icon -->
							<div class="text-center mb-3">

								<i class="bi bi-shield-lock-fill text-primary display-4"></i>

							</div>

							<!-- Heading -->
							<h3 class="text-center fw-bold text-primary mb-1">

								Change Password

							</h3>

							<p class="text-center text-muted mb-4">

								Update your account password

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


							<!-- Login ID -->
							<div class="mb-3">

								<label class="form-label fw-bold">

									<i class="bi bi-person-fill text-primary"></i>

									Login ID

									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-light">

										<i class="bi bi-person"></i>

									</span>

									<input

										type="text"

										name="login"

										class="form-control"

										placeholder="Enter your login ID"

										value="<%=ServletUtility.getParameter("login", request)%>">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("login", request)%>

								</div>

							</div>


							<!-- Old Password -->
							<div class="mb-3">

								<label class="form-label fw-bold">

									<i class="bi bi-lock-fill text-primary"></i>

									Old Password

									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-light">

										<i class="bi bi-lock"></i>

									</span>

									<input

										type="password"

										name="oldPassword"

										id="oldPassword"

										class="form-control"

										placeholder="Enter old password">

									<button

										type="button"

										class="btn btn-outline-secondary"

										onclick="togglePassword('oldPassword', 'oldEyeIcon')">

										<i class="bi bi-eye" id="oldEyeIcon"></i>

									</button>

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("oldPassword", request)%>

								</div>

							</div>


							<!-- New Password -->
							<div class="mb-3">

								<label class="form-label fw-bold">

									<i class="bi bi-key-fill text-primary"></i>

									New Password

									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-light">

										<i class="bi bi-key"></i>

									</span>

									<input

										type="password"

										name="newPassword"

										id="newPassword"

										class="form-control"

										placeholder="Enter new password">

									<button

										type="button"

										class="btn btn-outline-secondary"

										onclick="togglePassword('newPassword', 'newEyeIcon')">

										<i class="bi bi-eye" id="newEyeIcon"></i>

									</button>

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("newPassword", request)%>

								</div>

							</div>


							<!-- Change Password Button -->
							<div class="d-grid mt-4">

								<button

									type="submit"

									name="operation"

									value="<%=BaseCtl.OP_GO%>"

									class="btn btn-primary btn-lg">

									<i class="bi bi-shield-check"></i>

									&nbsp; Change Password

								</button>

							</div>


							<!-- Back to Login -->
							<div class="text-center mt-3">

								<a

									href="<%=ORSView.LOGIN_CTL%>"

									class="text-decoration-none">

									<i class="bi bi-arrow-left"></i>

									Back to Login

								</a>

							</div>

						</div>

					</div>

				</div>

			</div>

		</div>

	</form>


	<%@include file="Footer.jsp"%>


	<!-- Password Show / Hide -->
	<script>

		function togglePassword(passwordId, eyeIconId) {

			var password = document.getElementById(passwordId);

			var eyeIcon = document.getElementById(eyeIconId);

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