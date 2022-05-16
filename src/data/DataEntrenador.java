package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;

import entities.Entrenador;
import entities.Equipo;

public class DataEntrenador {

		public LinkedList<Entrenador> getAll(){	
			DbConnector conexion = new DbConnector();
			Connection cn = null;
			Statement stm = null;
			ResultSet rs = null;
			LinkedList<Entrenador> entrenadores= new LinkedList<>();			
			try {
				cn = conexion.conectar();
				stm = cn.createStatement();
				rs = stm.executeQuery("Select ent.dniEntrenador,ent.nombre,ent.apellido,ent.fechaNac"
						+ ",eq.id,eq.razonSocial,eq.localidad,eq.puntaje,eq.difGoles "
						+ "from entrenador ent inner join equipo eq"); // falta escudo			
				while (rs.next()) {
					Entrenador entrenador=new Entrenador();
					Equipo equipo= new Equipo();
					entrenador.setDni(rs.getString("ent.dniEntrenador"));
					entrenador.setNombre(rs.getString("ent.nombre"));
					entrenador.setApellido(rs.getString("ent.apellido"));
					entrenador.setFecha_nacimiento(rs.getObject("ent.fechaNac",LocalDate.class));
					equipo.setIdEquipo(rs.getInt("eq.id"));
					equipo.setNombre(rs.getString("eq.razonSocial"));
					equipo.setLocalidad(rs.getString("eq.localidad"));
					equipo.setPuntaje(rs.getInt("eq.puntaje"));
					equipo.setDifGoles(rs.getInt("eq.difGoles"));
					entrenador.setEquipo(equipo);
					entrenadores.add(entrenador);	
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
			return entrenadores;
		}
		public Entrenador getOne(Entrenador e) {
			DbConnector conexion = new DbConnector();
			Connection cn = null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			Entrenador entrenador = new Entrenador();
			Equipo equipo=new Equipo();
		    try {
		    	cn = conexion.conectar();
				ps =cn.prepareStatement("Select ent.dniEntrenador,ent.nombre,ent.apellido,ent.fechaNac"
								+ ",eq.id,eq.razonSocial,eq.localidad,eq.puntaje,eq.difGoles "
								+ "from entrenador ent inner join equipo eq where ent.dniEntrenador=?");
				ps.setString(1, e.getDni());
                rs=ps.executeQuery();  
		        while (rs.next()) {	
					entrenador.setDni(rs.getString("dniEntrenador"));
					entrenador.setNombre(rs.getString("nombre"));
					entrenador.setApellido(rs.getString("apellido"));
					entrenador.setFecha_nacimiento(rs.getObject("fechaNac",LocalDate.class));
					equipo.setIdEquipo(rs.getInt("eq.id"));
					equipo.setNombre(rs.getString("eq.razonSocial"));
					equipo.setLocalidad(rs.getString("eq.localidad"));
					equipo.setPuntaje(rs.getInt("eq.puntaje"));
					equipo.setDifGoles(rs.getInt("eq.difGoles"));
					entrenador.setEquipo(equipo);
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
		return entrenador;
			}
		public void add (Entrenador e) {	   
				DbConnector conexion = new DbConnector();
				Connection cn = null;
	    		PreparedStatement ps=null;
		        try {
		        	cn = conexion.conectar();
		    		ps=cn.prepareStatement("insert into entrenador(dniEntrenador,nombre,apellido,fechaNac) values (?,?,?,?)");
		    		ps.setString(1, e.getDni());
		    		ps.setString(2, e.getNombre());
					ps.setString(3,e.getApellido());
					ps.setObject(4,e.getFecha_nacimiento());
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
		public void delete(Entrenador e) {
				DbConnector conexion = new DbConnector();
				Connection cn = null;
				PreparedStatement ps=null;
			    try {
			    	cn = conexion.conectar();
			    	ps = cn.prepareStatement("delete from entrenador ent where ent.dniEntrenador=?");
					ps.setString(1, e.getDni());
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
		public void update (Entrenador e) {		   
			DbConnector conexion = new DbConnector();
			Connection cn = null;
			PreparedStatement ps=null;
		    try {
		    	cn = conexion.conectar();
				ps=cn.prepareStatement("update entrenador ent set ent.nombre=?, ent.apellido=?, ent.fechaNac=?,ent.idEquipo=? where ent.dniEntrenador=?");
				ps.setString(1, e.getNombre());
				ps.setString(2,e.getApellido());
				ps.setObject(3,e.getFecha_nacimiento());
				ps.setInt(4, e.getEquipo().getIdEquipo());
				ps.setString(5, e.getDni());
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
		public LinkedList<Entrenador> getEntrenadoresDisp() // lista de entrenadores sin equipo
		{	DbConnector conexion = new DbConnector();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		LinkedList<Entrenador> entrenadores= new LinkedList<>();			
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("Select ent.dniEntrenador,ent.nombre,ent.apellido,ent.fechaNac"
					+ " from entrenador ent where ent.idEquipo IS NULL"); // falta escudo			
			while (rs.next()) {
				Entrenador entrenador=new Entrenador();
				entrenador.setDni(rs.getString("ent.dniEntrenador"));
				entrenador.setNombre(rs.getString("ent.nombre"));
				entrenador.setApellido(rs.getString("ent.apellido"));
				entrenador.setFecha_nacimiento(rs.getObject("ent.fechaNac",LocalDate.class));
				entrenadores.add(entrenador);	
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
		return entrenadores;	
		}
		/*	public void deleteDependency(Equipo equipo)
		{
			Conexion conexion = new Conexion();
			Connection cn = null;
			PreparedStatement ps=null;
		    try {
		    	cn = conexion.conectar();
				ps=cn.prepareStatement("update entrenador ent inner join equipo eq on eq.id=ent.idEquipo set ent.idEquipo=null where ent.idEquipo=? and eq.fecha_baja is not null"
						+ "");
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