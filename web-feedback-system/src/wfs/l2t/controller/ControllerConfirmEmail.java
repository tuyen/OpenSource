package wfs.l2t.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wfs.l2t.model.ModelAccount;
import wfs.l2t.utility.LoginUtility;

/**
 * Servlet implementation class ControllerConfirmEmail
 */
@WebServlet("/ControllerConfirmEmail")
public class ControllerConfirmEmail extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	LoginUtility loginUtility = new LoginUtility();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerConfirmEmail()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//nêu đang đăng nhập
		if (this.loginUtility.isLogged(request, response)) {
			response.sendRedirect(request.getContextPath()+"/home");
		}
		
		String code = request.getParameter("code");
		int accountId = Integer.parseInt(request.getParameter("accountId"));

		ModelAccount md = new ModelAccount();

		if (md.checkConfirmCode(accountId, code))
		{
			md.updateActivation(accountId, 1);
			response.sendRedirect(request.getContextPath() + "/ControllerLogin");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		// TODO Auto-generated method stub
	}

}
