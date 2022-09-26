package entities;

public class Localidad {
	private int cod_postal;
	private String descripcion;
	private Zona zona;
	private int dias_de_tardanza;
	
	public int getCod_postal() {
		return cod_postal;
	}
	public void setCod_postal(int cod_postal) {
		this.cod_postal = cod_postal;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	public int getDias_de_tardanza() {
		return dias_de_tardanza;
	}
	public void setDias_de_tardanza(int dias_de_tardanza) {
		this.dias_de_tardanza = dias_de_tardanza;
	}
	
	
}
