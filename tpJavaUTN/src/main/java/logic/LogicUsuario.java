package logic;

import java.util.LinkedList;

import data.DataUsuario;
import entities.Usuario;

public class LogicUsuario {
	
private DataUsuario du;
	
	public LogicUsuario() {
		du=new DataUsuario();
	}
	
	public Usuario validate(Usuario u) {
		/* para hacer más seguro el manejo de passwords este sería un lugar 
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asimétrico como sha256 y utilizar el hash en lugar de la password en plano 
		 */
		return du.getByUser(u);
	}

	public LinkedList<Usuario> getAll(){
		return du.getAll();
	}
	
	public void add(Usuario u) {
		du.add(u);
	}

	public void update(Usuario u) {
		du.update(u);
		
	}

	public void delete(Usuario u) {
		du.delete(u);
		
	}
}
