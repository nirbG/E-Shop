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
		String a= request.getParameter("action");
		switch(a) {
		case "1":
			try {
				BasketDao.supp(user.getIdpanier());
			} catch (NumberFormatException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			response.sendRedirect("/E-Shop/Home");
			break;
		case "2":
			int idPr = Integer.parseInt(request.getParameter("idprod"));
			try {
				BasketDao.suppProduct(user.getIdpanier(), idPr);
				response.sendRedirect("/E-Shop/MyBasket");
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "3":
			System.out.println("min");
			int idProd = Integer.parseInt(request.getParameter("idprod"));
			ArrayList<Basket> lpuser;
			try {
				lpuser = BasketDao.findByProduct(user.getIdpanier(), idProd);
				if(lpuser.get(0).getQuantite()>1) {
					b = new Basket(user.getIdpanier(), idProd, lpuser.get(0).getQuantite() - 1);
					BasketDao.update(user.getIdpanier(), b.getIdproduit(), b.getQuantite());
				}else {
					BasketDao.suppProduct(user.getIdpanier(), idProd);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("/E-Shop/MyBasket");
			break;
		case "4":
			System.out.println("add");
			int idProduc = Integer.parseInt(request.getParameter("idprod"));
			ArrayList<Basket> lpuseradd;
			try {
				lpuseradd = BasketDao.findByProduct(user.getIdpanier(), idProduc);
				b = new Basket(user.getIdpanier(), idProduc, lpuseradd.get(0).getQuantite() + 1);
				BasketDao.update(user.getIdpanier(), b.getIdproduit(), b.getQuantite());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("/E-Shop/MyBasket");
			break;
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
