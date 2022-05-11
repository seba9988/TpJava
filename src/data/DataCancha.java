package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Cancha;

public class DataCancha {
		public LinkedList<Cancha> getAll(){	
			Conexion conexion = new Conexion();
			Connection cn = null;
			Statement stm = null;
			ResultSet rs = null;
			LinkedList<Cancha> canchas= new LinkedList<>();	
			try {
				cn = conexion.conectar();
				stm = cn.createStatement();
				rs = stm.executeQuery("Select * from cancha");			
				while (rs.next()) {
					Cancha c=new Cancha();
					c.setNroCancha(rs.getInt("numCancha"));
					c.setNombre(rs.getString("nombre"));				
					canchas.add(c);			
				}				
			}
			catch (SQLException e) {
				e.printStackTrace();
			} 
			finally {
				try {
					if (rs!= null) {
						rs.close();
					}				
					if (stm != null) {
						stm.close();
					}					
					if (cn != null) {
						cn.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return canchas;
		}
		public Cancha getOne(Cancha c) {
			Conexion conexion = new Conexion();
			Connection cn = null;
			PreparedStatement ps=null;
			Cancha cancha = new Cancha();
		    try {
		    	cn = conexion.conectar();
				ps =cn.prepareStatement("Select * from cancha where numCancha=?");
				ps.setInt(1, c.getNroCancha());
				ResultSet rs=ps.executeQuery();  
		        while (rs.next()) {				
					cancha.setNroCancha(rs.getInt("numCancha"));
					cancha.setNombre(rs.getString("nombre"));
		        }				
		        if(ps!=null)ps.close();
		        cn.close();	        
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		    finally {
				try {
					if (ps!= null) {
						ps.close();
					}				
					if (cn != null) {
						cn.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		return c;
			}
		public void add (Cancha c) {  
				Conexion conexion = new Conexion();
				Connection cn = null;
	    		PreparedStatement ps=null;
		        try {
		        	cn = conexion.conectar();
		    		ps=cn.prepareStatement("insert into cancha(numCancha,nombre) values (?,?)");
		    		ps.setInt(1, c.getNroCancha());
		    		ps.setString(2, c.getNombre());     
		            ps.executeUpdate();          
		        } catch (SQLException ex) {
		        	ex.printStackTrace();
		        }
				finally {
					try {
						if (ps!= null) {
							ps.close();
						}					
						if (cn != null) {
							cn.close();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}		
		public void delete(Cancha c) {
				Conexion conexion = new Conexion();
				Connection cn = null;
		    	PreparedStatement ps=null;
			    try {
			    	cn = conexion.conectar();
			    	ps = cn.prepareStatement("delete from cancha where numCancha=?");
					ps.setInt(1, c.getNroCancha());
					ps.executeUpdate();  				       
			    } catch (SQLException ex) {
			        ex.printStackTrace();
			    }	
			    finally {
					try {
						if (ps!= null) {
							ps.close();
						}				
						if (cn != null) {
							cn.close();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
		}
		public void update (Cancha c) {		   
			Conexion conexion = new Conexion();
			Connection cn = null;
			PreparedStatement ps=null;
		    try {
		    	cn = conexion.conectar();
				ps=cn.prepareStatement("update cancha set nombre=? where numCancha=?");
				ps.setString(1, c.getNombre());
				ps.setInt(2,c.getNroCancha());
				ps.executeUpdate();   
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		    finally {
				try {
					if (ps!= null) {
						ps.close();
					}				
					if (cn != null) {
						cn.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

		}