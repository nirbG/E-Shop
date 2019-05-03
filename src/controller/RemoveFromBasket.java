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
 * Servlet implementation class RemoveFromBasket
 */
@WebServlet("/RemoveFromBasket")
public class RemoveFromBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveFromBasket() {
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

		// Recuperation du produit a supprimer du panier
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
			
			if (idPr == -1) { // Vidage de panier 
				// On supprime les objetdu panier 
				BasketDao.supp(idPa);
				
				// Retour au home 
				response.sendRedirect("/E-Shop/Home");
			} else { // Suppression objet du panier 
				// On dupprime l'objet
				BasketDao.suppProduct(idPa, idPr);

				// Retour au panier 
				response.sendRedirect("/E-Shop/MyBasket");
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
