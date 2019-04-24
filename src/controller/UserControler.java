package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;



/**
 * Servlet implementation class UserControler
 */
@WebServlet("/Log/Admin")
public class UserControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String tableU="<table ><tbody id='tab'><tr>"+
				"<td>Id</td>"+
				"<td>Nom</td>"+
				"<td>Prenom</td>"+
				"<td>Email</td>"+
				"<td>Type</td>"+
				"<td></td>"+
				"</tr>";
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute( "client" );
		try {			
			String co="<a class='active' href='/E-Shop/Log'>Me connecter</a>";
			if(user!=null) {
				if( user.getType()==1) {
					co="<a class='active' href='/E-Shop/Log/User'>Mon compte</a>";
				}else {
					co="<a class='active' href='/E-Shop/Log/Admin'>Mon compte</a>";
				}
				request.setAttribute( "button", co );
				for (model.User u : UserDao.findAll()) {
					if(u.getId()!=user.getId()) {
						String btnSupp="";
						if(u.getType()==1) {
							btnSupp="<div class='btn suppUser'>supp</div>";
						}
						tableU+="<tr class='user' id='"+u.getId()+"'>"+
								"<td>"+u.getId()+"</td>"+
								"<td>"+u.getNom()+"</td>"+
								"<td>"+u.getPrenom()+"</td>"+
								"<td>"+u.getEmail()+"</td>"+
								"<td>"+u.getType()+"</td>"+
								"<td class='wrapperbtn'><div class='btn '><a href='/E-Shop/ModInfo?email="+u.getEmail()+"'>mod</a></div>"+btnSupp+"</td>"+
								"</tr>";
					}
				}
				request.setAttribute( "nom", user.getNom() );
				request.setAttribute( "prenom", user.getPrenom() );
				request.setAttribute( "email", user.getEmail());
				request.setAttribute( "users", tableU+"</tbody></table>" );
				this.getServletContext().getRequestDispatcher( "/WEB-INF/users.jsp" ).forward( request, response );
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

		String id = request.getParameter("action");
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		switch (id) {
		case "1":
			model.User u=null;
			try {
				u = UserDao.insert(request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("password"),request.getParameter("email"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String s="<td>"+u.getId()+"</td>"
					+"<td>"+u.getNom()+"</td>"
					+"<td>"+u.getPrenom()+"</td>"
					+"<td>"+u.getEmail()+"</td>"
					+"<td><a href=''> mod </a> <a href=''> supp </a></td>";
			response.getWriter().write(s);
			break;
		case "2":
			response.getWriter().write("<message style='color:red'>!!le champ password est vide!!</message>");
			break;
		case "3":
			response.getWriter().write("<message style='color:red'>!!le champ password est vide!!</message>");
			break;

		default:
			break;
		}
	}

}
