$(document).ready(function(){
	$("form[name='add-resume-form']").hide();
	$("#add-resume-link").click( function(){
		$("form[name='add-resume-form']").show();
	});
	$("#cancel-add-resume-button").click(function(){
		$("form[name='add-resume-form']").hide();
	});
});