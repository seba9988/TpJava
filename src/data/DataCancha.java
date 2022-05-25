package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Cancha;
import entities.Partido;

public class DataCancha {
		public LinkedList<Cancha> getAll(){	
			LinkedList<Cancha> canchas= new LinkedList<>();	
			String getAllStatement="Select numCancha, nombre from cancha where fecha_baja is null";
			try(Statement stm = DbConnector.getInstancia().getConn().createStatement();
				ResultSet rs= stm.executeQuery(getAllStatement);) {		
				while (rs.next()) {
					Cancha c=new Cancha();
					c.setNroCancha(rs.getInt("numCancha"));
					c.setNombre(rs.getString("nombre"));				
					canchas.add(c);			
				}				
			}
			catch (SQLException e) {
				e.printStackTrace();
			} 
			finally {
				try {	
					DbConnector.getInstancia().releaseConn();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return canchas;
		}
		public Cancha getOne(Cancha cancha) {
			String getOneStatement="Select numCancha, nombre from cancha where numCancha=? and fecha_baja is null";
		    try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(getOneStatement);) {
				ps.setInt(1, cancha.getNroCancha());
				try(ResultSet rs=ps.executeQuery();){
					while (rs.next()) {	
						cancha.setNroCancha(rs.getInt("numCancha"));
						cancha.setNombre(rs.getString("nombre"));
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
		return cancha;
		}
		public void add (Cancha c) {  
				String addStatement="insert into cancha(numCancha,nombre) values (?,?)";	    		
		        try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(addStatement);) {
		    		ps.setInt(1, c.getNroCancha());
		    		ps.setString(2, c.getNombre());     
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
		public void delete(Cancha c) {
				String deleteStatement="update cancha ca set ca.fecha_baja=CURRENT_DATE where ca.numCancha not in(select distinct ca.numCancha from partido pa inner join cancha ca on ca.numCancha=pa.numCancha WHERE PA.fecha>= CURRENT_DATE and ca.numCancha=?) and ca.numCancha=?";		    	
			    try(PreparedStatement ps= DbConnector.getInstancia().getConn().prepareStatement(deleteStatement);) {
					ps.setInt(1, c.getNroCancha());
					ps.setInt(2, c.getNroCancha());
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
		public void update (Cancha c) {		   
			String updateStatement="update cancha set nombre=? where numCancha=? and fecha_baja is null";
		    try (PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(updateStatement);){
				ps.setString(1, c.getNombre());
				ps.setInt(2,c.getNroCancha());
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
		public LinkedList<Cancha> getCanchasDisp(Partido partido) {
			String getCanchaDispStatement="select cancha.numCancha,cancha.nombre from cancha WHERE cancha.numCancha not IN (select DISTINCT cancha.numCancha from cancha inner join partido pa on cancha.numCancha= pa.numCancha WHERE PA.fecha=? and pa.hora=?) and cancha.fecha_baja is null;";
			LinkedList<Cancha>canchasDisp=new LinkedList<>();
		    try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(getCanchaDispStatement);) {
				ps.setObject(1, partido.getFecha());
				ps.setObject(2, partido.getHora());
				try(ResultSet rs=ps.executeQuery();){
					while (rs.next()) {	
						Cancha cancha=new Cancha();
						cancha.setNroCancha(rs.getInt("numCancha"));
						cancha.setNombre(rs.getString("nombre"));
						canchasDisp.add(cancha);
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
		return canchasDisp;
		}		
	}