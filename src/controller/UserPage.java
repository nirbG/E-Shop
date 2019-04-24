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
import model.User;

/**
 * Servlet implementation class User
 */
@WebServlet("/Log/User")
public class UserPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* Récupération de l'objet depuis la session */
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute( "client" );
		
		if(user!=null) {
			String res="<div class='prod'>"
					+ user.getNom()+","+user.getPrenom()
					+ "</div>" ;
			request.setAttribute( "compte", res );
			String co="<a class='active' href='/E-Shop/Log'>Me connecter</a>";
			if( user.getType()==1) {
				co="<a class='active' href='/E-Shop/Log/User'>Mon compte</a>";
			}else {
				co="<a class='active' href='/E-Shop/Log/Admin'>Mon compte</a>";
			}
			request.setAttribute( "button", co );
			request.setAttribute( "nom", user.getNom() );
			request.setAttribute( "prenom", user.getPrenom() );
			request.setAttribute( "email", user.getEmail());
			this.getServletContext().getRequestDispatcher( "/WEB-INF/userPage.jsp" ).forward( request, response );
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String act = request.getParameter("action");
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		switch(act) {
		case "1":
			try {
				modBasicInfo( request,  response);
				response.getWriter().write("<message >modUser</message>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	protected void modBasicInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute( "client" );
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		User u=UserDao.update(user.getId(),nom, prenom, email);
		
		session.setAttribute("client" , u);
	}
}
