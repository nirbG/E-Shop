package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class Log
 */
@WebServlet("/Log")
public class Log extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int     COOKIE_MAX_AGE  = 216000 ; // 1sec
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Log() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute( "client" );
		String co="<a class='active' href='/E-Shop/Log'>Me connecter</a>";
		if(user!=null) {
			if( user.getType()==1) {
				co="<a class='active' href='/E-Shop/Log/User'>Mon compte</a>";
			}else {
				co="<a class='active' href='/E-Shop/Log/Admin'>Mon compte</a>";
			}
			
		}
		if(request.getAttribute("erreur")==null) {
			request.setAttribute( "erreur", " " );
		}
		if(request.getAttribute("email")==null) {
			request.setAttribute( "email", "login@mail.com" );
		}
		request.setAttribute( "button", co );
		this.getServletContext().getRequestDispatcher( "/WEB-INF/logForm.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub;
	    request.setAttribute( "erreur", "" );
		String email = request.getParameter("id");
	    String pass = request.getParameter("pass");
	    System.out.println("email"+email+" pass "+pass);
	    if(pass.isEmpty() || email.isEmpty()) {
		    request.setAttribute( "erreur", "<p style='color:red'>Un ou plusieur champs sont vides</p>" );
			doGet(request, response);
	    }else {
	    	User u=null;
	    	try {
	    		u=UserDao.findByEmail(email);
	    	} catch (SQLException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    	if(u!=null) {
	    		 request.setAttribute( "email", u.getEmail());
	    			System.out.println(pass+""+u.getMdp());
	    		if(pass.equals(u.getMdp())){
	    			System.out.println("pass");
	    			/* Création ou récupération de la session */
	    			HttpSession session = request.getSession();
	    			session.setAttribute( "client", u );
	    			if(u.getType()==1) {
	    				response.sendRedirect("/E-Shop/Log/User");
	    			}else {
	    				response.sendRedirect("/E-Shop/Log/Admin");
	    			}
	    		}else {
	    			request.setAttribute( "erreur", "<p style='color:red'>Mot de passe incorrect</p>" );
	    			doGet(request, response);
	    		}
	    	}else {
	    		request.setAttribute( "erreur", "<p style='color:red'>L'utilisateur est inconnu</p>" );
	    		doGet(request, response);
	    	}
	    }
	}
	
	/*
	 * Méthode utilitaire gérant la création d'un cookie et son ajout à la
	 * réponse HTTP.
	 */
	private static void setCookie( HttpServletResponse response, String nom, String valeur, int maxAge ) {
		if(nom==null || nom.isEmpty() || valeur==null || valeur.isEmpty()) {
		}else {
			Cookie cookie = new Cookie( nom, valeur );
	    	cookie.setMaxAge( maxAge );
	    	response.addCookie( cookie );
		}
	}

}
