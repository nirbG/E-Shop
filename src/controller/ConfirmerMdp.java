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
 * Servlet implementation class ConfirmerMdp
 */
@WebServlet("/Log/ConfirmerMdp")
public class ConfirmerMdp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmerMdp() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute( "client" );
		
		String co="<a class='active' href='/E-Shop/Log'>Me connecter</a>";
		if(user!=null) {
			if( user.getType()==2) {
				co="<a class='active' href='/E-Shop/Log/Admin'>Mon compte</a>";
			}else {
				co="<a class='active' href='/E-Shop/Log/User'>Mon compte</a>";
			}
			if(request.getAttribute("erreur")==null) {
				request.setAttribute( "erreur", " " );
			}
			request.setAttribute( "button", co );
			this.getServletContext().getRequestDispatcher( "/WEB-INF/confpassword.jsp" ).forward( request, response );
		}else {
			response.sendRedirect("/E-Shop/Log");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub;
	    request.setAttribute( "erreur", "" );
	    String pass = request.getParameter("pass");
	    if(pass.isEmpty() ) {
		    request.setAttribute( "erreur", "<p style='color:red'>Un ou plusieur champs sont vides</p>" );
			doGet(request, response);
	    }else {
	    	HttpSession session = request.getSession();
			User user = (User) session.getAttribute( "client" );
	    	if(user==null) {
	    		response.sendRedirect("/E-Shop");
	    	}else{
	    		if(pass.equals(user.getMdp())){
	    			System.out.println("pass");
	    				response.sendRedirect("/E-Shop/Log/NewPassword");
	    		}else {
	    			request.setAttribute( "erreur", "<p style='color:red'>Mot de passe incorrect</p>" );
	    			doGet(request, response);
	    		}
	    	}
	    }
	}

}
