<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>${param.action == 'edit' ? 'CẬP NHẬT' : 'THÊM MỚI'}ĐIỆN
	THOẠI</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/css/bootstrap.min.css' />">
</head>
<body>
	<div class="container border mt-5 col-lg-8">
		<c:if test="${not empty errors}">
			<div class="alert alert-danger mb-3" role="alert">
				<c:forEach var="entry" items="${errors}">
					<div>${entry.key}:${entry.value}</div>
				</c:forEach>
			</div>
		</c:if>
		<h3 class="text-center mt-5 mb-5">${param.action == 'edit' ? 'CẬP NHẬT THÔNG TIN ĐIỆN THOẠI' : 'THÊM ĐIỆN THOẠI'}
		</h3>


		<c:choose>
			<c:when test="${param.action == 'edit'}">
				<form method="POST"
					action="${pageContext.request.contextPath}/dienThoai?action=update&id=${dienThoai.maDienThoai}"
					enctype="multipart/form-data">
			</c:when>

			<c:when test="${param.action == 'update'}">
				<form method="POST"
					action="${pageContext.request.contextPath}/dienThoai?action=add"
					enctype="multipart/form-data">
			</c:when>
		</c:choose>

		<input type="hidden" name="action" value="${param.action}" />
		<!-- Tên NCC -->
		<div class="row mb-3">
			<label class="col-3 col-form-label">Tên NCC</label>
			<div class="col-9">
				<select class="form-select" name="maNCC" required>
					<c:forEach var="ncc" items="${nhaCungCaps}">
						<option value="${ncc.maNCC}"
							${not empty dienThoai && dienThoai.nhaCungCap.maNCC == ncc.maNCC ? 'selected' : ''}>
							${ncc.tenNCC}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<!-- Tên Điện Thoại -->
		<div class="row mb-3">
			<label class="col-3 col-form-label">Tên Điện Thoại</label>
			<div class="col-9">
				<input type="text" class="form-control" name="tenDienThoai"
					value="${not empty dienThoai ? dienThoai.tenDienThoai : ''}"
					required>
			</div>
		</div>

		<!-- Năm sản xuất -->
		<div class="row mb-3">
			<label class="col-3 col-form-label">Năm sản xuất</label>
			<div class="col-9">
				<input type="number" class="form-control" name="namSanXuat"
					value="${not empty dienThoai ? dienThoai.namSanXuat : ''}" required>
			</div>
		</div>

		<!-- Cấu hình -->
		<div class="row mb-3">
			<label class="col-3 col-form-label">Cấu hình</label>
			<div class="col-9">
				<input type="text" class="form-control" name="cauHinh"
					value="${not empty dienThoai ? dienThoai.cauHinh : ''}" required>
			</div>
		</div>

		<!-- Hình ảnh -->
		<div class="row mb-3">
			<label class="col-3 col-form-label">Hình ảnh</label>
			<div class="col-9">
				<input type="file" class="form-control" name="hinhAnh">
				<c:if test="${not empty dienThoai.hinhAnh}">
					<img src="${pageContext.request.contextPath}${dienThoai.hinhAnh}"
						alt="Hình ảnh hiện tại" class="mt-3"
						style="width: 100px; height: auto;" />
				</c:if>
			</div>
		</div>

		<!-- Nút Lưu -->
		<div class="text-center">
			<button type="submit" class="btn btn-success">
				${param.action == 'edit' ? 'Cập nhật' : 'Thêm mới'}</button>
		</div>
		</form>
	</div>
</body>
</html>
