<%@page import="in.co.rays.proj4.bean.GymMemberBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.controller.UserCtl"%>
<%@page import="in.co.rays.proj4.controller.LoginCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@ page import="java.util.HashMap"%>

<!DOCTYPE html>
<html>

<head>

<title>Gym Member</title>

</head>

<body class="bg-light">

	<%@include file="Header.jsp"%>

	<%
	String _suc = ServletUtility.getSuccessMessage(request);
	String _err = ServletUtility.getErrorMessage(request);


	%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.GymMemberBean"
		scope="request">
	</jsp:useBean>


	<form action="<%=ORSView.GYM_MEMBER_CTL%>" method="post">

		<div class="container py-4">

			<div class="card border-0 shadow-lg rounded-4 mx-auto"
				style="max-width: 850px;">

				<div class="card-body p-4">


					<!-- Heading -->

					<div class="text-center mb-4">

						<div class="mb-2">

							<i class="bi bi-mortarboard-fill text-primary"
								style="font-size: 42px;"></i>

						</div>

						<h3 class="fw-bold text-dark mb-0">

							<%=bean != null && bean.getId() > 0 ? "Update Gym Member" : "Add Gym Member"%>

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


					<!-- Gym member Form -->

					<input type="hidden" name="id"
						value="<%=DataUtility.getStringData(bean.getId())%>">


					<div class="bg-light rounded-4 p-3">

						<div class="row g-3">


							<!-- name -->

							<div class="col-md-6">

								<label class="form-label fw-semibold"> Name<span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-person text-primary"></i>

									</span> <input type="text" name="name" class="form-control"
										value="<%=DataUtility.getStringData(bean.getName())%>"
										placeholder="Enter name">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("name", request)%>

								</div>

							</div>


							<!-- membership Type -->

							<div class="col-md-6">

								<label class="form-label fw-semibold">Membership Type <span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-person text-primary"></i>

									</span> <input type="text" name="membershipType" class="form-control"
										value="<%=DataUtility.getStringData(bean.getMembershipType())%>"
										placeholder="Enter membership Type">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("membershipType", request)%>

								</div>

							</div>


							<!-- joining Date -->

							<div class="col-md-6">

								<label class="form-label fw-semibold">Joining Date <span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-envelope text-primary"></i>

									</span> <input type="text" name="joiningDate" class="form-control"
										value="<%=DataUtility.getStringData(bean.getJoiningDate())%>"
										placeholder="Enter joiningDate">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("joiningDate", request)%>

								</div>

							</div>

							<!-- trainer Name -->
							<div class="col-md-6">

								<label class="form-label fw-semibold">Trainer Name<span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-envelope text-primary"></i>

									</span> <input type="text" name="trainerName" class="form-control"
										value="<%=DataUtility.getStringData(bean.getTrainerName())%>"
										placeholder="Enter trainerName">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("trainerName", request)%>

								</div>

							</div>



							<!-- Save Button -->

							<div class="col-12 text-center mt-3">

								<input type="submit" name="operation"
									value="<%=bean != null && bean.getId() > 0 ? "Update" : BaseCtl.OP_SAVE%>"
									class="btn btn-primary btn-sm px-4">

							</div>


						</div>

					</div>

				</div>

			</div>

		</div>

	</form>


	<%@include file="Footer.jsp"%>

</body>

</html>