package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * Servlet implementation class Home
 */
@WebServlet("/")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
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
			request.setAttribute( "button", co );
			ArrayList<Product> listP=ProductDao.findsome(10);
			String res="";
			for (Product p : listP) {
				res+="<div id='"+p.getId()+"' class='prod'>\r\n"+ 
						"	<div class='photo'></div>\r\n" +
						"	<div class='corp' >"+
						"  		<div class='h2-overflow'><a href='/E-Shop/DetailProduct?idprod="+p.getId()+"'>"+p.getNom()+"</a></div>\r\n" + 
						"  		<div class='prix'>\r\n" + 
						"  			<div>prix:"+p.getPrix()+"�</div>\r\n" + 
						"  			<div >\r\n" + 
						"				<a href=\"/E-Shop/AddToBasket?idprod=" + p.getId() + "\">" + 
						"  					<button class=\"btn\" style=\"float: inline-end;\">Ajouter au panier</button>\r\n" + 
						"				</a>"+		
						"  			</div>\r\n" + 
						"  		</div>\r\n" + 
						"	</div>\r\n" + 
						"</div>";
			}
			request.setAttribute( "prods", res );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher( "/WEB-INF/Home.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
