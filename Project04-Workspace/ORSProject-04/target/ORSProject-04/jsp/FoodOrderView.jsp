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

<title>Food Order</title>

</head>

<body class="bg-light">

	<%@include file="Header.jsp"%>

	<%
	String _suc = ServletUtility.getSuccessMessage(request);
	String _err = ServletUtility.getErrorMessage(request);
	
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("Pending", "Pending");
	map.put("Shipped", "Shipped");
	map.put("Out for Delivery", "Out for Delivery");
	map.put("Delivered", "Delivered");
	map.put("Cancelled", "Cancelled");
	%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.FoodOrderBean"
		scope="request">
	</jsp:useBean>


	<form action="<%=ORSView.FOOD_ORDER_CTL%>" method="post">

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

							<%=bean != null && bean.getId() > 0 ? "Update Food order" : "Add Food order"%>

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

								<label class="form-label fw-semibold"> Order ID <span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-person text-primary"></i>

									</span> <input type="text" name="orderId" class="form-control"
										value="<%=DataUtility.getStringData(bean.getOrderId())%>"
										placeholder="Enter Order ID">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("orderId", request)%>

								</div>

							</div>


							<!-- Customer Name -->

							<div class="col-md-6">

								<label class="form-label fw-semibold"> Customer Name <span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-person text-primary"></i>

									</span> <input type="text" name="customerName" class="form-control"
										value="<%=DataUtility.getStringData(bean.getCustomerName())%>"
										placeholder="Enter Customer name">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("customerName", request)%>

								</div>

							</div>


							<!-- restaurant -->

							<div class="col-md-6">

								<label class="form-label fw-semibold"> Restaurant <span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-envelope text-primary"></i>

									</span> <input type="text" name="restaurant" class="form-control"
										value="<%=DataUtility.getStringData(bean.getRestaurant())%>"
										placeholder="Enter restaurant">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("restaurant", request)%>

								</div>

							</div>


							<!-- Order Amount -->

							<div class="col-md-6">

								<label class="form-label fw-semibold"> Order Amount <span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-envelope text-primary"></i>

									</span> <input type="number" name="orderAmount" class="form-control"
										value="<%=DataUtility.getStringData(bean.getOrderAmount())%>"
										placeholder="Enter orderAmount">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("orderAmount", request)%>

								</div>
							</div>


							<!-- Has Voted -->
							<div class="col-md-6">

								<label class="form-label fw-semibold">Delivery Status<span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-building text-primary"></i>

									</span>

									<%=HTMLUtility.getList("deliveryStatus", DataUtility.getStringData(bean.getDeliveryStatus()), map)%>

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("deliveryStatus", request)%>

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