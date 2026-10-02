<%@page import="in.co.rays.proj4.bean.SmartParkingBean"%>
<%@page import="in.co.rays.proj4.model.CollegeModel"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>

<head>

<title>Smart Parking List</title>

</head>

<body class="bg-light">

    <%@include file="Header.jsp"%>

    <%
    int pageNo = ServletUtility.getPageNo(request);
    int pageSize = ServletUtility.getPageSize(request);
    int index = ((pageNo - 1) * pageSize) + 1;

    List<SmartParkingBean> list = ServletUtility.getList(request);
    Iterator<SmartParkingBean> it = list.iterator();

    String _suc = ServletUtility.getSuccessMessage(request);
    String _err = ServletUtility.getErrorMessage(request);
    %>


    <form action="<%=ORSView.SMART_PARKING_LIST_CTL%>" method="post">

        <div class="container py-4">

            <div class="card border-0 shadow-lg rounded-4">

                <div class="card-body p-4">


                    <!-- Heading -->

                    <div class="text-center mb-4">

                        <div class="mb-2">

                            <i class="bi bi-mortarboard-fill text-primary"
                                style="font-size: 42px;"></i>

                        </div>

                        <h3 class="fw-bold text-dark mb-0">

                            Smart Parking List

                        </h3>

                    </div>

					<!-- PDF Button - Top Right -->

					<div class="position-absolute top-0 end-0 mt-2 me-3">

						<a href="<%=ORSView.SMART_PARKING_REPORT_CTL%>?" target="_blank"
							class="btn btn-outline-danger btn-sm px-3"> <i
							class="bi bi-file-earmark-pdf me-1"></i> Download PDF
						</a> <a href="<%=ORSView.SMART_PARKING_REPORT_CTL%>?type=doc" target="_blank"
							class="btn btn-outline-primary btn-sm px-3"> <i
							class="bi bi-file-earmark-word me-1"></i> Download DOC
						</a>

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


                    <!-- Hidden Fields -->

                    <input
                        type="hidden"
                        name="pageNo"
                        value="<%=pageNo%>">

                    <input
                        type="hidden"
                        name="pageSize"
                        value="<%=pageSize%>">


                    <!-- Search Section -->

                    <div class="bg-light rounded-4 p-3 mb-4">

                        <div class="row g-3 align-items-center">


                            <!-- Search Voter Id -->

                            <div class="col-md-5">

                                <div class="input-group">

                                    <span
                                        class="input-group-text bg-white border-end-0">

                                        <i class="bi bi-person text-primary"></i>

                                    </span>

                                    <input
                                        type="text"
                                        name="vehileNumber"
                                        value=""
                                        class="form-control border-start-0"
                                        placeholder="Search by vehile Number">

                                </div>

                            </div>


                            <!-- Search by Constituency-->

                            <div class="col-md-5">

                                <div class="input-group">

                                    <span
                                        class="input-group-text bg-white border-end-0">

                                        <i class="bi bi-building text-primary"></i>

                                    </span>

                                    <input
                                        type="text"
                                        name="occupied"
                                        value=""
                                        class="form-control border-start-0"
                                        placeholder="Search by occupied">

                                </div>

                            </div>


                            <!-- Search Button -->

                            <div class="col-md-2">

                                <input
                                    type="submit"
                                    name="operation"
                                    class="btn btn-primary btn-sm px-3"
                                    value="<%=BaseCtl.OP_SEARCH%>">

                            </div>

                        </div>

                    </div>


                    <!-- Student Table -->

                    <div class="table-responsive">

                        <table
                            class="table table-bordered table-hover align-middle text-center mb-0">


                            <!-- Table Header -->

                            <thead class="table-primary">

                                <tr>

                                   <th><input type="checkbox" class="form-check-input"
										id="selectAll"
										onclick="document.querySelectorAll('input[name=ids]').forEach(c => c.checked = this.checked)">

									</th>

                                    <th>S.No</th>

                                    <th>Vehicle Number</th>

                                    <th>Vehicle Type</th>

                                    <th>Entry Time</th>

                                    <th>Occupied</th>

                                    <th>Edit</th>

                                </tr>

                            </thead>


                            <!-- Table Body -->

                            <tbody>

                                <%
                                while (it.hasNext()) {

                                	SmartParkingBean bean = it.next();

                                %>


                                <tr>


                                    <!-- Checkbox -->

                                    <td>

                                       <input type="checkbox"
											class="form-check-input"
											name="ids"
											value="<%=bean.getId()%>"
											onclick="document.getElementById('selectAll').checked = document.querySelectorAll('input[name=ids]:checked').length === document.querySelectorAll('input[name=ids]').length">

                                    </td>


                                    <!-- Serial Number -->

                                    <td>

                                        <%=index++%>

                                    </td>


                                    <!-- Vehicle Number -->

                                    <td>

                                        <%=bean.getVehicleNumber()%>

                                    </td>


                                    <!-- Vehicle Type -->

                                    <td>

                                        <%=bean.getVehicleType()%>

                                    </td>


                                    <!-- Entry Time -->

                                    <td>

                                        <%=bean.getEntryTime()%>

                                    </td>


                                    <!-- Occupied -->

                                    <td>

                                        <%=bean.isOccupied()%>

                                    </td>

                                    <!-- Edit -->

                                    <td>

                                        <a
                                            href="<%=ORSView.SMART_PARKING_CTL + "?id=" + bean.getId()%>"
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

                        <div
                            class="d-flex justify-content-center align-items-center gap-2">


                            <!-- Previous -->

                            <input
                                type="submit"
                                name="operation"
                                class="btn btn-primary btn-sm px-3"
                                <%=pageNo == 1 ? "disabled" : ""%>
                                value="<%=BaseCtl.OP_PREVIOUS%>">


                            <!-- Delete -->

                            <input
                                type="submit"
                                name="operation"
                                class="btn btn-danger btn-sm px-3"
                                value="<%=BaseCtl.OP_DELETE%>">


                            <!-- Next -->

                            <input
                                type="submit"
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
