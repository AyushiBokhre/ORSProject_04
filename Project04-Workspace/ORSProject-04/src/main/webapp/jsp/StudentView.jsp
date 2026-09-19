<%@page import="in.co.rays.proj4.bean.CollegeBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.controller.UserCtl"%>
<%@page import="in.co.rays.proj4.controller.LoginCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Student</title>

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

    List<CollegeBean> collegeList =
            (List) request.getAttribute("collegeList");
    %>

    <jsp:useBean id="bean"
        class="in.co.rays.proj4.bean.StudentBean"
        scope="request">
    </jsp:useBean>


    <form action="<%=ORSView.STUDENT_CTL%>" method="post">

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

                            <%=bean != null && bean.getId() > 0
                                    ? "Update Student"
                                    : "Add Student"%>

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


                    <!-- Student Form -->

                    <input
                        type="hidden"
                        name="id"
                        value="<%=DataUtility.getStringData(bean.getId())%>">


                    <div class="bg-light rounded-4 p-3">

                        <div class="row g-3">


                            <!-- First Name -->

                            <div class="col-md-6">

                                <label class="form-label fw-semibold">

                                    First Name
                                    <span class="text-danger">*</span>

                                </label>

                                <div class="input-group">

                                    <span class="input-group-text bg-white">

                                        <i class="bi bi-person text-primary"></i>

                                    </span>

                                    <input
                                        type="text"
                                        name="firstName"
                                        class="form-control"
                                        value="<%=DataUtility.getStringData(bean.getFirstName())%>"
                                        placeholder="Enter first name">

                                </div>

                                <div class="text-danger small mt-1">

                                    <%=ServletUtility.getErrorMessage("firstName", request)%>

                                </div>

                            </div>


                            <!-- Last Name -->

                            <div class="col-md-6">

                                <label class="form-label fw-semibold">

                                    Last Name
                                    <span class="text-danger">*</span>

                                </label>

                                <div class="input-group">

                                    <span class="input-group-text bg-white">

                                        <i class="bi bi-person text-primary"></i>

                                    </span>

                                    <input
                                        type="text"
                                        name="lastName"
                                        class="form-control"
                                        value="<%=DataUtility.getStringData(bean.getLastName())%>"
                                        placeholder="Enter last name">

                                </div>

                                <div class="text-danger small mt-1">

                                    <%=ServletUtility.getErrorMessage("lastName", request)%>

                                </div>

                            </div>


                            <!-- Email -->

                            <div class="col-md-6">

                                <label class="form-label fw-semibold">

                                    Email
                                    <span class="text-danger">*</span>

                                </label>

                                <div class="input-group">

                                    <span class="input-group-text bg-white">

                                        <i class="bi bi-envelope text-primary"></i>

                                    </span>

                                    <input
                                        type="email"
                                        name="email"
                                        class="form-control"
										value="<%=DataUtility.getStringData(bean.getEmail())%>"
                                        placeholder="Enter email">

                                </div>

                                <div class="text-danger small mt-1">

                                    <%=ServletUtility.getErrorMessage("email", request)%>

                                </div>

                            </div>


                            <!-- College -->

                            <div class="col-md-6">

                                <label class="form-label fw-semibold">

                                    College
                                    <span class="text-danger">*</span>

                                </label>

                                <div class="input-group">

                                    <span class="input-group-text bg-white">

                                        <i class="bi bi-building text-primary"></i>

                                    </span>

                                    <%=HTMLUtility.getList(
                                            "collegeId",
                                            DataUtility.getStringData(bean.getCollegeId()),
                                            collegeList)%>

                                </div>

                                <div class="text-danger small mt-1">

                                    <%=ServletUtility.getErrorMessage("collegeId", request)%>

                                </div>

                            </div>


                            <!-- DOB -->

                            <div class="col-md-6">

                                <label class="form-label fw-semibold">

                                    Date of Birth
                                    <span class="text-danger">*</span>

                                </label>

                                <div class="input-group">

                                    <span class="input-group-text bg-white">

                                        <i class="bi bi-calendar-event text-primary"></i>

                                    </span>

                                    <input
                                        type="date"
                                        name="dob"
                                        class="form-control"
                                        value="<%=DataUtility.getStringData(bean.getDateOfBirth())%>">

                                </div>

                                <div class="text-danger small mt-1">

                                    <%=ServletUtility.getErrorMessage("dob", request)%>

                                </div>

                            </div>


                            <!-- Mobile Number -->

                            <div class="col-md-6">

                                <label class="form-label fw-semibold">

                                    Mobile No
                                    <span class="text-danger">*</span>

                                </label>

                                <div class="input-group">

                                    <span class="input-group-text bg-white">

                                        <i class="bi bi-telephone text-primary"></i>

                                    </span>

                                    <input
                                        type="text"
                                        name="mobileNo"
                                        class="form-control"
                                        value="<%=DataUtility.getStringData(bean.getMobileNo())%>"
                                        placeholder="Enter mobile number">

                                </div>

                                <div class="text-danger small mt-1">

                                    <%=ServletUtility.getErrorMessage("mobileNo", request)%>

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

                </div>

            </div>

        </div>

    </form>


    <%@include file="Footer.jsp"%>

</body>

</html>