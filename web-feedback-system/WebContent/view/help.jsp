<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Trợ giúp</title>
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
		<!-- menu top -->
		<jsp:include page="menu/top-menu.jsp" />

		<!-- chia trang thanh 2 cot -->

		<div class="container-fluid main-content">

			<div class="row">
				<div class="col-md-3">
					<jsp:include page="menu/left-menu.jsp" />
				</div>
				<div class="col-md-9">
					<br> <br>
					<div class="jumbotron">
						<h1>Hướng dẫn!</h1>
						<p>
							Đây là hệ thống giới thiệu việc làm. Hiện tại chúng tôi sẽ thu
							thập việc làm và giới thiệu cho bạn. Bạn sẽ nhận được các việc
							làm mới nhất từ các lĩnh vực mà bạn quan tâm. Để phục vụ các bạn
							tốt hơn chúng tôi mong muốn nhận được các phản hồi từ các bạn đối
							với các công việc được đề nghị để phục vụ các bạn tốt hơn! <br>
							Các tính năng chính của hệ thống:
						<ol>
							<li><b>Xem việc làm mới:</b> <br> Mỗi ngày, hệ thống sẽ
								tự động thu thập các việc làm mới nhất từ mọi ngành nghề. Các
								việc làm này được lấy từ các website tuyển dụng hàng đầu như
								careerlink, vietnamworks,.. Các bạn có thể xem thông tin các
								việc làm này từ menu <b>"Việc Làm Mới"</b>.<br> <br></li>
							<li><b>Xem công việc gợi ý: </b><br> Hệ thống sẽ tổng
								hợp và phân tích CV của bạn để cung cấp các công việc có thể bạn
								quan tâm. Hệ thống sẽ gửi mail thông báo các công việc mà bạn có
								thể thích. Để tăng tính chính xác bạn hãy lưu lại các công việc
								mà bạn cảm thấy phù hợp. Chúng tôi sẽ dùng thông tin này để tìm
								ra các công việc tương tự để phục vụ bạn tốt hơn. Để sử dụng
								tính năng này bạn hãy chọn menu <b>"Việc Làm Gợi Ý"</b>.<br>
								<br></li>
							<li><b>Xem hồ sơ của bạn:</b> <br> Đây là tính năng cho
								phép bạn xem hồ sơ(CV) của mình, thêm hoặc chỉnh sửa hồ sơ. Bạn
								có thể sử dụng tính năng này bằng cách truy cập menu <b>" HỒ
									SƠ CỦA BẠN"</b>.<br> <br></li>

							<li><b>Xem việc làm đã lưu:</b> <br> Bạn có thể xem lại
								các thông tin về các công việc mà mình đã lưu ở đây. Đồng thời
								hệ thống sẽ sử dụng các thông tin này để tìm kiếm các công việc
								có thể bạn quan tâm. Để sử dụng tính năng này, hãy truy cập menu
								<b>"Việc Làm Gợi Ý"</b>.<br> <br></li>
							<li><b>Thiết lập gửi mail:</b> <br> Hệ thống chúng tôi
								sẽ gửi việc làm định kỳ cho các bạn qua email. Để thuận tiện,
								chúng tôi cung cấp tính năng này để các bạn có thể lựa chọn các
								lĩnh vực việc làm mà bạn quan tâm, số việc làm mà bạn muốn nhận
								trong một email, và thời điểm mà bạn muốn nhận mail. Để sử dụng
								tính năng này bạn có thể truy cập menu <b>"Thiết Lập Gửi
									Mail"</b>.<br> <br></li>
						</ol>

					</div>

				</div>
			</div>
		</div>
	</div>




</body>
</html>






