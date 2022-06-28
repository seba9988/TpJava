package data;

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
			String getAllStatement="Select ent.dniEntrenador,ent.nombre,ent.apellido,ent.fechaNac"
					+ ",eq.id,eq.razonSocial,eq.localidad,eq.puntaje,eq.difGoles "
					+ "from entrenador ent left join equipo eq on ent.idEquipo=eq.id";
			LinkedList<Entrenador> entrenadores= new LinkedList<>();			
			try (Statement stm = DbConnector.getInstancia().getConn().createStatement();
				ResultSet rs =stm.executeQuery(getAllStatement);){	
				while (rs.next()) {
					Entrenador entrenador=new Entrenador();
					Equipo equipo= new Equipo();
					entrenador.setDni(rs.getString("ent.dniEntrenador"));
					entrenador.setNombre(rs.getString("ent.nombre"));
					entrenador.setApellido(rs.getString("ent.apellido"));
					entrenador.setFechaNacimiento(rs.getObject("ent.fechaNac",LocalDate.class));
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
					DbConnector.getInstancia().releaseConn();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return entrenadores;
		}
		public Entrenador getOne(Entrenador e) {
			String getOneStatement="Select ent.dniEntrenador,ent.nombre,ent.apellido,ent.fechaNac"
					+ ",eq.id,eq.razonSocial,eq.localidad,eq.puntaje,eq.difGoles "
					+ "from entrenador ent inner join equipo eq where ent.dniEntrenador=?";		
			Entrenador entrenador = new Entrenador();
			Equipo equipo=new Equipo();
		    try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(getOneStatement);) {
				ps.setString(1, e.getDni());
				try(ResultSet rs=ps.executeQuery();){
		        while (rs.next()) {	
					entrenador.setDni(rs.getString("ent.dniEntrenador"));
					entrenador.setNombre(rs.getString("ent.nombre"));
					entrenador.setApellido(rs.getString("ent.apellido"));
					entrenador.setFechaNacimiento(rs.getObject("ent.fechaNac",LocalDate.class));
					equipo.setIdEquipo(rs.getInt("eq.id"));
					equipo.setNombre(rs.getString("eq.razonSocial"));
					equipo.setLocalidad(rs.getString("eq.localidad"));
					equipo.setPuntaje(rs.getInt("eq.puntaje"));
					equipo.setDifGoles(rs.getInt("eq.difGoles"));
					entrenador.setEquipo(equipo);
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
		return entrenador;
			}
		public void add (Entrenador e) {	   
			String addStatement="insert into entrenador(dniEntrenador,nombre,apellido,fechaNac) values (?,?,?,?)";
		        try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(addStatement);) {
		    		ps.setString(1, e.getDni());
		    		ps.setString(2, e.getNombre());
					ps.setString(3,e.getApellido());
					ps.setObject(4,e.getFechaNacimiento());
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
		public void delete(Entrenador e) {
			String DeleteStatement="delete from entrenador where dniEntrenador=?";		
			    try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(DeleteStatement);) {
					ps.setString(1, e.getDni());
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
		public void update (Entrenador e) {		
			String updateStatement="update entrenador ent set ent.nombre=?, ent.apellido=?, ent.fechaNac=?, ent.idEquipo=? where ent.dniEntrenador=?";
			Integer idEquipo=null;
		    try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(updateStatement);) {
				ps.setString(1, e.getNombre());
				ps.setString(2,e.getApellido());
				ps.setObject(3,e.getFechaNacimiento());
				if(e.getEquipo()!=null)
					{idEquipo= e.getEquipo().getIdEquipo();}
				ps.setObject(4,idEquipo);
				ps.setString(5, e.getDni());
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
		public LinkedList<Entrenador> getEntrenadoresDisp() /* lista de entrenadores sin equipo*/ {	
			String getEntrenadoresDispStmt="Select ent.dniEntrenador,ent.nombre,ent.apellido,ent.fechaNac"
					+ " from entrenador ent where ent.idEquipo IS NULL";
			Entrenador entrenadorVacio=new Entrenador(); // creo un entrenador vacio para agregar a la lista, este tiene la funcion de dar la opcion de no elegir ningun entrenador y evitar errores cuando el arreglo esta vacio 
			entrenadorVacio.setApellido("--Ninguno--"); 
			LinkedList<Entrenador> entrenadores= new LinkedList<>();	
			entrenadores.add(entrenadorVacio);
			try(Statement stm = DbConnector.getInstancia().getConn().createStatement();
				ResultSet rs = stm.executeQuery(getEntrenadoresDispStmt);) {		
			while (rs.next()) {
				Entrenador entrenador=new Entrenador();
				entrenador.setDni(rs.getString("ent.dniEntrenador"));
				entrenador.setNombre(rs.getString("ent.nombre"));
				entrenador.setApellido(rs.getString("ent.apellido"));
				entrenador.setFechaNacimiento(rs.getObject("ent.fechaNac",LocalDate.class));
				entrenadores.add(entrenador);	
				}		
			} 	catch (SQLException e) {
				e.printStackTrace();			
			} finally {
				try {
					DbConnector.getInstancia().releaseConn();
			} catch (Exception e2) {e2.printStackTrace();}
			}
		return entrenadores;	
		}
		public Entrenador getEntrenadorDeUnEquipo(Equipo equipo) throws SQLException {
			String getEntrenadorDeUnEquipoStmt="select ent.dniEntrenador, ent.nombre,ent.apellido,ent.fechaNac"
					+ " from entrenador ent inner join equipo eq on eq.idEquipo=ent.idEquipo";
			Entrenador entrenador=new Entrenador();
			try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(getEntrenadorDeUnEquipoStmt);){
				ps.setInt(1, equipo.getIdEquipo());
				try(ResultSet rs=ps.executeQuery();){
					entrenador.setDni(rs.getString("ent.dniEntrenador"));
					entrenador.setNombre(rs.getString("ent.nombre"));
					entrenador.setApellido(rs.getString("ent.apellido"));
					entrenador.setFechaNacimiento(rs.getObject("ent.fechaNac",LocalDate.class));
				}
			}	catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					DbConnector.getInstancia().releaseConn();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
				}
			return entrenador;
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