<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="wfs.l2t.dto.dtoAccount"%>
<%@ page import="wfs.l2t.model.ModelAccount"%>
<%
	String userId = (String) request.getAttribute("user");
	ModelAccount account = new ModelAccount();
	dtoAccount dtoAcc = account.getAccountById(userId);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Quản lý tài khoản</title>
<link rel="shortcut icon" href="view/resource/image/logo_uit_icon.ico" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="view/resource/bootstrap/css/bootstrap.min.css">
<script src="view/resource/lib/jquery-2.1.4.min.js"></script>
<script src="view/resource/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="view/resource/css/theme_customize.css">
</head>
<body>
	<div class="container">
		<jsp:include page="menu/top-menu.jsp"/>
		
		<!-- chia trang thanh 2 cot -->
		
		<div class="container-fluid main-content">
			
			<div class="row">
				<div class="col-md-3">
					<jsp:include page="menu/left-menu.jsp"/>
				</div>
				<div class="col-md-9 custom_background_color">
					<br>
					<%
						String error = (String) request.getAttribute("error_message");
						if (error != null) {
							out.print("<div class=\"alert alert-danger fade in\">");
							out.print("<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>");
							out.print("<strong>Lỗi!</strong> " + error + " </div>");
						}
					%>
					<div>
						<div class="panel panel-primary">
							<div class="panel-heading">Thay đổi mật khẩu</div>
							<div class="panel-body">
								<form role="form" method="post" action="account">
									<div class="form-group">
										<label for="pwd">Mật khẩu cũ:</label> <input type="password"
											name="fcp-old-pass" required class="form-control" id="pwd">
									</div>
									<div class="form-group">
										<label for="pwd">Mật khẩu mới:</label> <input type="password"
											name="fcp-new-pass" required class="form-control" id="pwd">
									</div>
									<div class="form-group">
										<label for="pwd">Xác nhận mật khẩu mới:</label> <input
											type="password" required name="fcp-confirm-pass" class="form-control"
											id="pwd">
									</div>
									<button type="submit" name="btnChangePass"
										value="fcp-btn-change"
										class="btn btn-primary navbar-right custom_margin">Cập
										nhật mật khẩu</button>
								</form>
							</div>
						</div>
						<div class="panel panel-primary">
							<div class="panel-heading">Thay đổi ảnh đại diện</div>
							<div class="panel-body">
								<div class="container-fluid">
									<div class="row">
										<div class="col-md-6">
											<img
												src="<%out.print(request.getContextPath() + dtoAcc.avatar);%>"
												class="img-rounded col-md-offset-3" width="170" height="170" id="avartar_preview">
										</div>
										<div class="col-md-6">
											<form role="form" method="post" action="account"
												enctype="multipart/form-data">
												<div class="form-group">
													<label for="file">Url ảnh:</label> <input type="file"
														accept="image/*" onchange="loadFile(event)" class="file" name="image-avatar"
														id="file">
												</div>
												<div class="form-group">
													<button type="submit" name="btnChangeAvatar"
														value="fca-btn-change"
														class="btn btn-primary navbar-right custom_margin">Thay
														đổi</button>
												</div>

											</form>
										</div>
									</div>
								</div>
							</div>
						</div>											
						<script>
							var loadFile = function(event) {
								var output = document.getElementById('avartar_preview');
								output.src = URL
										.createObjectURL(event.target.files[0]);
							};
						</script>
						<div class="panel panel-primary">
							<div class="panel-heading">Thay đổi tên truy cập</div>
							<div class="panel-body">
								<form role="form" method="post" action="account">
									<div class="form-group">
										<label for="pwd">Tên truy cập mới:</label> <input type="text"
											name="fcn-new-user-name" class="form-control"
											value="<%=dtoAcc.userName%>" required id="pwd">
									</div>
									<button type="submit" name="changeUserName"
										class="btn btn-primary navbar-right custom_margin">Thay
										đổi</button>
								</form>
							</div>
						</div>
					</div>




				</div>
			</div>
		</div>
	</div>
</body>
</html>






