package logic;

import java.util.LinkedList;

import data.DataEquipo;
import entities.Equipo;

public class EquipoLogic {
	private DataEquipo dEquipo;
	public EquipoLogic()
	{
		dEquipo= new DataEquipo();
	}
	public LinkedList<Equipo> getAll()
	{
		return dEquipo.getAll();
	}
	public Equipo getOne(Equipo e) 
	{
		return dEquipo.getOne(e);
	}
	public void add(Equipo e)
	{
		dEquipo.add(e);
	}
	public void delete(Equipo e)
	{
		dEquipo.delete(e);
	}
	public void update (Equipo e)
	{
		dEquipo.update(e);
	}


}
