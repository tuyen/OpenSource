<%@page import="wfs.l2t.dto.dtoAccount"%>
<%@page import="wfs.l2t.dto.dtoCategory"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="wfs.l2t.model.ModelCategory"%>
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
<title>Thiết lập gửi mail</title>
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
		<jsp:include page="menu/top-menu.jsp"/>
		
		<!-- chia trang thanh 2 cot -->
		
		<div class="container-fluid main-content">
			<div class="row">
				<div class="col-md-3">
					<jsp:include page="menu/left-menu.jsp"/>
				</div>
				<div class="col-md-9 ">
					<br>
					<!-- Menu chỉnh thời gian nhận mail -->
					<div class="container-fluid">
						<div class="row">
							<%
								//lay danh account hien tai	
								String currentUserId = (String) request.getAttribute("user");

								ModelAccount ac = new ModelAccount();
								dtoAccount acc_info = ac.getAccountById(currentUserId);
							%>
							<div class="col-md-6">
								<div class="panel panel-default">
									<div class="panel-heading"><h4>Thời gian nhận mail:</h4></div>
									<div class="panel-body">
										<form role="form" id="form-time-email" action="settings"
											method="post">
											<div class="form-group">
												<div>
													<label class="radio-inline"><input type="radio" value="daily"
														name="fte-rdo-time"
														<%if (acc_info.timeReceiveEmail.equals("daily"))
				out.print("checked=\"checked\"");%>>Hàng
														ngày </label>
														
														<label class="radio-inline"><input type="radio" value="weekly"
														name="fte-rdo-time"
														<%if (acc_info.timeReceiveEmail.equals("weekly"))
				out.print("checked=\"checked\"");%>>Hàng
														tuần </label>
														<label class="radio-inline"><input type="radio" value="monthly"
														name="fte-rdo-time"
														<%if (acc_info.timeReceiveEmail.equals("monthly"))
				out.print("checked=\"checked\"");%>>Hàng
														tháng </label>
												</div>																								
											</div>
											<div class="form-group custom_margin">
												<button type="submit" name="fte-btn-submit"
													class="btn btn-primary navbar-right">Lưu lại</button>
											</div>
										</form>

									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div class="panel panel-default">
									<div class="panel-heading"><h4>Số công việc một lần gửi:</h4></div>
									<div class="panel-body">
										<form action="settings" method="post" role="form"
											id="form-number-email">
											<div class="form-group">
												<div>
													<label class="radio-inline"><input type="radio" value="5"
														name="fne_rdo_number"
														<%if (acc_info.numberReceiveEmail.equals("5"))
				out.print("checked=\"checked\"");%>>Từ
														0 - 5</label>
														
														<label class="radio-inline"><input type="radio" value="10"
														name="fne_rdo_number"
														<%if (acc_info.numberReceiveEmail.equals("10"))
				out.print("checked=\"checked\"");%>>Từ
														5 - 10</label>
														
														<label class="radio-inline"><input type="radio" value="20"
														name="fne_rdo_number"
														<%if (acc_info.numberReceiveEmail.equals("20"))
				out.print("checked=\"checked\"");%>>Từ
														10 - 20</label>
												</div>
											</div>
											<div class="form-group custom_margin">
												<button type="submit" class="btn btn-primary navbar-right"
													value="submit" name="fne-btn-submit">Lưu lại</button>
											</div>
										</form>

									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- Chọn lĩnh vực người dùng quan tâm -->
					<div>
						<div class="panel panel-default">
							<div class="panel-heading">Chọn lĩnh vực mà bạn quan tâm</div>
							<div class="panel-body">
								<div>
									<form role="form" action="settings" id="form-care-category"
										method="post">
										<div class="form-group">
											<div class="container-fluid">
												<div class="row">
													<%
														ModelCategory category = new ModelCategory();
														List<dtoCategory> data = category
																.getAllCategoryByUser(currentUserId);
														int index1 = 0;
														int index2 = 0;
														int index3 = 0;
														int item_per_row = 0;
														if (!data.isEmpty()) {
															item_per_row = data.size() / 3;
															index1 = item_per_row;
															index2 = item_per_row * 2;
															index3 = data.size();
														}
													%>
													<div class="col-md-4">
														<div class="checkbox">
															<%
																if (!data.isEmpty()) {
																	for (int i = 0; i < index1; i++) {
																		dtoCategory tmp = data.get(i);
																		out.print("<label class=\"checkbox\"> <input name=\"fcc_ck_cat\" type=\"checkbox\" "
																				+ tmp.checked
																				+ " value=\""
																				+ tmp.categoryId
																				+ "\">" + tmp.categoryName + "</label>");
																	}
																}
															%>
														</div>
													</div>
													<div class="col-md-4">
														<div class="checkbox">
															<%
																if (!data.isEmpty()) {
																	for (int i = index1; i < index2; i++) {
																		dtoCategory tmp = data.get(i);
																		out.print("<label class=\"checkbox\"> <input  name=\"fcc_ck_cat\" type=\"checkbox\" "
																				+ tmp.checked
																				+ " value=\""
																				+ tmp.categoryId
																				+ "\">" + tmp.categoryName + "</label>");
																	}
																}
															%>
														</div>
													</div>
													<div class="col-md-4">
														<div class="checkbox">
															<%
																if (!data.isEmpty()) {
																	for (int i = index2; i < index3; i++) {
																		dtoCategory tmp = data.get(i);
																		out.print("<label class=\"checkbox\"> <input  name=\"fcc_ck_cat\"  type=\"checkbox\" "
																				+ tmp.checked
																				+ " value=\""
																				+ tmp.categoryId
																				+ "\" >" + tmp.categoryName + "</label>");
																	}
																}
															%>
														</div>
													</div>
													<div class="form-group custom_margin">
														<button type="submit" value="submit" name="fcc-btn-submit"
															class="btn btn-primary navbar-right">Lưu lại</button>
													</div>
									</form>
								</div>
							</div>
						</div>
					</div>


				</div>
			</div>
		</div>
	</div>
	</div>
	</div>

	</div>
</body>
</html>






