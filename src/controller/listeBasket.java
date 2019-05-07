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
import dao.User2panierDao;
import dao.UserDao;
import model.Product;
import model.User;
import model.User2panier;

/**
 * Servlet implementation class lusteBasket
 */
@WebServlet("/Log/Admin/listeBasket")
public class listeBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listeBasket() {
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
					request.setAttribute( "title", "Tous les paniers :" );
					request.setAttribute( "url", "/E-Shop/Log/Admin" );
					ArrayList<User2panier> listP=User2panierDao.findAll();
					String res="";
					for (User2panier p : listP) {
						User u=UserDao.findById( p.getIduser());
						float prix=BasketDao.getPrice(p.getIdpanier());
						String valider="<div class='photo' style=' background-color:orange;'></div>";
						if(p.getValider()==1) {
							 valider="<div class='photo'style=' background-color:green;'></div>";	
						}
						res+="<div id='"+p.getIdpanier()+"' class='prod'>\r\n"+ 
								valider +
								"	<div class='corp'>"+
								"  		<div class='h2-overflow'><a href='/E-Shop/Log/Admin/Basket?idpanier="+p.getIdpanier()+"'>"+u.getEmail()+"</a></div>\r\n" + 
								"  		<div class='prix'>\r\n" + 
								"  			<div>prix:"+prix+"€</div>\r\n" + 
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
