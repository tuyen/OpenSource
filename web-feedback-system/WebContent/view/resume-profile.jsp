<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="wfs.l2t.dto.*"%>
<%@ page import="wfs.l2t.model.ModelResume"%>
<%@ page import="wfs.l2t.model.ModelAccount"%>
<%@ page import="java.util.List"%>

<%
	String userId = (String) request.getAttribute("user");
	String resumeId = (String) request.getAttribute("id");
	ModelAccount account = new ModelAccount();
	dtoAccount dtoAcc = account.getAccountById(userId);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Hồ sơ của bạn</title>
<link rel="shortcut icon" href="view/resource/image/logo_uit_icon.ico" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/view/resource/bootstrap/css/bootstrap.min.css">
<script
	src="${pageContext.request.contextPath}/view/resource/lib/jquery-2.1.4.min.js"></script>
<script
	src="${pageContext.request.contextPath}/view/resource/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/view/resource/css/resume_profile.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/view/resource/css/theme_customize.css">


<script
	src="${pageContext.request.contextPath}/view/resource/datepicker/js/bootstrap-datepicker.js"></script>


<script
	src="${pageContext.request.contextPath}/view/resource/lib/form_validate.js"></script>
<script
	src="${pageContext.request.contextPath}/view/resource/lib/form_submit.js"></script>

<script src="view/resource/lib/jquery.jpanelmenu.js"></script>
<script src="view/resource/lib/menu.js"></script>
<link rel="stylesheet" href="view/resource/css/menu.css">
<style type="text/css">
hr {
	-moz-border-bottom-colors: none;
	-moz-border-image: none;
	-moz-border-left-colors: none;
	-moz-border-right-colors: none;
	-moz-border-top-colors: none;
	border-color: #EEEEEE -moz-use-text-color #FFFFFF;
	border-style: solid none;
	border-width: 1px 0;
	margin-top: 10px;
	margin-bottom: 5px;
}
</style>

</head>

<body>


	<!-- Xu ly tren submit form -->


	<div class="container">
		<!-- menu top -->
		<jsp:include page="menu/top-menu.jsp" />
		<jsp:include page="menu/left-menu-toggle.jsp" />
		<!-- chia trang thanh 2 cot -->
		<div id="disablingDiv"></div>
		<div class="container-fluid main-content">

			<div class="row">
				<div class="col-md-3">
					<jsp:include page="menu/left-menu.jsp" />
				</div>
				<div class="col-md-9 ">


					<br>
					<%
						ModelResume cvModel = new ModelResume();
									dtoResume cv = cvModel.getResume(resumeId);
					%>
					<div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4>
									Thông tin cá nhân
									<button id="btn_edit_cv" type="button"
										class="btn btn-primary navbar-right custom_margin">Chỉnh
										sửa</button>
								</h4>
							</div>
							<div class="panel-body">

								<div id="form_enter_resume" class="custom_hiden">
									<form id="form_update_profile" name="frm_update_profile"
										role="form" method="post" action="resume">
										<input name="id" type="hidden"
											value="<%out.print(resumeId);%>">
										<div class="form-group">
											<label for="cv_title">Tên của CV:</label> <input type="text"
												name="title_input" required maxlength="80"
												value="<%out.print(cv.resumeTitle);%>" class="form-control"
												id="email" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Vui lòng nhập tên của CV')">
										</div>

										<div class="form-group">
											<label for="cv_title">Họ và tên:</label> <input type="text"
												class="form-control" value="<%out.print(cv.name);%>"
												name="full_name_input" required maxlength="50" id="email" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Vui lòng nhập tên của bạn')">
										</div>
										<div class="form-group">
											<label for="birthday">Ngày Sinh:</label> <input
												class="form-control" id="birthday" readonly
												data-date-format="dd-mm-yyyy" name="birthday_input"
												value="<%out.print(cv.birthday);%>">
										</div>


										<div class="form-group">
											<label for="cv_title">Giới tính:</label> <label
												class="radio-inline"> <input checked="checked"
												type="radio" name="gender_input" value="Nam">Nam
											</label> <label class="radio-inline"> <input type="radio"
												name="gender_input" value="Nữ">Nữ
											</label>
										</div>

										<div class="form-group">
											<label for="cv_title">Tình trạng hôn nhân:</label> <label
												class="radio-inline"> <input value="0"
												checked="checked" type="radio" name="status_select">Chưa
												kết hôn
											</label> <label class="radio-inline"> <input value="1"
												type="radio" name="status_select">Đã kết hôn
											</label>
										</div>

										<div class="form-group">
											<label for="selelect_qt">Quốc tịch:</label> <select
												class="form-control" name="nationality_input"
												id="selelect_qt">
												<option>Vietnam</option>
												<option>Afghanistan</option>
												<option>Albania</option>
												<option>Algeria</option>
												<option>Andorra</option>
												<option>Angola</option>
												<option>Antigua and Barbuda</option>
												<option>Argentina</option>
												<option>Armenia</option>
												<option>Australia</option>
												<option>Austria</option>
												<option>Azerbaijan</option>
												<option>Bahamas</option>
												<option>Bahrain</option>
												<option>Bangladesh</option>
												<option>Barbados</option>
												<option>Belarus</option>
												<option>Belgium</option>
												<option>Belize</option>
												<option>Benin</option>
												<option>Bhutan</option>
												<option>Bolivia</option>
												<option>Bosnia and Herzegovina</option>
												<option>Botswana</option>
												<option>Brazil</option>
												<option>Brunei Darussalam</option>
												<option>Bulgaria</option>
												<option>Burkina Faso</option>
												<option>Burma</option>
												<option>Burundi</option>
												<option>Cambodia</option>
												<option>Cameroon</option>
												<option>Canada</option>
												<option>Cape Verde</option>
												<option>Central African Republic</option>
												<option>Chad</option>
												<option>Chile</option>
												<option>China</option>
												<option>Colombia</option>
												<option>Comoros</option>
												<option>Congo, Republic of</option>
												<option>Congo, Democratic Republic of the</option>
												<option>Costa Rica</option>
												<option>CÃ´te d'Ivoire</option>
												<option>Croatia</option>
												<option>Cuba</option>
												<option>Cyprus</option>
												<option>Czech Republic</option>
												<option>Denmark</option>
												<option>Djibouti</option>
												<option>Dominica</option>
												<option>Dominican Republic</option>
												<option>Ecuador</option>
												<option>Egypt</option>
												<option>El Salvador</option>
												<option>Equatorial</option>
												<option>Eritrea</option>
												<option>Estonia</option>
												<option>Ethiopia</option>
												<option>Fiji</option>
												<option>Finland</option>
												<option>France</option>
												<option>Gabon</option>
												<option>Gambia, The</option>
												<option>Georgia</option>
												<option>Germany</option>
												<option>Ghana</option>
												<option>Greece</option>
												<option>Grenada</option>
												<option>Guatemala</option>
												<option>Guinea</option>
												<option>Guinea-Bissau</option>
												<option>Guyana</option>
												<option>Haiti</option>
												<option>Honduras</option>
												<option>Hungary</option>
												<option>Iceland</option>
												<option>India</option>
												<option>Indonesia</option>
												<option>Iran</option>
												<option>Iraq</option>
												<option>Ireland</option>
												<option>Israel</option>
												<option>Italy</option>
												<option>Jamaica</option>
												<option>Japan</option>
												<option>Jordan</option>
												<option>Kazakhstan</option>
												<option>Kenya</option>
												<option>Kiribati</option>
												<option>Korea, North</option>
												<option>Korea, South</option>
												<option>Kuwait</option>
												<option>Kyrgyzstan</option>
												<option>Laos</option>
												<option>Latvia</option>
												<option>Lebanon</option>
												<option>Lesotho</option>
												<option>Liberia</option>
												<option>Libya</option>
												<option>Liechtenstein</option>
												<option>Lithuania</option>
												<option>Luxembourg</option>
												<option>Macedonia</option>
												<option>Madagascar</option>
												<option>Malawi</option>
												<option>Malaysia</option>
												<option>Maldives</option>
												<option>Mali</option>
												<option>Malta</option>
												<option>Marshall Islands</option>
												<option>Mauritania</option>
												<option>Mauritius</option>
												<option>Mexico</option>
												<option>Micronesia</option>
												<option>Moldova</option>
												<option>Monaco</option>
												<option>Mongolia</option>
												<option>Montenegro</option>
												<option>Morocco</option>
												<option>Mozambique</option>
												<option>Myanmar</option>
												<option>Namibia</option>
												<option>Nauru</option>
												<option>Nepal</option>
												<option>Netherlands</option>
												<option>New Zealand</option>
												<option>Nicaragua</option>
												<option>Niger</option>
												<option>Nigeria</option>
												<option>Norway</option>
												<option>Oman</option>
												<option>Pakistan</option>
												<option>Palau</option>
												<option>Palestinian State</option>
												<option>Panama</option>
												<option>Papua New Guinea</option>
												<option>Paraguay</option>
												<option>Peru</option>
												<option>Philippines</option>
												<option>Poland</option>
												<option>Portugal</option>
												<option>Qatar</option>
												<option>Romania</option>
												<option>Russia</option>
												<option>Rwanda</option>
												<option>St. Kitts and Nevis</option>
												<option>St. Lucia</option>
												<option>St. Vincent and The Grenadines</option>
												<option>Samoa</option>
												<option>San Marino</option>
												<option>SÃ£o TomÃ© and PrÃ­ncipe</option>
												<option>Saudi Arabia</option>
												<option>Senegal</option>
												<option>Serbia</option>
												<option>Seychelles</option>
												<option>Sierra Leone</option>
												<option>Singapore</option>
												<option>Slovakia</option>
												<option>Slovenia</option>
												<option>Solomon Islands</option>
												<option>Somalia</option>
												<option>South Africa</option>
												<option>Spain</option>
												<option>Sri Lanka</option>
												<option>Sudan</option>
												<option>Suriname</option>
												<option>Swaziland</option>
												<option>Sweden</option>
												<option>Switzerland</option>
												<option>Syria</option>
												<option>Taiwan</option>
												<option>Tajikistan</option>
												<option>Tanzania</option>
												<option>Thailand</option>
												<option>Togo</option>
												<option>Tonga</option>
												<option>Trinidad and Tobago</option>
												<option>Tunisia</option>
												<option>Turkey</option>
												<option>Turkmenistan</option>
												<option>Tuvalu</option>
												<option>Uganda</option>
												<option>Ukraine</option>
												<option>United Arab Emirates</option>
												<option>United Kingdom</option>
												<option>United States</option>
												<option>Uruguay</option>
												<option>Uzbekistan</option>
												<option>Vanuatu</option>
												<option>Vatican City</option>
												<option>Venezuela</option>
												<option>Western Sahara</option>
												<option>Yemen, Republic of</option>
												<option>Yugoslavia</option>
												<option>Zaire</option>
												<option>Zambia</option>
												<option>Zimbabwe</option>
											</select>
										</div>


										<div class="form-group">
											<label for="cv_title">Địa chỉ:</label> <input type="text"
												class="form-control" value="<%out.print(cv.address);%>"
												name="address_input" required id="email" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Vui lòng nhập địa chỉ của bạn')">
										</div>


										<div class="form-group">
											<label for="cv_title">Email liên hệ:</label> <input
												type="email" class="form-control"
												value="<%out.print(cv.email);%>" required maxlength="100"
												name="email_input" id="email" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Vui lòng nhập email của bạn')">
										</div>


										<div class="form-group">
											<label for="cv_title">Số điện thoại:</label> <input
												type="text" class="form-control"
												value="<%out.print(cv.phone);%>" required maxlength="15"
												name="phone_input" id="email" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Vui lòng nhập số điện thoại của bạn')">
										</div>

										<div class="form-group">
											<label for="cv_title">Sở thích cá nhân:</label> <input
												type="text" class="form-control"
												value="<%out.print(cv.hobby);%>" id="email"
												name="hobbies_input">
										</div>

										<button type="submit" class="btn btn-primary"
											name="add_resume_button">Cập nhật CV</button>

										<button id="btn_cancel_cv" type="button"
											class="btn btn-primary">Hủy</button>
									</form>
								</div>
								<div id="profile_content">
									<h1>
										<%
											out.print(cv.name);
										%>
									</h1>
									<h5>
										<b>Ngày Sinh:</b>
										<%
											out.print(cv.birthday);
										%>
									</h5>
									<h5>
										<b>Giới tính:</b>
										<%
											out.print(cv.gender);
										%>
									</h5>
									<h5>
										<b>Tình trạng hôn nhân:</b>
										<%
											if (cv.maritalStatus)
																				out.print("Đã kết hôn");
																			else
																				out.print("Chưa kết hôn");
										%>
									</h5>
									<h5>
										<b>Email:</b>
										<%
											out.print(cv.email);
										%>
									</h5>
									<h5>
										<b>Điện thoại:</b>
										<%
											out.print(cv.phone);
										%>
									</h5>
									<h5>
										<b>Địa chỉ:</b>
										<%
											out.print(cv.address);
										%>
									</h5>
									<h5>
										<b>Quốc tịch:</b>
										<%
											out.print(cv.nationality);
										%>
									</h5>
									<h5>
										<b>Sở thích cá nhân:</b>
										<%
											out.print(cv.hobby);
										%>
									</h5>
								</div>
							</div>
						</div>


						<div class="panel panel-default">

							<div class="panel-heading">
								<h4>
									Trình độ học vấn
									<button id="btn_add_education" type="button"
										class="btn btn-primary navbar-right custom_margin">+
										Thêm</button>
								</h4>
							</div>
							<div class="panel-body">

								<div id="form_nhap_education" class="custom_hiden">

									<form id="form_update_education" name="enter_edu" role="form"
										method="post" action="resume">


										<input name="id" type="hidden"
											value="<%out.print(resumeId);%>">


										<div class="form-group">
											<label for="cv_title">Tên trường:</label> <input type="text"
												name="school_name" class="form-control"
												value="Đại Học Công Nghệ Thông Tin - ĐHQG TPHCM" required
												maxlength="100" placeholder="Ex: Đại Học Công Nghệ Thông Tin - ĐHQG TP HCM" id="email" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Vui lòng nhập tên trường mà bạn học')">
										</div>

										<div class="form-group">
											<label for="cv_title">Trình độ đào tạo:</label> <input
												type="text" class="form-control" value="Đại học" required
												maxlength="50" name="education_level" placeholder="Ex: Đại Học" id="email" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Vui lòng nhập trình độ đào tạo')">
										</div>

										<div class="form-group">
											<label for="cv_title">Chuyên ngành đào tạo:</label> <input
												type="text" class="form-control" name="education_major"
												value="Công nghệ phần mềm" required maxlength="80"
												id="email" oninput="setCustomValidity('')" placeholder="Ex: Công Nghệ Phần Mềm" oninvalid="this.setCustomValidity('Vui lòng nhập chuyên ngành đào tạo')">
										</div>

										<div class="form-group">
											<label for="cv_title">Mô tả:</label> <input type="text"
												class="form-control" placeholder="Ex: Học về quy trình phần mềm" name="education_description" id="email">
										</div>

										<div class="form-group">
											<label for="birthday">Ngày bắt đầu:</label> <input
												class="form-control" id="startday_input" readonly
												data-date-format="dd-mm-yyyy" name="startday_input"
												value="01/01/2012">
										</div>

										<div class="form-group">
											<label for="birthday">Ngày kết thúc:</label> <input
												class="form-control" id="endday_input" readonly
												data-date-format="dd-mm-yyyy" name="endday_input"
												value="01/01/2017">
										</div>

										<button type="submit" onclick="return check_date()"
											class="btn btn-primary" name="btn_add_education">Thêm
											trường học</button>

										<button id="btn_cancel_education" type="button"
											class="btn btn-primary">Hủy</button>
									</form>
									<br>
									<script type="text/javascript">
										function getDate(str1) {
											// str1 format should be dd/mm/yyyy. Separator can be anything e.g. / or -. It wont effect
											var dt1 = parseInt(str1.substring(
													0, 2));
											var mon1 = parseInt(str1.substring(
													3, 5));
											var yr1 = parseInt(str1.substring(
													6, 10));
											var date1 = new Date(yr1, mon1, dt1);
											return date1;
										}
										function check_date() {
											try {
												var s = getDate(document.forms.enter_edu.startday_input.value);
												var e = getDate(document.forms.enter_edu.endday_input.value);
												if (s > e) {
													alert("ngày kết thúc phải lớn hơn ngày bắt đầu!");
													return false;
												}
											} catch (e) {
												// TODO: handle exception
												return false;
											}
											return true;
										}
									</script>
								</div>
								<div id="education_content">
									<%
										List<dtoEducation> edu = cvModel.getEducation(resumeId);
										for (dtoEducation i : edu) {

											out.print("<p><b>" + i.schoolName + " "
													+ "<button type=\"button\" onclick=\"location.href='"
													+ "resume?id=" + i.resumeId + "&delete_education="
													+ i.educationId
													+ "'\" class=\"btn btn-danger btn-xs\">Xóa</button>"
													+ " </b><br>");
											out.print("<b>Trình độ</b>: " + i.educationLevel + "<br>");
											out.print("<b>Chuyên ngành</b>:" + i.educationMajor + "<br>");
											out.print("<b>Thời gian đào tạo</b>: Từ ngày " + i.startDate
													+ " đến ngày " + i.endDate + "<br></p><hr>");
										}
									%>
								</div>

							</div>
						</div>


						<div class="panel panel-default">
							<div class="panel-heading">

								<h4>
									Trình độ ngoại ngữ
									<button id="btn_add_language" type="button"
										class="btn btn-primary navbar-right custom_margin">+
										Thêm</button>
								</h4>
							</div>
							<div class="panel-body">
								<div id="form_nhap_language" class="custom_hiden">
									<form id="form_update_language" role="form" method="post"
										action="resume">


										<input name="id" type="hidden"
											value="<%out.print(resumeId);%>">

										<div class="form-group">
											<label for="cv_title">Tên chứng chỉ:</label> <input
												type="text" name="language_name" placeholder="Ex: TOEIC"
												required maxlength="80" class="form-control" id="email" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Vui lòng nhập tên của chứng chỉ')">
										</div>

										<div class="form-group">
											<label for="cv_title">Cấp độ/Điểm:</label> <input type="text"
												class="form-control" placeholder="Ex: Điểm: 990"
												name="language_level" required maxlength="80" id="email" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Vui lòng nhập cấp độ/điểm số của chứng chỉ')">
										</div>

										<button type="submit" class="btn btn-primary"
											name="btn_add_language">Thêm bằng cấp</button>

										<button id="btn_cancel_language" type="button"
											class="btn btn-primary">Hủy</button>
									</form>
									<br>
								</div>
								<div id="language_content">
									<%
										List<dtoLanguage> languages = cvModel.getAllLanguage(resumeId);
										for (dtoLanguage i : languages) {
											out.print("<p>");
											out.print("<b>Chứng chỉ:</b> "
													+ i.name
													+ "   <button type=\"button\" onclick=\"location.href='"
													+ "resume?id=" + i.resumeId + "&delete_language="
													+ i.languageId
													+ "'\" class=\"btn btn-danger btn-xs\">Xóa</button>"
													+ "");
											out.print("<br><b>Cấp độ/Điểm:</b> " + i.level);
											out.print("</p><hr>");
										}
									%>
								</div>
							</div>
						</div>


						<div class="panel panel-default">
							<div class="panel-heading">

								<h4>
									Kinh nghiệm làm việc
									<button id="btn_add_experience" type="button"
										class="btn btn-primary navbar-right custom_margin">+
										Thêm</button>
								</h4>
							</div>
							<div class="panel-body">
								<div id="form_nhap_experience" class="custom_hiden">
									<form id="form_update_experience" role="form" method="post"
										action="resume">


										<input name="id" type="hidden"
											value="<%out.print(resumeId);%>">

										<div class="form-group">
											<label for="cv_title">Tên Công Ty:</label> <input type="text"
												name="company_name" class="form-control"
												placeholder="Ex: Google Inc" required maxlength="100"
												id="email" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Vui lòng nhập tên của công ty')">
										</div>

										<div class="form-group">
											<label for="cv_title">Tên Công Việc:</label> <input
												type="text" class="form-control"
												placeholder="Ex: Tư vấn tài chính" required maxlength="100"
												name="job_name" id="email" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Vui lòng nhập tên của công việc')">
										</div>

										<div class="form-group">
											<label for="cv_title">Vị Trí:</label> <input type="text"
												class="form-control" placeholder="Ex: Nhân Viên" required
												maxlength="50" name="job_position" id="email" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Vui lòng nhập chức vụ của bạn')">
										</div>

										<div class="form-group">
											<label for="cv_title">Mô Tả:</label> <input type="text"
												class="form-control"
												placeholder="Ex: Làm thư ký văn phòng, hỗ trợ, tư vấn khách hàng"
												required maxlength="150" name="job_description" id="email" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Vui lòng nhập mô tả công việc')">
										</div>

										<div class="form-group">
											<label for="cv_title">Thời Gian:</label> <input type="text"
												class="form-control" placeholder="Ex: Từ 2012 đến 2015"
												required maxlength="60" name="job_time" id="email" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Vui lòng nhập thời gian làm việc')">
										</div>
										<button type="submit" class="btn btn-primary"
											name="btn_add_experience">Thêm kinh nghiệm</button>

										<button id="btn_cancel_experience" type="button"
											class="btn btn-primary">Hủy</button>
									</form>
									<br>
								</div>

								<div id="experience_content">
									<%
										List<dtoExperience> epx = cvModel.getExperience(resumeId);
										for (dtoExperience i : epx) {
											out.print("<p>");
											String url = "   <button type=\"button\" onclick=\"location.href='"
													+ "resume?id="
													+ i.resumeId
													+ "&delete_experience="
													+ i.experienceId
													+ "'\" class=\"btn btn-danger btn-xs\">Xóa</button>";

											out.print("<h4>" + i.jobTitle + " tại " + i.companyName + " "
													+ url + " </h4>");
											out.print("<b>Vị Trí: </b>" + i.position + "<br>");
											out.print("<b>Mô Tả: </b>" + i.description + "<br>");
											out.print("<b>Thời Gian: </b>" + i.period);
											out.print("</p><hr>");
										}
									%>
								</div>
							</div>
						</div>


						<div class="panel panel-default">
							<div class="panel-heading">
								<h4>
									Kỹ năng
									<button id="btn_add_skill" type="button"
										class="btn btn-primary navbar-right custom_margin">+
										Thêm</button>

								</h4>
							</div>
							<div class="panel-body">
								<div id="form_nhap_skill" class="custom_hiden">
									<form id="form_update_skills" role="form" method="post"
										action="resume">

										<input name="id" type="hidden"
											value="<%out.print(resumeId);%>">

										<div class="form-group">
											<label for="cv_title">Tên Kỹ Năng:</label> <input type="text"
												name="skill_name" class="form-control"
												placeholder="Ex: Giao tiếp" required maxlength="100"
												id="email" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Vui lòng nhập tên kỹ năng')">
										</div>

										<div class="form-group">
											<label for="cv_title">Mô Tả/Level:</label> <input type="text"
												class="form-control" placeholder="Ex: Rất tốt" required
												maxlength="100" name="skill_level" id="email" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Vui lòng nhập mô tả kỹ năng, cấp độ')">
										</div>
										<button type="submit" class="btn btn-primary"
											name="btn_add_skill">Thêm kỹ năng</button>

										<button id="btn_cancel_skill" type="button"
											class="btn btn-primary">Hủy</button>
									</form>
									<br>

								</div>
								<div id="skill_list">
									<%
										List<dtoSkill> skills = cvModel.getSkill(resumeId);
										for (dtoSkill i : skills) {
											String url = "   <button type=\"button\" onclick=\"location.href='"
													+ "resume?id="
													+ i.resumeId
													+ "&delete_skill="
													+ i.skillId
													+ "'\" class=\"btn btn-danger btn-xs\">Xóa</button>";
											out.print("<p>");
											out.print("<p><b>");
											out.print(i.name + " " + url);
											out.print("</b></p>");
											out.print(i.level);
											out.print("<hr></p>");
										}
									%>

								</div>

							</div>
						</div>


						<div class="panel panel-default">
							<div class="panel-heading">
								<h4>
									Mục tiêu nghề nghiệp
									<button id="btn_add_objective" type="button"
										class="btn btn-primary navbar-right custom_margin">Cập
										nhật</button>
								</h4>
							</div>



							<div class="panel-body">
								<div id="form_nhap_objective" class="custom_hiden">

									<%
										dtoCareerObjective objective = cvModel.getObjective(resumeId);
									%>

									<form id="form_update_objective" role="form" method="post"
										action="resume">

										<input name="id" type="hidden"
											value="<%out.print(resumeId);%>">


										<div class="form-group">
											<label for="cv_title">Mức lương mong muốn(VND):</label> <input
												type="text" value="<%out.print(objective.desireSalary);%>" onkeypress='return (event.charCode == 0)||(event.charCode >= 48 && event.charCode <= 57)'
												name="desireSalary" placeholder="Ex. 10000000" maxlength="10" class="form-control"
												id="email">
										</div>

										<div class="form-group">
											<label for="cv_title">Mức lương gần đây(VND):</label> <input
												type="text" name="recentSalary" onkeypress='return (event.charCode == 0)||(event.charCode >= 48 && event.charCode <= 57)'
												value="<%out.print(objective.recentSalary);%>"
												class="form-control" placeholder="Ex. 10000000" maxlength="10" id="email">
										</div>

										<div class="form-group">
											<label for="cv_title">Cấp bậc mong muốn:</label> <input
												type="text" name="desireCareerLevel" maxlength="50"
												value="<%out.print(objective.desireCareerLevel);%>"
												class="form-control" placeholder="Ex. Giám Đốc" id="email">
										</div>

										<div class="form-group">
											<label for="cv_title">Loại công việc:</label> <input
												type="text" name="positionType"
												value="<%out.print(objective.positionType);%>"
												class="form-control" id="email" placeholder="Ex. Toàn Thời Gian" maxlength="50">
										</div>

										<div class="form-group">
											<label for="cv_title">Nơi làm việc ưa thích:</label> <input
												type="text" name="desireWorkLocation"
												value="<%out.print(objective.desireWorkLocation);%>"
												class="form-control" id="email" placeholder="Ex. TP Hồ Chí Minh" maxlength="30">
										</div>



										<div class="form-group">
											<label for="txt_CareerObjective">Mục tiêu nghề
												nghiệp:</label>
											<%
												out.print("<textarea class=\"form-control\" rows=\"5\" id=\"txt_CareerObjective\" name=\"CareerObjective\">"
														+ objective.careerObjective + "</textarea>");
											%>

										</div>

										<div class="form-group">
											<label class="checkbox-inline"> <input
												name="opt_object" type="checkbox" value="willingToRelocate"
												<%if (objective.willingToRelocate.equals("1"))
				out.print("checked");%>>Có
												thể đổi chỗ ở
											</label> <label class="checkbox-inline"><input
												name="opt_object" type="checkbox" value="WillingToTravel"
												<%if (objective.willingToTravel.equals("1"))
				out.print("checked");%>
												name="opt_object">Có thể đi công tác</label>
										</div>

										<button type="submit" class="btn btn-primary"
											name="btn_add_objective">Cập nhật</button>

										<button id="btn_cancel_objective" type="button"
											class="btn btn-primary">Hủy</button>
									</form>
									<br>
								</div>
								<div id="objective_content">
									<p>
										<b> Mức lương mong muốn(VND): </b>
										<%
											out.print(objective.desireSalary);
										%>
									</p>
									<hr>
									<p>
										<b> Mức lương gần đây(VND):</b>
										<%
											out.print(objective.recentSalary);
										%>
									</p>
									<hr>
									<p>
										<b> Loại công việc:</b>
										<%
											out.print(objective.positionType);
										%>
									</p>
									<hr>
									<p>
										<b> Cấp bậc mong muốn:</b>
										<%
											out.print(objective.desireCareerLevel);
										%>
									</p>
									<hr>
									<p>
										<b> Nơi làm việc mong muốn:</b>
										<%
											out.print(objective.desireWorkLocation);
										%>
									</p>
									<hr>
									<p>
										<b> Có thể đi công tác:</b>
										<%
											if (objective.willingToTravel.equals("1"))
												out.print("Có thể");
											else
												out.print("Không");
										%>
									</p>
									<hr>
									<p>
										<b> Có thể đổi chỗ ở:</b>
										<%
											if (objective.willingToRelocate.equals("1"))
												out.print("Có thể");
											else
												out.print("Không");
										%>
									</p>
									<hr>
									<p>
										<b> Mục tiêu nghề nghiệp:</b>
									</p>
									<%
										String s = objective.careerObjective;
										out.print("<pre class=\"pull-left\">" + s.trim() + "</pre>");
									%>
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