package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.User2panierDao;
import dao.UserDao;
import model.User;

/**
 * Servlet implementation class ModInfo
 */
@WebServlet("/Log/ModInfo")
public class ModInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String e=request.getParameter("email");
		User u;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute( "client" );
		if(user!=null) {
			String co ="";
			if( user.getType()==1) {
				co="<a class='active' href='/E-Shop/Log/User'>Mon compte</a>";
			}else {
				co="<a class='active' href='/E-Shop/Log/Admin'>Mon compte</a>";
			}
			request.setAttribute( "button", co );
			try {
			u = UserDao.findByEmail(e);
			request.setAttribute( "id", "<input name='id' type='hidden' value='"+u.getId()+"'>");
			request.setAttribute( "nom", u.getNom() );
			request.setAttribute( "prenom", u.getPrenom() );
			request.setAttribute( "email", u.getEmail());
			} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		}
		this.getServletContext().getRequestDispatcher( "/WEB-INF/modInfo.jsp" ).forward( request, response );
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
				response.getWriter().write("<message >modUser</message>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "2":
			try {
				modUser( request,  response);
				response.sendRedirect("/E-Shop/Log/Admin");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				response.getWriter().write("<message >modadmin</message>");
			break;
		case "3":
			try {
				supp( request,  response);
				response.getWriter().write("<message >supp</message>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		case "4":
			try {
				User u=insert(request,  response);
				if(u!=null) {
					u.setIdpanier(User2panierDao.newPanier(u.getId()));
				}
				response.getWriter().write(u.getId()+"");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		}
	}
	
	protected void modBasicInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute( "client" );
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		User u=UserDao.update(user.getId(),nom, prenom, email);
		
		session.setAttribute("client" , u);
	}
	
	protected void modUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String id = request.getParameter("id");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		User u=UserDao.update(Integer.parseInt(id),nom, prenom, email);

	}
	
	protected User insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		return UserDao.insert(nom, prenom, password, email);

	}
	
	protected void supp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String id = request.getParameter("id");
		User2panierDao.supp(Integer.parseInt(id));
		UserDao.supp(Integer.parseInt(id));
	}

}
