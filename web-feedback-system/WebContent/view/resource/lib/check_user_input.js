function create_message(message) {
	var data = "<div class=\"alert alert-danger\">"
			+ "<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>"
			+ "<strong>Lỗi!</strong> " + message + " </div>";
	return data;
}

function set_message(message) {
	var div_message = document.getElementById("error_message");
	div_message.innerHTML = create_message(message);
}

function validate_form() {
	// nhập cv
	if (document.forms.form_nhap_cv["title-input"].value.length > 80) {
		document.forms.form_nhap_cv["title-input"].style.background = "#FAE6E6";
		set_message("Tên của CV phải ít hơn 80 ký tự!");
		location.href = "#top";
		return false;
	}

	if (document.forms.form_nhap_cv["title-input"].value.length == 0) {
		document.forms.form_nhap_cv["title-input"].style.background = "#FAE6E6";
		set_message("Tên của CV không được trống!");
		location.href = "#top";
		return false;
	}
	// nhập họ và tên
	if (document.forms.form_nhap_cv["full-name-input"].value.length > 50) {
		document.forms.form_nhap_cv["full-name-input"].style.background = "#FAE6E6";
		set_message("Tên của bạn phải ít hơn 50 ký tự!");
		location.href = "#top";
		return false;
	}

	if (document.forms.form_nhap_cv["full-name-input"].value.length == 0) {
		document.forms.form_nhap_cv["full-name-input"].style.background = "#FAE6E6";
		set_message("Tên của bạn không được để trống!");
		location.href = "#top";
		return false;
	}

	// địa chỉ
	if (document.forms.form_nhap_cv["address-input"].value.length == 0) {
		document.forms.form_nhap_cv["address-input"].style.background = "#FAE6E6";
		set_message("Địa chỉ không được để trống!");
		location.href = "#top";
		return false;
	}
	// email
	if (document.forms.form_nhap_cv["email-input"].value.length == 0) {
		document.forms.form_nhap_cv["email-input"].style.background = "#FAE6E6";
		set_message("Email không được để trống!");
		location.href = "#top";
		return false;
	}
	// số điện thoại
	if (document.forms.form_nhap_cv["phone-input"].value.length > 15) {
		document.forms.form_nhap_cv["phone-input"].style.background = "#FAE6E6";
		set_message("Số điện thoại phải ít hơn 15 ký tự!");
		location.href = "#top";
		return false;
	}

	if (document.forms.form_nhap_cv["phone-input"].value.length == 0) {
		document.forms.form_nhap_cv["phone-input"].style.background = "#FAE6E6";
		set_message("Số điện thoại không được để trống!");
		location.href = "#top";
		return false;
	}
	return true;
}