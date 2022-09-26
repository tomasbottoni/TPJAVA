package logic;
import java.sql.SQLException;
import java.util.LinkedList;

import data.DataFlete;
import entities.Flete;

public class LogicFlete {
private DataFlete df;
	
	public LogicFlete() {
		df=new DataFlete();
	}

	public LinkedList<Flete> getAll(){
		return df.getAll();
	}
	
	public void add(Flete f) {
		df.add(f);
	}

	public void update(Flete f) {
		df.update(f);
		
	}

	public void delete(Flete f) throws SQLException {
		df.delete(f);
		
	}
	
	public Flete getByNombre(String nombre) {
		return df.getByNombre(nombre);
	}

	public Flete getOne(int id_flete) {
		return df.getOne(id_flete);
	}
}
