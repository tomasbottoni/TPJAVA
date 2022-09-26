package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Categoria;

public class DataCategoria {
	
	public LinkedList<Categoria> getAll(){	
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Categoria> categorias= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select id_categoria,denominacion from categoria");
			
			if(rs!=null) {
				while(rs.next()) {
					Categoria c= new Categoria();
					c.setId_categoria(rs.getInt("id_categoria"));
					c.setDenominacion(rs.getString("denominacion"));
					
					categorias.add(c);
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
		return categorias;
	}
	
	public Categoria getByDenominacion(String denominacion){
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Categoria categoria= new Categoria();
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select id_categoria,denominacion from categoria "
					+"where denominacion=?;"
					);
			stmt.setString(1, denominacion);
			rs=stmt.executeQuery();
			
			if(rs!=null) {
				while(rs.next()) {
					categoria.setId_categoria(rs.getInt("id_categoria"));
					categoria.setDenominacion(rs.getString("denominacion"));

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
		return categoria;
	}
	
	public void add(Categoria c) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into categoria(denominacion) values(?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, c.getDenominacion());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                c.setId_categoria(keyResultSet.getInt(1));
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

	public void update(Categoria c) {
		PreparedStatement stmt=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("UPDATE categoria " + 
					"SET denominacion=? " 
					+ "WHERE id_categoria = ?"
					);
			
			stmt.setString(1, c.getDenominacion());
			stmt.setInt(2, c.getId_categoria());
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

	public void delete(Categoria c) throws SQLException {
		
		PreparedStatement stmt=null;
		stmt=DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM categoria where id_categoria= ?");

		stmt.setInt(1, c.getId_categoria());
			
		stmt.executeUpdate();
		
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
	}

	public Categoria getOne(int id_categoria) {
		
		Categoria c = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT c.id_categoria,c.denominacion "
							+ "FROM categoria c "
							+ "where c.id_categoria=?;"
					);
			stmt.setInt(1, id_categoria);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				c= new Categoria();
				c.setId_categoria(rs.getInt("id_categoria"));
				c.setDenominacion(rs.getString("denominacion"));
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
		
		return c;
	}
}
