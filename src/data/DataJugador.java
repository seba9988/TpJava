package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;

import entities.Equipo;
import entities.Jugador;

public class DataJugador {
	
	public LinkedList<Jugador> getAll(){	
		Statement stm = null;
		ResultSet rs = null;
		LinkedList<Jugador> jugadores= new LinkedList<>();	
		try {
			stm = DbConnector.getInstancia().getConn().createStatement();
			rs = stm.executeQuery("Select jug.dniJugador,jug.nombre,jug.apellido,jug.fechaNac,jug.posicion,jug.goles,jug.asistencias,jug.amarillas,jug.rojas,jug.partJugados"
					+ ",eq.id,eq.razonSocial,eq.localidad,eq.puntaje,eq.difGoles "
					+ "from jugador jug left join equipo eq on jug.idEquipo=eq.id"); // falta buscar escudo		
			while (rs.next()) { // jugadores con equipo
				Jugador jugador=new Jugador();
				Equipo equipo= new Equipo();
				jugador.setDni(rs.getString("jug.dniJugador"));
				jugador.setNombre(rs.getString("jug.nombre"));
				jugador.setApellido(rs.getString("jug.apellido"));
				jugador.setFecha_nacimiento(rs.getObject("jug.fechaNac", LocalDate.class));
				jugador.setPosicion(rs.getString("jug.posicion"));
				jugador.setGoles(rs.getInt("jug.goles"));
				jugador.setAsistencias(rs.getInt("jug.asistencias"));
				jugador.setTarjetasA(rs.getInt("jug.amarillas"));
				jugador.setTarjetasR(rs.getInt("jug.rojas"));
				jugador.setPartidosJugados(rs.getInt("jug.partJugados"));
				equipo.setIdEquipo(rs.getInt("eq.id"));
				if(equipo.getIdEquipo()!=null) {
					equipo.setNombre(rs.getString("eq.razonSocial"));
					equipo.setLocalidad(rs.getString("eq.localidad"));
					equipo.setPuntaje(rs.getInt("eq.puntaje"));
					equipo.setDifGoles(rs.getInt("eq.difGoles"));
				}				
				jugador.setEquipo(equipo);
				jugadores.add(jugador);						
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
		return jugadores;
	}
	public LinkedList<Jugador> getAllByPosicion(Jugador j){	
		PreparedStatement ps= null;
		ResultSet rs = null;
		LinkedList<Jugador> jugadores= new LinkedList<>();	
		try {
			ps =DbConnector.getInstancia().getConn().prepareStatement("Select jug.dniJugador,jug.nombre,jug.apellido,jug.fechaNac,jug.posicion,jug.goles,jug.asistencias,jug.amarillas,jug.rojas,jug.partJugados"
					+ ",eq.id,eq.razonSocial,eq.localidad,eq.puntaje,eq.difGoles "
					+ "from jugador jug left join equipo eq on jug.idEquipo=eq.id where jug.posicion=?"); // falta buscar escudo	
			ps.setString(1, j.getPosicion());
			rs=ps.executeQuery();
			while (rs.next()) {
				Jugador jugador=new Jugador();
				Equipo equipo= new Equipo();
				jugador.setDni(rs.getString("jug.dniJugador"));
				jugador.setNombre(rs.getString("jug.nombre"));
				jugador.setApellido(rs.getString("jug.apellido"));
				jugador.setFecha_nacimiento(rs.getObject("jug.fechaNac", LocalDate.class));
				jugador.setPosicion(rs.getString("jug.posicion"));
				jugador.setGoles(rs.getInt("jug.goles"));
				jugador.setAsistencias(rs.getInt("jug.asistencias"));
				jugador.setTarjetasA(rs.getInt("jug.amarillas"));
				jugador.setTarjetasR(rs.getInt("jug.rojas"));
				jugador.setPartidosJugados(rs.getInt("jug.partJugados"));
				equipo.setIdEquipo(rs.getInt("eq.id"));
				if(equipo.getIdEquipo()!=null) {
					equipo.setNombre(rs.getString("eq.razonSocial"));
					equipo.setLocalidad(rs.getString("eq.localidad"));
					equipo.setPuntaje(rs.getInt("eq.puntaje"));
					equipo.setDifGoles(rs.getInt("eq.difGoles"));
				}
				jugador.setEquipo(equipo);
				jugadores.add(jugador);						
			}			
		} catch (SQLException e) {
			e.printStackTrace();		
		} finally {
			try {
				if (rs!= null) {rs.close();}	
				DbConnector.getInstancia().releaseConn();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return jugadores;
	}
	public Jugador getOne(Jugador j) {

		PreparedStatement ps=null;
		ResultSet rs=null;
		Jugador jugador = new Jugador();
		Equipo equipo= new Equipo();
	    try {
			ps =DbConnector.getInstancia().getConn().prepareStatement("Select jug.dniJugador,jug.nombre,jug.apellido,jug.fechaNac,jug.posicion,jug.goles,jug.asistencias,jug.amarillas,jug.rojas,jug.partJugados"
							+ ",eq.id,eq.razonSocial,eq.localidad,eq.puntaje,eq.difGoles "
							+ "from jugador jug left join equipo eq on jug.idEquipo=eq.id where jug.dniJugador=?");
			ps.setString(1, j.getDni());
			rs=ps.executeQuery();  
	        while (rs.next()) {
				jugador.setDni(rs.getString("jug.dniJugador"));
				jugador.setNombre(rs.getString("jug.nombre"));
				jugador.setApellido(rs.getString("jug.apellido"));
				jugador.setFecha_nacimiento(rs.getObject("jug.fechaNac", LocalDate.class));
				jugador.setPosicion(rs.getString("jug.posicion"));
				jugador.setGoles(rs.getInt("jug.goles"));
				jugador.setAsistencias(rs.getInt("jug.asistencias"));
				jugador.setTarjetasA(rs.getInt("jug.amarillas"));
				jugador.setTarjetasR(rs.getInt("jug.rojas"));
				jugador.setPartidosJugados(rs.getInt("jug.partJugados"));
				equipo.setIdEquipo(rs.getInt("eq.id"));
				if(equipo.getIdEquipo()!=null) {
					equipo.setNombre(rs.getString("eq.razonSocial"));
					equipo.setLocalidad(rs.getString("eq.localidad"));
					equipo.setPuntaje(rs.getInt("eq.puntaje"));
					equipo.setDifGoles(rs.getInt("eq.difGoles"));
				}
				jugador.setEquipo(equipo);	
	        }		     
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    finally {
			try {
				if (rs!= null) {rs.close();}	
				if (ps != null) {ps.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	return jugador;
		}
	public void add (Jugador j) {
    		PreparedStatement ps=null;
	        try {
	    		ps=DbConnector.getInstancia().getConn().prepareStatement("insert into jugador(dniJugador,nombre,apellido,fechaNac,posicion,goles,asistencias,amarillas,rojas,partJugados) values (?,?,?,?,?,?,?,?,?,?)");
	    		ps.setString(1, j.getDni());
	    		ps.setString(2, j.getNombre());
				ps.setString(3,j.getApellido());
				ps.setObject(4,j.getFecha_nacimiento());
				ps.setString(5,j.getPosicion());
				ps.setInt(6, j.getGoles());
				ps.setInt(7,j.getAsistencias());
				ps.setInt(8, j.getTarjetasA());
				ps.setInt(9, j.getTarjetasR());
				ps.setInt(10, j.getPartidosJugados());	         
	            ps.executeUpdate();
	        } catch (SQLException ex) {
	        	ex.printStackTrace();
	        }
	        finally {
				try {
					if (ps!= null) {ps.close();}					

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	public void delete(Jugador j) {
			PreparedStatement ps=null;
		    try {
		    	System.out.println("sssssssssssss");
		    	ps = DbConnector.getInstancia().getConn().prepareStatement("delete from jugador where dniJugador=?");
				ps.setString(1, j.getDni());
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
	public void update (Jugador j) {   
		PreparedStatement ps=null;
	    try {
			ps=DbConnector.getInstancia().getConn().prepareStatement("update jugador jug set jug.nombre=?, jug.apellido=?, jug.fechaNac=?,jug.posicion=?,jug.goles=?,"
					+ "jug.asistencias=?,jug.amarillas=?,jug.rojas=?,jug.partJugados=? where jug.dniJugador=?");
			ps.setString(1, j.getNombre());
			ps.setString(2,j.getApellido());
			ps.setObject(3,j.getFecha_nacimiento());
			ps.setString(4,j.getPosicion());
			ps.setInt(5, j.getGoles());
			ps.setInt(6, j.getAsistencias());
			ps.setInt(7, j.getTarjetasA());
			ps.setInt(8, j.getTarjetasR());
			ps.setInt(9, j.getPartidosJugados());
			ps.setString(10,j.getDni());		
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
/*	public void deleteDependency(Equipo equipo)
	{
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement ps=null;
	    try {
	    	cn = conexion.conectar();
			ps=cn.prepareStatement("update jugador j inner join equipo eq on j.idEquipo=eq.id set j.idEquipo=null where j.idEquipo=? and eq.fecha_baja is not null");
			ps.setInt(1, equipo.getIdEquipo());
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
	}*/
	}