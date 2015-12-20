package wfs.l2t.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wfs.l2t.dto.dtoAccount;
import wfs.l2t.model.ModelAccount;
import wfs.l2t.utility.EmailUtility;
import wfs.l2t.utility.Md5Utility;

/**
 * Servlet implementation class ControllerForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ControllerForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ModelAccount mda = new ModelAccount();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerForgotPassword() {
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
		request.getRequestDispatcher("view/forgot-password.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email-validate").toLowerCase();
		dtoAccount account;
		if (email != null) {
			account = mda.getAccount(email);
			if (account == null) {
				request.setAttribute("response", "0");
				request.getRequestDispatcher("view/forgot-password.jsp")
						.include(request, response);
			} else {
				// generate new password
				Md5Utility md5 = new Md5Utility();
				String newPass = md5.md5(UUID.randomUUID().toString());
				// update password
				mda.updatePassword(account.accountId, newPass);
				// send new password to use via email
				// reads SMTP server setting from web.xml file
				ServletContext context = getServletContext();
				String host = context.getInitParameter("host");
				String port = context.getInitParameter("port");
				String user = context.getInitParameter("user");
				String pass = context.getInitParameter("pass");
				String recipient = email;
				
				String link = "";				
			    try {
			        BufferedReader in = new BufferedReader(new FileReader(getServletContext().getResource("/WEB-INF/url-config.txt").getPath()));
			        String str;
			        while ((str = in.readLine()) != null) {
			            link +=str;
			        }
			        in.close();
			    } catch (IOException e) {
			    }
				
				String subject = "Thay đổi mật khẩu tài khoản " + account.userName;

				String content = "Tài khoản "
						+ account.userName
						+ " tại "+link+" đã được thay đổi mật khẩu. Mật khẩu mới của bạn là: "
						+ newPass
						+ ".<br>Cảm ơn bạn đã sử dụng hệ thống khuyến nghị việc làm của chúng tôi!";

				String resultMessage = "";
				try {
					EmailUtility.sendEmail(host, port, user, pass, recipient,
							subject, content);
					resultMessage = "1";
				} catch (Exception ex) {
					ex.printStackTrace();
					resultMessage = "2" + ex.getMessage();
				} finally {
					request.setAttribute("response", resultMessage);
					request.getRequestDispatcher("view/forgot-password.jsp")
							.include(request, response);
				}
			}
		}
	}
}
