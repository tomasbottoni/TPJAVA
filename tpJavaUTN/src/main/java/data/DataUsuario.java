package data;

import entities.*;
import java.sql.*;
import java.util.LinkedList;


public class DataUsuario {
	
	public LinkedList<Usuario> getAll(){
		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Usuario> users= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select id_usuario,nombre,apellido,nombreUsuario,email,id_tipoUsuario,clave from usuario");
			
			if(rs!=null) {
				while(rs.next()) {
					Usuario u= new Usuario();
					u.setId_usuario(rs.getInt("id_usuario"));
					u.setNombre(rs.getString("nombre"));
					u.setApellido(rs.getString("apellido"));
					u.setNombreUsuario(rs.getString("nombreUsuario"));
					u.setEmail(rs.getString("email"));
					u.setTipoUsuario(new TipoUsuario());
					u.getTipoUsuario().setId_TipoUsuario(rs.getInt("id_tipoUsuario"));
					u.setClave(rs.getString("clave"));
					
					users.add(u);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}
	
	public Usuario getByUser(Usuario us) {
		
		Usuario u =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select id_usuario,nombre,apellido,nombreUsuario,email,id_tipoUsuario,clave from usuario where nombreUsuario=? and clave=?"
					);
			stmt.setString(1, us.getNombreUsuario());
			stmt.setString(2, us.getClave());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				u= new Usuario();
				u.setId_usuario(rs.getInt("id_usuario"));
				u.setNombre(rs.getString("nombre"));
				u.setApellido(rs.getString("apellido"));
				u.setNombreUsuario(rs.getString("nombreUsuario"));
				u.setEmail(rs.getString("email"));
				u.setTipoUsuario(new TipoUsuario());
				u.getTipoUsuario().setId_TipoUsuario(rs.getInt("id_tipoUsuario"));
				u.setClave(rs.getString("clave"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			u = null;
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return u;
	}
	
	public void add(Usuario u) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into usuario(nombre, apellido, nombreUsuario, email, id_tipoUsuario, clave) values(?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, u.getNombre());
			stmt.setString(2, u.getApellido());
			stmt.setString(3, u.getNombreUsuario());
			stmt.setString(4, u.getEmail());
			stmt.setInt(5, u.getTipoUsuario().getId_TipoUsuario());
			stmt.setString(6, u.getClave());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                u.setId_usuario(keyResultSet.getInt(1));
            }

			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
    }

	public void update(Usuario u) {
		PreparedStatement stmt=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("UPDATE usuario " + 
					"SET nombre = ?, apellido = ?, nombreUsuario = ?, email = ?, id_tipoUsuario= ?, clave= ? " 
					+ "WHERE id_usuario = ?"
					);
			
			stmt.setString(1, u.getNombre());
			stmt.setString(2, u.getApellido());
			stmt.setString(3, u.getNombreUsuario());
			stmt.setString(4, u.getEmail());
			stmt.setInt(5, u.getTipoUsuario().getId_TipoUsuario());
			stmt.setString(6, u.getClave());
			stmt.setInt(7, u.getId_usuario());
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void delete(Usuario u) {
		PreparedStatement stmt=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM usuario where id_usuario = ?");

			stmt.setInt(1, u.getId_usuario());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
