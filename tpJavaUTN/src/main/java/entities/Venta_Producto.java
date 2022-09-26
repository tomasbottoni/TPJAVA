package entities;

public class Venta_Producto {
	private Venta venta;
	private Producto prod;
	private int cantidad;
	private boolean wasValued;
	
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	public Producto getProd() {
		return prod;
	}
	public void setProd(Producto prod) {
		this.prod = prod;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public boolean ifWasValued() {
		return wasValued;
	}
	public void setWasValued(boolean wasValued) {
		this.wasValued = wasValued;
	}
	
	
}
