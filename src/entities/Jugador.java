package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Jugador {
						
	private String dni;
	private String nombre;
	private String apellido;
	private  LocalDate fecha_nacimiento;
	private String dateFormat = "dd/MM/yyyy";
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

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
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
				+ (fecha_nacimiento==null?null:fecha_nacimiento.format(dFormat)) + ", posicion=" + posicion + ", goles=" + goles + ", asistencias=" + asistencias
				+ ", partidosJugados=" + partidosJugados + ", tarjetasA=" + tarjetasA + ", tarjetasR=" + tarjetasR
				+", Equipo "+ "]";
	}


	
}
