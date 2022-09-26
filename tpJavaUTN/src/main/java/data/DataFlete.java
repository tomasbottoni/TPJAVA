package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;


import entities.Flete;

public class DataFlete {
	
	public LinkedList<Flete> getAll(){	
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Flete> fletes= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select id_flete,nombre from flete");
			
			if(rs!=null) {
				while(rs.next()) {
					Flete f= new Flete();
					f.setId_flete(rs.getInt("id_flete"));
					f.setNombre(rs.getString("nombre"));
					
					fletes.add(f);
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
		return fletes;
	}
	
	public Flete getByNombre(String nombre){
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Flete flete= new Flete();
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select id_flete,nombre from flete "
					+"where nombre=?;"
					);
			stmt.setString(1, nombre);
			rs=stmt.executeQuery();
			
			if(rs!=null) {
				while(rs.next()) {
					flete.setId_flete(rs.getInt("id_flete"));
					flete.setNombre(rs.getString("nombre"));

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
		return flete;
	}
	
	public void add(Flete f) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into flete(nombre) values(?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, f.getNombre());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                f.setId_flete(keyResultSet.getInt(1));
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

	public void update(Flete f) {
		PreparedStatement stmt=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("UPDATE flete " + 
					"SET nombre=? " 
					+ "WHERE id_flete = ?"
					);
			
			stmt.setString(1, f.getNombre());
			stmt.setInt(2, f.getId_flete());
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

	public void delete(Flete f) throws SQLException {
		PreparedStatement stmt=null;
		
		stmt=DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM flete where id_flete= ?");

		stmt.setInt(1, f.getId_flete());
			
		stmt.executeUpdate();
	
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
	}
	
	/*public void delete(Flete f) {
		PreparedStatement stmt=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM flete where id_flete= ?");

			stmt.setInt(1, f.getId_flete());
			
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
		
	}*/

	public Flete getOne(int id_flete) {
		
		Flete f = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT f.id_flete,f.nombre "
							+ "FROM flete f "
							+ "where f.id_flete=?;"
					);
			stmt.setInt(1, id_flete);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				f= new Flete();
				f.setId_flete(rs.getInt("id_flete"));
				f.setNombre(rs.getString("nombre"));
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
		
		return f;
	}
}
