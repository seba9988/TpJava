package data;


import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

import entities.*;

public class DataEquipo {
	
	public LinkedList<Equipo> getAll(){	
	Conexion conexion = new Conexion();
	Connection cn = null;
	Statement stm = null;
	ResultSet rs = null;
	LinkedList<Equipo> equipos= new LinkedList<>();	
	try {
		cn = conexion.conectar();
		stm = cn.createStatement();
		rs = stm.executeQuery("Select eq.id,eq.razonSocial,eq.localidad,eq.puntaje,eq.difGoles from equipo eq where eq.fecha_baja is null");	
		while (rs.next()) {
			Equipo e=new Equipo();
			e.setIdEquipo(rs.getInt("id"));
			e.setNombre(rs.getString("razonSocial"));
			e.setLocalidad(rs.getString("localidad"));
			e.setPuntaje(rs.getInt("puntaje"));
			e.setDifGoles(rs.getInt("difGoles"));			
			equipos.add(e);			
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
	return equipos;
}	
	public Equipo getOne(Equipo e) {
	Conexion conexion = new Conexion();
	Connection cn = null;
	PreparedStatement ps=null;
	Equipo equipo = new Equipo();
    try {
    	cn = conexion.conectar();
		ps =cn.prepareStatement("Select eq.id,eq.razonSocial,eq.localidad,eq.puntaje,eq.difGoles from equipo eq where id=? and eq.fecha_baja is null");
		ps.setInt(1, e.getIdEquipo());
		ResultSet rs=ps.executeQuery();  
        while (rs.next()) {
        	equipo.setIdEquipo(rs.getInt("id"));
        	equipo.setNombre(rs.getString("razonSocial"));
        	equipo.setLocalidad(rs.getString("localidad"));
        	equipo.setPuntaje(rs.getInt("puntaje"));
        	equipo.setDifGoles(rs.getInt("difGoles"));
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
return equipo;
	}
	public void add (Equipo e) { 
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement ps=null;
        try {
        	cn = conexion.conectar();
    		ps=cn.prepareStatement("insert into equipo(id,razonSocial,localidad,puntaje,difGoles) values (?,?,?,?,?)");
    		ps.setInt(1, e.getIdEquipo());
    		ps.setString(2, e.getNombre());
			ps.setString(3,e.getLocalidad());
			ps.setInt(4,e.getPuntaje());
			ps.setInt(5,e.getDifGoles());    
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
	public void delete(Equipo e) { //  baja logica/soft delete
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement ps=null;
	    try {
	    	cn = conexion.conectar();
	    	LocalDate fechaHoy= LocalDate.now();
	    	ps = cn.prepareStatement("update equipo eq set eq.fecha_baja=? where eq.id=? and eq.fecha_baja is null"); 
	    	ps.setObject(1,fechaHoy); // fecha de hoy en formato  yyyyMMdd
			ps.setInt(2, e.getIdEquipo()); 
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
	public void update (Equipo e) {	   
	Conexion conexion = new Conexion();
	Connection cn = null;
	PreparedStatement ps=null;
    try {
    	cn = conexion.conectar();
		ps=cn.prepareStatement("update equipo eq set eq.razonSocial=?, eq.localidad=?, eq.puntaje=?, eq.difGoles=? where eq.id=? and eq.fecha_baja is null");
		ps.setString(1, e.getNombre());
		ps.setString(2,e.getLocalidad());
		ps.setInt(3,e.getPuntaje());
		ps.setInt(4,e.getDifGoles());
		ps.setInt(5,e.getIdEquipo());
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