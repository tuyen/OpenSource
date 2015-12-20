package wfs.l2t.controller;

import wfs.l2t.dto.*;
import wfs.l2t.model.*;
import wfs.l2t.utility.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Resume")
public class ControllerResume extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private LoginUtility login = new LoginUtility();
	dtoResume resume = new dtoResume();

	public ControllerResume() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		if (login.isLogged(request, response)) {
			String resumeId = request.getParameter("id");
			String delete_education = request.getParameter("delete_education");
			String delete_language = request.getParameter("delete_language");
			String delete_experience = request
					.getParameter("delete_experience");
			String delete_skill = request.getParameter("delete_skill");
			if (resumeId != null && resumeId != "") {

				ModelResume resume = new ModelResume();
				// kiem tra cv co thuoc ve user hay không
				if (resume.canModify(this.login.getLoggedUserId(), resumeId)) {
					// xóa education
					if (delete_education != null && delete_education != "") {
						resume.deleteEducation(resumeId, delete_education);
					}
					// xoa language
					if (delete_language != null && delete_language != "") {
						resume.deleteLanguage(resumeId, delete_language);
					}
					// xoa kinh nghiem
					if (delete_experience != null && delete_experience != "") {
						resume.deleteExperience(resumeId, delete_experience);
					}
					// xóa skill
					if (delete_skill != null && delete_skill != "") {
						resume.deleteSkill(resumeId, delete_skill);
					}
				}else{
					response.sendRedirect(request.getContextPath() + "/listresume");
					return;
				}

				request.setAttribute("user", this.login.getLoggedUserId());
				request.setAttribute("id", resumeId);
				request.getRequestDispatcher("view/resume-profile.jsp")
						.include(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/listresume");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		if (login.isLogged(request, response)) {

			String resumeId = request.getParameter("id");
			String btn_add_education = request
					.getParameter("btn_add_education");
			String btn_add_language = request.getParameter("btn_add_language");
			String addResume = request.getParameter("add_resume_button");
			String btn_add_experience = request
					.getParameter("btn_add_experience");
			String btn_add_skill = request.getParameter("btn_add_skill");

			String btn_add_objective = request
					.getParameter("btn_add_objective");
			// xu ly cap nhat thong tin cá nhân
			if (addResume != null) {
				this.updateResume(request, response);
				return;
			}
			// xu ly them skill
			if (btn_add_skill != null) {
				this.addSkill(request, response);
				return;
			}

			// xu ly them kinh nghiêm lam viec

			if (btn_add_experience != null) {
				this.addExperience(request, response);
				return;
			}
			// xu ly them education
			if (btn_add_education != null) {
				this.addAnEduation(request, response);
				return;
			}

			// xu ly them language
			if (btn_add_language != null) {
				this.addAnLanguage(request, response);
				return;
			}

			// them objective
			if (btn_add_objective != null) {
				this.updateObjective(request, response);
				return;
			}

			request.setAttribute("user", this.login.getLoggedUserId());
			request.setAttribute("id", resumeId);
			request.getRequestDispatcher("view/resume-profile.jsp").include(
					request, response);
		}
	}

	public void LoadPage(HttpServletRequest request) {

	}

	// /cac ham xu ly con

	private String getMysqlDate(String date) {
		String[] data = date.split("/");
		if (data.length > 0) {
			date = data[2] + "-" + data[1] + "-" + data[0];
		}
		return date;
	}

	// them mot education
	private Boolean addAnEduation(HttpServletRequest request,
			HttpServletResponse response) {
		String resumeId = request.getParameter("id");
		String school = request.getParameter("school_name");
		String level = request.getParameter("education_level");
		String major = request.getParameter("education_major");
		String description = request.getParameter("education_description");
		String start = request.getParameter("startday_input");
		String end = request.getParameter("endday_input");

		start = this.getMysqlDate(start);
		end = this.getMysqlDate(end);
		ModelResume resume = new ModelResume();
		resume.AddEducation(resumeId, school, level, major, description, start,
				end);
		
		PrintWriter out;
		try {
			out = response.getWriter();
			List<dtoEducation> edu = resume.getEducation(resumeId);
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
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	private Boolean addAnLanguage(HttpServletRequest request,
			HttpServletResponse response) {
		String resumeId = request.getParameter("id");
		String language = request.getParameter("language_name");
		String level = request.getParameter("language_level");
		ModelResume resume = new ModelResume();
		resume.addLanguage(resumeId, language, level);
		PrintWriter out;
		try {
			out = response.getWriter();
			List<dtoLanguage> languages = resume.getAllLanguage(resumeId);
			for (dtoLanguage i : languages) {
				out.print("<p>");
				out.print("<h4><b>Chứng chỉ:</b> "
						+ i.name
						+ "   <button type=\"button\" onclick=\"location.href='"
						+ "resume?id=" + i.resumeId + "&delete_language="
						+ i.languageId
						+ "'\" class=\"btn btn-danger btn-xs\">Xóa</button>"
						+ "</h4>");
				out.print("<b>Cấp độ/Điểm:</b> " + i.level + "<br>");
				out.print("</p><hr>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	private Boolean updateResume(HttpServletRequest request,
			HttpServletResponse response) {

		String resumeId = request.getParameter("id");
		String title = request.getParameter("title_input");
		// personal
		String name = request.getParameter("full_name_input");
		String birthday = request.getParameter("birthday_input");

		String gender = request.getParameter("gender_input");
		String maritalStatus = request.getParameter("status_select");
		String nationality = request.getParameter("nationality_input");
		// contact
		String address = request.getParameter("address_input");
		String email = request.getParameter("email_input");
		String phone = request.getParameter("phone_input");
		// hobbies
		String hobbies = request.getParameter("hobbies_input");
		ModelResume model = new ModelResume();

		model.updateResume(resumeId, this.login.getLoggedUserId(), title, name,
				birthday, gender, maritalStatus, nationality, address, email,
				phone, hobbies);

		ModelResume cvModel = new ModelResume();
		dtoResume cv = cvModel.getResume(resumeId);

		try {
			PrintWriter out = response.getWriter();

			out.print("<h1>" + cv.name + "</h1> ");
			out.print("<h5><b>Ngày Sinh: </b> " + cv.birthday + "</h5>");
			out.print("<h5><b>Giới tính: </b> " + cv.gender + "</h5>");
			out.print("<h5><b>Tình trạng hôn nhân: </b> ");
			if (cv.maritalStatus) {
				out.print("Đã kết hôn");
			} else {
				out.print("Chưa kết hôn");
			}
			out.print("</h5><h5><b>Email: </b> " + cv.email + "</h5>");
			out.print("<h5><b>Điện thoại: </b> " + cv.phone + "</h5>");
			out.print("<h5><b>Địa chỉ: </b> " + cv.address + "</h5>");
			out.print("<h5><b>Quốc tịch: </b> " + cv.nationality + "</h5>");
			out.print("<h5><b>Sở thích cá nhân: </b> " + cv.hobby + "</h5>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}

	private Boolean addSkill(HttpServletRequest request,
			HttpServletResponse response) {

		String resumeId = request.getParameter("id");
		String skill_name = request.getParameter("skill_name");
		String skill_level = request.getParameter("skill_level");
		ModelResume model = new ModelResume();
		model.addSkill(skill_name, skill_level, resumeId);
		try {
			PrintWriter out = response.getWriter();
			List<dtoSkill> skills = model.getSkill(resumeId);
			for (dtoSkill i : skills) {
				String url = "   <button type=\"button\" onclick=\"location.href='"
						+ "resume?id="
						+ i.resumeId
						+ "&delete_skill="
						+ i.skillId
						+ "'\" class=\"btn btn-danger btn-xs\">Xóa</button>";
				out.print("<p>");
				out.print("<h4>");
				out.print(i.name + " " + url);
				out.print("</h4>");
				out.print(i.level);
				out.print("<br><hr></p>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	private Boolean addExperience(HttpServletRequest request,
			HttpServletResponse response) {

		String resumeId = request.getParameter("id");
		String company_name = request.getParameter("company_name");
		String job_name = request.getParameter("job_name");
		String job_position = request.getParameter("job_position");
		String job_description = request.getParameter("job_description");
		String job_time = request.getParameter("job_time");
		ModelResume model = new ModelResume();
		model.addExperience(resumeId, company_name, job_name, job_position,
				job_description, job_time);
		
		PrintWriter out;
		try {
			out = response.getWriter();
			List<dtoExperience> epx = model.getExperience(resumeId);
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
				out.print("Vị Trí: " + i.position + "<br>");
				out.print("Mô Tả: " + i.description + "<br>");
				out.print("Thời Gian: " + i.period + "<br>");
				out.print("</p><hr>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}

	private Boolean updateObjective(HttpServletRequest request,
			HttpServletResponse response) {
		String resumeId = request.getParameter("id");
		ModelResume model = new ModelResume();
		// neu chưa có objective
		if (!model.hasAObject(resumeId)) {
			model.addObjective(resumeId, "0", "0", "Toàn thời gian",
					"Nhân Viên", "Chưa xác định", "0", "0", "Chưa xác định");
		}

		String desireSalary = request.getParameter("desireSalary");
		String recentSalary = request.getParameter("recentSalary");
		String positionType = request.getParameter("positionType");
		String desireCareerLevel = request.getParameter("desireCareerLevel");
		String desireWorkLocation = request.getParameter("desireWorkLocation");
		String CareerObjective = request.getParameter("CareerObjective");
		String willingToRelocate = "0";
		String WillingToTravel = "0";

		String[] data = request.getParameterValues("opt_object[]");
		if (data != null) {
			for (String i : data) {
				if (i.equals("willingToRelocate")) {
					willingToRelocate = "1";
				}
				if (i.equals("WillingToTravel")) {
					WillingToTravel = "1";
				}
			}
		}

		model.updateObjective(resumeId, desireSalary, recentSalary,
				positionType, desireCareerLevel, desireWorkLocation,
				willingToRelocate, WillingToTravel, CareerObjective);
		
		try {
			PrintWriter out = response.getWriter();
			dtoCareerObjective objective = model.getObjective(resumeId);
			out.print("<h4>Mức lương mong muốn(VND): "+objective.desireSalary+"</h4><hr>");
			out.print("<h4>Mức lương gần đây(VND): "+objective.recentSalary+"</h4><hr>");
			out.print("<h4>Loại công việc: "+objective.positionType+"</h4><hr>");
			out.print("<h4>Cấp bậc mong muốn: "+objective.desireCareerLevel+"</h4><hr>");
			out.print("<h4>Nơi làm việc mong muốn:"+objective.desireWorkLocation+"</h4><hr>");
			
			if (objective.willingToTravel.equals("1")){
				out.print("<h4>Có thể đi công tác: "+"Có thể"+"</h4><hr>");
			}else{
				out.print("<h4>Có thể đi công tác: "+"Không"+"</h4><hr>");
			}
			
			if (objective.willingToRelocate.equals("1")){
				out.print("<h4>Có thể đổi chỗ ở: "+"Có thể"+"</h4><hr>");
			}else{
				out.print("<h4>Có thể đổi chỗ ở: "+"Không"+"</h4><hr>");
			}
			
			out.print("<h4>Mục tiêu nghề nghiệp:"+objective.careerObjective+"</h4>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
