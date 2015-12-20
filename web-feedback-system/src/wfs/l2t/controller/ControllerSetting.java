package wfs.l2t.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wfs.l2t.model.ModelAccount;
import wfs.l2t.model.ModelCare;
import wfs.l2t.utility.LoginUtility;

/**
 * Servlet implementation class ControllerSetting
 */
@WebServlet("/ControllerSetting")
public class ControllerSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginUtility loginUtility = new LoginUtility();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerSetting() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		if (this.loginUtility.isLogged(request, response)) {

			request.setAttribute("user", this.loginUtility.getLoggedUserId());
			request.getRequestDispatcher("view/configure-system.jsp").include(
					request, response);
		} else {
			// request.getRequestDispatcher("/login").forward(request,
			// response);
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

		if (!this.loginUtility.isLogged(request, response)) {
			// request.getRequestDispatcher("/login").forward(request,
			// response);
			response.sendRedirect(request.getContextPath() + "/login");
		}
		request.setAttribute("user", this.loginUtility.getLoggedUserId());
		String form_number_email = request.getParameter("fne-btn-submit");
		String form_time_email = request.getParameter("fte-btn-submit");
		String form_care_category = request.getParameter("fcc-btn-submit");

		// xu ly khi sutmit form chon so email mot lan gui
		if (form_number_email != null) {
			String number = request.getParameter("fne_rdo_number");
			ModelAccount model = new ModelAccount();
			if (number != null)
				model.setReceiveEmailNumber(
						this.loginUtility.getLoggedUserId(), number);
			request.getRequestDispatcher("view/configure-system.jsp").include(
					request, response);
			return;
		}

		// xu ly khi chon form chon thoi gian nhan mail
		if (form_time_email != null) {
			String time = request.getParameter("fte-rdo-time");
			ModelAccount model = new ModelAccount();
			if (time != null)
				model.setReceiveEmailTime(this.loginUtility.getLoggedUserId(),
						time);
			// fte-rdo-time
			request.getRequestDispatcher("view/configure-system.jsp").include(
					request, response);
			return;
		}

		// xu ly form chon thoi gian quan tam
		if (form_care_category != null) {
			// lay du lieu
			String[] data = request.getParameterValues("fcc_ck_cat");
			ModelCare careModel = new ModelCare();
			if (data != null) {
				
				if (data.length > 0) {
					// luu du liêu					
					careModel.uncareAllCategory(this.loginUtility
							.getLoggedUserId());
					careModel.careCategory(this.loginUtility.getLoggedUserId(),
							data);
					request.getRequestDispatcher("view/configure-system.jsp")
							.include(request, response);
				}
			}
			else
			{
				careModel.uncareAllCategory(this.loginUtility
						.getLoggedUserId());
				request.getRequestDispatcher("view/configure-system.jsp")
				.include(request, response);
			}
			return;
		}

	}

}
