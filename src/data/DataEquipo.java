package data;


import java.sql.*;
import java.util.LinkedList;
import entities.*;

public class DataEquipo {
	
	public LinkedList<Equipo> getAll(){	
	Statement stm = null;
	ResultSet rs = null;
	LinkedList<Equipo> equipos= new LinkedList<>();	
	try {
		stm = DbConnector.getInstancia().getConn().createStatement();
		rs = stm.executeQuery("Select eq.id,eq.razonSocial,eq.localidad,eq.puntaje,eq.difGoles from equipo eq where eq.fecha_baja is null");	
		while (rs.next()) {
			Equipo e=new Equipo();
			e.setIdEquipo(rs.getInt("eq.id"));
			e.setNombre(rs.getString("eq.razonSocial"));
			e.setLocalidad(rs.getString("eq.localidad"));
			e.setPuntaje(rs.getInt("eq.puntaje"));
			e.setDifGoles(rs.getInt("eq.difGoles"));			
			equipos.add(e);		
		}	
	} catch (SQLException e) {
		e.printStackTrace();		
	} finally {
		try {
			if (rs!= null) {rs.close();}		
			if (stm != null) {stm.close();}		
			DbConnector.getInstancia().releaseConn();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	return equipos;
}	
	public Equipo getOne(Equipo e) {
	PreparedStatement ps=null;
	Equipo equipo = new Equipo();
    try {
		ps =DbConnector.getInstancia().getConn().prepareStatement("Select eq.id,eq.razonSocial,eq.localidad,eq.puntaje,eq.difGoles "
				+ "from equipo eq where id=? and eq.fecha_baja is null");
		ps.setInt(1, e.getIdEquipo());
		ResultSet rs=ps.executeQuery();  
        while (rs.next()) {
        	equipo.setIdEquipo(rs.getInt("eq.id"));
        	equipo.setNombre(rs.getString("eq.razonSocial"));
        	equipo.setLocalidad(rs.getString("eq.localidad"));
        	equipo.setPuntaje(rs.getInt("eq.puntaje"));
        	equipo.setDifGoles(rs.getInt("eq.difGoles"));
        }	
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    finally {
		try {
			if (ps!= null) {ps.close();}			
			DbConnector.getInstancia().releaseConn();
		} catch (Exception e2) {
			e2.printStackTrace();
	}
		}
return equipo;
	}
	public void add (Equipo e) { 
		PreparedStatement ps=null;
        try {
    		ps=DbConnector.getInstancia().getConn().prepareStatement("insert into equipo(id,razonSocial,localidad,puntaje,difGoles) values (?,?,?,?,?)");
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
    			if (ps!= null) {ps.close();}			
    			DbConnector.getInstancia().releaseConn();
    		} catch (Exception e2) {
    			e2.printStackTrace();
    		}
    		}
	}	
	public void delete(Equipo e) { //  baja logica/soft delete
		String updateEnetrenadorDependency=/*"update entrenador ent "
				+ "inner join equipo eq on eq.id=ent.idEquipo"
				+ " set ent.idEquipo=null"
				+ "where ent.idEquipo=? and eq.fecha_baja is not null"*/"update entrenador ent inner join equipo eq on eq.id=ent.idEquipo set ent.idEquipo=null where ent.idEquipo=? and eq.fecha_baja is not null";
		
		String updateJugadoresDependency=/*"update jugador j "
				+ "inner join equipo eq on j.idEquipo=eq.id "
				+ "set j.idEquipo=null "
				+ "where j.idEquipo=? and eq.fecha_baja is not null"*/"update jugador j inner join equipo eq on j.idEquipo=eq.id set j.idEquipo=null where j.idEquipo=? and eq.fecha_baja is not null";
		
		String deleteEquipo=/*"update equipo eq"
    			+ "set eq.fecha_baja=CURRENT_DATE"
    			+ " where eq.id not in"
    			+ "	(select distinct eq.id"
    			+ "	from partido pa"
    			+ "	inner join equipo eq on eq.id= pa.idEquipo1 or eq.id= pa.idEquipo1"
    			+ "	WHERE PA.fecha>= CURRENT_DATE and eq.id=?)"
    			+ "and eq.id=?"*/"update equipo eq set eq.fecha_baja=CURRENT_DATE where eq.id not in (select distinct eq.id from partido pa inner join equipo eq on eq.id= pa.idEquipo1 or eq.id= pa.idEquipo2 WHERE PA.fecha>= CURRENT_DATE and eq.id=?) and eq.id=?";
	    try(PreparedStatement psUpdateEntrenador = DbConnector.getInstancia().getConn().prepareStatement(updateEnetrenadorDependency);
	        PreparedStatement psUpdateJugador = DbConnector.getInstancia().getConn().prepareStatement(updateJugadoresDependency);
	    	PreparedStatement psDeleteEquipo = DbConnector.getInstancia().getConn().prepareStatement(deleteEquipo))
	    {
	    	DbConnector.getInstancia().getConn().setAutoCommit(false);
	    	psDeleteEquipo.setInt(1, e.getIdEquipo());   
	    	psDeleteEquipo.setInt(2, e.getIdEquipo()); 
			if(psDeleteEquipo.executeUpdate()>0) // si se modifica equipo en la base de datos
			{
				psUpdateJugador.setInt(1, e.getIdEquipo());
				psUpdateJugador.executeUpdate();
				psUpdateEntrenador.setInt(1, e.getIdEquipo());
				psUpdateEntrenador.executeUpdate();
				DbConnector.getInstancia().getConn().commit();
			}	
			else
				throw new SQLException();
	    }     
	    catch (SQLException ex) 
	    {
	            try {
	              System.err.print("Transaction rolled back");
	              DbConnector.getInstancia().getConn().rollback();
	            } catch (SQLException excep) {
	              ex.printStackTrace();
	            }
	    }    
	    finally {
    		try {		
    			 DbConnector.getInstancia().releaseConn();
    		}
    		 catch (Exception e2) {
    			e2.printStackTrace();
    		}
    		}
	}
	public void update (Equipo e) {	   
	PreparedStatement ps=null;
    try {
		ps=DbConnector.getInstancia().getConn().prepareStatement("update equipo eq "
				+ "set eq.razonSocial=?, eq.localidad=?, eq.puntaje=?, eq.difGoles=?"
				+ " where eq.id=? and eq.fecha_baja is null");
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
			if (ps!= null) {ps.close();}			
			DbConnector.getInstancia().releaseConn();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		}
}
}