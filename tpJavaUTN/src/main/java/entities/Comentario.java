package entities;

public class Comentario {
	private int id_usuario;
	private int id_producto;
	private String descripcion;
	private int valoracion;
	private boolean isHabilitado;
	
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public int getId_producto() {
		return id_producto;
	}
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	public boolean isHabilitado() {
		return isHabilitado;
	}
	public void setHabilitado(boolean isHabilitado) {
		this.isHabilitado = isHabilitado;
	}
	
	
}
