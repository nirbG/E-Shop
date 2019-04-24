package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;

/**
 * Servlet implementation class Sinscire
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("action");
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		switch (id) {
		case "1"://insert user
			model.User u=null;
			try {
				u = UserDao.insert(request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("password"),request.getParameter("email"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    	HttpSession session = request.getSession();
			session.setAttribute( "client", u );
	    	response.sendRedirect("/E-Shop/Log/User");
		}
	}

}
