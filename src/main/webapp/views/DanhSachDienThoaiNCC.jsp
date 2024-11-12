<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
</head>
<body>
	<c:if test="${empty param.action || param.action != 'list'}">
		<c:redirect url="/dienThoai?action=list" />
	</c:if>

	<div class="container border">
		<header class="bg-primary" style="height: 200px; width: 100%;">
			<img src="${pageContext.request.contextPath}/assets/imgs/banner.png"
				alt="Banner" style="width: 100%; height: 200px; object-fit: cover;">
		</header>

		<div class="d-flex justify-content-center border pt-2 pb-3">
			<a href="${pageContext.request.contextPath}/dienThoai?action=list"
				class="me-2">Danh sách sản phẩm</a> <a
				href="${pageContext.request.contextPath}/dienThoai?action=new"
				class="me-2">Thêm mới sản phẩm</a> <a href="#">Chức năng quản lý</a>
		</div>

		<!-- Dropdown chọn nhà cung cấp -->
		<div class="row mt-3">
			<div class="d-flex justify-content-end col-12 pe-3">
				<form method="GET"
					action="${pageContext.request.contextPath}/dienThoai">
					<input type="hidden" name="action" value="list" />
					<button type="button" class="btn btn-success me-3">Nhà
						Cung Cấp</button>
					<select class="form-select w-auto" name="maNCC"
						onchange="this.form.submit()">
						<option value="">-- Tất cả nhà cung cấp --</option>
						<c:forEach var="ncc" items="${nhaCungCaps}">
							<option value="${ncc.maNCC}"
								${param.maNCC == ncc.maNCC ? 'selected' : ''}>${ncc.tenNCC}</option>
						</c:forEach>
					</select>
				</form>
			</div>
		</div>

		<!-- Bảng danh sách sản phẩm -->
		<table class="table table-bordered mt-5 text-center">
			<thead>
				<tr>
					<th>Tên NCC</th>
					<th>Mã ĐT</th>
					<th>Tên ĐT</th>
					<th>Năm sản xuất</th>
					<th>Cấu hình</th>
					<th>Hình ảnh</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="d" items="${dienThoais}">
					<tr>
						<td>${d.nhaCungCap.tenNCC}</td>
						<td>${d.maDienThoai}</td>
						<td>${d.tenDienThoai}</td>
						<td>${d.namSanXuat}</td>
						<td>${d.cauHinh}</td>
						<td><c:if test="${not empty d.hinhAnh}">
								<img src="${pageContext.request.contextPath}${d.hinhAnh}"
									alt="Hình ảnh" style="width: 100px; height: auto;" />
							</c:if></td>
						<td><a
							href="${pageContext.request.contextPath}/dienThoai?action=edit&id=${d.maDienThoai}"
							class="btn btn-warning">Sửa</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<footer class="text-center">Nguyễn Thị Thu Hồng - 21097741 -
			DHKTPM17BTT</footer>
	</div>

</body>
</html>