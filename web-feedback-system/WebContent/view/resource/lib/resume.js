/**
 * 
 */
function removeEduEven(id)
{
	$.ajax({
		type: "POST",
		url: "resume",
		data: {
			eduRemoveId:id,
		}
	});
}
function removeExpEven(id)
{
	$.ajax({
		type: "POST",
		url: "resume",
		data: {
			expRemoveId:id,
		}
	});
}
function removeSkillEven(id)
{
	$.ajax({
		type: "POST",
		url: "resume",
		data: {
			skillRemoveId:id,
		}
	});
}
function removeRefEven(id)
{
	$.ajax({
		type: "POST",
		url: "resume",
		data: {
			RefRemoveId:id,
		}
	});
}


$(document).ready(function() {

		$("form").attr("class","collapse");
		$("#male-checkbox").attr("checked", false);
		$("#female-checkbox").attr("checked", false);
		//personal
		
		$("#personal-edit-button").click(function() {
			$("#personal-list").hide();
			$("#personal-form").show();
			
		});
		$("#personal-submit,#personal-cancel").click(function() {
			$("#personal-list").show();
			$("#personal-form").hide();
		});
		$("#female-checkbox").click(function(){
			$("#male-checkbox").attr("checked", false);
		})
		$("#male-checkbox").click(function(){
			$("#female-checkbox").attr("checked", false);
		});
		//contact
		$("#contact-edit-button").click(function() {
			$("#contact-list").hide();
			$("#contact-form").show();
		});
		$("#contact-submit, #contact-cancel").click(function() {
			$("#contact-list").show();
			$("#contact-form").hide();
		});
		//education
		$("[name='education-remove-button']").click(function() {
			$(this).parent().parent().hide();
		});
		$("#education-submit, #education-cancel").click(function() {
			$("#education-list").show();
			$("#education-form").hide();
		});
//		$("#education-add-button").click(function() {
//			$("form[id='education-form'] input[type='text']").attr("value","");
//		});
		//experience
//		$("#experience-edit-button").click(function() {
//			$("#experience-form").attr("class", "collapse in");
//			$("#experience-form").attr("style","");
//			$("#experience-list").hide();
//			$("#experience-form").show();
//		});
		
		$("[name='experience-remove-button']").click(function(){
			$(this).parent().parent().hide();
		});
		$("#experience-submit, #experience-cancel").click(function() {
			$("#experience-list").show();
			$("#experience-form").hide();
		});
		$("#experience-add-button").click(function() {
			
			$("form[id='experience-form'] input[type='text']").attr("value","");
		});
		
		//skill
		$("[name='skill-remove-button']").click(function(){
			$(this).parent().parent().parent().parent().hide();
		});
		$("#skill-add-submit, #skill-add-cancel").click(function(){
			$("#skill-edit-form").hide();
			$("#skill-add-form").hide();
			$("#skill-list").show();
		});
		//reference
		$("[name='reference-remove-button']").click(function(){
			$(this).parent().parent().parent().hide();
		});
		$("#reference-submit, #reference-cancel").click(function(){
			$("#referenceform").hide();
			$("#reference-list").show();
		});
		$("#reference-add-button").click(function() {
			$("form[id='reference-form'] input[type='text']").attr("value","");
		});
		//hobbies
		$("#hobbies-edit-button").click(function() {
			$("#hobbies-edit-form").attr({class:"collapse in", style:""});
			$("#hobbies-list").hide();
			$("#hobbies-edit-form").show();
		});
		$("#hobbies-edit-submit, #hobbies-edit-cancel, #hobbies-add-submit, #hobbies-add-cancel").click(function() {
			$("#hobbies-list").show();
			$("#hobbies-edit-form").hide();
			$("#hobbies-add-form").hide();
		});
		//career-object-button
		$("#career-object-edit-button").click(function() {
			$("#career-object-list").hide();
			$("#career-object-form").show();
		});
		$("#career-object-submit, #career-object-cancel").click(function() {
			$("#career-object-list").show();
			$("#career-object-form").hide();
		});
	});