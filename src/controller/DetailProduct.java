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
 * Servlet implementation class DetailProduct
 */
@WebServlet("/DetailProduct")
public class DetailProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("idprod");
		System.out.println(id);
		if(id!=null) {
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
				Product p=ProductDao.find(Integer.parseInt(id));
				if(p!=null) {
				String res="<div id='"+p.getId()+"' class='prod'>\r\n" + 
						"  			<div class='prix'>\r\n" + 
						"  			<h3 style='margin:0%'>"+p.getNom()+"</h3>\r\n" +
						"  				<div style='text-align:end'>prix:"+p.getPrix()+"€</div>\r\n" +  
						"  			</div>\r\n" + 
						"  			<div class='prix'>\r\n" + 
						"				<div id='desc'>"+p.getDescription()+"</div>" + 
						"			<div >\r\n" + 
						"				<a href=\"/E-Shop/AddToBasket?idprod=" + p.getId() + "\">" + 
						"  					<button class=\"btn\" style=\"float: inline-end;\">Ajouter au panier</button>\r\n" + 
						"				</a>"+		
						"  			</div>\r\n" +
						"  			</div>\r\n" + 
						"  		</div>";
				request.setAttribute( "prod", res );
				}
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher( "/WEB-INF/detailProd.jsp" ).forward( request, response );
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
