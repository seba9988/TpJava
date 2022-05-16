package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

import entities.*;

public class DataPartido {
	
	public LinkedList<Partido> getAll () {
		Statement stm = null;
		ResultSet rs = null;
		LinkedList<Partido> Partidos = new LinkedList<>();
		try {
			stm = DbConnector.getInstancia().getConn().createStatement();
			rs = stm.executeQuery("SELECT p.fecha,p.hora,p.resultado,p.incidencias,"
					+ "eq1.id,eq1.razonSocial,eq1.localidad,eq1.puntaje,eq1.difGoles,"
					+ "eq2.id,eq2.razonSocial,eq2.localidad,eq2.puntaje,eq2.difGoles,"
					+ "c.numCancha,c.nombre"
					+ "FROM partido p inner join equipo eq1 on p.idEquipo1=eq1.id "
					+ "inner join equipo eq2 on p.idEquipo2=eq2.id "
					+ "inner join cancha c on p.numCancha=c.numCancha;"); // hacer join con equipo , cancha y arbitro
			while (rs.next()) {
								Partido partido = new Partido();
								Equipo equipo1=new Equipo();
								Equipo equipo2=new Equipo();
								Cancha cancha=new Cancha();
								partido.setFecha(rs.getObject("fecha", LocalDate.class));
								partido.setHora(rs.getObject("hora", LocalTime.class));
								partido.setResultado(rs.getString("resultado"));
								partido.setIncidencias(rs.getString("incidencias"));
								equipo1.setIdEquipo(rs.getInt("eq1.id"));
								equipo1.setNombre(rs.getString("eq1.razonSocial"));
								equipo1.setLocalidad(rs.getString("eq1.localidad"));
								equipo1.setPuntaje(rs.getInt("eq1.puntaje"));
								equipo1.setDifGoles(rs.getInt("eq1.difGoles"));
								equipo2.setIdEquipo(rs.getInt("eq2.id"));
								equipo2.setNombre(rs.getString("eq2.razonSocial"));
								equipo2.setLocalidad(rs.getString("eq2.localidad"));
								equipo2.setPuntaje(rs.getInt("eq2.puntaje"));
								equipo2.setDifGoles(rs.getInt("eq2.difGoles"));
								cancha.setNroCancha(rs.getInt("c.numCancha"));
								cancha.setNombre(rs.getString("c.nombre"));
								partido.setEquipo1(equipo1);
								partido.setEquipo2(equipo2);
								partido.setCancha(cancha);
								Partidos.add(partido);
								}
			}catch(Exception ex) {ex.printStackTrace();}
		finally {
			try {
				if (rs!= null) {rs.close();}
				if (stm != null) {stm.close();}			
				DbConnector.getInstancia().releaseConn();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return Partidos;
	}
	public Partido getOne(Partido p) // busca un partido con fecha, hora, nroCancha
	{	PreparedStatement ps=null;
		ResultSet rs=null;
		Partido partido = new Partido();
		try {
			Equipo equipo1=new Equipo();
			Equipo equipo2=new Equipo();
			Cancha cancha=new Cancha();
			ps =DbConnector.getInstancia().getConn().prepareStatement("SELECT p.fecha,p.hora,p.resultado,p.incidencias,"
							+ "eq1.id,eq1.razonSocial,eq1.localidad,eq1.puntaje,eq1.difGoles,"
							+ "eq2.id,eq2.razonSocial,eq2.localidad,eq2.puntaje,eq2.difGoles,"
							+ "c.numCancha,c.nombre"
							+ "FROM partido p inner join equipo eq1 on p.idEquipo1=eq1.id "
							+ "inner join equipo eq2 on p.idEquipo2=eq2.id "
							+ "inner join cancha c on p.numCancha=c.numCancha where p.fecha=? and p.hora=? and p.numCancha=?;"); // hacer join con equipo,chancha y arbitro	
			ps.setObject(1, p.getFecha());
			ps.setObject(2, p.getHora());
			ps.setInt(3, p.getCancha().getNroCancha());
			rs=ps.executeQuery();  
			rs.next();		
			partido.setFecha(rs.getObject("fecha", LocalDate.class));
			partido.setHora(rs.getObject("hora", LocalTime.class));
			partido.setResultado(rs.getString("resultado"));
			partido.setIncidencias(rs.getString("incidencias"));
			equipo1.setIdEquipo(rs.getInt("eq1.id"));
			equipo1.setNombre(rs.getString("eq1.razonSocial"));
			equipo1.setLocalidad(rs.getString("eq1.localidad"));
			equipo1.setPuntaje(rs.getInt("eq1.puntaje"));
			equipo1.setDifGoles(rs.getInt("eq1.difGoles"));
			equipo2.setIdEquipo(rs.getInt("eq2.id"));
			equipo2.setNombre(rs.getString("eq2.razonSocial"));
			equipo2.setLocalidad(rs.getString("eq2.localidad"));
			equipo2.setPuntaje(rs.getInt("eq2.puntaje"));
			equipo2.setDifGoles(rs.getInt("eq2.difGoles"));
			cancha.setNroCancha(rs.getInt("c.numCancha"));
			cancha.setNombre(rs.getString("c.nombre"));
			partido.setEquipo1(equipo1);
			partido.setEquipo2(equipo2);
			partido.setCancha(cancha);				 
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		finally {
			try {
				if (ps!= null) {ps.close();}				
				if (rs != null) {rs.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return partido;
	}
	public void update(Partido p) {
		PreparedStatement ps= null;
		try { 
			ps =DbConnector.getInstancia().getConn().prepareStatement("update partido set resultado = ?, idEquipo1 =?, idEquipo2=?, dniArbitro=? where  fecha=? and hora =? and numCancha= ?" );
			ps.setString(1, p.getResultado());
			ps.setInt(2, p.getEquipo1().getIdEquipo());
			ps.setInt(3, p.getEquipo2().getIdEquipo());
			ps.setString(4, p.getArbitro().getDni());
			//stmt.setString(2, p.getIncidencias());
			ps.setObject(5, p.getFecha());
			ps.setObject(6, p.getHora());
			ps.setInt(7, p.getCancha().getNroCancha());
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
	public void add (Partido p) {	   
   		PreparedStatement ps=null;
        try {
    		ps=DbConnector.getInstancia().getConn().prepareStatement("insert into partido(fecha,hora,idEquipo1,idEquipo2,numCancha,resultado) values (?,?,?,?,?,?)"); // no pongo incidencias ni resultado porque es un partido nuevo
    		ps.setObject(1, p.getFecha());
    		ps.setObject(2, p.getHora());
			ps.setInt(3, p.getEquipo1().getIdEquipo());
			ps.setInt(4,p.getEquipo2().getIdEquipo());
			//ps.setInt(5,p.getDniArbitro());
			ps.setInt(5,p.getCancha().getNroCancha());
			ps.setString(6, p.getResultado());
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
	public void delete(Partido p) {
		PreparedStatement ps=null;
	    try {	    	
	    	ps = DbConnector.getInstancia().getConn().prepareStatement("delete from partido where fecha=? and hora=? and numCancha=?");
			ps.setObject(1, p.getFecha());
			ps.setObject(2, p.getHora());
			ps.setInt(3, p.getCancha().getNroCancha());		
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