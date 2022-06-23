package entities;

import java.time.format.DateTimeFormatter;

public class Entrenador extends Persona {
	
		private Equipo equipo;
		private String dateFormat = "dd/MM/yyyy";
		
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
					+ (fechaNacimiento==null?null:fechaNacimiento.format(dFormat)) + "]\n";
		}		
}
