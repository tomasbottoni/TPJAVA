package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.LinkedList;

import entities.Venta;
import entities.Venta_Producto;
import entities.Producto;
import logic.LogicProducto;

public class DataVenta {
	
	public void confirmarVenta(Venta v, ArrayList<int[]> listaCarrito) {
		
		PreparedStatement insertarVenta = null;
		PreparedStatement descontarStock = null;
		PreparedStatement insertarVentaProducto = null;
		ResultSet keyResultSet=null;
		Producto p;
		LogicProducto lp = new LogicProducto();
		
		try {
			DbConnector.getInstancia().getConn().setAutoCommit(false);
			
			insertarVenta = DbConnector.getInstancia().getConn().prepareStatement(
					"INSERT INTO venta (fechaVenta, horaVenta, id_usuario, cod_postal, id_flete, estado) " + 
					"VALUES (?, ?, ?, ?, ?, ?); ",
					PreparedStatement.RETURN_GENERATED_KEYS 
					);
	
			insertarVenta.setObject(1, v.getFechaVenta());
			insertarVenta.setObject(2, v.getHoraVenta());
			insertarVenta.setInt(3, v.getId_usuario());
			insertarVenta.setInt(4, v.getCod_postal());
			insertarVenta.setInt(5, v.getId_flete());
			insertarVenta.setString(6, v.getEstado());
			insertarVenta.executeUpdate();
		
			keyResultSet=insertarVenta.getGeneratedKeys();
	        if(keyResultSet!=null && keyResultSet.next()){
	        	v.setId_venta(keyResultSet.getInt(1));
	        }
		
		for(int i=0; i<listaCarrito.size(); i++) {
			
			if(listaCarrito.get(i)[1] == 1) {
				
				p = lp.getOne(listaCarrito.get(i)[0]);
				
				insertarVentaProducto = DbConnector.getInstancia().getConn().prepareStatement(
						"INSERT INTO venta_producto (id_producto, id_venta, cantidad) " + 
						"VALUES (?, ?, ?); " 
						);

				insertarVentaProducto.setInt(1, p.getId());
				insertarVentaProducto.setInt(2, v.getId_venta());
				insertarVentaProducto.setInt(3, 1);
				insertarVentaProducto.executeUpdate();
				
				descontarStock = DbConnector.getInstancia().getConn().prepareStatement("UPDATE producto " + 
						"SET stock = ? WHERE id_producto = ?" 
						);

				descontarStock.setInt(1, p.getStock()-1);
				descontarStock.setInt(2, p.getId());
				descontarStock.executeUpdate();
				
			}else {
				
				p = lp.getOne(listaCarrito.get(i)[0]);
				
				insertarVentaProducto = DbConnector.getInstancia().getConn().prepareStatement(
						"INSERT INTO venta_producto (id_producto, id_venta, cantidad) " + 
						"VALUES (?, ?, ?); " 
						);

				insertarVentaProducto.setInt(1, p.getId());
				insertarVentaProducto.setInt(2, v.getId_venta());
				int cant = listaCarrito.get(i)[1];
				insertarVentaProducto.setInt(3, cant);
				insertarVentaProducto.executeUpdate();
				
				descontarStock = DbConnector.getInstancia().getConn().prepareStatement("UPDATE producto " + 
						"SET stock = ? WHERE id_producto = ?" 
						);

				descontarStock.setInt(1, p.getStock()-cant);
				descontarStock.setInt(2, p.getId());
				descontarStock.executeUpdate();
	
			}
			
			
		}
			
		DbConnector.getInstancia().getConn().commit();
		
		} catch (SQLException e) {
			try {
				DbConnector.getInstancia().getConn().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				if(insertarVenta!=null) {insertarVenta.close();}
				if(insertarVentaProducto !=null) {insertarVentaProducto.close();}
				if(descontarStock !=null) {descontarStock.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		
	public LinkedList<Venta_Producto> getAllVentaProducto(int id_usuario){

		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Venta_Producto> ventasProductos = new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT v.id_venta, v.fechaVenta, v.estado, v.cod_postal, vp.wasValued, p.id_producto, p.descripcion, p.precio "
					+ "FROM venta_producto vp "
					+ "inner join venta v on v.id_venta = vp.id_venta "
					+ "inner join producto p on p.id_producto = vp.id_producto "
					+ "where v.id_usuario=?;");
			
			stmt.setInt(1, id_usuario);
			rs=stmt.executeQuery();
			
			if(rs!=null) {
				while(rs.next()) {
					Venta_Producto vp = new Venta_Producto();
					vp.setVenta(new Venta());
					vp.getVenta().setId_venta(rs.getInt("id_venta"));
					vp.getVenta().setFechaVenta(rs.getDate("fechaVenta").toLocalDate());
					vp.getVenta().setEstado(rs.getString("estado"));
					vp.getVenta().setCod_postal(rs.getInt("cod_postal"));
					vp.setWasValued(rs.getBoolean("wasValued"));
					vp.setProd(new Producto());
					vp.getProd().setId(rs.getInt("id_producto"));
					vp.getProd().setDescripcion(rs.getString("descripcion"));
					vp.getProd().setPrecio(rs.getDouble("precio"));
					ventasProductos.add(vp);
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
		return ventasProductos;
	}
	
	public void UpdateWasValued(int id_venta, int id_producto, boolean condicion) {
		PreparedStatement stmt=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("UPDATE venta_producto " + 
					"SET wasValued=? " 
					+ "WHERE id_venta = ? AND id_producto=?"
					);
			
			stmt.setBoolean(1, condicion);
			stmt.setInt(2, id_venta);
			stmt.setInt(3, id_producto);
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
	
	public void UpdateEstado(int id, String estado) {
		PreparedStatement stmt=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("UPDATE venta " + 
					"SET estado=? " 
					+ "WHERE id_venta = ?"
					);
			
			stmt.setString(1, estado);
			stmt.setInt(2, id);
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


