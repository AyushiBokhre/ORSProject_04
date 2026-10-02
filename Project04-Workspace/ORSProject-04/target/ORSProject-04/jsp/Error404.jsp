<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>404 - Page Not Found</title>
</head>

<body class="bg-light">

	<%@ include file="Header.jsp"%>

	<div class="container text-center py-5">

		<div class="card shadow-lg border-0 rounded-4 p-5">

			<i class="bi bi-exclamation-circle text-primary"
				style="font-size: 80px;"></i>

			<h1 class="display-4 fw-bold text-primary mt-3">
				404
			</h1>

			<h3>Page Not Found</h3>

			<p class="text-muted">
				Sorry, the page you are looking for does not exist.
			</p>

			<a href="<%=request.getContextPath()%>/WelcomeCtl"
				class="btn btn-primary">

				<i class="bi bi-house-fill"></i>
				&nbsp; Back to Home

			</a>

		</div>

	</div>

	<%@ include file="Footer.jsp"%>

</body>
</html>