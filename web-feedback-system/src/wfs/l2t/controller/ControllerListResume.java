package wfs.l2t.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wfs.l2t.model.ModelResume;
import wfs.l2t.utility.LoginUtility;

/**
 * Servlet implementation class ControllerListResume
 */
@WebServlet("/ListResume")
public class ControllerListResume extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginUtility login = new LoginUtility();
	ModelResume model = new ModelResume();
	String accountId;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerListResume() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		if (login.isLogged(request, response)) {
			String deleteId = request.getParameter("delete");
			if (deleteId != null) {
				ModelResume model = new ModelResume();
				model.deleteResumeById(deleteId, this.login.getLoggedUserId());
			}
			request.setAttribute("user", this.login.getLoggedUserId());
			request.getRequestDispatcher("view/list-resume.jsp").include(
					request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		if (login.isLogged(request, response)) {

			String addResume = request.getParameter("add-resume-button");
			if (addResume != null) {

				String title = request.getParameter("title-input");
				// personal
				String name = request.getParameter("full-name-input");
				String birthday = request.getParameter("birthday-input");
				String[] data = birthday.split("/");
				if (data.length == 3) {
					birthday = data[2] + "-" + data[1] + "-" + data[0];
					String gender = request.getParameter("gender-input");
					String maritalStatus = request
							.getParameter("status_select");
					String nationality = request
							.getParameter("nationality_input");
					// contact
					String address = request.getParameter("address-input");
					String email = request.getParameter("email-input");
					String phone = request.getParameter("phone-input");
					// hobbies
					String hobbies = request.getParameter("hobbies-input");

					model.AddResume(this.login.getLoggedUserId(), title, name,
							birthday, gender, maritalStatus, nationality, "",
							address, email, phone, hobbies);
					int resumeId = model
							.getMaxResumeId(login.getLoggedUserId());
					response.sendRedirect(request.getContextPath()
							+ "/resume?id=" + resumeId);
					return;
				} else {
					// lỗi khi sutmit ngày
					request.setAttribute("user", this.login.getLoggedUserId());
					request.getRequestDispatcher("view/list-resume.jsp")
							.include(request, response);
					return;
				}

			}

			// request.getRequestDispatcher("view/list-resume.jsp").include(
			// request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}

	}
}
