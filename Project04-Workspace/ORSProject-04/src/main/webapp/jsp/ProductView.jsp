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

<title>Product</title>

</head>

<body class="bg-light">

	<%@include file="Header.jsp"%>

	<%
	String _suc = ServletUtility.getSuccessMessage(request);
	String _err = ServletUtility.getErrorMessage(request);

	
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("1", "Electronics");
    map.put("2", "Clothing");
    map.put("3", "Food");
    map.put("4", "Books");

	%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.ProductBean"
		scope="request">
	</jsp:useBean>


	<form action="<%=ORSView.PRODUCT_CTL%>" method="post">

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

							<%=bean != null && bean.getId() > 0 ? "Update Product" : "Add Product"%>

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



							<!-- Subject -->

							<div class="col-md-6">

								<label class="form-label fw-semibold">Product Name<span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-person text-primary"></i>

									</span> <input type="text" name="productName" class="form-control"
										value="<%=DataUtility.getStringData(bean.getProductName())%>"
										placeholder="Enter productName">

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("productName", request)%>

								</div>

							</div>

							<!-- Total Questions -->

							<div class="col-md-6">

								<label class="form-label fw-semibold"> Category <span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-envelope text-primary"></i>

									</span> <%=HTMLUtility.getList("category", DataUtility.getStringData(bean.getCategory()), map)%>

								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("category", request)%>

								</div>

							</div>


							<!-- Duration -->

							<div class="col-md-6">

								<label class="form-label fw-semibold"> Price <span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-envelope text-primary"></i>

									</span> <input type="text"  name="price" class="form-control"
										placeholder="enter price"
										value="<%=DataUtility.getStringData(bean.getPrice())%>">
								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("price", request)%>

								</div>

							</div>


							<!-- Total Marks -->
							<div class="col-md-6">

								<label class="form-label fw-semibold">Quantity<span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-building text-primary"></i>

									</span> <input type="text" name="quantity" class="form-control"
										value="<%=DataUtility.getStringData(bean.getQuantity())%>"
										placeholder="Enter venue">


								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("quantity", request)%>

								</div>

							</div>

							<!-- Total Marks -->
							<div class="col-md-6">

								<label class="form-label fw-semibold">Discount<span
									class="text-danger">*</span>

								</label>

								<div class="input-group">

									<span class="input-group-text bg-white"> <i
										class="bi bi-building text-primary"></i>

									</span> <input type="text" name="discount" class="form-control"
										value="<%=DataUtility.getStringData(bean.getDiscount())%>"
										placeholder="Enter discount">


								</div>

								<div class="text-danger small mt-1">

									<%=ServletUtility.getErrorMessage("discount", request)%>

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