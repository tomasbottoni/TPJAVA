package entities;

public class Zona {
	private int cod_zona;
	private String descripcion;
	private Flete flete;
	
	public int getCod_zona() {
		return cod_zona;
	}
	public void setCod_zona(int cod_zona) {
		this.cod_zona = cod_zona;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Flete getFlete() {
		return flete;
	}
	public void setFlete(Flete flete) {
		this.flete = flete;
	}
	
	
}
