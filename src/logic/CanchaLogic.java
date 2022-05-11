package logic;

import java.util.LinkedList;

import data.DataCancha;
import entities.Cancha;

public class CanchaLogic {
	private DataCancha dCancha;
	public CanchaLogic()
	{
		dCancha= new DataCancha();
	}

	public LinkedList<Cancha> getAll () {
		return dCancha.getAll();
	}
	public Cancha getOne (Cancha c)
	{
		return dCancha.getOne(c);
	}
	public void add(Cancha c)
	{
		dCancha.add(c);
	}
	public void delete (Cancha c)
	{
		dCancha.delete(c);
	}
	public void update(Cancha c)
	{
		dCancha.update(c);
	}

}
