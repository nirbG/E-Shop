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
import dao.User2panierDao;
import dao.UserDao;
import model.Basket;
import model.Product;
import model.User;
import model.User2panier;

/**
 * Servlet implementation class DetailBasket
 */
@WebServlet("/Log/Basket")
public class DetailBasketUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailBasketUser() {
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
				if( user.getType()==1) {
					co="<a class='active' href='/E-Shop/Log/User'>Mon compte</a>";
				}else {
					co="<a class='active' href='/E-Shop/Log/Admin'>Mon compte</a>";
				}
				request.setAttribute( "url", "/E-Shop/Log/VoirPanier" );
			} else {
				response.sendRedirect("/E-Shop/Log");
			}
			request.setAttribute( "button", co );
			String idpanier = request.getParameter("idpanier");
			ArrayList<Basket> listP=BasketDao.findById(Integer.parseInt(idpanier));
			User2panier user2p=User2panierDao.findByIdpanier(Integer.parseInt(idpanier));
			User u=UserDao.findById(user2p.getIduser());
			String res="";
			float prix=0;
			for (Basket basket : listP) {
				Product p = ProductDao.find(basket.getIdproduit());
				res+="<div id='"+p.getId()+"' class='prod'>\r\n"+ 
						"	<div class='corp' style='width:100%' >"+
						"  		<div class='h2-overflow' style='font-size:20px'><a href='/E-Shop/DetailProduct?idprod="+p.getId()+"'>"+p.getNom()+"</a></div>\r\n" + 
						"  		<div class='prix' style='float:right' >\r\n" + 
						"  			<div>prix:"+p.getPrix()+"€</div>\r\n" + 
						"			<br>" +
						"			<div>quantite:"+
						"        		"+basket.getQuantite()+
						"			</div>\r\n" +	
						"  		</div>\r\n" + 
						"	</div>\r\n" + 
						"</div>";
				prix+=p.getPrix()*basket.getQuantite();
			}
			request.setAttribute( "prods", res );
			request.setAttribute( "user", u.getEmail() );
			request.setAttribute( "prix", prix+"€" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher( "/WEB-INF/MyBasketAdmin.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
