package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataZona;
import entities.Zona;

public class LogicZona {
private DataZona dz;
	
	public LogicZona() {
		dz=new DataZona();
	}

	public LinkedList<Zona> getAll(){
		return dz.getAll();
	}
	
	public void add(Zona z) {
		dz.add(z);
	}

	public void update(Zona z) {
		dz.update(z);
		
	}

	public void delete(Zona z) throws SQLException {
		dz.delete(z);
		
	}

	public Zona getOne(int id) { 

		return dz.getOne(id);
	}

	public Zona getByDescripcion(String descripcion) {
		return dz.getByDescripcion(descripcion);
	}
}
