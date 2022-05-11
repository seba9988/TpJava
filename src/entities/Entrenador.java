package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Entrenador {
		private String dni;
		private String nombre;
		private String apellido;
		private Equipo equipo;
		private  LocalDate fecha_nacimiento; 
		private String dateFormat = "dd/MM/yyyy";
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
		public Equipo getEquipo() {
			return equipo;
		}
		public void setEquipo(Equipo equipo) {
			this.equipo = equipo;
		}

		@Override
		public String toString() {
			DateTimeFormatter dFormat = DateTimeFormatter.ofPattern(dateFormat);
			return "Entrenador [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", fecha_nacimiento="
					+ (fecha_nacimiento==null?null:fecha_nacimiento.format(dFormat)) + "]\n";
		}
	
		
	

}
