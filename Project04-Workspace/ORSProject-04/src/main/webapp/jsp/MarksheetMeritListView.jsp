<%@ page import="java.util.List"%>
<%@ page import="in.co.rays.proj4.bean.MarksheetBean"%>
<%@ page import="in.co.rays.proj4.util.ServletUtility"%>

<!DOCTYPE html>
<html>

<head>

<title>Merit Marksheet</title>

</head>

<body class="bg-light d-flex flex-column min-vh-100">

	<%@ include file="Header.jsp"%>

	<div class="container py-4 flex-grow-1">

		<div class="card border-0 shadow-lg rounded-4">

			<div class="card-body p-4">

				<!-- Heading -->

				<div class="text-center mb-4">

					<div class="mb-2">

						<i class="bi bi-trophy-fill text-warning" style="font-size: 42px;"></i>

					</div>

					<h3 class="fw-bold text-dark mb-0">Merit Marksheet</h3>

				</div>


				<!-- Merit List -->

				<%
				List<MarksheetBean> list = (List<MarksheetBean>) request.getAttribute("list");
				%>

				<div class="table-responsive">

					<table
						class="table table-bordered table-hover
                              text-center align-middle">

						<thead class="table-primary">

							<tr>

								<th>Rank</th>
								<th>Roll No</th>
								<th>Student Name</th>
								<th>Physics</th>
								<th>Chemistry</th>
								<th>Maths</th>
								<th>Total</th>
								<th>Percentage</th>

							</tr>

						</thead>

						<tbody>

							<%
							if (list != null && !list.isEmpty()) {

								int rank = 1;

								for (MarksheetBean bean : list) {

									int total = bean.getPhysics() + bean.getChemistry() + bean.getMaths();

									double percentage = total / 3.0;
							%>

							<tr>

								<td class="fw-bold">
									<%
									if (rank == 1) {
									%> <i class="bi bi-trophy-fill text-warning fs-4" title="1st Rank"></i>

									<%
									} else if (rank == 2) {
									%> <i class="bi bi-trophy-fill text-secondary fs-4" title="2nd Rank"></i>

									<%
									} else if (rank == 3) {
									%> <i class="bi bi-trophy-fill text-danger fs-4" title="3rd Rank"></i>
									<%
									}
									%> <%=rank%>

								</td>
								
								<td><%=bean.getRollNo()%></td>

								<td class="fw-semibold"><%=bean.getName()%></td>

								<td><%=bean.getPhysics()%></td>

								<td><%=bean.getChemistry()%></td>

								<td><%=bean.getMaths()%></td>

								<td class="fw-bold"><%=total%></td>

								<td><%=String.format("%.2f", percentage)%>%</td>

							</tr>

							<%
							rank++;
							}

							} else {
							%>

							<tr>

								<td colspan="8" class="text-muted py-4"><i
									class="bi bi-info-circle me-1"></i> No merit records found.</td>

							</tr>

							<%
							}
							%>

						</tbody>

					</table>

				</div>

			</div>

		</div>

	</div>

	<%@ include file="Footer.jsp"%>

</body>

</html>
