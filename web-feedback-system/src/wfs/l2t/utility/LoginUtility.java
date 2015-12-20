package wfs.l2t.utility;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wfs.l2t.dto.dtoAccount;
import wfs.l2t.model.ModelAccount;

public class LoginUtility {

	ModelAccount account;
	Md5Utility md5 = new Md5Utility();

	public LoginUtility() {
		account = new ModelAccount();
	}

	private String currentUserId = null;

	public void logOut(HttpServletRequest request, HttpServletResponse response) {
		this.currentUserId = null;
		Cookie[] cookie = request.getCookies();
		if (cookie == null)
			return;
		for (Cookie c : cookie) {
			c.setMaxAge(0);
			response.addCookie(c);
		}
	}

	public Boolean isLoggedByToken(HttpServletRequest request,
			HttpServletResponse response) {
		String token = request.getParameter("token");
		String userId = request.getParameter("id");
		String checkSum = request.getParameter("ck");
		if (userId != null && token != null) {
			dtoAccount dto = this.account.getAccountById(userId);
			if (dto.accountId != "") {
				String auth = dto.email + dto.password + checkSum;
				auth = md5.md5(auth);
				auth = md5.md5(auth);
				if (token.equals(auth)) {
					this.currentUserId = userId;
					Cookie cookieRemember = new Cookie("jobrec_login_remember",
							"true");
					cookieRemember.setMaxAge(31104000);
					Cookie cookieUserId = new Cookie("jobrec_login_cookie",
							dto.accountId);
					cookieUserId.setMaxAge(31104000);

					String _token = md5.generateToken();
					account.setToken(dto.accountId, _token);
					Cookie cookieToken = new Cookie("jobrec_login_token",
							_token);
					cookieToken.setMaxAge(31104000);

					response.addCookie(cookieUserId);
					response.addCookie(cookieToken);
					response.addCookie(cookieRemember);
					return true;
				}
			}
			return false;
		}
		return false;
	}

	public Boolean isLogged(HttpServletRequest request,
			HttpServletResponse response) {
		this.currentUserId = null;
		String token = "";
		String remember = "";
		Cookie[] cookie = request.getCookies();
		if (cookie == null)
			return false;

		for (int i = 0; i < cookie.length; i++) {
			switch (cookie[i].getName()) {
			case "jobrec_login_remember":
				remember = cookie[i].getValue();
				break;
			case "jobrec_login_cookie":
				this.currentUserId = cookie[i].getValue();
				break;
			case "jobrec_login_token":
				token = cookie[i].getValue();
				break;
			default:
				break;
			}
		}

		switch (remember) {
		case "true":
			if (account.getLoginToken(this.currentUserId).equals(token)) {
				return true;
			}
			return false;
		case "false":
			HttpSession session = request.getSession();
			loginSession obj = (loginSession) session
					.getAttribute("login_session");
			if (obj == null)
				return false;
			this.currentUserId = obj.userId;
			token = obj.token;
			if (this.currentUserId != null) {
				if (account.getLoginToken(this.currentUserId).equals(token)) {
					return true;
				} else
					return false;
			}
			break;
		default:
			return false;
		}
		return false;
	}

	public String getLoggedUserId() {
		return this.currentUserId;
	}

}
