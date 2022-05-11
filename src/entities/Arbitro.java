package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Arbitro {
		private String dni;
		private String nombre;
		private String apellido;
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
		public void setFecha_nacimiento(LocalDate localDate) {
			this.fecha_nacimiento = localDate;
		}
		@Override
		public String toString() {
			DateTimeFormatter dFormat = DateTimeFormatter.ofPattern(dateFormat);
			return "Arbitro [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", fecha_nacimiento="
					+ (fecha_nacimiento==null?null:fecha_nacimiento.format(dFormat)) + "]\n";
		}
	
		

}
