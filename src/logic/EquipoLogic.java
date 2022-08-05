package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataEquipo;
import entities.Entrenador;
import entities.Equipo;
import entities.Partido;

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
	public void add(Equipo equipo,Entrenador entrenador)
	{
		int equipoId=dEquipo.add(equipo);
		if((entrenador.getDni()!=null) && equipoId!=0)
		{
			EntrenadorLogic entrenadorL=new EntrenadorLogic();
			entrenador=entrenadorL.getOne(entrenador);
			equipo.setIdEquipo(equipoId);
			entrenador.setEquipo(equipo); //Al entrenador le agrego el equipo al que ahora pertenece	
			entrenadorL.update(entrenador); // que hacer si falla el update??
		}			
	}
	public void delete(Equipo e) throws SQLException
	{
		dEquipo.delete(e);
	}
	public void update (Equipo e)
	{
		dEquipo.update(e);
	}
	public LinkedList<Equipo>  getEquiposDispPartido(Partido p)
	{
		return dEquipo.getEquiposDispPartido(p);
	}
	public boolean dispParaReprogramar(Partido partido) 
	{
		return dEquipo.dispParaReprogramar(partido);
	}
}
