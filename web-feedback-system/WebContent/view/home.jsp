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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String userId = (String) request.getAttribute("user");
	ModelAccount account = new ModelAccount();
	dtoAccount dtoAcc = account.getAccountById(userId);

	//get category list
	ModelCategory mdc = new ModelCategory();
	List<dtoCategory> categoryList = mdc.getAllCategory();

	int _page = 0;
	if(request.getParameter("page") != null)
		_page = Integer.parseInt(request.getParameter("page"));
	List<Document> results = (List<Document>) request.getAttribute("results");
	int total = (int) request.getAttribute("total");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Tìm kiếm - JobRecSys</title>
<link rel="shortcut icon" href="view/resource/image/logo_uit_icon.ico" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="view/resource/bootstrap/css/bootstrap.min.css">
<script src="view/resource/lib/jquery-2.1.4.min.js"></script>
<script src="view/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="view/resource/lib/notify.js"></script>
<script src="view/resource/lib/LuceneSearcher.js" charset="UTF-8"></script>
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
						<div class="results_search_lucene"
							style="margin-top: 10px; color: #CD0000;">
							<%
								if(request.getAttribute("total") != null){
									out.write("<i>Tìm thấy "+total +" kết quả</i>");
								}
							%>
						</div>
						<%
							out.write("<input class = 'hidden_key' style = 'display:none' value = '" + request.getParameter("search_lucene") + "'>");
						%>
					</div>
					<%
						for (Document doc : results) {
							out.write("<h4><a target = '_blank' href = '/ControllerJobDetail?jobId="+doc.get("JobId")+"'> "+ doc.get("JobName")+"</a></h4>");
						}
					%>
					<%
						int currentPage = Integer.parseInt(request.getAttribute("currentPage").toString());
						int noOfPage = Integer.parseInt(request.getAttribute("noOfPage").toString());
						int end = Integer.parseInt(request.getAttribute("end").toString());;
						int start = Integer.parseInt(request.getAttribute("start").toString());;						
					%>
					<ul class="pagination">
						<%
							if (currentPage != 1){
						%>
						<li><a
							href="?search_lucene=<%=request.getParameter("search_lucene") %>&page=${currentPage - 1}">Previous</a></li>
						<%
							}
						%>
						<%
							for(int i = start; i <= end; i++){
								if(currentPage == i){
						%>
						<li class="active"><a style = "pointer-events: none; cursor:default;" href="#"><%= i%></a></li>
						<%
							}else{
						%>
						<li><a
							href="?search_lucene=<%=request.getParameter("search_lucene") %>&page=<%= i%>"><%= i%></a></li>
						<%
							}}
						%>
						<%
							if(currentPage < noOfPage){
						%>
						<li><a
							href="?search_lucene=<%=request.getParameter("search_lucene") %>&page=${currentPage + 1}">Next</a></li>
						<%
							}
						%>
					</ul>

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

	<script type="text/javascript" charset="UTF-8">
		$(".form_search_lucene").LuceneSearch({
			_server_ : "ControllerSearcher",
		});

		$(document).ready(function() {
			$(".in_search_lucene").val($(".hidden_key").val());
		});
	</script>

</body>
</html>