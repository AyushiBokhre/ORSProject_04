<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.bean.SubjectBean"%>
<%@page import="in.co.rays.proj4.bean.CourseBean"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<meta name="viewport"
 content="width=device-width, initial-scale=1.0">

<title>TimeTable</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link
	rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

</head>

<body class="bg-light">

<%@include file="Header.jsp"%>

<%
String _suc = ServletUtility.getSuccessMessage(request);
String _err = ServletUtility.getErrorMessage(request);

List<CourseBean> courseList =
(List) request.getAttribute("courseList");

List<SubjectBean> subjectList =
		(List) request.getAttribute("subjectList");
%>

<jsp:useBean id="bean"
	class="in.co.rays.proj4.bean.TimeTableBean"
	scope="request">
</jsp:useBean>

<form action="<%=ORSView.TIMETABLE_CTL%>" method="post">

	<input type="hidden"
		name="id"
		value="<%=DataUtility.getStringData(bean.getId())%>">

	<div class="container pt-4 pb-5 mb-5">

		<div class="card border-0 shadow-lg rounded-4 mx-auto"
			style="max-width: 850px;">

			<div class="card-body p-4">

				<!-- Heading -->

				<div class="text-center mb-4">

					<div class="mb-2">
						<i class="bi bi-calendar3 text-primary"
							style="font-size: 42px;"></i>
					</div>

					<h3 class="fw-bold text-dark mb-0">

						<%=bean != null && bean.getId() > 0
								? "Update TimeTable"
								: "Add TimeTable"%>

					</h3>

				</div>


				<!-- Success Message -->

				<%
				if (_suc != null && !_suc.isEmpty()) {
				%>

				<div class="alert alert-success text-center py-2">

					<i class="bi bi-check-circle-fill me-2"></i>

					<%=_suc%>

				</div>

				<%
				}
				%>


				<!-- Error Message -->

				<%
				if (_err != null && !_err.isEmpty()) {
				%>

				<div class="alert alert-danger text-center py-2">

					<i class="bi bi-exclamation-triangle-fill me-2"></i>

					<%=_err%>

				</div>

				<%
				}
				%>


				<div class="bg-light rounded-4 p-3">

					<!-- Semester + Description -->

					<div class="row">

						<div class="col-md-6 mb-3">

							<label class="form-label fw-semibold">
								Semester <span class="text-danger">*</span>
							</label>

							<div class="input-group">

								<span class="input-group-text bg-white">
									<i class="bi bi-layers-fill text-primary"></i>
								</span>

								<input type="text"
									name="semester"
									value="<%=DataUtility.getStringData(bean.getSemester())%>"
									placeholder="Enter semester"
									class="form-control">

							</div>

							<div class="text-danger small mt-1">
								<%=ServletUtility.getErrorMessage("semester", request)%>
							</div>

						</div>


						<div class="col-md-6 mb-3">

							<label class="form-label fw-semibold">
								Description <span class="text-danger">*</span>
							</label>

							<div class="input-group">

								<span class="input-group-text bg-white">
									<i class="bi bi-card-text text-primary"></i>
								</span>

								<input type="text"
									name="description"
									value="<%=DataUtility.getStringData(bean.getDescription())%>"
									placeholder="Enter description"
									class="form-control">

							</div>

							<div class="text-danger small mt-1">
								<%=ServletUtility.getErrorMessage("description", request)%>
							</div>

						</div>

					</div>


					<!-- Exam Date + Exam Time -->

					<div class="row">

						<div class="col-md-6 mb-3">

							<label class="form-label fw-semibold">
								Exam Date <span class="text-danger">*</span>
							</label>

							<div class="input-group">

								<span class="input-group-text bg-white">
									<i class="bi bi-calendar-event-fill text-primary"></i>
								</span>

								<input type="date"
									name="examDate"
									value="<%=DataUtility.getStringData(bean.getExamDate())%>"
									placeholder="Enter exam date"
									class="form-control">

							</div>

							<div class="text-danger small mt-1">
								<%=ServletUtility.getErrorMessage("examDate", request)%>
							</div>

						</div>


						<div class="col-md-6 mb-3">

							<label class="form-label fw-semibold">
								Exam Time <span class="text-danger">*</span>
							</label>

							<div class="input-group">

								<span class="input-group-text bg-white">
									<i class="bi bi-clock-fill text-primary"></i>
								</span>

								<input type="text"
									name="examTime"
									value="<%=DataUtility.getStringData(bean.getExamTime())%>"
									placeholder="Enter exam time"
									class="form-control">

							</div>

							<div class="text-danger small mt-1">
								<%=ServletUtility.getErrorMessage("examTime", request)%>
							</div>

						</div>

					</div>


					<!-- Course + Subject -->

					<div class="row">

						<div class="col-md-6 mb-3">

							<label class="form-label fw-semibold">
								Course Name <span class="text-danger">*</span>
							</label>

							<div class="input-group">

								<span class="input-group-text bg-white">
									<i class="bi bi-book-fill text-primary"></i>
								</span>

								<%=HTMLUtility.getList(
										"courseId",
										DataUtility.getStringData(bean.getCourseId()),
										courseList)%>

							</div>

							<div class="text-danger small mt-1">
								<%=ServletUtility.getErrorMessage("courseId", request)%>
							</div>

						</div>


						<div class="col-md-6 mb-3">

							<label class="form-label fw-semibold">
								Subject Name <span class="text-danger">*</span>
							</label>

							<div class="input-group">

								<span class="input-group-text bg-white">
									<i class="bi bi-journal-bookmark-fill text-primary"></i>
								</span>

								<%=HTMLUtility.getList(
										"subjectId",
										DataUtility.getStringData(bean.getSubjectId()),
										subjectList)%>

							</div>

							<div class="text-danger small mt-1">
								<%=ServletUtility.getErrorMessage("subjectId", request)%>
							</div>

						</div>

					</div>

				</div>


				<!-- Save / Update Button -->

				<div class="col-12 text-center mt-3">

					<input type="submit"
						name="operation"
						value="<%=bean != null && bean.getId() > 0
								? "Update"
								: BaseCtl.OP_SAVE%>"
						class="btn btn-primary btn-sm px-4">

				</div>

			</div>

		</div>

	</div>

</form>

<%@include file="Footer.jsp"%>

</body>

</html>
