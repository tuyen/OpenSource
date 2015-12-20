package wfs.l2t.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wfs.l2t.dto.dtoJob;
import wfs.l2t.dto.dtoJobRecommended;
import wfs.l2t.model.ModelJob;
import wfs.l2t.model.ModelJobRecommended;
import wfs.l2t.utility.LoginUtility;

/**
 * Servlet implementation class ControllerHome
 */
@WebServlet("/ControllerHome")
public class ControllerHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginUtility loginUtility;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerHome() {
		super();
		loginUtility = new LoginUtility();
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

			if (request.getParameter("logout") != null) {
				this.loginUtility.logOut(request, response);
				response.sendRedirect(request.getContextPath() + "/login");
			}
			//
			request.setAttribute("user", loginUtility.getLoggedUserId());

			// new-job.jsp

			HttpSession session = request.getSession();
			session.setAttribute("offset", "0");
			// if(request.getParameter("cate") != null)
			session.setAttribute("cate", request.getParameter("cate"));
			// if (request.getParameter("cate") == null)
//			request.getRequestDispatcher("view/new-job.jsp").include(request,
//					response);
			
			request.getRequestDispatcher("view/new-job.jsp").include(request,
					response);
			// loadNewJob(request, response);
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
		// check if user is logged in
		if (!loginUtility.isLogged(request, response)) {
			response.getWriter().write("");
		} else {
			request.setAttribute("user", loginUtility.getLoggedUserId());
			loadNewJob(request, response, loginUtility.getLoggedUserId());
		}
	}

	private void loadNewJob(HttpServletRequest request,
			HttpServletResponse response, String userId)
			throws ServletException, IOException {
		ModelJob mdj = new ModelJob();
		HttpSession session = request.getSession();
		int offset = Integer
				.parseInt(session.getAttribute("offset").toString());
		List<dtoJob> jobList;
		if (session.getAttribute("cate") != null)
			jobList = mdj.getJobByCategory(offset, session.getAttribute("cate")
					.toString(), userId);
		else
			jobList = mdj.getJob(offset, userId);
		// mdj.getJob();
		dtoJob job = new dtoJob();
		offset += 11;
		session.setAttribute("offset", offset);

		if (jobList.size() == 0)
			if (offset == 11)
				writeHtml("Chưa có việc mới!", request, response);
			else
				writeHtml("Hết việc mới rồi!", request, response);
		else
			for (int i = 0; i < jobList.size(); i++) {
				job = jobList.get(i);

				writeHtml(job, mdj.getShortDescription(job.jobId), job.save,
						job.rating, request, response);

			}
	}

	private void writeHtml(dtoJob job, String shortDescription, String save,
			String rating, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(
				"<div class=\"panel panel-info\" id = 'panel" + job.jobId
						+ "'>");
		response.getWriter().write("<div class='panel-heading'>");
		response.getWriter()
				.write("<a id=\"see-more"
						+ job.jobId
						+ "\" class=\"btn btn-link\"onclick=\"myCollapse('"
						+ job.jobId
						+ "')\"> <b style='font-size: 16px;white-space: pre-wrap; word-break: normal;' class = 'text-left pull-left'>"
						+ job.jobName + "</b></a>");

		response.getWriter().write("</div>");
		response.getWriter().write("<div class='panel-body'>");
		response.getWriter().write("<div class='row'>");
		response.getWriter().write("<div class='company'>");
		response.getWriter().write(
				"<pre><b>Công ty: </b>" + job.company + "</pre>");
		response.getWriter().write("</div>");
		response.getWriter().write("<div class='location'>");
		response.getWriter().write(
				"<pre><b>Địa chỉ: </b>" + job.location + "</pre>");
		response.getWriter().write("</div>");
		response.getWriter().write("<div class='salary'>");
		response.getWriter().write(
				"<pre><b>Lương: </b>" + job.salary + " </pre>");
		response.getWriter().write("</div>");
		response.getWriter().write(
				"<div id='short-description" + job.jobId + "'>");
		response.getWriter().write(
				"<pre><b>Mô tả: </b>" + shortDescription + " ...</pre>");
		response.getWriter().write("</div>");
		response.getWriter().write(
				"<div id='full-info" + job.jobId + "' class='custom_hiden'>");
		response.getWriter().write("<div class='description'>");
		response.getWriter().write("<pre><b>Mô tả: </b>");
		response.getWriter().write(job.description);
		response.getWriter().write("</pre>");
		response.getWriter().write("</div>");
		response.getWriter().write("<div class='requirement'>");
		response.getWriter().write("<pre><b>Yêu cầu: </b>");
		response.getWriter().write(job.requirement);
		response.getWriter().write("</pre>");
		response.getWriter().write("</div>");
		response.getWriter().write("<div class='benifit'>");
		response.getWriter().write("<pre><b>Lợi ích: </b>");
		response.getWriter().write(job.benifit);
		response.getWriter().write("</pre>");
		response.getWriter().write("</div>");
		response.getWriter().write("<div class='expire'>");
		response.getWriter().write(
				"<pre><b>Ngày hết hạn: </b>" + job.expired + " </pre>");
		response.getWriter().write("</div>");
		response.getWriter().write("<div class='source'>");
		response.getWriter().write(
				"<pre><b>Nguồn: </b><a href = '" + job.source
						+ "'target='_blank'>" + job.source + "</a> </pre>");
		response.getWriter().write("</div>");
		response.getWriter().write("</div>");
		response.getWriter().write("</div>");
		response.getWriter().write("</div>");
		response.getWriter()
				.write("<div class='panel-footer'> <i> Mức độ phù hợp của việc làm này với bạn? </i>");
		for (int i = 1; i <= 5; i++) {
			if (i <= Integer.parseInt(rating))
				response.getWriter()
						.write("<a class = 'bookmark' id = '"
								+ job.jobId
								+ "_"
								+ i
								+ "'onclick = 'rating(this, "
								+ i
								+ ", "
								+ job.jobId
								+ ")' onmouseover = 'mouseOverRating("
								+ job.jobId
								+ ", "
								+ i
								+ ")' onmouseout = 'mouseOutRating("
								+ job.jobId
								+ ")' href='#/' value = '1' style='color:#F9D400;font-size:25px; margin-left:3px;'><span class='glyphicon glyphicon-star'></span></a>");
			else
				response.getWriter()
						.write("<a class = 'bookmark' id = '"
								+ job.jobId
								+ "_"
								+ i
								+ "'onclick = 'rating(this, "
								+ i
								+ ", "
								+ job.jobId
								+ ")' onmouseover = 'mouseOverRating("
								+ job.jobId
								+ ", "
								+ i
								+ ")'onmouseout = 'mouseOutRating("
								+ job.jobId
								+ ")' href='#/' value = '0' style='color:#D9EDF7;font-size:25px; margin-left:3px;'><span class='glyphicon glyphicon-star'></span></a>");
		}
		response.getWriter().write(
				"<i style = 'margin-left:20px;' id = 'tip_" + job.jobId
						+ "'><span></span></i>");
		if ("0".equals(save))
			response.getWriter()
					.write("<a class = 'bookmark pull-right' id = '"
							+ job.jobId
							+ "' onclick = likeClick(this,"
							+ job.jobId
							+ ") href='#/' value = '0' style='margin-left: 15px; margin-right: 15px;color:#AFB4BD;font-size:15px;'><span class='glyphicon glyphicon-floppy-saved'></span> Lưu việc làm</a>");
		else
			response.getWriter()
					.write("<a class = 'bookmark pull-right' id = '"
							+ job.jobId
							+ "' onclick = likeClick(this,"
							+ job.jobId
							+ ") href='#/' value = '1' style='margin-left: 15px; margin-right: 15px;color:#5890FF;font-size:15px;'><span class='glyphicon glyphicon-floppy-saved'></span> Lưu việc làm</a>");

		response.getWriter().write("</div>");

		response.getWriter().write("</div>");
		response.getWriter().write("<br>");
	}

	private void writeHtml(String noti, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(
				"<p class = 'text-center' id = 'done' <b> <i> " + noti
						+ " </i>  </b></p>");
	}
}
