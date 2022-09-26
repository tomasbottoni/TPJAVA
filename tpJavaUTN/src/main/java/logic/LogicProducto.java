package logic;

import java.util.LinkedList;

import data.DataProducto;
import entities.Producto;

public class LogicProducto {
	
	private DataProducto dp;
	
	public LogicProducto() {
		dp=new DataProducto();
	}
	
	public Producto getOne(int id) { 

		return dp.getOne(id);
	}
	
	public LinkedList<Producto> getAll(){
		return dp.getAll();
	}
	
	public LinkedList<Producto> getbyPrecio(Producto pro, String cond){
		return dp.getByPrecio(pro, cond);
	}
	
	public LinkedList<Producto> getbyCategoria(String catDen){
		return dp.getByCategoria(catDen);
	}
	
	public void add(Producto p) {
		dp.add(p);
	}
	
	public void update(Producto p) {
		dp.update(p);
		
	}
	
	public void delete(Producto p) {
		dp.delete(p);
		
	}
	
	public void UpdateValoracion(int id_prod, int promedio, int cantidad) {
		dp.UpdateValoracion(id_prod, promedio, cantidad);
	}
}
