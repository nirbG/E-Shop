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
import model.User;
import model.User2panier;

/**
 * Servlet implementation class VoirPanier
 */
@WebServlet("/Log/VoirPanier")
public class VoirPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoirPanier() {
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
					request.setAttribute( "url", "/E-Shop/Log/Admin" );
				}else {
					co="<a class='active' href='/E-Shop/Log/User'>Mon compte</a>";
					request.setAttribute( "url", "/E-Shop/Log/User" );
				}
				request.setAttribute( "button", co );
				request.setAttribute( "title", "Vos panier d�ja valid� :" );
				ArrayList<User2panier> listP=User2panierDao.findsByIduser(user.getId());
				String res="";
				for (User2panier p : listP) {
					if(p.getValider()==1) {
						User u=UserDao.findById( p.getIduser());
						float prix=BasketDao.getPrice(p.getIdpanier());
						String valider="<div class='photo' style=' background-color:orange;'></div>";
						if(p.getValider()==1) {
							valider="<div class='photo' style=' background-color:green;'></div>";	
						}
						res+="<div id='"+p.getIdpanier()+"' class='prod'>\r\n"+ 
							valider +
							"	<div class='corp'>"+
							"  		<div class='h2-overflow'><a href='/E-Shop/Log/Basket?idpanier="+p.getIdpanier()+"'>"+u.getEmail()+"</a></div>\r\n" + 
							"  		<div class='prix'>\r\n" + 
							"  			<div>prix:"+prix+"�</div>\r\n" + 
							"  		</div>\r\n" + 
							"	</div>\r\n" + 
							"</div>";
					}
				}
				request.setAttribute( "prods", res );
				this.getServletContext().getRequestDispatcher( "/WEB-INF/listModProd.jsp" ).forward( request, response );
				}else {
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
