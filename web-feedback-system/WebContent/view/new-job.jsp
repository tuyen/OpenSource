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

	//get category list
	ModelCategory mdc = new ModelCategory();
	List<dtoCategory> categoryList = mdc.getAllCategory();

	//get resume to check condition show notification invite user input a CV
	ModelResume mdr = new ModelResume();
	List<dtoResume> listResume = mdr.getUserResumes(userId);

	String link = "";
	try {
		BufferedReader in = new BufferedReader(new FileReader(
				getServletContext().getResource(
						"/WEB-INF/url-config.txt").getPath()));
		String str;
		while ((str = in.readLine()) != null) {
			link += str;
		}
		in.close();
	} catch (IOException e) {
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>JobRecSys - Việc làm mới</title>
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
				<div class="col-md-9 custom_background_color flip-container" style="min-height: 500px">
					<div class="panel search panel-default">
						<div class = "form form_search_lucene"></div>						
					</div>
					<!-- invite user input their CV -->
					<%
						if (listResume.size() == 0) {
							out.print("<div id='tt' class='alert alert-danger' style = 'border-color:red;margin-bottom:10px;'> <p class='text-center'><b>Bạn chưa có CV nào trong tủ hồ sơ! Vui lòng <a href='"
									+ request.getContextPath()
									+ "/listresume"
									+ "'>Tạo CV</a> ngay bây giờ để nhận được những việc làm phù hợp với bạn!</b></p></div>");
						}
					%>
									
					<!-- show jobs -->
					<div id="content-wrapper" class="panel-group">				
					</div>
					<!-- loading icon -->
					<div id="loading" style="margin: 0 auto;" class="loading-icon custom_hiden"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- go to top button -->
	<a href="#" class="go-to-top"></a>
	<script type="text/javascript" src="view/resource/lib/job-utility.js"></script>
	<script type="text/javascript">
	$(".form_search_lucene").LuceneSearch({
		_server_	:	"ControllerSearcher",
	});
	</script>
</body>
</html>