<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="wfs.l2t.dto.dtoAccount"%>
<%@ page import="wfs.l2t.model.ModelAccount"%>
<%
String userId = (String) request.getAttribute("user");
ModelAccount account = new ModelAccount();
dtoAccount dtoAcc = account.getAccountById(userId);
%>
<div class="left-menu-toggle"id="left-menu-toggle">
	
	<div class="container-fluid col-md-offset-0">
		<img src="<%out.print(request.getContextPath() + dtoAcc.avatar);%>"
			class="img-rounded" width="170" height="170">
	</div>
	<br>
	<div>
		<ul class="nav nav-stacked nav-pills custom_font_bold" role="tablist">
			<li id='new-job'><a
				href="<%out.print(request.getContextPath()+"/home");%>"><span
					class="glyphicon glyphicon glyphicon-home"></span> Việc Làm Mới</a></li>
			<li id='rec-job'><a
				href="<%out.print(request.getContextPath() + "/recommendation");%>"><span
					class="glyphicon glyphicon glyphicon-pencil"></span> Việc Làm Gợi Ý</a></li>
			<li id='listresume'><a
				href="<%out.print(request.getContextPath() + "/listresume");%>"><span
					class="glyphicon glyphicon-list-alt"></span> Hồ Sơ Của Bạn</a></li>
			<li id="care"><a href="<%out.print(request.getContextPath() + "/care");%>"><span
					class="glyphicon glyphicon-heart"></span> Việc Làm Đã Lưu</a></li>
			<li id="setting"><a
				href="<%out.print(request.getContextPath() + "/settings");%>"><span
					class="glyphicon glyphicon-cog"></span> Thiết Lập Gửi Mail</a></li>

			<li id="help"><a href="<%out.print(request.getContextPath() + "/help");%>"><span
					class="glyphicon glyphicon-question-sign"></span> Trợ Giúp</a></li>
			<li id="about" ><a
				href="<%out.print(request.getContextPath() + "/help?about");%>"><span
					class=" glyphicon glyphicon-info-sign"></span> Về chúng tôi</a></li>
		</ul>
	</div>
</div>
