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
import dao.UserDao;
import model.Product;
import model.User;

/**
 * Servlet implementation class ProdControler
 */
@WebServlet("/Log/Admin/ProdControler")
public class ProdControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdControler() {
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
				if( user.getType()==2) {
					co="<a class='active' href='/E-Shop/Log/Admin'>Mon compte</a>";
					request.setAttribute( "button", co );
					String id=request.getParameter("idprod");
					Product p;
						p = ProductDao.find(Integer.parseInt(id));
						request.setAttribute( "id", "<input id='id' name='id' type='hidden' value='"+p.getId()+"'>");
						request.setAttribute( "nom", p.getNom() );
						request.setAttribute( "description", p.getDescription() );
						request.setAttribute( "prix","<input id='prix' type='number' step='any' name='prix' value='"+p.getPrix()+"'> ");
						this.getServletContext().getRequestDispatcher( "/WEB-INF/prodControler.jsp" ).forward( request, response );
				}else {
					response.sendRedirect("/E-Shop/Log");
				}
			}else{
				response.sendRedirect("/E-Shop/Log");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String act = request.getParameter("action");
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		switch(act) {
		case "1":
			try {
				modBasicInfo( request,  response);
				response.getWriter().write("<message >modprod</message>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "2":
			try {
				supp( request,  response);
				response.getWriter().write("<message >supp</message>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		case "3":
			try {
				insert(request,  response);
				response.getWriter().write("<message >add</message>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		}
	}
	
	protected void modBasicInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String id = request.getParameter("id");
		String nom = request.getParameter("nom");
		String desc = request.getParameter("description");
		Float prix =Float.parseFloat( request.getParameter("prix"));
		ProductDao.update(Integer.parseInt(id),nom, desc,prix);

	}
	
	
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		String prix = request.getParameter("prix");
		ProductDao.insert(nom, description, Float.parseFloat(prix));

	}
	
	protected void supp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String id = request.getParameter("id");
		ProductDao.supp(Integer.parseInt(id));
	}

}
