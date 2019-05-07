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

import dao.BasketDao;
import dao.ProductDao;
import model.Basket;
import model.Product;
import model.User;

/**
 * Servlet implementation class MyBasket
 */
@WebServlet("/MyBasket")
public class MyBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBasket() {
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
			} else {
				response.sendRedirect("/E-Shop/Log");
			}
			request.setAttribute( "button", co );
			
			ArrayList<Basket> listP=BasketDao.findById(user.getIdpanier());

			String res="";
			for (Basket basket : listP) {
				Product p = ProductDao.find(basket.getIdproduit());
				res+="<div id='"+p.getId()+"' style='padding:1%'class=''>\r\n"+ 
						"	<div class='corp' style='width:100%' >"+
						"  		<div class='h2-overflow' style='font-size:20px'><a href='/E-Shop/DetailProduct?idprod="+p.getId()+"'>"+p.getNom()+"</a></div>\r\n" + 
						"  		<div class='prix' style='float:left' >\r\n" + 
						"  			<div>prix:"+p.getPrix()+"€</div>\r\n" +
						"			<div>quantite:"+
						"        		"+basket.getQuantite()+
						"			</div>\r\n" +	
						"  		</div>\r\n" + 
						"		<div style='width: 50%;display: inline-block;' >\r\n" + 
						"				<a style='margin:2%' href=\"/E-Shop/RemoveFromBasket?action=4&idprod="+p.getId()+"\">"+ 
						"				 	<button class='btn' style='float: inline-end;'>+</button>"+ 
						"				</a>"+
						"				<a style='margin:2%' href=\"/E-Shop/RemoveFromBasket?action=3&idprod="+p.getId()+"\">"+ 
						"				 	<button class='btn' style='float: inline-end;'>-</button>"+ 
						"				</a>"+
						"				<a style='margin:2%' href=\"/E-Shop/RemoveFromBasket?action=2&idprod="+p.getId()+"\">" + 
						"  					<button class=\"btn\" style=\"float: inline-end;\">Supprimer</button>\r\n" + 
						" 				</a>" +
						"  			</div>\r\n" +
						"	</div>\r\n" + 
						"</div>";
			}
			request.setAttribute( "prods", res );
			
			request.setAttribute("url","/E-Shop/RemoveFromBasket?action=1&idpanier="+user.getIdpanier()+"&idprod=-1" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher( "/WEB-INF/MyBasket.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
