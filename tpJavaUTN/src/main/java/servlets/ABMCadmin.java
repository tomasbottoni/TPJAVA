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

import entities.Flete;
import entities.Localidad;
import entities.Zona;
import logic.LogicFlete;
import logic.LogicLocalidad;
import logic.LogicZona;

/**
 * Servlet implementation class ABMCflete
 */
@WebServlet("/ABMCadmin")
public class ABMCadmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMCadmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion;
		LogicZona lz = new LogicZona();
		LogicLocalidad ll = new LogicLocalidad();
		LogicFlete lf = new LogicFlete();
		LinkedList<Flete> listaFletes;
		LinkedList<Zona> listaZonas;
		LinkedList<Localidad> listaLoca;
		RequestDispatcher distpacher = null;
		
		accion = request.getParameter("accion");

		if("nuevoFlete".equals(accion)) {

			distpacher = request.getRequestDispatcher("nuevoFlete.jsp");

			
	} else if ("insertFlete".equals(accion)) {

		Flete fle= new Flete();
		
		fle.setNombre(request.getParameter("nombre"));
		lf.add(fle);
		distpacher = request.getRequestDispatcher("Fletes.jsp");
		listaFletes = lf.getAll();
		request.setAttribute("listaFle", listaFletes);
		

	} else if("modificarFlete".equals(accion)) {
		
		Flete fle= new Flete();
		
		int id = Integer.parseInt(request.getParameter("id"));
		fle = lf.getOne(id);
		request.setAttribute("flete", fle);
		distpacher = request.getRequestDispatcher("modificarFlete.jsp");
		
	}else if("updateFlete".equals(accion)) {
		
		Flete fle= new Flete();
		fle.setId_flete(Integer.parseInt(request.getParameter("id")));
		fle.setNombre(request.getParameter("nombre"));
		lf.update(fle);
		
		
		listaFletes = lf.getAll();
		request.setAttribute("listaFle", listaFletes);
		distpacher = request.getRequestDispatcher("Fletes.jsp");
	}else if("eliminarFlete".equals(accion)) {
		Flete fle= new Flete();
		
		fle.setId_flete(Integer.parseInt(request.getParameter("id")));
		
		try {lf.delete(fle);}
		catch(SQLException e) {request.setAttribute("error", "Error al eliminar el flete ya que está asociado a una zona.");}
		
		listaFletes = lf.getAll();
		request.setAttribute("listaFle", listaFletes);
		distpacher = request.getRequestDispatcher("Fletes.jsp");
	} else if ("nuevaZona".equals(accion)) {
		
		distpacher = request.getRequestDispatcher("nuevaZona.jsp");
		
	}else if("insertZona".equals(accion)) {
		Zona zo = new Zona();
		Flete fle;
		
		zo.setDescripcion(request.getParameter("descripcion"));
		fle = lf.getByNombre(request.getParameter("tipoFlete"));
		zo.setFlete(fle);
		lz.add(zo);
		
		
		listaZonas = lz.getAll();
		request.setAttribute("listaZonas", listaZonas);
		distpacher = request.getRequestDispatcher("Zonas.jsp");
	}else if("modificarZona".equals(accion)) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Zona zo = lz.getOne(id);
		request.setAttribute("zona", zo);
		distpacher = request.getRequestDispatcher("modificarZona.jsp");
		
	}else if("updateZona".equals(accion)) {
		Zona zo = new Zona();
		Flete fle = new Flete();
		
		zo.setCod_zona(Integer.parseInt(request.getParameter("id")));;
		zo.setDescripcion(request.getParameter("descripcion"));
		fle = lf.getByNombre(request.getParameter("tipoFlete"));
		zo.setFlete(fle);
		lz.update(zo);
		
		
		listaZonas = lz.getAll();
		request.setAttribute("listaZonas", listaZonas);
		distpacher = request.getRequestDispatcher("Zonas.jsp");
	}else if("eliminarZona".equals(accion)) {
		Zona zo = new Zona();
		
		zo.setCod_zona(Integer.parseInt(request.getParameter("id")));
		
		try {
			lz.delete(zo);
		} catch (SQLException e) {
			request.setAttribute("error", "Ha ocurrido un error al eliminar la zona ya que está asociada a una localidad");
		}	
		
		listaZonas = lz.getAll();
		request.setAttribute("listaZonas", listaZonas);
		distpacher = request.getRequestDispatcher("Zonas.jsp");
	}
	else if("nuevaLoca".equals(accion)) {
		
		distpacher = request.getRequestDispatcher("nuevaLocalidad.jsp");
		
	}else if("insertLoca".equals(accion)) {
		Localidad lo = new Localidad();
		Zona zo;
		
		lo.setCod_postal(Integer.parseInt(request.getParameter("codPostal")));
		lo.setDescripcion(request.getParameter("descripcion"));
		zo = lz.getByDescripcion(request.getParameter("tipoZona"));
		lo.setZona(zo);
		ll.add(lo);
		
		
		listaLoca = ll.getAll();
		request.setAttribute("listaLoca", listaLoca);
		distpacher = request.getRequestDispatcher("Localidades.jsp");
	}else if("modificarLoca".equals(accion)) {
		
		int id = Integer.parseInt(request.getParameter("codPostal"));
		Localidad lo = ll.getOne(id);
		request.setAttribute("localidad", lo);
		distpacher = request.getRequestDispatcher("modificarLocalidad.jsp");
		
	}else if("updateLoca".equals(accion)) {
		Localidad lo = new Localidad();
		Zona zo;
		
		lo.setCod_postal(Integer.parseInt(request.getParameter("codPostal")));;
		lo.setDescripcion(request.getParameter("descripcion"));
		zo = lz.getByDescripcion(request.getParameter("tipoZona"));
		lo.setZona(zo);
		ll.update(lo);
		
		listaLoca = ll.getAll();
		request.setAttribute("listaLoca", listaLoca);
		distpacher = request.getRequestDispatcher("Localidades.jsp");
	}else if("eliminarLoca".equals(accion)) {
		Localidad lo = new Localidad();
		
		lo.setCod_postal(Integer.parseInt(request.getParameter("id")));
		
		try {
			ll.delete(lo);
		} catch (SQLException e) {
			request.setAttribute("error", "Ha ocurrido un error al eliminar la localidad");
		}	
		
		listaLoca = ll.getAll();
		request.setAttribute("listaLoca", listaLoca);
		distpacher = request.getRequestDispatcher("Localidades.jsp");
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
