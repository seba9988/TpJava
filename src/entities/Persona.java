package entities;

import java.time.LocalDate;

public class Persona {
	
	protected String dni;
	protected String nombre;
	protected String apellido;
	protected  LocalDate fechaNacimiento; 
	
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
		return fechaNacimiento;
	}
	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fechaNacimiento = fecha_nacimiento;
	}
}
