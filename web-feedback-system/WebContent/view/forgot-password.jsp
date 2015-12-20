<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>JobRecSys - Quên mật khẩu</title>
<link rel="shortcut icon" href="view/resource/image/logo_uit_icon.ico" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="view/resource/bootstrap/css/bootstrap.min.css">
<script src="view/resource/lib/jquery-2.1.4.min.js" async></script>
<script src="view/resource/bootstrap/js/bootstrap.min.js" async></script>
<link rel="stylesheet" href="view/resource/css/theme_customize.css">
<style>
html, body {
	height: 100%;
}

body {
	background-color: #F1F1F1;
	margin: 0;
	padding: 0;
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
		<div class="container-fluid row" style="padding: 20px;">
			<div class="col-md-7">
				<img alt=""
					src="<%out.print(request.getContextPath()
					+ "/view/resource/image/poster.jpg");%>"
					width="700" height="400">
			</div>
			<div class="col-md-5">
				<div class="container-fluid row">
					<form action="" class="form" role="form" method="post">
						<div class="form-group jumbotron">
							<p class="text-center">Quên mật khẩu</p>
							<div class="form-group">
								<lable>Vui lòng nhập email đã đăng ký!</lable>
								<input type="email" name="email-validate" id="email-validate"
									class="form-control" placeholder="email">
							</div>
							<div id="noti">
								<%
									if (request.getAttribute("response") != null) {
										String rs = (String) request.getAttribute("response");
										//int x = Integer.parseInt(rs);
										switch (rs) {
										case "0":
											out.print("<span class='glyphicon glyphicon-info-sign' style='color: red;'>	<i id='noti'>Email này không tồn tại trong hệ thống. Vui lòng kiểm tra lại!</i></span>");
											break;
										case "1":
											out.print("<span class='glyphicon glyphicon-ok-sign' style='color: #00B9F2;'>	<i id='noti'>Mật khẩu của bạn đã được thay đổi. Vui lòng kiểm tra email để nhận mật khẩu mới!</i></span>");
											break;
										case "2":
											out.print("<span class='glyphicon glyphicon-info-sign' style='color: red;'>	<i id='noti'>Rất tiếc. Việc gửi mail đã gặp sự cố, vui lòng thử lại sau!</i></span>");
											break;
										default:
											break;
										}
									}
								%>
							</div>
							<div class="form-group form-inline pull-right">
								<button type="submit" class="form-control btn btn-primary">
									Gửi</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<footer class="navbar-fixed-bottom custom_navbar_color">
		<div class="container text-center">
			<address>
				<strong>&copy Nhóm sinh viên UIT</strong> <br> <strong>
					<span class="glyphicon glyphicon-map-marker"></span> Khu phố 6,
					P.Linh Xuân, Q.Thủ Đức, TP.Hồ Chí Minh
				</strong> <br> <strong> <span
					class="glyphicon glyphicon-envelope"></span> <a>uit.recsys@gmail.com</a>
				</strong> <br>
				<strong> <span class="glyphicon glyphicon-earphone"></span>
					084 165 799 0105
				</strong>
			</address>
		</div>
	</footer>
</body>
</html>