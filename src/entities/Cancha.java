package entities;
public class Cancha {
				
			private int nroCancha;
			private String nombre;
			public int getNroCancha() {
				return nroCancha;
			}
			public void setNroCancha(int nroCancha) {
				this.nroCancha = nroCancha;
			}
			public String getNombre() {
				return nombre;
			}
			public void setNombre(String nombre) {
				this.nombre = nombre;
			}

			@Override
			public String toString() {
				return "Cancha [nroCancha=" + nroCancha + ", nombre=" + nombre + "]\n";
			}
			
		
}
