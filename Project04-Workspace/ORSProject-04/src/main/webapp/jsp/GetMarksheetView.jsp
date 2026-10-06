<%@page import="in.co.rays.proj4.bean.MarksheetBean"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>

<!DOCTYPE html>
<html>

<head>

<title>Get Marksheet</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
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

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.MarksheetBean"
		scope="request">
	</jsp:useBean>


	<div class="container py-4  pb-5 mb-5">

		<div class="card border-0 shadow-lg rounded-4 mx-auto"
			style="max-width: 850px;">

			<div class="card-body p-4">


				<!-- Heading -->

				<div class="text-center mb-4">

					<div class="mb-2">

						<i class="bi bi-journal-check text-primary"
							style="font-size: 42px;"></i>

					</div>

					<h3 class="fw-bold text-dark mb-0">Get Marksheet</h3>

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


				<!-- Search Form -->

				<form action="<%=ORSView.GET_MARKSHEET_CTL%>" method="post">


					<div class="bg-light rounded-4 p-3">

						<div class="row g-3">


							<!-- Roll Number -->

							<div class="col-md-8 mx-auto">

								<label class="form-label fw-semibold"> Roll No <span
									class="text-danger">*</span>

								</label>


								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-hash text-primary"></i>

									</span> <input type="text" name="rollNo" class="form-control"
										value="<%=DataUtility.getStringData(bean.getRollNo())%>"
										placeholder="Enter roll no">


								</div>


								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("rollNo", request)%>

								</div>

							</div>

						</div>


						<!-- Buttons -->

						<div class="text-center mt-4">

							<button type="submit" class="btn btn-primary me-2">

								<i class="bi bi-search me-1"></i> Search

							</button>


							<button type="button" class="btn btn-secondary"
								onclick="window.location.href='<%=ORSView.GET_MARKSHEET_CTL%>'">

								<i class="bi bi-arrow-clockwise me-1"></i> Reset

							</button>

						</div>

					</div>

				</form>


				<%
				if (bean != null && bean.getRollNo() != null && !bean.getRollNo().trim().isEmpty()) {

					int total = bean.getPhysics() + bean.getChemistry() + bean.getMaths();

					double percentage = total / 3.0;
				%>


				<!-- Marksheet Result -->

				<div class="mt-4">


					<div class="text-center mb-3">

						<h4 class="fw-bold text-primary">

							<i class="bi bi-award me-1"></i> Marksheet

						</h4>

					</div>


					<!-- Student Details -->

					<div class="card border-0 shadow-sm rounded-4 mb-4">

						<div class="card-header bg-primary text-white">

							<h5 class="mb-0">

								<i class="bi bi-person me-1"></i> Student Details

							</h5>

						</div>


						<div class="card-body">

							<div class="table-responsive">

								<table class="table table-bordered mb-0">

									<tbody>

										<tr>

											<th class="table-light" width="35%">Roll No</th>

											<td><%=bean.getRollNo()%></td>

										</tr>


										<tr>

											<th class="table-light">Name</th>

											<td><%=bean.getName()%></td>

										</tr>

									</tbody>

								</table>

							</div>

						</div>

					</div>


					<!-- Subject Marks -->

					<div class="card border-0 shadow-sm rounded-4">

						<div class="card-header bg-primary text-white">

							<h5 class="mb-0">

								<i class="bi bi-book me-1"></i> Subject Marks

							</h5>

						</div>


						<div class="card-body">

							<div class="table-responsive">

								<table
									class="table table-bordered text-center align-middle mb-0">


									<thead class="table-light">

										<tr>

											<th>S.No.</th>

											<th>Subject</th>

											<th>Marks</th>

											<th>Maximum Marks</th>

										</tr>

									</thead>


									<tbody>


										<!-- Physics -->

										<tr>

											<td>1</td>

											<td>Physics</td>

											<td><%=bean.getPhysics()%></td>

											<td>100</td>

										</tr>


										<!-- Chemistry -->

										<tr>

											<td>2</td>

											<td>Chemistry</td>

											<td><%=bean.getChemistry()%></td>

											<td>100</td>

										</tr>


										<!-- Maths -->

										<tr>

											<td>3</td>

											<td>Maths</td>

											<td><%=bean.getMaths()%></td>

											<td>100</td>

										</tr>


										<!-- Total -->

										<tr class="table-success">

											<th colspan="2">Total</th>

											<th><%=total%></th>

											<th>300</th>

										</tr>


										<!-- Percentage -->

										<tr class="table-info">

											<th colspan="3">Percentage</th>

											<th><%=String.format("%.2f", percentage)%>%</th>

										</tr>


										<!-- Pass / Fail -->
										<%
										boolean pass = bean.getPhysics() >= 40 && bean.getChemistry() >= 40 && bean.getMaths() >= 40;
										%>
										<tr class="<%=pass ? "table-success" : "table-danger"%>">

											<th colspan="3">Result</th>

											<th colspan="2">
												<%
												if (pass) {
												%> <span class="text-success fw-bold"> <i
													class="bi bi-check-circle-fill me-1"></i> PASS
											</span> <%
													 } else {
													 %> <span class="text-danger fw-bold"> <i class="bi bi-x-circle-fill me-1"></i> FAIL
														</span> <%
													 }
													 %>

											</th>

										</tr>




									</tbody>

								</table>

							</div>

						</div>

					</div>

				</div>


				<%
				}
				%>


			</div>

		</div>

	</div>


	<%@include file="Footer.jsp"%>

</body>

</html>