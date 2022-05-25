package logic;


import java.util.LinkedList;

import data.DataPartido;
import entities.Partido;

public class PartidoLogic {
	
	private DataPartido dPartido;
	public PartidoLogic()
	{
		dPartido= new DataPartido();
	}
	public LinkedList<Partido> getAll () {
		return dPartido.getAll();
	}
	public Partido getOne(Partido p)
	{	
		return dPartido.getOne(p);	
	}
	public void add (Partido p)
	{
		 dPartido.add(p);
	}
	public void delete(Partido p)
	{
		 dPartido.delete(p);
	}
	public void update(Partido p)
	{
		dPartido.update(p);
	}
	public void reprogramarPartido(Partido partidoNuevo, Partido partidoAReprogramar)
	{
		dPartido.reprogramarPartido(partidoNuevo, partidoAReprogramar);
	}
}


