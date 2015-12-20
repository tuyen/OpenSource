package wfs.l2t.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import wfs.l2t.dto.dtoAccount;
import wfs.l2t.model.ModelAccount;
import wfs.l2t.utility.LoginUtility;
import wfs.l2t.utility.Md5Utility;

/**
 * Servlet implementation class ControllerAccount
 */
@WebServlet("/ControllerAccount")
public class ControllerAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginUtility loginUtility;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerAccount() {
		super();
		// TODO Auto-generated constructor stub
		loginUtility = new LoginUtility();
	}

	// where this project's location?
	private void uploadAvatar(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// process for change avatar
		if (ServletFileUpload.isMultipartContent(request)) {

			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			List<FileItem> files = fileUpload.parseRequest(request);
			for (FileItem i : files) {
				if (!i.isFormField()) {
					String path = this.getServletContext().getRealPath("/");
					File f = new File(path + "/view/resource/image/avatar/");
					int list = f.listFiles().length;
					
					String des = path
							+ "/view/resource/image/avatar/"
							+ list
							+ "."
							+ FilenameUtils.getExtension(i.getName()).toUpperCase();
					
					i.write(new File(des));
					
					Thumbnails.of(des)
					.size(510, 510)
					.toFile(des);

					ModelAccount account = new ModelAccount();
					account.changeAvatar(this.loginUtility.getLoggedUserId(),
							"/view/resource/image/avatar/"
									+ list
									+ "."
									+ FilenameUtils.getExtension(i.getName())
											.toUpperCase());
				}
			}
		}
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

		if (loginUtility.isLogged(request, response)) {
			request.setAttribute("user", loginUtility.getLoggedUserId());
			request.getRequestDispatcher("view/account-manager.jsp").include(
					request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	String errorMessage = null;
	Md5Utility md5 = new Md5Utility();

	// change password
	private void changePassword(HttpServletRequest request,
			HttpServletResponse response) {
		String old_pass = request.getParameter("fcp-old-pass");
		String new_pass = request.getParameter("fcp-new-pass");
		String confirm_pass = request.getParameter("fcp-confirm-pass");
		ModelAccount account = new ModelAccount();
		dtoAccount dto = account.getAccountById(loginUtility.getLoggedUserId());
		if (dto.password.equals(md5.md5(old_pass))) {
			if (new_pass.equals(confirm_pass)) {
				// thay đổi pass ở đây
				account.updatePassword(loginUtility.getLoggedUserId(),
						new_pass);
			} else {
				errorMessage = "Mật khẩu mới không khớp!";
			}
		} else {// nofity error: password not match
			errorMessage = "Mật khẩu cũ không khớp!";
		}

	}

	public Boolean changeUserName(HttpServletRequest request,
			HttpServletResponse response) {
		String newUserName = request.getParameter("fcn-new-user-name");
		ModelAccount account = new ModelAccount();
		return account.changeUserName(this.loginUtility.getLoggedUserId(),
				newUserName);
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

		if (!loginUtility.isLogged(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
		}

		this.errorMessage = null;

		request.setAttribute("user", loginUtility.getLoggedUserId());

		// change password from is submited
		String change_pass = request.getParameter("btnChangePass");
		// change user name
		String changeUserName = request.getParameter("changeUserName");
		// process for change password
		if (change_pass != null) {
			changePassword(request, response);
			request.setAttribute("user", loginUtility.getLoggedUserId());
			request.setAttribute("error_message", errorMessage);
			request.getRequestDispatcher("view/account-manager.jsp").include(
					request, response);
			return;
		}

		if (changeUserName != null) {
			this.changeUserName(request, response);
			request.setAttribute("user", loginUtility.getLoggedUserId());
			request.getRequestDispatcher("view/account-manager.jsp").include(
					request, response);
			return;
		}
		// change avatar from is submited
		try {
			uploadAvatar(request, response);
			request.setAttribute("user", loginUtility.getLoggedUserId());
			request.getRequestDispatcher("view/account-manager.jsp").include(
					request, response);
			// String path = this.getServletContext().getRealPath("/");
			// response.getWriter().print(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("user", loginUtility.getLoggedUserId());
			request.setAttribute("error_message",
					"Lỗi xảy ra khi thay thế avatar");
			request.getRequestDispatcher("view/account-manager.jsp").include(
					request, response);
			e.printStackTrace();
		}
	}

}
