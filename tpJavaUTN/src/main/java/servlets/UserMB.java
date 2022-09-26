package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.TipoUsuario;
import entities.Usuario;
import logic.LogicUsuario;

/**
 * Servlet implementation class UserMB
 */
@WebServlet("/UserMB")
public class UserMB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMB() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LogicUsuario lu = new LogicUsuario();
		String opc = request.getParameter("accion");
		RequestDispatcher redir = null;
		HttpSession sesion = request.getSession();
		
		if(opc.equalsIgnoreCase("update"))
		{
			
			lu.update(getUser(request));
			
			sesion.setAttribute("user", getUser(request)); //creo una sesion nueva
			
			redir = request.getRequestDispatcher("miPerfil.jsp");
		}
		else if(opc.equalsIgnoreCase("delete"))
		{
			lu.delete(getUser(request));
			
			sesion.invalidate();
			redir = request.getRequestDispatcher("index.jsp");
			
		}
		
		redir.forward(request, response);
		
	}

	
	private Usuario getUser(HttpServletRequest request) {
		
		Usuario user = new Usuario();
		TipoUsuario usertype = new TipoUsuario();
		
		user.setId_usuario(Integer.parseInt(request.getParameter("id_user")));
		user.setApellido(request.getParameter("apellido"));
		user.setClave(request.getParameter("clave"));
		user.setEmail(request.getParameter("mail"));
		user.setNombre(request.getParameter("nombre"));
		user.setNombreUsuario(request.getParameter("username"));
		usertype.setId_TipoUsuario(Integer.parseInt(request.getParameter("usertype")));
		user.setTipoUsuario(usertype);
		
		return user;
		
	}

}
