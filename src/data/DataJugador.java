package data;

import java.sql.Connection;
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
		Conexion conexion = new Conexion();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		LinkedList<Jugador> jugadores= new LinkedList<>();	
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("Select j.dniJugador,j.nombre,j.apellido,j.fechaNac,j.posicion,j.goles,j.asistencias,j.amarillas,j.rojas,j.partJugados"
					+ ",e.id,e.razonSocial,e.localidad,e.puntaje,e.difGoles "
					+ "from jugador j inner join equipo e on j.idEquipo=e.id"); // falta buscar escudo		
			while (rs.next()) {
				Jugador jugador=new Jugador();
				Equipo equipo= new Equipo();
				jugador.setDni(rs.getString("j.dniJugador"));
				jugador.setNombre(rs.getString("j.nombre"));
				jugador.setApellido(rs.getString("j.apellido"));
				jugador.setFecha_nacimiento(rs.getObject("j.fechaNac", LocalDate.class));
				jugador.setPosicion(rs.getString("j.posicion"));
				jugador.setGoles(rs.getInt("j.goles"));
				jugador.setAsistencias(rs.getInt("j.asistencias"));
				jugador.setTarjetasA(rs.getInt("j.amarillas"));
				jugador.setTarjetasR(rs.getInt("j.rojas"));
				jugador.setPartidosJugados(rs.getInt("j.partJugados"));
				equipo.setIdEquipo(rs.getInt("e.id"));
				equipo.setNombre(rs.getString("e.razonSocial"));
				equipo.setLocalidad(rs.getString("e.localidad"));
				equipo.setPuntaje(rs.getInt("e.puntaje"));
				equipo.setDifGoles(rs.getInt("e.difGoles"));
				jugador.setEquipo(equipo);
				jugadores.add(jugador);						
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
		return jugadores;
	}
	public Jugador getOne(Jugador j) {
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Jugador jugador = new Jugador();
		Equipo equipo= new Equipo();
	    try {
	    	cn = conexion.conectar();
			ps =cn.prepareStatement("Select j.dniJugador,j.nombre,j.apellido,j.fechaNac,j.posicion,j.goles,j.asistencias,j.amarillas,j.rojas,j.partJugados"
							+ ",e.id,e.razonSocial,e.localidad,e.puntaje,e.difGoles "
							+ "from jugador j inner join equipo e on j.idEquipo=e.id where j.dniJugador=?");
			ps.setString(1, j.getDni());
			rs=ps.executeQuery();  
	        while (rs.next()) {
				jugador.setDni(rs.getString("j.dniJugador"));
				jugador.setNombre(rs.getString("j.nombre"));
				jugador.setApellido(rs.getString("j.apellido"));
				jugador.setFecha_nacimiento(rs.getObject("j.fechaNac", LocalDate.class));
				jugador.setPosicion(rs.getString("j.posicion"));
				jugador.setGoles(rs.getInt("j.goles"));
				jugador.setAsistencias(rs.getInt("j.asistencias"));
				jugador.setTarjetasA(rs.getInt("j.amarillas"));
				jugador.setTarjetasR(rs.getInt("j.rojas"));
				jugador.setPartidosJugados(rs.getInt("j.partJugados"));
				equipo.setIdEquipo(rs.getInt("e.equipo.id"));
				equipo.setNombre(rs.getString("e.equipo.razonSocial"));
				equipo.setLocalidad(rs.getString("e.equipo.localidad"));
				equipo.setPuntaje(rs.getInt("e.equipo.puntaje"));
				equipo.setDifGoles(rs.getInt("e.equipo.difGoles"));
				jugador.setEquipo(equipo);	
	        }		     
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    finally {
			try {
				if (rs!= null) {
					rs.close();
				}	
				if (ps != null) {
					ps.close();
				}		
				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	return jugador;
		}
	public void add (Jugador j) {
			Conexion conexion = new Conexion();
			Connection cn = null;
    		PreparedStatement ps=null;
	        try {
	        	cn = conexion.conectar();
	    		ps=cn.prepareStatement("insert into jugador(dniJugador,nombre,apellido,fechaNac,posicion,goles,asistencias,amarillas,rojas,partJugados) values (?,?,?,?,?,?,?,?,?,?)");
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
	public void delete(Jugador j) {
			Conexion conexion = new Conexion();
			Connection cn = null;
			PreparedStatement ps=null;
		    try {
		    	cn = conexion.conectar();
		    	ps = cn.prepareStatement("delete from jugador where dniJugador=?");
				ps.setString(1, j.getDni());
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
	public void update (Jugador j) {   
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement ps=null;
	    try {
	    	cn = conexion.conectar();
			ps=cn.prepareStatement("update jugador j set j.nombre=?, j.apellido=?, j.fechaNac=?,j.posicion=?,j.goles=?,"
					+ "j.asistencias=?,j.amarillas=?,j.rojas=?,j.partJugados=?,j.idEquipo=? where j.dniJugador=?");
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
			ps.setInt(11, j.getEquipo().getIdEquipo());
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
	public void deleteDependency(Equipo equipo)
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
	}
	}