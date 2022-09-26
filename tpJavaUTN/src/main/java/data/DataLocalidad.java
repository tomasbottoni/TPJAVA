package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Flete;
import entities.Localidad;
import entities.Zona;

public class DataLocalidad {
	
	public LinkedList<Localidad> getAll(){
		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Localidad> loc= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select cod_postal,descripcion,cod_zona,dias_de_tardanza_envio from localidad");
			
			if(rs!=null) {
				while(rs.next()) {
					Localidad l= new Localidad();
					l.setCod_postal(rs.getInt("cod_postal"));
					l.setDescripcion(rs.getString("descripcion"));
					l.setZona(new Zona());
					l.getZona().setCod_zona(rs.getInt("cod_zona"));
					l.setDias_de_tardanza(rs.getInt("dias_de_tardanza_envio"));
					loc.add(l);
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
		return loc;
	}
	
	public Localidad getOne(int cod_postal) {
		
		Localidad l = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT l.cod_postal,l.descripcion,l.cod_zona,l.dias_de_tardanza_envio,f.id_flete "
							+ "FROM localidad l "
							+ "INNER JOIN zona z on z.cod_zona = l.cod_zona "
							+ "INNER JOIN flete f on f.id_flete = z.id_flete "
							+ "WHERE cod_postal=?;"
					);
			stmt.setInt(1, cod_postal);
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()) {
				l = new Localidad();
				l.setCod_postal(rs.getInt("cod_postal"));
				l.setDescripcion(rs.getString("descripcion"));
				l.setZona(new Zona());
				l.getZona().setCod_zona(rs.getInt("cod_zona"));
				l.setDias_de_tardanza(rs.getInt("dias_de_tardanza_envio"));
				l.getZona().setFlete(new Flete());
				l.getZona().getFlete().setId_flete(rs.getInt("id_flete"));
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
		
		return l;
	}
	
	public void add(Localidad l) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into localidad(cod_postal, descripcion, cod_zona, dias_de_tardanza_envio) values(?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, l.getCod_postal());
			stmt.setString(2, l.getDescripcion());
			stmt.setInt(3, l.getZona().getCod_zona());
			stmt.setInt(4, l.getDias_de_tardanza());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                l.setCod_postal(keyResultSet.getInt(1));
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
	
	public void update(Localidad l) {
		PreparedStatement stmt=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("UPDATE localidad " + 
					"SET cod_postal = ?, descripcion = ?, cod_zona = ?, dias_de_tardanza_envio = ? " 
					+ "WHERE cod_postal = ?"
					);
			
			stmt.setInt(1, l.getCod_postal());
			stmt.setString(2, l.getDescripcion());
			stmt.setInt(3, l.getZona().getCod_zona());		
			stmt.setInt(4, l.getDias_de_tardanza());
			stmt.setInt(5, l.getCod_postal());
			
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
	
	public void delete(Localidad l) throws SQLException {
		PreparedStatement stmt=null;
		
		stmt=DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM localidad where cod_postal= ?");

		stmt.setInt(1, l.getCod_postal());
			
		stmt.executeUpdate();

		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
	}
	
	public Localidad getByDescripcion(String descripcion){
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Localidad lo= new Localidad();
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select cod_postal,descripcion from localidad "
					+"where descripcion=?;"
					);
			stmt.setString(1, descripcion);
			rs=stmt.executeQuery();
			
			if(rs!=null) {
				while(rs.next()) {
					lo.setCod_postal(rs.getInt("cod_postal"));
					lo.setDescripcion(rs.getString("descripcion"));

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
		return lo;
	}
}
