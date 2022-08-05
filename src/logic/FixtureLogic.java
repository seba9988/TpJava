package logic;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import entities.*;

public class FixtureLogic {

	//crea el fixture, de parametros recibe los equipos que participan en el fixture y la fecha desde la cual deben jugarse partidos, esta fecha debe ser lunes,
	//si la fecha no es lunes automaticamente se cambiara al proximo lunes
	//funciona con hasta 100 equipos, si hay  mas de 100 equipos van a comenzar a haber partidos mezclados con semanas que no les corresponde
	
	//se crea el fixture usando el metodo round robin
	//se auto genera fecha de lunes a viernes con horario de 13:00 a 21:00 y nro de cancha aleatoria
	//si la cantidad de equipos es menor a 50 se juega un partido cada 2 horas con un total de 5 partidos por dia
	//si la cantidad de equipos es mayor a 50 se jugan dos partidos cada 2 horas con un total de 10 partidos por dia
	//se verifica que no se repita la cancha cuando se juega doble partido por hora 
	public LinkedList <Partido> createFixture(LinkedList<Equipo> equipos, LocalDate fechaInicio){ 
			
		if(equipos.size()% 2 != 0)
		{
			Equipo equipoFalso= null; 
			equipos.add(equipoFalso);
		}

		int nroSemanas = equipos.size() - 1;
		boolean doblePartido=false; // bandera para saber si se juegan dos partidos por hora
		LocalDate fecha= fechaInicio;
		
		if(fecha.getDayOfWeek() != DayOfWeek.MONDAY) {
			fecha = calcProxLunes(fecha);
		}
		
		LocalTime hora=null; 	
		CanchaLogic canchaL=new CanchaLogic();
		LinkedList<Cancha>canchas=canchaL.getAll();
		LinkedList<Partido>partidos= new LinkedList<>();
		Equipo equipoFijo = equipos.get(0);
		
		equipos.remove(0); // remuevo el primer equipo ya que este es fijo y no rota	
		int nroEquipos = equipos.size();
		
		if(nroEquipos >50) {
			doblePartido=true;					
		}
			
		for (int semana = 0; semana < nroSemanas; semana++)
	    {
	        if(partidos.size()!=0) {
	        	fecha=calcProxLunes(fecha);
	        }
			hora=LocalTime.parse("13:00");
			Partido partidoACrear= new Partido();
			partidoACrear.setFecha(fecha);
			partidoACrear.setHora(hora);
			Cancha canchaRandom= calculaCanchaRandom(doblePartido, canchas, partidos);
	        int teamIndex = semana % nroEquipos;
	        Partido partido=new Partido(fecha,hora,null,null,equipos.get(teamIndex),equipoFijo,null,canchaRandom);
	        agregarPartido(partido,partidos,canchaRandom);// corregir esto aca esta el problema probablemente        
	        crearEmparejamiento(semana,nroEquipos,partidoACrear,partidos,equipos,canchas);
	    }
		System.out.println(partidos);
		return partidos;
	}
	
	private void agregarPartido(Partido partido,LinkedList<Partido>partidos,Cancha cancha) { //verifico que no se cree un partido con el equipo falso 	y agrego al listado si es asi
		
        if(partido.getEquipo1() != null && partido.getEquipo2() != null) { // verifico que no se cree un partido con el equipo falso 	    
            partidos.add(partido);
        } 	
	}
	
	private void crearEmparejamiento(int semana, int nroEquipos, Partido partidoACrear,LinkedList<Partido> partidos, LinkedList<Equipo>equipos,LinkedList<Cancha>canchas){  // faltaria agregar variable booleana horaDoble y objeto partido que solo tenga fecha/hora		
		LocalDate fecha=partidoACrear.getFecha();
		LocalTime hora=partidoACrear.getHora();
		int mitadSize= (nroEquipos+1)/2; // le sumo 1 ya que hay un equipo fijo que saque
		boolean doblePartido=false; // bandera para saber si se juegan dos partidos por hora
		if(nroEquipos >50) {
			doblePartido=true;					
		}
		for (int index = 1; index < mitadSize; index++) // empieza el index en 1 porque ya estoy asigne previamente un partido con el equipoFijo
        {               
        	Equipo primerEquipo=equipos.get((semana + index) % nroEquipos);
            Equipo segundoEquipo=equipos.get((semana + nroEquipos - index) % nroEquipos);
                              
            // verificar si alguno de los equipos es vacio, de ser asi pasar de largo
            if(primerEquipo != null && segundoEquipo != null) {  
            	hora=calculaHora(hora, fecha, doblePartido, partidos);
            	fecha=calculaFecha(hora, fecha, partidos.getLast());
                Cancha cancha=calculaCanchaRandom(doblePartido,canchas,partidos); // genera cancha random y verificar que las canchas sean diferentes si se jugan doble partidos por hora
                Partido partido= new Partido(fecha,hora,null,null,primerEquipo,segundoEquipo,null,cancha);
                agregarPartido(partido,partidos,cancha); 
            }        	           	       	                          
        }
	} 
					
	private LocalDate calcProxLunes(LocalDate fecha) {
		  return fecha.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		}
	
	private Cancha calculaCanchaRandom(boolean doblePartido,LinkedList<Cancha>canchas, LinkedList<Partido> partidos) {
		int min=0;
		int max=canchas.size()-1;
		int randomNum=ThreadLocalRandom.current().nextInt(min, max + 1);
		Cancha cancha=canchas.get(randomNum);
		if(doblePartido && partidos.size()!=0) {
			while(partidos.getLast().getCancha() == cancha) {
				randomNum=ThreadLocalRandom.current().nextInt(min, max + 1);
				cancha=canchas.get(randomNum);
        	} 
		} 		
		return cancha;
	}
	
	private LocalDate calculaFecha(LocalTime hora, LocalDate fecha, Partido ultimoPartido) { // avanzo al siguiente dia habil si actualmente la hora es 13h y el ultimo partido se jugo a las 21h
		
		LocalTime horaInicial=LocalTime.parse("13:00");
		LocalTime horaFinal=LocalTime.parse("21:00");
		
		int esHoraInicial =hora.compareTo(horaInicial); // 0 si son iguales
		int ultimaHora=horaFinal.compareTo(ultimoPartido.getHora()); // comparo si el ultimo partido se jugo a las 21 hs
	
		if(esHoraInicial == 0 && ultimaHora == 0){
			fecha = cambioFecha(fecha);	
		}
		
		return fecha;										
	}
	
	private LocalDate cambioFecha(LocalDate fecha) {
		
		if(fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
			return calcProxLunes(fecha);
		}
		return fecha.plusDays(1);
	}
	
	private LocalTime calculaHora(LocalTime hora,LocalDate fecha, boolean doblePartido, LinkedList<Partido> partidos) { // se verifica si la hora debe ser cambiada
		int horasIguales=1; // inicializo en 1 en caso de que no se hayan jugado dos partidos
		int esHoraFinal=1; // inicializo en 1 en caso de que no se hayan jugado partidos
		
		if(partidos.size()>1){ // verifico que se hayan jugado almenos 2 partidos
			horasIguales= partidos.getLast().getHora().compareTo(partidos.get(partidos.size()-2).getHora()); // comparo si la hora de los dos ultimos partidos son iguales, de ser asi se asgina 0
			esHoraFinal= partidos.getLast().getHora().compareTo(LocalTime.parse("21:00")); // compara si se el ultimo partido se jugo a las 21h, de ser asi se asigna 0
		} 
		
		if(doblePartido && horasIguales == 0) { // si se juegan 2 partidos por cada horario solo cambio de hora cuando se jueguen dos partidos seguidos a la misma hora
			return cambioHora(hora,esHoraFinal);		
		}      
		// si se juegan dos partidos por horario y no se repite dos veces la misma hora esta no se cambia
		// si el ultimo partido jugado tiene una fecha diferente de la actual quiere decir que se salteo un agregarEquipo
		// esto debido a que el equipo fijo jugo con el falso, en este caso no cambio de hora
		if(doblePartido || partidos.getLast().getFecha().compareTo(fecha)!= 0) { 
			return hora;
		}		
		
		return cambioHora(hora,esHoraFinal);	// si se juega un partido por horario siempre aumento la hora
	}
	
	private LocalTime cambioHora(LocalTime hora, int esHoraFinal) { // se aumenta la hora en 2 o se resetea a 13h si el ultimo partido fue a las 21
		
		if(esHoraFinal == 0) {
    		return LocalTime.parse("13:00");
    	} 
		return hora.plusHours(2);		
	}		
}
