package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Flete;
import entities.Zona;

public class DataZona {
	
	public LinkedList<Zona> getAll(){
		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Zona> zonas= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("SELECT z.cod_zona, z.descripcion, z.id_flete, f.nombre "
					+ "FROM zona z inner join flete f on f.id_flete = z.id_flete;");
			
			if(rs!=null) {
				while(rs.next()) {
					Zona z= new Zona();
					Flete flete = new Flete();
					z.setCod_zona(rs.getInt("z.cod_zona"));
					z.setDescripcion(rs.getString("z.descripcion"));
					flete.setId_flete(rs.getInt("z.id_flete"));
					flete.setNombre(rs.getString("f.nombre"));
					z.setFlete(flete);
					
					zonas.add(z);
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
		return zonas;
	}
	
	public void add(Zona z) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into zona(descripcion, id_flete) values(?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, z.getDescripcion());
			stmt.setInt(2, z.getFlete().getId_flete());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                z.setCod_zona(keyResultSet.getInt(1));
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

	public void update(Zona z) {
		PreparedStatement stmt=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("UPDATE zona " + 
					"SET descripcion = ?, id_flete = ? " 
					+ "WHERE cod_zona = ?"
					);
			
			stmt.setString(1, z.getDescripcion());
			stmt.setInt(2, z.getFlete().getId_flete());
			stmt.setInt(3, z.getCod_zona());
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

	public void delete(Zona z) throws SQLException {
		PreparedStatement stmt=null;
		
		stmt=DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM zona where cod_zona= ?");

		stmt.setInt(1, z.getCod_zona());
			
		stmt.executeUpdate();
		
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
	}

public Zona getOne(int id) {
		
		Zona z = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT z.cod_zona,z.descripcion,f.id_flete,f.nombre "
							+ "FROM zona z "
							+ "inner join flete f on f.id_flete = z.id_flete "
							+ "where z.cod_zona=?;"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				z= new Zona();
				z.setCod_zona(rs.getInt("z.cod_zona"));
				z.setDescripcion(rs.getString("z.descripcion"));
				z.setFlete(new Flete());
				z.getFlete().setId_flete(rs.getInt("f.id_flete"));
				z.getFlete().setNombre(rs.getString("f.nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return z;
	}

public Zona getByDescripcion(String descripcion){
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Zona zona= new Zona();
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select cod_zona,descripcion from zona "
					+"where descripcion=?;"
					);
			stmt.setString(1, descripcion);
			rs=stmt.executeQuery();
			
			if(rs!=null) {
				while(rs.next()) {
					zona.setCod_zona(rs.getInt("cod_zona"));
					zona.setDescripcion(rs.getString("descripcion"));

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
		return zona;
	}
}
