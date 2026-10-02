<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>500 - Internal Server Error</title>
</head>

<body class="bg-light">

	<%@ include file="Header.jsp"%>

	<div class="container text-center py-5">

		<div class="card shadow-lg border-0 rounded-4 p-5">

			<i class="bi bi-exclamation-triangle-fill text-primary"
				style="font-size: 80px;"></i>

			<h1 class="display-4 fw-bold text-primary mt-3">
				500
			</h1>

			<h3>Internal Server Error</h3>

			<p class="text-muted">
				Sorry, something went wrong on the server.
				Please try again later.
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