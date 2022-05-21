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
		String getAllStatement="SELECT p.fecha,p.hora,p.resultado,p.incidencias, eq1.id,eq1.razonSocial,eq1.localidad,eq1.puntaje,eq1.difGoles, eq2.id,eq2.razonSocial,eq2.localidad,eq2.puntaje,eq2.difGoles, c.numCancha,c.nombre, arb.dniArbitro, arb.nombre, arb.apellido, arb.fechaNac FROM partido p inner join equipo eq1 on p.idEquipo1=eq1.id inner join equipo eq2 on p.idEquipo2=eq2.id inner join cancha c on p.numCancha=c.numCancha left join arbitro arb on arb.dniArbitro=p.dniArbitro";
		LinkedList<Partido> Partidos = new LinkedList<>();
		try(Statement stm = DbConnector.getInstancia().getConn().createStatement();
			ResultSet rs =stm.executeQuery(getAllStatement)) {
			while (rs.next()) {			
				Partido partido = new Partido();
				Equipo equipo1=new Equipo();
				Equipo equipo2=new Equipo();
				Cancha cancha=new Cancha();
				Arbitro arbitro= new Arbitro();
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
				arbitro.setDni(rs.getString("arb.dniArbitro"));
				arbitro.setNombre(rs.getString("arb.nombre"));
				arbitro.setApellido(rs.getString("arb.apellido"));
				arbitro.setFecha_nacimiento(rs.getObject("arb.fechaNac",LocalDate.class));
				partido.setEquipo1(equipo1);
				partido.setEquipo2(equipo2);
				partido.setCancha(cancha);
				partido.setArbitro(arbitro);
				Partidos.add(partido);							}
			}
		catch(Exception ex) {ex.printStackTrace();}
		finally {
			try {	
				DbConnector.getInstancia().releaseConn();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return Partidos;
	}
	public Partido getOne(Partido p) // busca un partido con fecha, hora, nroCancha
	{	String getOneStatement="SELECT p.fecha,p.hora,p.resultado,p.incidencias, eq1.id,eq1.razonSocial,eq1.localidad,eq1.puntaje,eq1.difGoles, eq2.id,eq2.razonSocial,eq2.localidad,eq2.puntaje,eq2.difGoles, c.numCancha,c.nombre, arb.dniArbitro, arb.nombre, arb.apellido, arb.fechaNac FROM partido p inner join equipo eq1 on p.idEquipo1=eq1.id inner join equipo eq2 on p.idEquipo2=eq2.id inner join cancha c on p.numCancha=c.numCancha left join arbitro arb on arb.dniArbitro=p.dniArbitro where p.fecha=? and p.hora=? and p.numCancha=?;";	
		Partido partido = new Partido();
		try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(getOneStatement);) {			
			ps.setObject(1, p.getFecha());
			ps.setObject(2, p.getHora());
			ps.setInt(3, p.getCancha().getNroCancha());
			try(ResultSet rs=ps.executeQuery();){
				while(rs.next()) {	
					Equipo equipo1=new Equipo();
					Equipo equipo2=new Equipo();
					Cancha cancha=new Cancha();
					Arbitro arbitro= new Arbitro();					
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
					arbitro.setDni(rs.getString("arb.dniArbitro"));
					arbitro.setNombre(rs.getString("arb.nombre"));
					arbitro.setApellido(rs.getString("arb.apellido"));
					arbitro.setFecha_nacimiento(rs.getObject("arb.fechaNac",LocalDate.class));
					partido.setEquipo1(equipo1);
					partido.setEquipo2(equipo2);
					partido.setCancha(cancha);		
					partido.setArbitro(arbitro);
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
		return partido;
	}
	public void update(Partido p) {
		String updateStatement="update partido set resultado = ?, incidencias=?, idEquipo1 =?, idEquipo2=?, dniArbitro=? where  fecha=? and hora =? and numCancha= ?" ;
		try(PreparedStatement ps= DbConnector.getInstancia().getConn().prepareStatement(updateStatement);) { 
			ps.setString(1, p.getResultado());
			ps.setString(2, p.getIncidencias());
			ps.setInt(3, p.getEquipo1().getIdEquipo());
			ps.setInt(4, p.getEquipo2().getIdEquipo());
			ps.setString(5, p.getArbitro().getDni());
			ps.setObject(6, p.getFecha());
			ps.setObject(7, p.getHora());
			ps.setInt(9, p.getCancha().getNroCancha());
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
	public void add (Partido p) {	   
   		String addStatement="insert into partido(fecha,hora,idEquipo1,idEquipo2,numCancha,resultado) values (?,?,?,?,?,?)";
        try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(addStatement);){  		
    		ps.setObject(1, p.getFecha());
    		ps.setObject(2, p.getHora());
			ps.setInt(3, p.getEquipo1().getIdEquipo());
			ps.setInt(4,p.getEquipo2().getIdEquipo());
			ps.setInt(5,p.getCancha().getNroCancha());
			ps.setString(6, p.getResultado());
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
	public void delete(Partido p) {
		String deleteStatement="delete from partido where fecha=? and hora=? and numCancha=?";	
	    try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(deleteStatement);) {	    	
			ps.setObject(1, p.getFecha());
			ps.setObject(2, p.getHora());
			ps.setInt(3, p.getCancha().getNroCancha());		
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
}