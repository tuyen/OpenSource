package wfs.l2t.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.TopDocs;

import wfs.l2t.utility.LuceneSearcher;

/**
 * Servlet implementation class ControllerSearcher
 */
@WebServlet("/ControllerSearcher")
public class ControllerSearcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String[] fields = { "JobId", "JobName", "Location", "Description", "Tags",
			"Requeriment", "Benifit", "Company" };
	LuceneSearcher lc = new LuceneSearcher(fields,
			"E:/ANHTUYEN/HOCTAP/MaNguonMo/OS/IndexFile");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerSearcher() {
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
		response.setContentType("text/html;charset=UTF-8");

		String query = request.getParameter("search_lucene");
		String page = request.getParameter("page");
		List<Document> results = null;
		if (page == null) {			
			page = "0";
		}
		results = lc.search(query, Integer.parseInt(page)*10, Integer.parseInt(page)*10 + 10);
		request.setAttribute("total", results.size());
		int noOfPages = (int) Math.ceil(results.size() * 1.0 / 10);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
		request.setAttribute("results", results);
		request.getRequestDispatcher("view/home.jsp")
				.include(request, response);
		
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
