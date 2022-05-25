package logic;

import java.util.LinkedList;

import data.DataArbitro;
import entities.Arbitro;
import entities.Partido;

public class ArbitroLogic {
	private DataArbitro dArbitro;
	public ArbitroLogic()
	{
		dArbitro= new DataArbitro();
	}
	public LinkedList<Arbitro> getAll () {
		return dArbitro.getAll();
	}
	public Arbitro getOne(Arbitro a) 
	{
		return dArbitro.getOne(a);
	}
	public void add(Arbitro a)
	{
		dArbitro.add(a);
	}
	public void delete(Arbitro a)
	{
		dArbitro.delete(a);
	}
	public void update(Arbitro a)
	{
		dArbitro.update(a);
	}
	public LinkedList<Arbitro> getArbitrosDisp(Partido partido)
	{
		return dArbitro.getArbitrosDisp(partido);
	}
}
