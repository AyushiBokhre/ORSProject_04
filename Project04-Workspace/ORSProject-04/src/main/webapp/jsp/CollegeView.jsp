
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>College</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

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

	<jsp:useBean id="bean"
		class="in.co.rays.proj4.bean.CollegeBean"
		scope="request">
	</jsp:useBean>


	<div class="container py-4">

		<div class="card border-0 shadow-lg rounded-4 mx-auto"
			style="max-width: 850px;">

			<div class="card-body p-4">

				<!-- Heading -->

				<div class="text-center mb-4">

					<div class="mb-2">

						<i class="bi bi-building text-primary"
							style="font-size: 42px;"></i>

					</div>

					<h3 class="fw-bold text-dark mb-0">

						<%=bean != null && bean.getId() > 0
								? "Update College"
								: "Add College"%>

					</h3>

				</div>


				<!-- Success Message -->

				<%
				if (_suc != null && !_suc.trim().isEmpty()) {
				%>

				<div class="alert alert-success text-center py-2">

					<i class="bi bi-check-circle me-1"></i>

					<%=_suc%>

				</div>

				<%
				}
				%>


				<!-- Error Message -->

				<%
				if (_err != null && !_err.trim().isEmpty()) {
				%>

				<div class="alert alert-danger text-center py-2">

					<i class="bi bi-exclamation-circle me-1"></i>

					<%=_err%>

				</div>

				<%
				}
				%>


				<!-- College Form -->

				<form action="<%=ORSView.COLLEGE_CTL%>" method="post">

					<input type="hidden"
						name="id"
						value="<%=DataUtility.getStringData(bean.getId())%>">


					<div class="bg-light rounded-4 p-3">

						<div class="row g-3">


							<!-- College Name -->

							<div class="col-md-6">

								<label class="form-label fw-semibold">

									College Name
									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">

										<i class="bi bi-building text-primary"></i>

									</span>

									<input type="text"
										name="name"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getName())%>"
										placeholder="Enter college name">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("name", request)%>

								</div>

							</div>


							<!-- Phone -->

							<div class="col-md-6">

								<label class="form-label fw-semibold">

									Phone No
									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">

										<i class="bi bi-telephone text-primary"></i>

									</span>

									<input type="text"
										name="phoneNo"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getPhoneNo())%>"
										placeholder="Enter phone number">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("phoneNo", request)%>

								</div>

							</div>


							<!-- Address -->

							<div class="col-md-12">

								<label class="form-label fw-semibold">

									Address
									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">

										<i class="bi bi-geo-alt text-primary"></i>

									</span>

									<input type="text"
										name="address"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getAddress())%>"
										placeholder="Enter address">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("address", request)%>

								</div>

							</div>


							<!-- State -->

							<div class="col-md-6">

								<label class="form-label fw-semibold">

									State
									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">

										<i class="bi bi-map text-primary"></i>

									</span>

									<input type="text"
										name="state"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getState())%>"
										placeholder="Enter state">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("state", request)%>

								</div>

							</div>


							<!-- City -->

							<div class="col-md-6">

								<label class="form-label fw-semibold">

									City
									<span class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white">

										<i class="bi bi-pin-map text-primary"></i>

									</span>

									<input type="text"
										name="city"
										class="form-control"
										value="<%=DataUtility.getStringData(bean.getCity())%>"
										placeholder="Enter city">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("city", request)%>

								</div>

							</div>


							<!-- Save Button -->

							<div class="col-12 text-center mt-3">

								<input
									type="submit"
									name="operation"
									value="<%=bean != null && bean.getId() > 0
											? "Update"
											: BaseCtl.OP_SAVE%>"
									class="btn btn-primary btn-sm px-4">

							</div>


						</div>

					</div>

				</form>

			</div>

		</div>

	</div>


	<%@include file="Footer.jsp"%>


</body>

</html>
