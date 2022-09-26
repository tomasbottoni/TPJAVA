package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Producto;
import logic.LogicProducto;

/**
 * Servlet implementation class ListProduct
 */
@WebServlet({"/ListProducts", "/listProducts"})
public class ListProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
    	Producto pr = new Producto();
		LogicProducto lp = new LogicProducto();
		String cond = null;
		LinkedList<Producto> listProd;
		
		if(request.getParameter("precio").isBlank()) {
				listProd = lp.getAll();
			}else {
				pr.setPrecio(Integer.parseInt(request.getParameter("precio")));
				cond = getPressedBtn(request);
				listProd = lp.getbyPrecio(pr, cond);
			}
				
		request.setAttribute("lista", listProd);
		request.getRequestDispatcher("listProducts.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String getPressedBtn(HttpServletRequest request){
	    return request.getParameter("button");
	}

}
