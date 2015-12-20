package wfs.l2t.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wfs.l2t.utility.LoginUtility;

/**
 * Servlet implementation class HelpController
 */
@WebServlet("/HelpController")
public class ControllerHelp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginUtility loginUtility;
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public ControllerHelp() {
        super();
        // TODO Auto-generated constructor stub
        loginUtility = new LoginUtility();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set charset
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//return help view
		if (loginUtility.isLogged(request, response)) {
		request.setAttribute("user", loginUtility.getLoggedUserId());
		}
		if(request.getParameter("about")!= null)
		{
			request.getRequestDispatcher("view/about.jsp").include(request, response);
			return;
		}
		request.getRequestDispatcher("view/help.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
