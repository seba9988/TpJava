package entities;


public class Equipo {
	private Integer idEquipo;
	private String nombre;
	private String Localidad;
	private int puntaje;
	private int difGoles;
	public Integer getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLocalidad() {
		return Localidad;
	}
	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}
	
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public int getDifGoles() {
		return difGoles;
	}
	public void setDifGoles(int difGoles) {
		this.difGoles = difGoles;
	}
	@Override
	public String toString() {
		return "Equipo [idEquipo=" + idEquipo + ", nombre=" + nombre + ", Localidad=" + Localidad + ", puntaje=" + puntaje + ", difGoles=" + difGoles + "]";
		
	}
	
	

}
