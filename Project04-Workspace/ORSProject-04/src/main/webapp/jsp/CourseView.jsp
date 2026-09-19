<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Course</title>

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

    /* CourseBean bean = (CourseBean) request.getAttribute("bean"); */

    %>

    <jsp:useBean id="bean"
        class="in.co.rays.proj4.bean.CourseBean"
        scope="request">
    </jsp:useBean>


    <div class="container py-3">

        <div class="card border-0 shadow-lg rounded-4 mx-auto"
            style="max-width: 650px;">

            <div class="card-body p-4">


                <!-- Heading -->

                <div class="text-center mb-4">

                    <div class="mb-2">

                        <i class="bi bi-book text-primary"
                            style="font-size: 42px;"></i>

                    </div>

                    <h3 class="fw-bold text-dark mb-0">

                        <%=bean != null && bean.getId() > 0
                                ? "Update Course"
                                : "Add Course"%>

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


                <!-- Course Form -->

                <form action="<%=ORSView.COURSE_CTL%>" method="post">

                    <!-- Hidden ID -->

                    <input
                        type="hidden"
                        name="id"
                        value="<%=DataUtility.getStringData(bean.getId())%>">


                    <div class="bg-light rounded-4 p-3">

                        <div class="row g-3">


                            <!-- Course Name -->

                            <div class="col-12">

                                <label class="form-label fw-semibold">

                                    Name
                                    <span class="text-danger">*</span>

                                </label>

                                <div class="input-group">

                                    <span class="input-group-text bg-white">

                                        <i class="bi bi-bookmark text-primary"></i>

                                    </span>

                                    <input
                                        type="text"
                                        name="name"
                                        class="form-control"
                                        value="<%=DataUtility.getStringData(bean.getName())%>"
                                        placeholder="Enter course name">

                                </div>

                                <div class="text-danger small mt-1">

                                    <%=ServletUtility.getErrorMessage("name", request)%>

                                </div>

                            </div>


                            <!-- Description -->

                            <div class="col-12">

                                <label class="form-label fw-semibold">

                                    Description
                                    <span class="text-danger">*</span>

                                </label>

                                <div class="input-group">

                                    <span class="input-group-text bg-white">

                                        <i class="bi bi-card-text text-primary"></i>

                                    </span>

                                    <input
                                        type="text"
                                        name="description"
                                        class="form-control"
                                        value="<%=DataUtility.getStringData(bean.getDescription())%>"
                                        placeholder="Enter course description">

                                </div>

                                <div class="text-danger small mt-1">

                                    <%=ServletUtility.getErrorMessage("description", request)%>

                                </div>

                            </div>


                            <!-- Duration -->

                            <div class="col-12">

                                <label class="form-label fw-semibold">

                                    Duration
                                    <span class="text-danger">*</span>

                                </label>

                                <div class="input-group">

                                    <span class="input-group-text bg-white">

                                        <i class="bi bi-clock text-primary"></i>

                                    </span>

                                    <input
                                        type="text"
                                        name="duration"
                                        class="form-control"
                                        value="<%=DataUtility.getStringData(bean.getDuration())%>"
                                        placeholder="Enter course duration">

                                </div>

                                <div class="text-danger small mt-1">

                                    <%=ServletUtility.getErrorMessage("duration", request)%>

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
