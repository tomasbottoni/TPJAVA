package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Producto;
import logic.LogicProducto;

/**
 * Servlet implementation class MainPage
 */
@WebServlet({ "/MainPage", "/mainPage", "/mainpage" })
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LogicProducto lp = new LogicProducto();
		LinkedList<Producto> list;
		
		String accion = request.getParameter("accion");
		
		if(accion.equals("logout")) {
		CloseSession(request);
		request.setAttribute("msg", "Sesión cerrada con éxito");
		request.getRequestDispatcher("./index.jsp").forward(request, response);
		}
		else if(accion.equals("home")) {
			if(request.getSession(false) != null) {
			request.getRequestDispatcher("WEB-INF/mainPage.jsp").forward(request, response);
			}
		}
		else if(accion.equals("list")) {
			
			list = lp.getbyCategoria(request.getParameter("catDen"));
			request.setAttribute("list", list);
			request.getRequestDispatcher("listProductsByCat.jsp").forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	private void CloseSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
		    session.invalidate();
		}
	}

}
