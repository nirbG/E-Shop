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
import model.Basket;
import model.User;

/**
 * Servlet implementation class AddToBasket
 */
@WebServlet("/AddToBasket")
public class AddToBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToBasket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Initialisation du panier 
		Basket b;
		// Recuperation de l'utilisateur 
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute( "client" );
		int idU = user.getId();
		
		// Recuperation du produit a ajouter au panier
		int idPr = Integer.parseInt(request.getParameter("idprod"));
		
		// Recuperer l'id du panier 
		ArrayList<Basket> lpuser;
		try {
			lpuser = BasketDao.findByUser(idU);
			int idPa;
			if (lpuser.size() == 0) {
				idPa = BasketDao.newPanier();
			} else {
				idPa = lpuser.get(0).getIdpanier();
			}
			
			// On regarde si l'utilisateur a deja le produit dans son panier
			lpuser = BasketDao.findByProductUser(idU, idPr);
			if (lpuser.size() == 0) {
				b = new Basket(idPa, user.getId(), idPr, 1);
				BasketDao.insert(b.getIdpanier(), b.getIduser(), b.getIdproduit(), b.getQuantite());
			} else {
				b = new Basket(idPa, user.getId(), idPr, lpuser.get(0).getQuantite() + 1);
				BasketDao.update(b.getIdpanier(), b.getIduser(), b.getIdproduit(), b.getQuantite());
				
			}
			
	    	response.sendRedirect("/E-Shop/ListProducts");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
