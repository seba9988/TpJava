package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;

import entities.Arbitro;


public class DataArbitro {

	public LinkedList<Arbitro> getAll(){	
		Conexion conexion = new Conexion();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		LinkedList<Arbitro> arbitros= new LinkedList<>();	
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("Select * from arbitro");		
			while (rs.next()) {
				Arbitro a=new Arbitro();
				a.setDni(rs.getString("dniArbitro"));
				a.setNombre(rs.getString("nombre"));
				a.setApellido(rs.getString("apellido"));
				a.setFecha_nacimiento(rs.getObject("fechaNac",LocalDate.class));		
				arbitros.add(a);		
			}	
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
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
		return arbitros;
	}
	public Arbitro getOne(Arbitro a) {
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement ps=null;
		Arbitro arbitro = new Arbitro();
	    try {
	    	cn = conexion.conectar();
			ps =cn.prepareStatement("Select * from arbitro where dniArbitro=?");
			ps.setString(1, a.getDni());
			ResultSet rs=ps.executeQuery();  
	        while (rs.next()) {			
	        	arbitro.setDni("dniArbitro");
	        	arbitro.setNombre(rs.getString("nombre"));
	        	arbitro.setApellido(rs.getString("apellido"));
	        	arbitro.setFecha_nacimiento(rs.getObject("fechaNac",LocalDate.class));
	        }			      
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
	return arbitro;
		}
	public void add (Arbitro a) {  
			Conexion conexion = new Conexion();
			Connection cn = null;
    		PreparedStatement ps=null;
	        try {
	        	cn = conexion.conectar();
	    		ps=cn.prepareStatement("insert into arbitro(dniArbitro,nombre,apellido,fechaNac) values (?,?,?,?)");
	    		ps.setString(1, a.getDni());
	    		ps.setString(2, a.getNombre());
				ps.setString(3,a.getApellido());
				ps.setObject(4,a.getFecha_nacimiento());    
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
	public void delete(Arbitro a) {
			Conexion conexion = new Conexion();
			Connection cn = null;
			PreparedStatement ps=null;
		    try {
		    	cn = conexion.conectar();
		    	ps = cn.prepareStatement("delete from arbitro where dniArbitro=?");
				ps.setString(1, a.getDni());
				ps.executeUpdate();  				        
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }	
	}
	public void update (Arbitro a) {		   
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement ps=null;
	    try {
	    	cn = conexion.conectar();
			ps=cn.prepareStatement("update arbitro set nombre=?, apellido=?, fechaNac=? where dniArbitro=?");
			ps.setString(1, a.getNombre());
			ps.setString(2,a.getApellido());
			ps.setObject(3,a.getFecha_nacimiento());
			ps.setString(4,a.getDni());
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