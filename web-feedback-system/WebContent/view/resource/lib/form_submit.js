/////////////////////////////////////////////////////
//xu ly form update profile
$(document).ready(function() {      
    
    $('#form_update_profile').submit(function () {    	    	
    	var id_v = $("#form_update_profile input[name=id]").val();
    	var title_input_v = $("#form_update_profile input[name=title_input]").val();
    	var full_name_input_v = $("#form_update_profile input[name=full_name_input]").val();
    	var birthday_input_v = $("#form_update_profile input[name=birthday_input]").val();
    	var gender_input_v = $("#form_update_profile input[name=gender_input]:checked").val();
    	var status_select_v = $("#form_update_profile input[name=status_select]:checked").val();
    	var nationality_input_v = $("#selelect_qt").val();
    	var address_input_v = $("#form_update_profile input[name=address_input]").val();
    	var email_input_v = $("#form_update_profile input[name=email_input]").val();
    	var phone_input_v = $("#form_update_profile input[name=phone_input]").val();
    	var hobbies_input_v = $("#form_update_profile input[name=hobbies_input]").val();    			    	   
        $.post("resume",{
        	add_resume_button:"",
        	id:id_v,
        	title_input:title_input_v,
        	full_name_input:full_name_input_v,
        	birthday_input:birthday_input_v,
        	gender_input:gender_input_v,
        	status_select:status_select_v,
        	nationality_input:nationality_input_v,
        	address_input:address_input_v,
        	email_input:email_input_v,
        	phone_input:phone_input_v,
        	hobbies_input:hobbies_input_v
        },
        function(data,status){        	
        	$("#profile_content").html(data);
    	});
		$("#btn_edit_cv").show();		
        $('#form_enter_resume').slideUp();
            	
        return false;	    
    });
	
});


//






//xu lu form update education
$(document).ready(function() {      
    
    $('#form_update_education').submit(function () {    	    	
    	var id = $("#form_update_education input[name=id]").val();
    	var school_name = $("#form_update_education input[name=school_name]").val();
    	var education_major = $("#form_update_education input[name=education_major]").val();
    	var education_description = $("#form_update_education input[name=education_description]").val();
    	var education_level = $("#form_update_education input[name=education_level]").val();
    	var startday_input = $("#form_update_education input[name=startday_input]").val();
    	var endday_input = $("#form_update_education input[name=endday_input]").val();
    
   			    	   
        $.post("resume",{
        	btn_add_education:"",
        	id:id,
        	school_name:school_name,
        	education_major:education_major,
        	education_description:education_description,
        	education_level:education_level,
        	startday_input:startday_input,
        	endday_input:endday_input
        },
        function(data,status){        	
        	$("#education_content").html(data);
    	});
        
		$("#btn_add_education").show();	        
        $('#form_nhap_education').slideUp();    
                        
        return false;	    
    });
	
});





//xu ly form update language	
$(document).ready(function() {      
		    
    $('#form_update_language').submit(function () {    	    	
    	var id = $("#form_update_education input[name=id]").val();
    	var language_name = $("#form_update_language input[name=language_name]").val();
    	var language_level = $("#form_update_language input[name=language_level]").val();
	    	
       			    	   
        $.post("resume",{
        	btn_add_language:"",
        	id:id,
        	language_name:language_name,
        	language_level:language_level
        },
        function(data,status){        	
        	$("#language_content").html(data);
    	});
        
		$("#btn_add_language").show();	        
        $('#form_nhap_language').slideUp();       
        
        
        $("#form_update_language input[name=language_name]").val("");
    	$("#form_update_language input[name=language_level]").val("");
        return false;	    
    });
	
});
//////////////
//xu ly form nhap kinh nh=ghiệm


$(document).ready(function() {      
    
    $('#form_update_experience').submit(function () {    	
    	
    	var id = $("#form_update_experience input[name=id]").val();
    	var company_name = $("#form_update_experience input[name=company_name]").val();
    	var job_name = $("#form_update_experience input[name=job_name]").val();
    	var job_position = $("#form_update_experience input[name=job_position]").val();
    	var job_description = $("#form_update_experience input[name=job_description]").val();
    	var job_time = $("#form_update_experience input[name=job_time]").val();
	   
        $.post("resume",{
        	btn_add_experience:"",
        	id:id,
        	company_name:company_name,
        	job_name:job_name,
        	job_position:job_position,
        	job_description:job_description,
        	job_time:job_time
        },
        function(data,status){        	
        	$("#experience_content").html(data);
    	});
        
		$("#btn_add_experience").show();	        
        $('#form_nhap_experience').slideUp();        
        
        
        $("#form_update_experience input[name=company_name]").val("");
    	$("#form_update_experience input[name=job_name]").val("");
    	$("#form_update_experience input[name=job_position]").val("");
    	$("#form_update_experience input[name=job_description]").val("");
    	$("#form_update_experience input[name=job_time]").val("");
        return false;	    
    });
	
});


////
//xu ly them skill

$(document).ready(function() {      
    
    $('#form_update_skills').submit(function () {    	
    	
    	var id = $("#form_update_skills input[name=id]").val();
    	var skill_name = $("#form_update_skills input[name=skill_name]").val();
    	var skill_level = $("#form_update_skills input[name=skill_level]").val();
	   
        $.post("resume",{
        	btn_add_skill:"",
        	id:id,
        	skill_name:skill_name,
        	skill_level:skill_level
        },
        function(data,status){        	
        	$("#skill_list").html(data);
    	});
        
		$("#btn_add_skill").show();	        
        $('#form_nhap_skill').slideUp();     
        
        $("#form_update_skills input[name=skill_name]").val("");
    	$("#form_update_skills input[name=skill_level]").val("");
        return false;	    
    });
	
});
///xu ly capnhat objective





$(document).ready(function() {      
    
    $('#form_update_objective').submit(function () {    	
    	
    	var id = $("#form_update_objective input[name=id]").val();
    	var desireSalary = $("#form_update_objective input[name=desireSalary]").val();
    	var recentSalary = $("#form_update_objective input[name=recentSalary]").val();
    	var desireCareerLevel = $("#form_update_objective input[name=desireCareerLevel]").val();
    	var positionType = $("#form_update_objective input[name=positionType]").val();
    	var desireWorkLocation = $("#form_update_objective input[name=desireWorkLocation]").val();
    	var CareerObjective = $("#txt_CareerObjective").val();
    	var dataa = [];
    	var opt_object = $("#form_update_objective input[name='opt_object']:checked").each(function() {
    		dataa.push( $(this).val());
    	});
        $.post("resume",{
        	btn_add_objective:"",
        	id:id,
        	desireSalary:desireSalary,
        	recentSalary:recentSalary,
        	desireCareerLevel:desireCareerLevel,
        	positionType:positionType,
        	desireWorkLocation:desireWorkLocation,
        	CareerObjective:CareerObjective,
        	opt_object:dataa
        
        },
        function(data,status){        	
        	$("#objective_content").html(data);
    	});
        
		$("#btn_add_objective").show();	        
        $('#form_nhap_objective').slideUp();        
        return false;	    
    });
	
});








