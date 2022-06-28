package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;

import entities.Arbitro;
import entities.Partido;


public class DataArbitro {

	public LinkedList<Arbitro> getAll(){	
		String getAllStatement="select arb.dniArbitro, arb.nombre, arb.apellido,arb.fechaNac from arbitro arb where fecha_baja is null";
		LinkedList<Arbitro> arbitros= new LinkedList<>();	
		try(Statement stm=DbConnector.getInstancia().getConn().createStatement();
			ResultSet rs=stm.executeQuery(getAllStatement);) {				
			while (rs.next()) {	
				Arbitro a=new Arbitro();
				a.setDni(rs.getString("dniArbitro"));
				a.setNombre(rs.getString("nombre"));
				a.setApellido(rs.getString("apellido"));
				a.setFechaNacimiento(rs.getObject("fechaNac",LocalDate.class));		
				arbitros.add(a);
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
	return arbitros;
	}
	public Arbitro getOne(Arbitro a) {
		String getOneStatement="select arb.dniArbitro, arb.nombre, arb.apellido,arb.fechaNac from arbitro arb where fecha_baja is null and arb.dniArbitro=?";	
		Arbitro arbitro = new Arbitro();
	    try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(getOneStatement);) {
			ps.setString(1, a.getDni());
			try(ResultSet rs=ps.executeQuery(); ){
				while (rs.next()) {			
		        	arbitro.setDni(rs.getString("arb.dniArbitro"));
		        	arbitro.setNombre(rs.getString("arb.nombre"));
		        	arbitro.setApellido(rs.getString("arb.apellido"));
		        	arbitro.setFechaNacimiento(rs.getObject("arb.fechaNac",LocalDate.class));
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
	return arbitro;
	}
	public void add (Arbitro a) {  
			String addStatement="insert into arbitro(dniArbitro,nombre,apellido,fechaNac) values (?,?,?,?)";
	        try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(addStatement);) {
	    		ps.setString(1, a.getDni());
	    		ps.setString(2, a.getNombre());
				ps.setString(3,a.getApellido());
				ps.setObject(4,a.getFechaNacimiento());    
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
	public void delete(Arbitro a) { // soft delete
			String deleteStatement="update arbitro arb set arb.fecha_baja=CURRENT_DATE where arb.dniArbitro not in(select distinct arb.dniArbitro from partido pa inner join arbitro arb on arb.dniArbitro=pa.dniArbitro WHERE PA.fecha>= CURRENT_DATE and arb.dniArbitro=?) and arb.dniArbitro=?";			
		    try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(deleteStatement);) {
				ps.setString(1, a.getDni());
				ps.setString(2, a.getDni());
				ps.executeUpdate();  				        
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }	
	}
	public void update (Arbitro a) {		   
		String updateStatement="update arbitro set nombre=?, apellido=?, fechaNac=? where dniArbitro=?";	
	    try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(updateStatement)) {
			ps.setString(1, a.getNombre());
			ps.setString(2,a.getApellido());
			ps.setObject(3,a.getFechaNacimiento());
			ps.setString(4,a.getDni());
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
	public LinkedList<Arbitro> getArbitrosDisp(Partido partido) { // obtiene un listado de los arbitros disponibles en la fecha/hora indicada
		String getArbitrosDispStatement="select arb.dniArbitro, arb.nombre, arb.apellido,arb.fechaNac from arbitro arb"
				+ " WHERE arb.dniArbitro not IN"
				+ " (select DISTINCT arb.dniArbitro from arbitro arb"
				+ " inner join partido pa on arb.dniArbitro= pa.dniArbitro"
				+ " WHERE PA.fecha=? and pa.hora=?)"
				+ " and arb.fecha_baja is null;";	
		LinkedList<Arbitro>arbitrosDisp=new LinkedList<>();
	    try(PreparedStatement ps=DbConnector.getInstancia().getConn().prepareStatement(getArbitrosDispStatement);) {
	    	ps.setObject(1, partido.getFecha());
			ps.setObject(2, partido.getHora());
			try(ResultSet rs=ps.executeQuery();){
				while (rs.next()) {
					Arbitro arbitro = new Arbitro();
		        	arbitro.setDni(rs.getString("arb.dniArbitro"));
		        	arbitro.setNombre(rs.getString("arb.nombre"));
		        	arbitro.setApellido(rs.getString("arb.apellido"));
		        	arbitro.setFechaNacimiento(rs.getObject("arb.fechaNac",LocalDate.class));
		        	arbitrosDisp.add(arbitro);
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
	return arbitrosDisp;
	}
}