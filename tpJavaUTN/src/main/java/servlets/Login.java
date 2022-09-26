package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Usuario;
import logic.LogicUsuario;

/**
 * Servlet implementation class Login
 */
@WebServlet({ "/Login", "/login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario us = new Usuario();
		LogicUsuario lu = new LogicUsuario();
		
		us.setNombreUsuario(request.getParameter("user"));
		us.setClave(request.getParameter("pass"));
		
		us= lu.validate(us);
		
		if(us != null) {
	        request.getSession().setAttribute("user", us);
	        //response.getWriter().append("Bienvenido ").append(us.getNombre()).append(us.getApellido());
	        request.getRequestDispatcher("WEB-INF/mainPage.jsp").forward(request, response);
	        }
	        else {
	        	request.setAttribute("msg", "Usuario y/o contraseña inexistente");
	        	request.getRequestDispatcher("index.jsp").forward(request, response);;
	        }
	
		
	}

}
