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
 * Servlet implementation class ListProducts
 */
@WebServlet("/Log/Admin/ListModProd")
public class ListModProd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListModProd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute( "client" );
			String co="<a class='active' href='/E-Shop/Log'>Me connecter</a>";
			if(user!=null) {
				if( user.getType()==2) {
					co="<a class='active' href='/E-Shop/Log/Admin'>Mon compte</a>";
					request.setAttribute( "button", co );
					request.setAttribute( "url", "/E-Shop/Log/Admin" );
					request.setAttribute( "title", "Modifier ou supprimer un produit :" );
					ArrayList<Product> listP=ProductDao.findAll();
					String res="";
					for (Product p : listP) {
						res+="<div id='"+p.getId()+"' class='prod'>\r\n"+ 
								"	<div class='photo'></div>\r\n" +
								"	<div class='corp'>"+
								"  		<div class='h2-overflow'><a href='/E-Shop/Log/Admin/ProdControler?idprod="+p.getId()+"'>"+p.getNom()+"</a></div> \r\n" + 
								"		<div class='prix'>\r\n" + 
								"  			<div>prix:"+p.getPrix()+"�</div>\r\n" + 
								"  			<div >\r\n" + 
								"  				<div class='btn suppProd' style='float: inline-end;'>supp le produit</div>\r\n" + 
								"  			</div>\r\n" + 
								"  		</div>\r\n" + 
								"	</div>\r\n" + 
								"</div>";
					}
					request.setAttribute( "prods", res );
					this.getServletContext().getRequestDispatcher( "/WEB-INF/listModProd.jsp" ).forward( request, response );
				}else {
					response.sendRedirect("/E-Shop/Log");
				}
			}else{
				response.sendRedirect("/E-Shop/Log");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
