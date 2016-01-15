package wfs.l2t.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.TopDocs;

import wfs.l2t.utility.LoginUtility;
import wfs.l2t.utility.LuceneSearcher;

/**
 * Servlet implementation class ControllerSearcher
 */
@WebServlet("/ControllerSearcher")
public class ControllerSearcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginUtility loginUtility;
	String[] fields = { "JobId", "JobName", "Location", "Description", "Tags",
			"Requeriment", "Benifit", "Company" };
	int end = 0;

	private String getIndexedFileLocation(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String link = "";
		ServletContext context = getServletContext();
		try {
			BufferedReader in = new BufferedReader(new FileReader(context
					.getResource("/WEB-INF/indexed-location.txt").getPath()));
			String str;
			while ((str = in.readLine()) != null) {
				link += str;
			}
			in.close();
		} catch (IOException e) {
		}
		return link;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerSearcher() {
		super();
		loginUtility = new LoginUtility();
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
		response.setContentType("text/html;charset=UTF-8");

		// check if user is logged in
		if (loginUtility.isLogged(request, response)) {

			if (request.getParameter("logout") != null) {
				this.loginUtility.logOut(request, response);
				response.sendRedirect(request.getContextPath() + "/login");
			}
			//
			request.setAttribute("user", loginUtility.getLoggedUserId());
		
		String query = request.getParameter("search_lucene");
		String page = request.getParameter("page");
		List<Document> results = null;
		int start = 1;		
		int currentPage;
		if (page == null) {
			page = "1";
			currentPage = Integer.parseInt(page);
			end = 10;					
		}else{
			currentPage = Integer.parseInt(page);
			if(currentPage >= end - 3){
				end +=3;
			}
		}		
		start = end - 9;
		
		LuceneSearcher lc = new LuceneSearcher(fields, getIndexedFileLocation(request,response));
		results = lc.search(query, (currentPage-1) * 10,
				(currentPage-1) * 10 + 10);
		request.setAttribute("total", lc.getTotal());
		int noOfPages = (int) Math.ceil(lc.getTotal() * 1.0 / 10);
		if(noOfPages < end){
			end = noOfPages;
			start = end - 9;
			if(start <= 0)
				start = 1;
		}
		request.setAttribute("noOfPage", noOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("results", results);
		request.getRequestDispatcher("view/home.jsp")
				.include(request, response);
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
		response.setContentType("text/html;charset=UTF-8");

		request.getRequestDispatcher("view/home.jsp")
				.include(request, response);

	}
}
