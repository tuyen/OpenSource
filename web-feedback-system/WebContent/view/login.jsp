<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="wfs.l2t.dto.dtoCategory"%>
<%@page import="wfs.l2t.model.ModelCategory"%>
<%@page import="java.util.*"%>
<%
	ModelCategory mdc = new ModelCategory();
	List<dtoCategory> listCate = mdc.getAllCategory();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>JobRecSys - Đăng nhập - Đăng ký</title>
<link rel="shortcut icon" href="view/resource/image/logo_uit_icon.ico" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="view/resource/bootstrap/css/bootstrap.min.css">
<script src="view/resource/lib/jquery-2.1.4.min.js"></script>
<script src="view/resource/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="view/resource/css/theme_customize.css">
<script src="view/resource/lib/check-register.js"></script>
<style>
body {
	background-color: #F1F1F1;
	margin: 0;
	padding: 0;
	height: auto;
	min-height: 100%;
}

#footer {
	text-align: center;
	line-height: 1.5em;
}

a:hover {
	color: #fff;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="container-fluid row custom_navbar_color">
			<div class="col-md-7">
				<img
					src="<%out.print(request.getContextPath()
					+ "/view/resource/image/logo.png");%>"
					class="img-responsive">
			</div>
			<!-- form login -->
			<div class="col-md-5 row">
				<form class="form" role="form" action="login" method="post">
					<div class="col-md-5">
						<div style="margin-top: 5px" class="form-group checkbox">
							<lable> Email </lable>
							<input type="email" class="form-control" placeholder="Email"
								name="login-email"> <input type="checkbox"
								style="margin-left: 0px;" name="login-check">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Duy
							trì đăng nhập
						</div>
						<div>
							<p style="color: red;">
								<%=request.getAttribute("Message") != null ? request
					.getAttribute("Message") : ""%>
							</p>
						</div>
					</div>
					<div class="col-md-5">
						<div style="margin-top: 5px" class="form-group">
							<lable for="pwd"> Mật khẩu</lable>
							<input type="password" class="form-control" name="login-pass">
							<a href="./ForgotPassword"> Quên mật khẩu?</a>
						</div>
					</div>
					<div class="col-md-2">
						<div style="margin-top: 25px;"></div>
						<div>
							<button type="submit" name="submit" class="btn btn-primary"
								value="login">Đăng nhập</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- form register -->
		<div class="container-fluid row" style="padding: 20px;">
			<div class="col-md-7">
				<img alt=""
					src="<%out.print(request.getContextPath()
					+ "/view/resource/image/poster.jpg");%>"
					width="100%" height="430">
			</div>
			<div class="col-md-5">
				<h2 style="font-family: arial; color: #329FEA;">Đăng ký nhận
					việc làm ngay hôm nay!</h2>
				<p>
					<i> Tìm việc làm nhanh chóng, tiện lợi </i>
				</p>
				<form action="ControllerLogin" class="form" role="form"
					method="post" id="reg-form">
					<div class="form-group">
						<div>
							<i style="color: red;"> <%=request.getAttribute("err_reg") != null ? request
					.getAttribute("err_reg") : ""%>
							</i>
						</div>
						<lable>Email</lable>
						<input required oninput="setCustomValidity('')"
							oninvalid="this.setCustomValidity('Vui lòng nhập địa chỉ email')"
							name="reg-email" type="email" placeholder="Email"
							class="form-control" id="reg-email"> <i id="txtHintEmail"
							style="color: red;"> </i>
					</div>
					<div class="form-group">
						<lable>Tên tài khoản</lable>
						<input required oninput="setCustomValidity('')"
							oninvalid="this.setCustomValidity('Vui lòng nhập tên người dùng')"
							name="reg-username" type="text" placeholder="tên tài khoản"
							class="form-control" id="reg-user">
						<div>
							<i id="txtHintUserName"></i>
						</div>
					</div>
					<div class="radio-inline">
						<label style="margin-right: 10px;"> Loại tài khoản </label> <label
							class="radio-inline disabled" data-toggle="tooltip"
							title="chức năng này chưa được kích hoạt"> <input
							class="disabled" disabled type="radio" name="radio"
							value="employer">Nhà tuyển dụng
						</label> <label class="radio-inline"> <input type="radio"
							name="radio" value="job-seeker" checked>Người tìm việc
						</label>
					</div>
					<div class="form-group">
						<lable>Lĩnh vực ngành nghề </lable>
						<select class="form-control" name="reg-category">
							<%
								for (dtoCategory cate : listCate) {
									if (cate.categoryName.trim().equals("IT - Phần mềm"))
										out.print("<option selected value = '" + cate.categoryId
												+ "'>" + cate.categoryName + "</option>");
									else
										out.print("<option value = '" + cate.categoryId + "'>"
												+ cate.categoryName + "</option>");
								}
							%>
						</select>
					</div>
					<div class="form-group" id="reg-pass">
						<lable>Mật khẩu</lable>
						<input required oninput="setCustomValidity('')"
							oninvalid="this.setCustomValidity('Vui lòng nhập mật khẩu')"
							name="reg-password" type="password" placeholder="mật khẩu"
							class="form-control" id="reg-pwd">
						<div>
							<i id="txtHintpwd" style="color: red;"></i>
						</div>
					</div>
					<div class="form-group" id="reg-rpass">
						<lable>Nhập lại mật khẩu</lable>
						<input required oninput="setCustomValidity('')"
							oninvalid="this.setCustomValidity('Vui lòng nhập lại mật khẩu')"
							name="reg-re-type-password" type="password"
							placeholder="mật khẩu" class="form-control" id="reg-rpwd">
						<div>
							<i id="txtHintrpwd" style="color: red;"></i>
						</div>
					</div>
					<div class="form-group">
						<button type="submit" name="submit"
							class="form-control btn btn-primary" value="register"
							id="reg-button">Đăng ký</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="footer" class="container-fluid custom_navbar_color"
		style="padding-top: 20px;">
		<div class="row">

			<div class="col-md-5 col-md-offset-5"
				style="float: none; margin: 0 auto;">
				<div>
					<strong>&copy Nhóm sinh viên UIT</strong>
				</div>
				<div>
					<lable class="glyphicon glyphicon-map-marker"> <strong>Khu
						phố 6, P.Linh Xuân, Q.Thủ Đức, TP.Hồ Chí Minh </strong> </lable>
				</div>
				<div>
					<lable class="glyphicon glyphicon-envelope"> <strong>
						<a>uit.recsys@gmail.com</a>
					</strong> </lable>
				</div>
				<div>
					<lable class="glyphicon glyphicon-earphone"> <strong>
						084 165 799 0105 </strong></lable>
				</div>
			</div>

		</div>
	</div>
</body>
</html>