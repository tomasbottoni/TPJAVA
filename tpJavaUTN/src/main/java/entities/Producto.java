package entities;

public class Producto {

	private int id;
	private String descripcion;
	private double precio;
	private int stock;
	private Categoria categoria;
	private int promedio_valoracion;
	private int cantidad_valoraciones;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public int getPromedio_valoracion() {
		return promedio_valoracion;
	}
	public void setPromedio_valoracion(int promedio_valoracion) {
		this.promedio_valoracion = promedio_valoracion;
	}
	
	public int getCantidad_valoraciones() {
		return cantidad_valoraciones;
	}
	public void setCantidad_valoraciones(int cantidad_valoraciones) {
		this.cantidad_valoraciones = cantidad_valoraciones;
	}
	
	
	
}
