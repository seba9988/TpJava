package entities;

import java.time.format.DateTimeFormatter;

public class Jugador extends Persona {
						
	String dateFormat = "dd/MM/yyyy";
	private String posicion;
	private int goles;
	private int asistencias;
	private int partidosJugados;
	private int tarjetasA; // amarillas
	private int tarjetasR; // rojas
	private Equipo equipo;


	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public int getGoles() {
		return goles;
	}
	public void setGoles(int goles) {
		this.goles = goles;
	}
	public int getAsistencias() {
		return asistencias;
	}
	public void setAsistencias(int asistencias) {
		this.asistencias = asistencias;
	}
	public int getPartidosJugados() {
		return partidosJugados;
	}
	public void setPartidosJugados(int partidosJugados) {
		this.partidosJugados = partidosJugados;
	}
	public int getTarjetasA() {
		return tarjetasA;
	}
	public void setTarjetasA(int tarjetasA) {
		this.tarjetasA = tarjetasA;
	}
	public int getTarjetasR() {
		return tarjetasR;
	}
	public void setTarjetasR(int tarjetasR) {
		this.tarjetasR = tarjetasR;
	}
	@Override
	public String toString() {
		DateTimeFormatter dFormat = DateTimeFormatter.ofPattern(dateFormat);
		return "Jugador [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", fecha_nacimiento="
				+ (fechaNacimiento==null?null:fechaNacimiento.format(dFormat)) + ", posicion=" + posicion + ", goles=" + goles + ", asistencias=" + asistencias
				+ ", partidosJugados=" + partidosJugados + ", tarjetasA=" + tarjetasA + ", tarjetasR=" + tarjetasR
				+", Equipo "+ "]";
	}


	
}
