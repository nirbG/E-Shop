package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDao;
import model.Product;
import model.User;

/**
 * Servlet implementation class CreateProd
 */
@WebServlet("/Log/Admin/CreateProd")
public class CreateProd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute( "client" );
		String co="<a class='active' href='/E-Shop/Log'>Me connecter</a>";
		if(user!=null) {
			if( user.getType()==2) {
				co="<a class='active' href='/E-Shop/Log/Admin'>Mon compte</a>";
				request.setAttribute( "button", co );
				this.getServletContext().getRequestDispatcher( "/WEB-INF/createProd.jsp" ).forward( request, response );
			}else {
				response.sendRedirect("/E-Shop/Log");
			}
		}else{
			response.sendRedirect("/E-Shop/Log");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
