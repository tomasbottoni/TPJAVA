package logic;

import data.DataVenta;
import entities.Venta_Producto;
import entities.Venta;

import java.util.ArrayList;
import java.util.LinkedList;

public class LogicVenta {
	
	private DataVenta dv;
	
	public LogicVenta() {
		dv = new DataVenta();
	}
	
	public void confirmarVenta(Venta v, ArrayList<int[]> listaCarrito)  {
		
		dv.confirmarVenta(v, listaCarrito);
	}
	
	public LinkedList<Venta_Producto> getAllVentaProducto(int id_usuario){
		return dv.getAllVentaProducto(id_usuario);
	}
	
	public void UpdateWasValued(int id_venta, int id_producto, boolean condicion) {
		dv.UpdateWasValued(id_venta, id_producto, condicion);
	}
	
	public void UpdateEstado(int id, String estado) {
		dv.UpdateEstado(id, estado);
	}
	
}
