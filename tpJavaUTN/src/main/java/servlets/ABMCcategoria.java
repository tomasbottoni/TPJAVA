package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Categoria;
import logic.LogicCategoria;

/**
 * Servlet implementation class ABMCcategoria
 */
@WebServlet("/ABMCcategoria")
public class ABMCcategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMCcategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion;
		LogicCategoria lc = new LogicCategoria();
		LinkedList<Categoria> listaCategorias;
		RequestDispatcher distpacher = null;
		
		accion = request.getParameter("accion");

		if("nuevo".equals(accion)) {

			distpacher = request.getRequestDispatcher("nuevaCategoria.jsp");

			
	} else if ("insert".equals(accion)) {

		Categoria cat= new Categoria();
		
		cat.setDenominacion(request.getParameter("denominacion"));
		lc.add(cat);
		distpacher = request.getRequestDispatcher("Categorias.jsp");
		listaCategorias = lc.getAll();
		request.setAttribute("listaCat", listaCategorias);
		

	} else if("modificar".equals(accion)) {
		
		Categoria cat= new Categoria();
		
		int id = Integer.parseInt(request.getParameter("id"));
		cat = lc.getOne(id);
		request.setAttribute("categoria", cat);
		distpacher = request.getRequestDispatcher("modificarCategoria.jsp");
		
	}else if("update".equals(accion)) {
		
		Categoria cat= new Categoria();
		
		cat.setId_categoria(Integer.parseInt(request.getParameter("id")));
		cat.setDenominacion(request.getParameter("denominacion"));
		
		lc.update(cat);
		
		
		listaCategorias = lc.getAll();
		request.setAttribute("listaCat", listaCategorias);
		distpacher = request.getRequestDispatcher("Categorias.jsp");
	}else if("eliminar".equals(accion)) {
		Categoria cat= new Categoria();
		
		cat.setId_categoria(Integer.parseInt(request.getParameter("id")));
		
		try {
			lc.delete(cat);
		} catch (SQLException e) {
			request.setAttribute("error", "Ha ocurrido un error al eliminar la categoría");
		}	
		
		listaCategorias = lc.getAll();
		request.setAttribute("listaCat", listaCategorias);
		distpacher = request.getRequestDispatcher("Categorias.jsp");
	}
		
		
		distpacher.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
