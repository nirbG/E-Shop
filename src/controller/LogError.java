package controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValiderS
 */
@WebServlet("/LogError")
public class LogError extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogError() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String id = request.getParameter("idError");
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		switch (id) {
		case "1":
			response.getWriter().write("<message style='color:red'>!!le champ nom est vide!!</message>");
			break;
		case "2":
			response.getWriter().write("<message style='color:red'>!!le champ prenom est vide!!</message>");
			break;
		case "3":
			response.getWriter().write("<message style='color:red'>!!le champ ??? est vide!!</message>");
			break;
		case "4":
			response.getWriter().write("<message style='color:red'>!!le champ password est vide!!</message>");
			break;
		case "5":
			response.getWriter().write("<message style='color:red'>!!le champ email est vide!!</message>");
			break;
		case "6":
			response.getWriter().write("<message style='color:red'>!!les champs sont vides!!</message>");
			break;
		case "7":
			response.getWriter().write("<message style='color:red'>!!les passwords ne sont pas identiques!!</message>");
			break;
		case "8":
			response.getWriter().write("<message style='color:red'>!!le prix est soit vide ou négatif!!</message>");
			break;
		default:
			response.getWriter().write("<message style='color:red'>!!tous les champs sont vides!!</message>");
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
