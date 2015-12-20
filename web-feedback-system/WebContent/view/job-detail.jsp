<%@page import="org.apache.lucene.document.Document"%>
<%@page import="org.apache.lucene.search.TopDocs"%>
<%@page import="wfs.l2t.dto.dtoResume"%>
<%@page import="wfs.l2t.model.ModelResume"%>
<%@page import="wfs.l2t.dto.dtoCategory"%>
<%@page import="wfs.l2t.model.ModelCategory"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="wfs.l2t.dto.dtoJob"%>
<%@ page import="wfs.l2t.model.ModelJob"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@ page import="wfs.l2t.controller.ControllerHome"%>
<%@page import="wfs.l2t.dto.dtoAccount"%>
<%@ page import="wfs.l2t.model.ModelAccount"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.FileReader"%>
<%@ page import="java.io.IOException"%>
<%
	String userId = (String) request.getAttribute("user");
	ModelAccount account = new ModelAccount();
	dtoAccount dtoAcc = account.getAccountById(userId);

	ModelJob mdj = new ModelJob();
	dtoJob job = mdj.getJob(request.getParameter("jobId"));
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title><%= job.jobName %> - JobRecSys</title>
<link rel="shortcut icon" href="view/resource/image/logo_uit_icon.ico" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="view/resource/bootstrap/css/bootstrap.min.css">
<script src="view/resource/lib/jquery-2.1.4.min.js"></script>
<script src="view/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="view/resource/lib/notify.js"></script>
<script src="view/resource/lib/LuceneSearcher.js"></script>
<link rel="stylesheet" href="view/resource/css/theme_customize.css">

<style>
.panel-footer {
	background-color: #F6F7F8;
}

.panel.search {
	padding: 40px;
	background-color: #00B9F2;
	margin-top: 20px;
}

.dropdown-menu {
	min-width: auto;
	width: 100%;
}

.scrollable-menu {
	height: auto;
	max-height: 200px;
	overflow-x: hidden;
}
</style>

</head>
<body>

	<div class="container flip-container">
		<!-- menu top -->
		<jsp:include page="menu/top-menu.jsp"></jsp:include>

		<!-- chia trang thanh 2 cot -->

		<div class="container-fluid main-content">
			<div class="row flip-container">
				<div class="col-md-3">
					<jsp:include page="menu/left-menu.jsp" />
				</div>
				<div class="col-md-9 custom_background_color flip-container"
					style="min-height: 500px">
					<div class="panel search panel-default">
						<div class="form_search_lucene"></div>

					</div>
					<div class = "panel panel-info">
						<div class = "panel-heading">
							<h3><%= job.jobName %></h3>
						</div>
						<div class = "panel-body" >
							<pre><b>Công ty: </b> <%= job.company %> </pre>
							<pre><b>Địa chỉ: </b> <%= job.location %> </pre>
							<pre><b>Lương: </b> <%= job.salary %> </pre>
							<pre style = "text-align: justify;"><b>Mô tả: </b> <%= job.description %> </pre>
							<pre style = "text-align: justify;"><b>Yêu cầu: </b> <%= job.requirement %> </pre>
							<pre style = "text-align: justify;"><b>Lợi ích: </b> <%= job.benifit %> </pre>
							<pre><b>Ngày hết hạn: </b> <%= job.expired %> </pre>
							<pre><b>Nguồn: </b> <a href = '<%= job.source %>' target = '_blank'><%= job.source %></a> </pre>
						</div>
					</div>
					<!-- loading icon -->
					<div id="loading" style="margin: 0 auto;"
						class="loading-icon custom_hiden"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- go to top button -->
	<a href="#" class="go-to-top"></a>
	<script type="text/javascript" src="view/resource/lib/home.js"></script>

	<script type="text/javascript">
		$(".form_search_lucene").LuceneSearch({
			_server_ : "ControllerSearcher",
		});
	</script>
</body>
</html>