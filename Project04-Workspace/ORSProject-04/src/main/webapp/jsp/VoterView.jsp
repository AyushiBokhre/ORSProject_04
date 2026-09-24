<%@page import="in.co.rays.proj4.bean.VoterBean"%>
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

<title>Voter</title>

</head>

<body class="bg-light">

	<%@include file="Header.jsp"%>

	<%
	String _suc = ServletUtility.getSuccessMessage(request);
	String _err = ServletUtility.getErrorMessage(request);
	HashMap<String, String> constituencyMap = new HashMap<String, String>();
	constituencyMap.put("Indore-1", "Indore-1");
	constituencyMap.put("Indore-2", "Indore-2");
	constituencyMap.put("Indore-3", "Indore-3");
	constituencyMap.put("Indore-4", "Indore-4");
	constituencyMap.put("Indore-5", "Indore-5");
	constituencyMap.put("Rau", "Rau");
	constituencyMap.put("Depalpur", "Depalpur");
	
	HashMap<String, String> hasVotedMap = new HashMap<String, String>();
	hasVotedMap.put("true", "Yes");
	hasVotedMap.put("false", "No");
	%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.VoterBean"
		scope="request">
	</jsp:useBean>


	<form action="<%=ORSView.VOTER_CTL%>" method="post">

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

							<%=bean != null && bean.getId() > 0 ? "Update Voter" : "Add Voter"%>

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


					<!-- Voter Form -->

					<input type="hidden" name="id"
						value="<%=DataUtility.getStringData(bean.getId())%>">


					<div class="bg-light rounded-4 p-3">

						<div class="row g-3">


							<!-- Voter Id -->

							<div class="col-md-6">

								<label class="form-label fw-semibold"> Voter ID <span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-person text-primary"></i>

									</span> <input type="text" name="voterId" class="form-control"
										value="<%=DataUtility.getStringData(bean.getVoterId())%>"
										placeholder="Enter Voter ID">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("voterId", request)%>

								</div>

							</div>


							<!-- Voter Name -->

							<div class="col-md-6">

								<label class="form-label fw-semibold"> Name <span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-person text-primary"></i>

									</span> <input type="text" name="name" class="form-control"
										value="<%=DataUtility.getStringData(bean.getName())%>"
										placeholder="Enter Voter name">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("name", request)%>

								</div>

							</div>


							<!-- Age -->

							<div class="col-md-6">

								<label class="form-label fw-semibold"> Age <span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-envelope text-primary"></i>

									</span> <input type="number" name="age" class="form-control"
										value="<%=DataUtility.getStringData(bean.getAge())%>"
										placeholder="Enter age">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("age", request)%>

								</div>

							</div>


							<!-- Constituency -->

							<div class="col-md-6">

								<label class="form-label fw-semibold"> Constituency <span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-building text-primary"></i>

									</span>

									<%=HTMLUtility.getList("constituency", DataUtility.getStringData(bean.getConstituency()), constituencyMap)%>

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("constituency", request)%>

								</div>

							</div>


							<!-- Has Voted -->
							<div class="col-md-6">

								<label class="form-label fw-semibold"> Has Voted<span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-building text-primary"></i>

									</span>

									<%=HTMLUtility.getList("hasVoted", DataUtility.getStringData(bean.isHasVoted()), hasVotedMap)%>

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("hasVoted", request)%>

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