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
 * Servlet implementation class NewPassword
 */
@WebServlet("/Log/NewPassword")
public class NewPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPassword() {
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
			this.getServletContext().getRequestDispatcher( "/WEB-INF/newpassword.jsp" ).forward( request, response );
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
	    String confpass = request.getParameter("confpass");
	    if(pass.isEmpty()||confpass.isEmpty()) {
		    request.setAttribute( "erreur", "<p style='color:red'>Un ou plusieur champs sont vides</p>" );
			doGet(request, response);
	    }else {
	    	if(pass.equals(confpass)) {
	    		HttpSession session = request.getSession();
	    		User user = (User) session.getAttribute( "client" );
	    		if(user!=null) {
	    			try {
						UserDao.updatePassword(user.getId(), pass);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			user.setMdp(pass);
	   				System.out.println("pass");
	   				if(user.getType()==1) {
	    				response.sendRedirect("/E-Shop/Log/User");
	    			}else {
	    				response.sendRedirect("/E-Shop/Log/Admin");
	    			}
	    		}else {
	    			response.sendRedirect("/E-Shop/Log/NewPassword");
	    		}
	    	}else {
	    		request.setAttribute( "erreur", "<p style='color:red'>les mots de passes ne sont pas identiques</p>" );
				doGet(request, response);
	    	}
	    }
	}

}
