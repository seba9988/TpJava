package entities;

import java.time.format.DateTimeFormatter;

public class Arbitro extends Persona {
		private String dateFormat = "dd/MM/yyyy";
		@Override
		public String toString() {
			DateTimeFormatter dFormat = DateTimeFormatter.ofPattern(dateFormat);
			return "Arbitro [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", fecha_nacimiento="
					+ (fecha_nacimiento==null?null:fecha_nacimiento.format(dFormat)) + "]\n";
		}
	
		

}
