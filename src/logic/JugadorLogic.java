package logic;

import java.util.LinkedList;

import data.DataJugador;
import entities.Jugador;

public class JugadorLogic {
	private DataJugador dJugador;
	public JugadorLogic()
	{
		dJugador=new DataJugador();
	}	
	public LinkedList<Jugador> getAll()
	{
		return dJugador.getAll();
	}
	public LinkedList<Jugador> getAllByPosicion(Jugador j)
	{
		return dJugador.getAllByPosicion(j);
	}
	public Jugador getOne(Jugador j)
	{
		return dJugador.getOne(j);
	}
	public void add(Jugador j)
	{
		dJugador.add(j);
	}
	public void delete(Jugador j)
	{
		dJugador.delete(j);
	}
	public void update(Jugador j)
	{
		dJugador.update(j);
	}
	/*public void deleteDependency(Equipo equipo)
	{
		dJugador.deleteDependency(equipo);
	}*/

}
