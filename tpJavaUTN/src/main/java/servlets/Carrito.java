package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;

import entities.Producto;
import entities.Usuario;
import logic.LogicProducto;
import logic.LogicVenta;
import entities.Venta;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/Carrito", "/carrito" })
public class Carrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
    public Carrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher distpacher = null;
		
		LinkedList<Producto> listaProductos;
		LogicProducto lp= new LogicProducto();
		
		double totalPagar;
		ArrayList<int[]> listaCarrito; //creo un arraylist, cada elemento del arraylist va a tener un array que en la primera posicion 
										//tenga el id del producto y en la otra posicion la cantidad
		
		if(request.getSession().getAttribute("listaCarrito") == null) {
			listaCarrito = new ArrayList<>();
		}else {
			listaCarrito = (ArrayList<int[]>)request.getSession().getAttribute("listaCarrito");
		}
		
		String accion = request.getParameter("accion");
		
		if("agregarCarrito".equals(accion)) { 
			int idp= Integer.parseInt(request.getParameter("id"));
			boolean bandera=false;
			int indice = 0;
			
			//Me fijo si idp ya está en el array
			for(int i=0; i<listaCarrito.size(); i++) {
				if(idp == listaCarrito.get(i)[0]) {
					bandera = true;
					indice  = i;
					break;
				}else {
					bandera = false;
				}
			}
			
			//Si idp ya está, le sumo uno a la cantidad, sino está lo agrego al arraylist
			if(bandera == true) {
				listaCarrito.get(indice)[1] = listaCarrito.get(indice)[1] + 1;
			}else {
				int[] elemento = {idp, 1};
				listaCarrito.add(elemento);
			}
			
			
			listaProductos = lp.getAll();
			request.getSession().setAttribute("listaCarrito", listaCarrito);
			request.setAttribute("lista", listaProductos);
			distpacher = request.getRequestDispatcher("listProducts.jsp");
			
		}else if("agregarCarritoxProd".equals(accion)) { 
			int idp= Integer.parseInt(request.getParameter("id"));
			boolean bandera=false;
			int indice = 0;
			
			//Me fijo si idp ya está en el array
			for(int i=0; i<listaCarrito.size(); i++) {
				if(idp == listaCarrito.get(i)[0]) {
					bandera = true;
					indice  = i;
					break;
				}else {
					bandera = false;
				}
			}
			
			//Si idp ya está, le sumo uno a la cantidad, sino está lo agrego al arraylist
			if(bandera == true) {
				listaCarrito.get(indice)[1] = listaCarrito.get(indice)[1] + 1;
			}else {
				int[] elemento = {idp, 1};
				listaCarrito.add(elemento);
			}
			
			
			listaProductos = lp.getAll();
			request.getSession().setAttribute("listaCarrito", listaCarrito);
			request.setAttribute("lista", listaProductos);
			LinkedList<Producto> list = lp.getbyCategoria(request.getParameter("catDen"));
			request.setAttribute("list", list);
			distpacher = request.getRequestDispatcher("listProductsByCat.jsp");
			
		}else if("carrito".equals(accion)) {
			
			totalPagar=0.0;
			Producto p ;
			
			for(int i=0; i< listaCarrito.size(); i++) {
				int id = listaCarrito.get(i)[0];
				p =  lp.getOne(id);
				totalPagar = totalPagar + (p.getPrecio() * listaCarrito.get(i)[1]);	
			}
			
			request.setAttribute("totalPagar",  String.valueOf(totalPagar));
			distpacher = request.getRequestDispatcher("Carro_de_compras.jsp");
			
		}else if("limpiar".equals(accion)) {
			listaCarrito.clear();
			distpacher = request.getRequestDispatcher("Carro_de_compras.jsp");
		}else if("comprar".equals(accion)) {
			
				if(listaCarrito.size() == 0) {
					
					listaProductos = lp.getAll();
					request.setAttribute("lista", listaProductos);
					distpacher = request.getRequestDispatcher("listProducts.jsp");
				}else {
					
					totalPagar=0.0;
					Producto p ;
					
					for(int i=0; i< listaCarrito.size(); i++) {
						int id = listaCarrito.get(i)[0];
						p =  lp.getOne(id);
						totalPagar = totalPagar + (p.getPrecio() * listaCarrito.get(i)[1]);	
					}
					request.setAttribute("totalPagar",  String.valueOf(totalPagar));
					distpacher = request.getRequestDispatcher("ConfirmarCompra.jsp");
				}	
		}else {
			listaProductos = lp.getAll();
			request.setAttribute("lista", listaProductos);
			distpacher = request.getRequestDispatcher("listProducts.jsp");
		}
		
		distpacher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		LogicVenta lv = new LogicVenta();
		
		int id_venta = Integer.parseInt(request.getParameter("cambiarEstado")); 
		
		lv.UpdateEstado(id_venta,"entregado");
		
		request.getRequestDispatcher("MisCompras.jsp").forward(request, response);
	}

}
