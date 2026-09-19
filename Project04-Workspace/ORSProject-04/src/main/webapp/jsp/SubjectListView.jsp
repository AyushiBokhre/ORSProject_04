<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.bean.SubjectBean"%>
<%@page import="in.co.rays.proj4.bean.CourseBean"%>
<%@page import="in.co.rays.proj4.model.CourseModel"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Subject List</title>

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
    int pageNo = ServletUtility.getPageNo(request);
    int pageSize = ServletUtility.getPageSize(request);
    int index = ((pageNo - 1) * pageSize) + 1;

    List<SubjectBean> list = ServletUtility.getList(request);
    Iterator<SubjectBean> it = list.iterator();

    String _suc = ServletUtility.getSuccessMessage(request);
    String _err = ServletUtility.getErrorMessage(request);
%>

<form action="<%=ORSView.SUBJECT_LIST_CTL%>" method="post">

    <input type="hidden" name="pageNo" value="<%=pageNo%>">
    <input type="hidden" name="pageSize" value="<%=pageSize%>">

    <div class="container py-4">

        <div class="card border-0 shadow-lg rounded-4">

            <div class="card-body p-4">

                <!-- Heading -->
                <div class="text-center mb-4">

                    <div class="mb-2">
                        <i class="bi bi-book-half text-primary"
                           style="font-size: 42px;"></i>
                    </div>

                    <h3 class="fw-bold text-dark mb-0">
                        Subject List
                    </h3>

                </div>
                
                <!-- PDF Button - Top Right -->
					
					<div class="position-absolute top-0 end-0 mt-2 me-3">
					
						<a href="<%=ORSView.SUBJECT_REPORT_CTL%>?type=pdf"
							class="btn btn-outline-danger btn-sm px-3">
							
							 <i class="bi bi-file-earmark-pdf me-1"></i> PDF
							 
						</a>
						
					</div>
					
					

                <!-- Success Message -->
                <% if (_suc != null && !_suc.trim().isEmpty()) { %>

                <div class="alert alert-success text-center py-2">
                    <i class="bi bi-check-circle me-1"></i>
                    <%=_suc%>
                </div>

                <% } %>

                <!-- Error Message -->
                <% if (_err != null && !_err.trim().isEmpty()) { %>

                <div class="alert alert-danger text-center py-2">
                    <i class="bi bi-exclamation-circle me-1"></i>
                    <%=_err%>
                </div>

                <% } %>

                <!-- Search Section -->
                <div class="bg-light rounded-4 p-3 mb-4">

                    <div class="row g-3 align-items-center">

                        <div class="col-md-5">

                            <div class="input-group">

                                <span class="input-group-text bg-white border-end-0">
                                    <i class="bi bi-book text-primary"></i>
                                </span>

                                <input type="text"
                                       name="name"
                                       value=""
                                       class="form-control border-start-0"
                                       placeholder="Search by Subject Name">

                            </div>

                        </div>

                        <div class="col-md-5">

                            <div class="input-group">

                                <span class="input-group-text bg-white border-end-0">
                                    <i class="bi bi-card-text text-primary"></i>
                                </span>

                                <input type="text"
                                       name="description"
                                       value=""
                                       class="form-control border-start-0"
                                       placeholder="Search by Description">

                            </div>

                        </div>

                        <div class="col-md-2">

                            <input type="submit"
                                   name="operation"
                                   class="btn btn-primary btn-sm px-3 w-100"
                                   value="<%=BaseCtl.OP_SEARCH%>">

                        </div>

                    </div>

                </div>

                <!-- Subject Table -->
                <div class="table-responsive">

                    <table class="table table-bordered table-hover align-middle text-center mb-0">

                        <thead class="table-primary">

                            <tr>

                                <th>
                                    <input type="checkbox"
                                           class="form-check-input"
                                           onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">
                                </th>

                                <th>S.No</th>

                                <th>Name</th>

                                <th>Description</th>

                                <th>Course Name</th>

                                <th>Edit</th>

                            </tr>

                        </thead>

                        <tbody>

                        <%
                        while (it.hasNext()) {

                            SubjectBean bean = it.next();

                            CourseModel cmodel = new CourseModel();

                            CourseBean cbean =
                                cmodel.findByPK(bean.getCourseId());
                        %>

                            <tr>

                                <td>
                                    <input type="checkbox"
                                           name="ids"
                                           value="<%=bean.getId()%>"
                                           class="form-check-input">
                                </td>

                                <td>
                                    <%=index++%>
                                </td>

                                <td>
                                    <%=bean.getName()%>
                                </td>

                                <td>
                                    <%=bean.getDescription()%>
                                </td>

                                <td>
                                    <%=cbean.getName()%>
                                </td>

                                <td>

                                    <a href="<%=ORSView.SUBJECT_CTL + "?id=" + bean.getId()%>"
                                       class="btn btn-sm btn-outline-primary">

                                        <i class="bi bi-pencil-square me-1"></i>
                                        Edit

                                    </a>

                                </td>

                            </tr>

                        <%
                        }
                        %>

                        </tbody>

                    </table>

                </div>

                <!-- Pagination / Actions -->
                <div class="border-top mt-4 pt-3 pb-5">

                    <div class="d-flex justify-content-center align-items-center gap-2">

                        <input type="submit"
                               name="operation"
                               class="btn btn-primary btn-sm px-3"
                               <%=pageNo == 1 ? "disabled" : ""%>
                               value="<%=BaseCtl.OP_PREVIOUS%>">

                        <input type="submit"
                               name="operation"
                               class="btn btn-danger btn-sm px-3"
                               value="<%=BaseCtl.OP_DELETE%>">

                        <input type="submit"
                               name="operation"
                               class="btn btn-primary btn-sm px-3"
                               <%=list.size() < 10 ? "disabled" : ""%>
                               value="<%=BaseCtl.OP_NEXT%>">

                    </div>

                </div>

            </div>

        </div>

    </div>

</form>
<%@include file="Footer.jsp"%>
</body>

</html>
