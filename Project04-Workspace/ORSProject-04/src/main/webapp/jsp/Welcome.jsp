
<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Welcome Page</title>

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

	<%@ include file="Header.jsp"%>

	<!-- Welcome Section -->
	<div class="container py-5">
		<div class="row justify-content-center">
			<div class="col-lg-10">

				<div class="bg-white rounded-4 shadow-lg p-5 text-center">

					<!-- Icon -->
					<div class="mb-4">
						<i class="bi bi-mortarboard-fill text-primary"
							style="font-size: 70px;"></i>
					</div>

					<!-- Heading -->
					<h1 class="display-4 fw-bold text-dark mb-3">
						Welcome to ORS

						<%
					if (isLogin) {
					%>

						<div class="mt-2">
							<span class="text-primary fs-4"> Hello <%=userBean.getFirstName()%>
							</span>
						</div>

						<%
						}
						%>

					</h1>

					<!-- Description -->
					<p class="text-secondary mb-4">Manage students, colleges,
						marksheets and academic information easily in one place.</p>

					<%
					if (!isLogin) {
					%>

					<!-- Guest Buttons -->
					<div class="d-flex justify-content-center gap-3">

						<a href="<%=ORSView.LOGIN_CTL%>"
							class="btn btn-primary btn-lg px-4"> <i
							class="bi bi-box-arrow-in-right me-2"></i> Login

						</a> <a href="<%=ORSView.USER_REGISTRATION_CTL%>"
							class="btn btn-outline-primary btn-lg px-4"> <i
							class="bi bi-person-plus me-2"></i> Sign Up

						</a>

					</div>

					<%
					} else {
					%>

					<!-- Logged-in Message -->
					<div class="alert alert-success mt-4 mb-0">

						<i class="bi bi-check-circle-fill me-2"></i> You are successfully
						logged in.

					</div>

					<%
					}
					%>

				</div>
			</div>
		</div>

		<!-- Features -->
		<div class="row g-4 mt-4">

			<div class="col-md-4">
				<div class="card h-100 border-0 shadow-sm rounded-4 text-center p-4">

					<i class="bi bi-people-fill text-primary" style="font-size: 45px;"></i>

					<h4 class="fw-bold mt-3">Students</h4>

					<p class="text-muted">Manage student information and academic
						records easily.</p>

				</div>
			</div>

			<div class="col-md-4">
				<div class="card h-100 border-0 shadow-sm rounded-4 text-center p-4">

					<i class="bi bi-file-earmark-bar-graph-fill text-success"
						style="font-size: 45px;"></i>

					<h4 class="fw-bold mt-3">Marksheets</h4>

					<p class="text-muted">Add, search and manage student marksheet
						records.</p>

				</div>
			</div>

			<div class="col-md-4">
				<div class="card h-100 border-0 shadow-sm rounded-4 text-center p-4">

					<i class="bi bi-building-fill text-warning"
						style="font-size: 45px;"></i>

					<h4 class="fw-bold mt-3">Colleges</h4>

					<p class="text-muted">Manage college information and academic
						details.</p>

				</div>
			</div>

		</div>
	</div>

	<%@ include file="Footer.jsp"%>

</body>
</html>
