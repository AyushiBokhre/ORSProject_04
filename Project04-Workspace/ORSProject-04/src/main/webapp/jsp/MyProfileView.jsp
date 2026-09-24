<%@page import="in.co.rays.proj4.bean.RoleBean"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.controller.UserCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User</title>
<!-- Bootstrap CSS/JS already loaded via Header.jsp, isliye yahan dobara nahi liya -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body class="bg-light">

	<%@ include file="Header.jsp"%>

	<%
	String _suc = ServletUtility.getSuccessMessage(request);
	String _err = ServletUtility.getErrorMessage(request);
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("male", "male");
	map.put("female", "female");
	%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.UserBean"
		scope="request"></jsp:useBean>

	<!-- FIX: extra bottom padding taaki fixed/sticky Footer Save button ko
	     overlap na kare -->
	<div class="container pt-5" style="padding-bottom: 100px !important;">
		<div class="row justify-content-center">
			<div class="col-md-10 col-lg-8">

				<div class="card shadow-lg border-0">

					<div class="card-header bg-white text-center py-3">
						<h3 class="mb-1 text-primary">
							<i class="bi bi-person-vcard-fill"></i> My Profile
						</h3>
						<small class="text-muted">Manage ORS user accounts</small>
					</div>

					<div class="card-body p-4">

						<%
						if (_suc != null && _suc.length() > 0) {
						%>
						<div class="alert alert-success">
							<i class="bi bi-check-circle-fill"></i>
							<%=_suc%></div>
						<%
						}
						if (_err != null && _err.length() > 0) {
						%>
						<div class="alert alert-danger">
							<i class="bi bi-exclamation-triangle-fill"></i>
							<%=_err%></div>
						<%
						}
						%>

						<%-- 	<%
						if (bean.getId() > 0) {
						%>
						<div class="text-center mb-4 pb-3 border-bottom">
							<form action="<%=ORSView.UPLOAD_PHOTO_CTL%>" method="POST"
								enctype="multipart/form-data">
								<input type="hidden" name="id" value="<%=bean.getId()%>">

								<img src="<%=ORSView.UPLOAD_PHOTO_CTL%>?id=<%=bean.getId()%>"
									onerror="this.style.display='none';" alt="User Photo"
									width="90" height="90"
									class="rounded-circle border mb-2"
									style="object-fit: cover;"><br>

								<input type="file" name="photo" accept="image/*"
									class="form-control form-control-sm d-inline-block w-auto mb-2">
								<br>
								<input type="submit" value="Upload Photo"
									class="btn btn-sm btn-outline-primary">
							</form>
						</div>
						<%
						}
						%> --%>

						<form action="<%=ORSView.MY_PROFILE_CTL%>" method="post">

							<input type="hidden" name="id"
								value="<%=DataUtility.getStringData(bean.getId())%>"> <input
								type="hidden" name="roleId"
								value="<%=DataUtility.getStringData(bean.getRoleId())%>">

							<div class="row">
								<div class="col-md-6 mb-3">
									<label class="form-label fw-bold"><i
										class="bi bi-person-fill text-primary"></i> FirstName <span
										class="text-danger">*</span></label> <input type="text"
										name="firstName" class="form-control"
										value="<%=DataUtility.getStringData(bean.getFirstName())%>"
										placeholder="enter your firstName"> <small
										class="text-danger"><%=ServletUtility.getErrorMessage("firstName", request)%></small>
								</div>
								<div class="col-md-6 mb-3">
									<label class="form-label fw-bold"><i
										class="bi bi-person-fill text-success"></i> LastName <span
										class="text-danger">*</span></label> <input type="text"
										name="lastName" class="form-control"
										value="<%=DataUtility.getStringData(bean.getLastName())%>"
										placeholder="enter your lastName"> <small
										class="text-danger"><%=ServletUtility.getErrorMessage("lastName", request)%></small>
								</div>
							</div>

							<div class="mb-3">
								<label class="form-label fw-bold"><i
									class="bi bi-person-circle text-info"></i> Login <span
									class="text-danger">*</span></label> <input type="text" name="login"
									class="form-control"
									value="<%=DataUtility.getStringData(bean.getLogin())%>"
									placeholder="enter an email"> <small
									class="text-danger"><%=ServletUtility.getErrorMessage("login", request)%></small>
							</div>

							<div class="row">
								<div class="col-md-6 mb-3">
									<label class="form-label fw-bold"><i
										class="bi bi-lock-fill text-warning"></i> Password <span
										class="text-danger">*</span></label> <input type="password"
										name="password" class="form-control"
										value="<%=DataUtility.getStringData(bean.getPassword())%>"
										placeholder="enter a password"> <small
										class="text-danger"><%=ServletUtility.getErrorMessage("password", request)%></small>
								</div>

								<div class="col-md-6 mb-3">
									<label class="form-label fw-bold"><i
										class="bi bi-gender-ambiguous text-success"></i> Gender <span
										class="text-danger">*</span></label>
									<%
									String genderHtml = HTMLUtility.getList("gender", DataUtility.getStringData(bean.getGender()), map);
									genderHtml = genderHtml.replaceFirst("<select", "<select class=\"form-control\"");
									%>
									<%=genderHtml%>
									<small class="text-danger"><%=ServletUtility.getErrorMessage("gender", request)%></small>
								</div>
							</div>



							<div class="mb-4">
								<label class="form-label fw-bold"><i
									class="bi bi-calendar-event-fill text-primary"></i> DOB <span
									class="text-danger">*</span></label> <input type="date" name="dob"
									class="form-control"
									value="<%=DataUtility.getStringData(bean.getDob())%>">
								<small class="text-danger"><%=ServletUtility.getErrorMessage("dob", request)%></small>
							</div>

							<div class="text-center">
								<input type="submit" name="operation"
									class="btn btn-primary px-4" value="<%=UserCtl.OP_SAVE%>">
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="Footer.jsp"%>
</body>
</html>