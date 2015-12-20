/**
 * 
 */

// xu ly form nhap mục tiêu nghê nghiep
$(document).ready(function() {

	// bấm nút thêm education
	$("#btn_add_objective").click(function() {

		$("#form_nhap_objective").slideDown();
		$("#btn_add_objective").hide();
	});

	// bấm nút hủy add education
	$("#btn_cancel_objective").click(function() {

		$("#form_nhap_objective").slideUp();
		$("#btn_add_objective").show();
	});
});

$(document).ready(function() {

	// bấm nút thêm education
	$("#btn_add_education").click(function() {

		$("#form_nhap_education").slideDown();
		$("#btn_add_education").hide();
	});

	// bấm nút hủy add education
	$("#btn_cancel_education").click(function() {

		$("#form_nhap_education").slideUp();
		$("#btn_add_education").show();
	});

	$('#startday_input').datepicker({
		format : 'dd/mm/yyyy'
	});

	$('#endday_input').datepicker({
		format : 'dd/mm/yyyy'
	});
});

// xu ly form nhap ngoai ngu
$(document).ready(function() {

	// bấm nút thêm education
	$("#btn_add_language").click(function() {

		$("#form_nhap_language").slideDown();
		$("#btn_add_language").hide();
	});

	// bấm nút hủy add education
	$("#btn_cancel_language").click(function() {

		$("#form_nhap_language").slideUp();
		$("#btn_add_language").show();
	});
});

// xu ly form nhap kinh nghiệm
$(document).ready(function() {

	// bấm nút thêm education
	$("#btn_add_experience").click(function() {

		$("#form_nhap_experience").slideDown();
		$("#btn_add_experience").hide();
	});

	// bấm nút hủy add education
	$("#btn_cancel_experience").click(function() {

		$("#form_nhap_experience").slideUp();
		$("#btn_add_experience").show();
	});
});

// xu ly form nhap kỹ năng
$(document).ready(function() {

	// bấm nút thêm kỹ năng
	$("#btn_add_skill").click(function() {

		$("#form_nhap_skill").slideDown();
		$("#btn_add_skill").hide();
	});

	// bấm nút hủy add education
	$("#btn_cancel_skill").click(function() {

		$("#form_nhap_skill").slideUp();
		$("#btn_add_skill").show();
	});
});

$(document).ready(function() {

	// bấm nút thêm cv
	$("#btn_edit_cv").click(function() {

		$("#form_enter_resume").slideDown();
		$("#btn_edit_cv").hide();
		
	});

	// bấm nút hủy cv
	$("#btn_cancel_cv").click(function() {

		$("#form_enter_resume").slideUp();
		$("#btn_edit_cv").show();		
	});

	$('#birthday').datepicker({
		format : 'yyyy/mm/dd'
	});
});
