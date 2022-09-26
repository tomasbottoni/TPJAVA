package entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Venta {
	private int id_venta;
	private int id_usuario;
	private int cod_postal;
	private int id_flete;
	private LocalDate fechaVenta;
	private LocalTime horaVenta;
	private String estado;
	
	public int getId_venta() {
		return id_venta;
	}
	public void setId_venta(int id_venta) {
		this.id_venta = id_venta;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public int getCod_postal() {
		return cod_postal;
	}
	public void setCod_postal(int cod_postal) {
		this.cod_postal = cod_postal;
	}
	public int getId_flete() {
		return id_flete;
	}
	public void setId_flete(int id_flete) {
		this.id_flete = id_flete;
	}
	public LocalDate getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public LocalTime getHoraVenta() {
		return horaVenta;
	}
	public void setHoraVenta(LocalTime horaVenta) {
		this.horaVenta = horaVenta;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
