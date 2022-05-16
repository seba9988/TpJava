# TpJava
## Documentacion:

Sistema para administrar una liga de fútbol semi profesional

En el mismo se llevará registro de los equipos, jugadores y árbitros registrados en la liga.
Una vez sorteados los partidos, se asignarán árbitros, dejando disponible el fixture.
Permitirá también tener acceso a la tabla de posiciones, estadísticas y sanciones durante la liga.
El sistema notificará , vía mail, al DT encargado de cada equipo cuando un jugador deba cumplir una sanción, así también, a principios de cada mes, los partidos a disputar durante el mismo.
Por el mismo medio, notificará a un árbitro cuando se le designe para un partido.

Regularidad


| REQUERIMIENTO    | Listado de casos incluidos          |
| -------------    | ----------------------------------- |
| ABMC Simple      | Jugador - DT - Árbitro - Cancha     |
| ABMC Dependiente | Equipo - Partido                    |
| CU               | Asignar árbitro - Registrar Partido |
| Listado Complejo | Filtro por posicion de jugador      |
| Nivel de acceso  |  Administrador                      |


Aprobación directa

| REQUERIMIENTO    | Listado de casos incluidos             |
| -------------    | -----------------------------------    |
| ABMC Todos       | Jugador - DT - Árbitro - Cancha - Equipo - partido -...   |
| CU  Complejo     | Reprogramación Fecha - Generar Fixture |
| Listado Complejo | Filtro por posicion de jugador - Listar Máximos asistentes, goleadores - Jugadores sancionados        |
| Nivel de acceso  | Usuario - Administrador                |

