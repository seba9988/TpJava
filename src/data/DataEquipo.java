package data;


import java.sql.*;
import java.util.LinkedList;
import entities.*;

public class DataEquipo {
	
	public LinkedList<Equipo> getAll(){	
	String getAllStatement="Select eq.id,eq.razonSocial,eq.localidad,eq.puntaje,eq.difGoles from equipo eq where eq.fecha_baja is null";
	LinkedList<Equipo> equipos= new LinkedList<>();	
	try(Statement stm = DbConnector.getInstancia().getConn().createStatement();
		ResultSet rs = stm.executeQuery(getAllStatement);) {	
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
			DbConnector.getInstancia().releaseConn();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	return equipos;
}	
	public Equipo getOne(Equipo e) {
	String getOneStatement="Select eq.id,eq.razonSocial,eq.localidad,eq.puntaje,eq.difGoles "
			+ "from equipo eq where id=? and eq.fecha_baja is null";
	Equipo equipo = new Equipo();
    try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(getOneStatement);) {
		ps.setInt(1, e.getIdEquipo());
		try(ResultSet rs=ps.executeQuery(); ){
			 while (rs.next()) {
		        	equipo.setIdEquipo(rs.getInt("eq.id"));
		        	equipo.setNombre(rs.getString("eq.razonSocial"));
		        	equipo.setLocalidad(rs.getString("eq.localidad"));
		        	equipo.setPuntaje(rs.getInt("eq.puntaje"));
		        	equipo.setDifGoles(rs.getInt("eq.difGoles"));
		        }	
		}		       
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    finally {
		try {			
			DbConnector.getInstancia().releaseConn();
		} catch (Exception e2) {
			e2.printStackTrace();
	}
		}
    return equipo;
	}
	public int add (Equipo e) { 
		String addStatement="insert into equipo(razonSocial,localidad,puntaje,difGoles) values (?,?,?,?)";
		int id=0;
        try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(addStatement,PreparedStatement.RETURN_GENERATED_KEYS);) {
    		/*ps.setInt(1, e.getIdEquipo());*/
    		ps.setString(1, e.getNombre());
			ps.setString(2,e.getLocalidad());
			ps.setInt(3,e.getPuntaje());
			ps.setInt(4,e.getDifGoles());    
            ps.executeUpdate();  
            try(ResultSet keyResultSet=ps.getGeneratedKeys();){
            if(keyResultSet!=null && keyResultSet.next())
            	id= keyResultSet.getInt(1);  
            }
        } 
        catch (SQLException ex) {
        	ex.printStackTrace();
        }
        finally {
    		try {			
    			DbConnector.getInstancia().releaseConn();
    		} catch (Exception e2) {
    			e2.printStackTrace();
    		}
    		}
		return id;
	}	
	public void delete(Equipo e) { //  baja logica/soft delete
		String updateEnetrenadorDependency=/*"update entrenador ent"
				+ " inner join equipo eq on eq.id=ent.idEquipo"
				+ " set ent.idEquipo=null"
				+ " where ent.idEquipo=? and eq.fecha_baja is not null"*/"update entrenador ent set ent.idEquipo=null where ent.idEquipo=?";
		
		String updateJugadoresDependency=/*"update jugador j"
				+ " inner join equipo eq on j.idEquipo=eq.id"
				+ " set j.idEquipo=null"
				+ " where j.idEquipo=? and eq.fecha_baja is not null"*/"update jugador j set j.idEquipo=null where j.idEquipo=?";
		
		String deleteEquipo=/*"update equipo eq"
    			+ " set eq.fecha_baja=CURRENT_DATE"
    			+ " where eq.id not in"
    			+ "	(select distinct eq.id"
    			+ "	from partido pa"
    			+ "	inner join equipo eq on eq.id= pa.idEquipo1 or eq.id= pa.idEquipo1"
    			+ "	WHERE PA.fecha>= CURRENT_DATE and eq.id=?)"
    			+ " and eq.id=?"*/"update equipo eq set eq.fecha_baja=CURRENT_DATE where eq.id not in (select distinct eq.id from partido pa inner join equipo eq on eq.id= pa.idEquipo1 or eq.id= pa.idEquipo2 WHERE PA.fecha>= CURRENT_DATE and eq.id=?) and eq.id=?";
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
	String updateStatement="update equipo eq "
			+ "set eq.razonSocial=?, eq.localidad=?, eq.puntaje=?, eq.difGoles=?"
			+ " where eq.id=? and eq.fecha_baja is null";
    try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(updateStatement);) {	
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
			DbConnector.getInstancia().releaseConn();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		}
	}
	public LinkedList<Equipo> getEquiposDispPartido(Partido partido){	//busca equipos que no participen en una fecha/hora dada, tengan entrenador y 5 jugadores
		String getEquiDispStatement="select eq.id, eq.razonSocial, eq.localidad,eq.puntaje, eq.difGoles from equipo eq"
				+ " inner join jugador jug on eq.id=jug.idEquipo"
				+ " inner join entrenador ent on eq.id=ent.idEquipo"
				+ " WHERE eq.id not IN"
				+ " (select eq.id from partido pa"
				+ " inner join equipo eq on eq.id= pa.idEquipo1 or eq.id= pa.idEquipo2"
				+ " WHERE PA.fecha=? and pa.hora=?)"
				+ " and eq.fecha_baja is null"
				+ " group by eq.id"
				+ " having count(jug.idEquipo)>=5";
		LinkedList<Equipo> equipos= new LinkedList<>();	
		try(PreparedStatement ps = DbConnector.getInstancia().getConn().prepareStatement(getEquiDispStatement);) {	
			ps.setObject(1,partido.getFecha());
			ps.setObject(2, partido.getHora());
			try(ResultSet rs = ps.executeQuery();){
				while (rs.next()) {
					Equipo e=new Equipo();
					e.setIdEquipo(rs.getInt("eq.id"));
					e.setNombre(rs.getString("eq.razonSocial"));
					e.setLocalidad(rs.getString("eq.localidad"));
					e.setPuntaje(rs.getInt("eq.puntaje"));
					e.setDifGoles(rs.getInt("eq.difGoles"));			
					equipos.add(e);		
				}	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();		
		} finally {
			try {
				DbConnector.getInstancia().releaseConn();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return equipos;
	}
	public boolean dispParaReprogramar(Partido partido) {
		String  dispParaReprogramarStmt="select eq.id from equipo eq"
				+ " inner join jugador jug on eq.id=jug.idEquipo"
				+ " inner join entrenador ent on eq.id=ent.idEquipo"
				+ " WHERE eq.id not IN"
				+ " (select eq.id from partido pa"
				+ " inner join equipo eq on eq.id= pa.idEquipo1 or eq.id= pa.idEquipo2"
				+ " WHERE PA.fecha=? and pa.hora=?)"
				+ " and eq.fecha_baja is null and (eq.id=? or eq.id=?)"
				+ " group by eq.id"
				+ " having count(jug.idEquipo)>=5";				
		int contador=0;
		try(PreparedStatement ps = DbConnector.getInstancia().getConn().prepareStatement(dispParaReprogramarStmt);) {	
			ps.setObject(1,partido.getFecha());
			ps.setObject(2, partido.getHora());
			ps.setInt(3, partido.getEquipo1().getIdEquipo());
			ps.setInt(4, partido.getEquipo2().getIdEquipo());
			try(ResultSet rs = ps.executeQuery();){
				while (rs.next()) {
					contador++;		
				}	
			}			
		} catch (SQLException e) {
			e.printStackTrace();		
		} finally {
			try {
				DbConnector.getInstancia().releaseConn();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return (contador==2)?true:false;
	}
}