<%@ page import="in.co.rays.proj4.util.MessageSource"%>
<%@ page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page import="in.co.rays.proj4.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<!-- Bootstrap 5 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<!-- Bootstrap Icons -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
	rel="stylesheet">

<title>ORS Project</title>

</head>

<body>

	<%
	UserBean userBean = (UserBean) session.getAttribute("user");
	String roleName = (String) session.getAttribute("role");
	MessageSource ms = MessageSource.getInstance();
	boolean isLogin = userBean != null;
	String locale = ms.getLanguage();
	%>


	<!-- ================= NAVBAR ================= -->

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">

		<div class="container-fluid">

			<!-- ================= LOGO ================= -->

			<a class="navbar-brand" href="<%=ORSView.WELCOME_CTL%>">

				<img src="<%=ORSView.APP_CONTEXT%>/img/customLogo.jpg"
					width="175" height="50" alt="ORS Logo" class="rounded">

			</a>


			<!-- ================= MOBILE TOGGLE ================= -->

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">

				<span class="navbar-toggler-icon"></span>

			</button>


			<!-- ================= NAVBAR CONTENT ================= -->

			<div class="collapse navbar-collapse" id="navbarNav">

				<%
				if (isLogin) {
				%>


				<!-- ================= LEFT SIDE MENU ================= -->

				<ul class="navbar-nav me-auto mb-2 mb-lg-0">


					<!-- ================= ROLE ================= -->

					<li class="nav-item dropdown">

						<a class="nav-link dropdown-toggle" href="#"
							id="roleDropdown" role="button" data-bs-toggle="dropdown"
							aria-expanded="false">

							<i class="bi bi-person-badge"></i> Role

						</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="roleDropdown">

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.ROLE_CTL%>">

									<i class="bi bi-plus-circle me-2"></i>
									Add Role

								</a>
							</li>

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.ROLE_LIST_CTL%>">

									<i class="bi bi-list-ul me-2"></i>
									Role List

								</a>
							</li>

						</ul>

					</li>


					<!-- ================= USER ================= -->

					<li class="nav-item dropdown">

						<a class="nav-link dropdown-toggle" href="#"
							id="userDropdown" role="button" data-bs-toggle="dropdown"
							aria-expanded="false">

							<i class="bi bi-people"></i> User

						</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="userDropdown">

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.USER_CTL%>">

									<i class="bi bi-person-plus me-2"></i>
									Add User

								</a>
							</li>

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.USER_LIST_CTL%>">

									<i class="bi bi-list-ul me-2"></i>
									User List

								</a>
							</li>

						</ul>

					</li>


					<!-- ================= COLLEGE ================= -->

					<li class="nav-item dropdown">

						<a class="nav-link dropdown-toggle" href="#"
							id="collegeDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">

							<i class="bi bi-building"></i> College

						</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="collegeDropdown">

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.COLLEGE_CTL%>">

									<i class="bi bi-plus-circle me-2"></i>
									Add College

								</a>
							</li>

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.COLLEGE_LIST_CTL%>">

									<i class="bi bi-list-ul me-2"></i>
									College List

								</a>
							</li>

						</ul>

					</li>


					<!-- ================= STUDENT ================= -->

					<li class="nav-item dropdown">

						<a class="nav-link dropdown-toggle" href="#"
							id="studentDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">

							<i class="bi bi-mortarboard"></i> Student

						</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="studentDropdown">

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.STUDENT_CTL%>">

									<i class="bi bi-person-plus me-2"></i>
									Add Student

								</a>
							</li>

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.STUDENT_LIST_CTL%>">

									<i class="bi bi-list-ul me-2"></i>
									Student List

								</a>
							</li>

						</ul>

					</li>


					<!-- ================= MARKSHEET ================= -->

					<li class="nav-item dropdown">

						<a class="nav-link dropdown-toggle" href="#"
							id="marksheetDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">

							<i class="bi bi-file-earmark-text"></i> Marksheet

						</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="marksheetDropdown">

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.MARKSHEET_CTL%>">

									<i class="bi bi-plus-circle me-2"></i>
									Add Marksheet

								</a>
							</li>

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.MARKSHEET_LIST_CTL%>">

									<i class="bi bi-list-ul me-2"></i>
									Marksheet List

								</a>
							</li>

						</ul>

					</li>


					<!-- ================= COURSE ================= -->

					<li class="nav-item dropdown">

						<a class="nav-link dropdown-toggle" href="#"
							id="courseDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">

							<i class="bi bi-book"></i> Course

						</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="courseDropdown">

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.COURSE_CTL%>">

									<i class="bi bi-plus-circle me-2"></i>
									Add Course

								</a>
							</li>

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.COURSE_LIST_CTL%>">

									<i class="bi bi-list-ul me-2"></i>
									Course List

								</a>
							</li>

						</ul>

					</li>


					<!-- ================= SUBJECT ================= -->

					<li class="nav-item dropdown">

						<a class="nav-link dropdown-toggle" href="#"
							id="subjectDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">

							<i class="bi bi-journal-bookmark"></i> Subject

						</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="subjectDropdown">

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.SUBJECT_CTL%>">

									<i class="bi bi-plus-circle me-2"></i>
									Add Subject

								</a>
							</li>

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.SUBJECT_LIST_CTL%>">

									<i class="bi bi-list-ul me-2"></i>
									Subject List

								</a>
							</li>

						</ul>

					</li>


					<!-- ================= FACULTY ================= -->

					<li class="nav-item dropdown">

						<a class="nav-link dropdown-toggle" href="#"
							id="facultyDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">

							<i class="bi bi-person-workspace"></i> Faculty

						</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="facultyDropdown">

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.FACULTY_CTL%>">

									<i class="bi bi-person-plus me-2"></i>
									Add Faculty

								</a>
							</li>

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.FACULTY_LIST_CTL%>">

									<i class="bi bi-list-ul me-2"></i>
									Faculty List

								</a>
							</li>

						</ul>

					</li>


					<!-- ================= MODULE ================= -->

					<li class="nav-item dropdown">

						<a class="nav-link dropdown-toggle" href="#"
							id="moduleDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">

							<i class="bi bi-book"></i> Module

						</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="moduleDropdown">

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.VOTER_CTL%>">

									<i class="bi bi-plus-circle me-2"></i>
									Add Voter

								</a>
							</li>

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.VOTER_LIST_CTL%>">

									<i class="bi bi-list-ul me-2"></i>
									Voter List

								</a>
							</li>

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.FOOD_ORDER_CTL%>">

									<i class="bi bi-plus-circle me-2"></i>
									Add Food Order

								</a>
							</li>

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.FOOD_ORDER_LIST_CTL%>">

									<i class="bi bi-list-ul me-2"></i>
									Food Order List

								</a>
							</li>

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.SMART_PARKING_CTL%>">

									<i class="bi bi-plus-circle me-2"></i>
									Add Parking

								</a>
							</li>

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.SMART_PARKING_LIST_CTL%>">

									<i class="bi bi-list-ul me-2"></i>
									Parking List

								</a>
							</li>

						</ul>

					</li>


					<!-- ================= TIMETABLE ================= -->

					<li class="nav-item dropdown">

						<a class="nav-link dropdown-toggle" href="#"
							id="timetableDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">

							<i class="bi bi-calendar3"></i> TimeTable

						</a>

						<ul class="dropdown-menu dropdown-menu-dark"
							aria-labelledby="timetableDropdown">

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.TIMETABLE_CTL%>">

									<i class="bi bi-plus-circle me-2"></i>
									Add TimeTable

								</a>
							</li>

							<li>
								<a class="dropdown-item"
									href="<%=ORSView.TIMETABLE_LIST_CTL%>">

									<i class="bi bi-list-ul me-2"></i>
									TimeTable List

								</a>
							</li>

						</ul>

					</li>

				</ul>


				<!-- ================= RIGHT SIDE ================= -->

				<div class="d-flex align-items-center gap-2">


					<!-- ================= LANGUAGE ================= -->

					<form class="m-0">

						<select name="lang"
							onchange="this.form.submit()"
							class="form-select form-select-sm">

							<option value="en"
								<%=("en".equals(locale)) ? "selected" : ""%>>
								English
							</option>

							<option value="hi"
								<%=("hi".equals(locale)) ? "selected" : ""%>>
								Hindi
							</option>

						</select>

					</form>


					<!-- ================= JAVADOC ================= -->

					<a class="btn btn-outline-light btn-sm px-3"
						href="<%=ORSView.JAVA_DOC_VIEW%>" target="_blank">

						<i class="bi bi-file-earmark-code me-1"></i>
						JavaDoc

					</a>


					<!-- ================= USER NAME BUTTON ================= -->

					<div class="dropdown">

						<a class="btn btn-outline-light btn-sm px-3 dropdown-toggle"
							href="#" id="profileDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">

							<i class="bi bi-person-circle me-1"></i>

							Hi, <%=userBean.getFirstName()%>

							<%
							if (roleName != null && !roleName.isEmpty()) {
							%>

							(<%=roleName%>)

							<%
							}
							%>

						</a>


						<ul class="dropdown-menu dropdown-menu-dark dropdown-menu-end"
							aria-labelledby="profileDropdown">

							<li>

								<a class="dropdown-item"
									href="<%=ORSView.MY_PROFILE_CTL + "?id=" + userBean.getId()%>">

									<i class="bi bi-person-circle me-2"></i>
									My Profile

								</a>

							</li>

							<li>

								<a class="dropdown-item"
									href="<%=ORSView.CHANGE_PASSWORD_CTL%>">

									<i class="bi bi-key-fill me-2"></i>
									Change Password

								</a>

							</li>

						</ul>

					</div>


					<!-- ================= LOGOUT BUTTON ================= -->

					<a class="btn btn-outline-danger btn-sm"
						href="<%=ORSView.LOGIN_CTL%>?operation=logout">

						<i class="bi bi-box-arrow-right me-1"></i>
						Logout

					</a>

				</div>


				<%
				} else {
				%>


				<!-- ================= GUEST MENU ================= -->

				<ul class="navbar-nav ms-auto align-items-lg-center">


					<!-- Welcome Button -->

					<li class="nav-item">

						<a class="btn btn-outline-light btn-sm me-2 px-3"
							href="<%=ORSView.WELCOME_CTL%>">

							<i class="bi bi-house-door me-1"></i>
							Welcome

						</a>

					</li>


					<!-- Login Button -->

					<li class="nav-item">

						<a class="btn btn-outline-light btn-sm me-2 px-3"
							href="<%=ORSView.LOGIN_CTL%>">

							<i class="bi bi-box-arrow-in-right me-1"></i>
							Login

						</a>

					</li>


					<!-- SignUp Button -->

					<li class="nav-item">

						<a class="btn btn-outline-light btn-sm me-2 px-3"
							href="<%=ORSView.USER_REGISTRATION_CTL%>">

							<i class="bi bi-person-plus me-1"></i>
							SignUp

						</a>

					</li>

				</ul>


				<%
				}
				%>

			</div>

		</div>

	</nav>


	<!-- Bootstrap JavaScript -->

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js">
	</script>

</body>

</html>