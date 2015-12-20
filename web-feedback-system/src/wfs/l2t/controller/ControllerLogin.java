package wfs.l2t.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import wfs.l2t.dto.dtoAccount;
import wfs.l2t.model.ModelAccount;
import wfs.l2t.model.ModelCare;
import wfs.l2t.utility.EmailUtility;
import wfs.l2t.utility.LoginUtility;
import wfs.l2t.utility.Md5Utility;
import wfs.l2t.utility.loginSession;

/**
 * Servlet implementation class ControllerLogin
 */
@WebServlet("/ControllerLogin")
public class ControllerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private dtoAccount account = new dtoAccount();	
	private ModelCare mdCare = new ModelCare();
	private ModelAccount mdAccount = new ModelAccount();
	private LoginUtility loginUtility = new LoginUtility();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerLogin() {
		super();
		// TODO Auto-generated constructor stub
		md5 = new Md5Utility();
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
		if (loginUtility.isLogged(request, response)) {
			request.setAttribute("user", loginUtility.getLoggedUserId());
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		if (request.getParameter("submit") != null) {
			response.getWriter().write("get cái gì ở đây !!!");
		} else {
			request.getRequestDispatcher("view/login.jsp").include(request,
					response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		if (loginUtility.isLogged(request, response)) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}

		if (request.getParameter("submit") != null) {

			switch (request.getParameter("submit")) {
			case "login":
				login(request, response);
				break;
			case "register":
				register(request, response);
				break;
			default:
				break;
			}

		} else {
			request.getRequestDispatcher("view/login.jsp").include(request,
					response);
			// response.sendRedirect(request.getContextPath() + "/Login");
		}
	}

	private Md5Utility md5;

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// check email
		if (mdAccount.getAccount(request.getParameter("login-email")) != null) {
			account = mdAccount.getAccount(request.getParameter("login-email"));
			// check password
			String pass = request.getParameter("login-pass");
			pass = md5.md5(pass);
			if (account.password.equals(pass)) {
				// check activate status
				if (account.isActive == 1) {
					// check if user want to keep login
					if (request.getParameter("login-check") != null) {

						Cookie cookieRemember = new Cookie(
								"jobrec_login_remember", "true");
						cookieRemember.setMaxAge(31104000);

						Cookie cookieUserId = new Cookie("jobrec_login_cookie",
								account.accountId);
						cookieUserId.setMaxAge(31104000);

						String token = md5.generateToken();
						mdAccount.setToken(account.accountId, token);
						Cookie cookieToken = new Cookie("jobrec_login_token",
								token);
						cookieToken.setMaxAge(31104000);

						response.addCookie(cookieUserId);
						response.addCookie(cookieToken);
						response.addCookie(cookieRemember);

					} else {
						Cookie cookieRemember = new Cookie(
								"jobrec_login_remember", "false");
						cookieRemember.setMaxAge(31104000);
						HttpSession session = request.getSession();
						loginSession obj = new loginSession();
						obj.userId = account.accountId;

						String token = md5.generateToken();
						mdAccount.setToken(account.accountId, token);
						obj.token = token;
						session.setAttribute("login_session", obj);
						response.addCookie(cookieRemember);
					}
					response.sendRedirect(request.getContextPath() + "/home");
				} else {
					// not activate account
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
					request.setAttribute("link", link);
					request.setAttribute(
							"Message",
							"Bạn chưa xác thực email. Vui lòng xác thực email để kích hoạt tài khoản của bạn!");
					request.getRequestDispatcher("view/notification.jsp")
							.include(request, response);
				}
			} else {
				// login fail - password do not match
				request.setAttribute("Message", "Sai mật khẩu!");
				request.getRequestDispatcher("view/login.jsp").include(request,
						response);
			}
		} else
		// email do not exist
		{
			request.setAttribute("Message", "Sai email!");
			request.getRequestDispatcher("view/login.jsp").include(request,
					response);
		}
	}

	private void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// check condition register
		String email = request.getParameter("reg-email").toLowerCase();
		String userName = request.getParameter("reg-username");
		String password = request.getParameter("reg-password");
		String rpassword = request.getParameter("reg-re-type-password");
		String category = request.getParameter("reg-category");
		ModelAccount mdLogin = new ModelAccount();
		if (mdLogin.getAccount(email) != null)
		{						
			request.setAttribute("err_reg", "Email của bạn đã được đăng ký!");
			request.getRequestDispatcher("view/login.jsp").include(request,
					response);
			return;
		}
		if(!password.equals(rpassword))
		{
			request.setAttribute("err_reg", "Mật khẩu không trùng khớp!");
			request.getRequestDispatcher("view/login.jsp").include(request,
					response);
			return;
		}
		if (email != "" && userName != "" && password != "" && rpassword != "") {
			if (password.equals(rpassword)) {
				account.userName = userName;
				account.email = email;
				account.password = password;
				String accType = request.getParameter("radio");
				account.accountType = "employer".equals(accType) ? "employer"
						: "job-seeker";
				account.timeReceiveEmail = "daily";
				account.numberReceiveEmail = "10";
				account.isActive = 0;
				account.confirmCode = UUID.randomUUID().toString();
				account.avatar = "/view/resource/image/avatar/icon-user-default.png";
				mdAccount.addAccount(account);
				mdCare.careCategory(mdAccount.getAccountId(email), category);

				// verify by email
				// reads SMTP server setting from web.xml file
				ServletContext context = getServletContext();
				String host = context.getInitParameter("host");
				String port = context.getInitParameter("port");
				String user = context.getInitParameter("user");
				String pass = context.getInitParameter("pass");
				String recipient = request.getParameter("reg-email");
				String subject = "Xác thực tài khoản";				

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
			    
			    String content = "Xin chào " +account.userName+"! <br>Đây là email xác thực tài khoản bạn đã đăng ký tại "+link+". <br>Vui lòng <a href = " +link+ "ControllerConfirmEmail?code="
						+ account.confirmCode
						+ "&accountId="
						+ mdAccount.getAccountId(account.email)
						+ "> bấm vào đây </a> để xác thực tài khoản của bạn.";
				
				String resultMessage = "";
				try {
					EmailUtility.sendEmail(host, port, user, pass, recipient,
							subject, content);
					resultMessage = "Để bắt đầu sử dụng hệ thống vui lòng xác nhận đăng ký qua email của bạn!";					
				} catch (Exception ex) {
					ex.printStackTrace();
					resultMessage = "Lỗi gửi mail. Sorry!!!" + ex.getMessage();
				} finally {
					request.setAttribute("Message", resultMessage);
					request.setAttribute("link", link);
					request.getRequestDispatcher("view/notification.jsp")
							.include(request, response);
				}
			}
		} else {
			request.setAttribute("err_reg",
					"Vui lòng điền đầy đủ thông tin!");
			request.getRequestDispatcher("view/login.jsp").include(request,
					response);
		}
	}
}
