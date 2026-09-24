<%@ page import="in.co.rays.proj4.bean.UserBean"%>
<%@ page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page import="in.co.rays.proj4.util.MessageSource" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<title>Welcome-ORS</title>

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

						<%=ms.get("welcome.title")%>

						<%
						if (isLogin) {
						%>

						<div class="mt-2">

							<span class="text-primary fs-4">
								<%=ms.get("welcome.hello")%> <%=userBean.getFirstName()%> !
							</span>

						</div>

						<%
						} else {
						%>

						<div class="mt-2">

							<span class="text-primary fs-4">
								<%=ms.get("welcome.guest")%>
							</span>

						</div>

						<%
						}
						%>

					</h1>


					<!-- Description -->

					<p class="text-secondary mb-4">
						<%=ms.get("welcome.description")%>
					</p>


					<%
					if (!isLogin) {
					%>

					<!-- Guest Buttons -->

					<div class="d-flex justify-content-center gap-3">

						<a href="<%=ORSView.LOGIN_CTL%>"
							class="btn btn-primary btn-lg px-4">

							<i class="bi bi-box-arrow-in-right me-2"></i>
							<%=ms.get("login.button")%>

						</a>

						<a href="<%=ORSView.USER_REGISTRATION_CTL%>"
							class="btn btn-outline-primary btn-lg px-4">

							<i class="bi bi-person-plus me-2"></i>
							<%=ms.get("signup.button")%>

						</a>

					</div>

					<%
					} else {
					%>

					<!-- Logged-in Message -->

					<div class="alert alert-success mt-4 mb-0">

						<i class="bi bi-check-circle-fill me-2"></i>
						<%=ms.get("welcome.login.success")%>

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

				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-4">

					<i class="bi bi-people-fill text-primary"
						style="font-size: 45px;"></i>

					<h4 class="fw-bold mt-3">
						<%=ms.get("welcome.students")%>
					</h4>

					<p class="text-muted">
						<%=ms.get("welcome.students.description")%>
					</p>

				</div>

			</div>


			<div class="col-md-4">

				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-4">

					<i class="bi bi-file-earmark-bar-graph-fill text-success"
						style="font-size: 45px;"></i>

					<h4 class="fw-bold mt-3">
						<%=ms.get("welcome.marksheets")%>
					</h4>

					<p class="text-muted">
						<%=ms.get("welcome.marksheets.description")%>
					</p>

				</div>

			</div>


			<div class="col-md-4">

				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-4">

					<i class="bi bi-building-fill text-warning"
						style="font-size: 45px;"></i>

					<h4 class="fw-bold mt-3">
						<%=ms.get("welcome.colleges")%>
					</h4>

					<p class="text-muted">
						<%=ms.get("welcome.colleges.description")%>
					</p>

				</div>

			</div>

		</div>

	</div>

	<%@ include file="Footer.jsp"%>

</body>
</html>