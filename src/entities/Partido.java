package entities;

import java.time.*;

public class Partido {

            private LocalDate fecha;
            private LocalTime hora;
            private String resultado;
            private String incidencias;
            private Equipo equipo1;
            private Equipo equipo2;
            private Arbitro arbitro;
            private Cancha cancha;
            public LocalDate getFecha() {
                return fecha;
                
            }
            public void setFecha(LocalDate fecha) {
                this.fecha = fecha;
            }
            public LocalTime getHora() {
                return hora;
            }
            public void setHora(LocalTime hora) {
                this.hora = hora;
            }
            public String getResultado() {
                return resultado;
            }
            public void setResultado(String resultado) {
                this.resultado = resultado;
            }
            public String getIncidencias() {
                return incidencias;
            }
            public void setIncidencias(String incidencias) {
                this.incidencias = incidencias;
            }
            @Override
            public String toString() {
                return "Partido [fecha=" + fecha + ", hora=" + hora + ", resultado=" + resultado + ", incidencias="
                        + incidencias +", equipo 1"+this.equipo1.getNombre()+", equipo 2"+this.equipo2.getNombre()+ "]";
            }
            
            public Equipo getEquipo1() {
				return equipo1;
			}
			public void setEquipo1(Equipo equipo1) {
				this.equipo1 = equipo1;
			}
			public Equipo getEquipo2() {
				return equipo2;
			}
			public void setEquipo2(Equipo equipo2) {
				this.equipo2 = equipo2;
			}
			public Arbitro getArbitro() {
				return arbitro;
			}
			public void setArbitro(Arbitro arbitro) {
				this.arbitro = arbitro;
			}
			public Cancha getCancha() {
				return cancha;
			}
			public void setCancha(Cancha cancha) {
				this.cancha = cancha;
			}
			public Partido(LocalDate fecha, LocalTime hora, String resultado, String incidencias, Equipo equipo1, Equipo equipo2, Arbitro arbitro, Cancha cancha)
            {
                this.setArbitro(arbitro);
                this.setFecha(fecha);
                this.setHora(hora);
                this.setEquipo1(equipo1);
                this.setEquipo2(equipo2);
                this.setIncidencias(incidencias);
                this.setResultado(resultado);
                this.setCancha(cancha);
            }
            public Partido(LocalDate fecha, LocalTime hora, String resultado, String incidencias, int idEquipo1, int idEquipo2, int numCancha)
            {
                this.setArbitro(arbitro);
                this.setFecha(fecha);
                this.setHora(hora);
                this.setEquipo1(equipo1);
                this.setEquipo2(equipo2);
                this.setIncidencias(incidencias);
                this.setResultado(resultado);
                this.setCancha(cancha);
            }
            public Partido()
            {}
            
}