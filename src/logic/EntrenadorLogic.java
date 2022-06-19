package logic;
import java.sql.SQLException;
import java.util.LinkedList;

import data.DataEntrenador;
import entities.Entrenador;
import entities.Equipo;

public class EntrenadorLogic {
	private DataEntrenador dEntrenador;
	public EntrenadorLogic()
	{
		dEntrenador= new DataEntrenador();
	}

	public LinkedList<Entrenador> getAll()
	{
		return dEntrenador.getAll();
	}
	public Entrenador getOne(Entrenador e )
	{
		return dEntrenador.getOne(e);
	}
	public void add (Entrenador e)
	{
		dEntrenador.add(e);
	}
	public void delete(Entrenador e)
	{
		dEntrenador.delete(e);
	}
	public void update(Entrenador e )
	{
		dEntrenador.update(e);
	}
	public LinkedList<Entrenador> getEntrenadoresDisp()
	{
		return dEntrenador.getEntrenadoresDisp();
	}
	public Entrenador getEntrenadorDeUnEquipo(Equipo equipo) throws SQLException {
		return dEntrenador.getEntrenadorDeUnEquipo(equipo);
	}
	/*public void deleteDependency(Equipo equipo)          
	{
		dEntrenador.deleteDependency(equipo);
	}*/

	

}
