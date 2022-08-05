package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Arbitro;
import entities.Equipo;
import entities.Jugador;
import entities.Partido;
import logic.ArbitroLogic;
import logic.EquipoLogic;
import logic.JugadorLogic;
import logic.PartidoLogic;

@WebServlet("/FixtureServlet")
public class FixtureServlet extends HttpServlet {
	String showFormCreate="fixtureCreate.jsp";
	String administrar="fixtureAdministrar.jsp";
	private static final long serialVersionUID = 1L;
    
    public FixtureServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("accion");
		switch(action) {
		case "formCreate": // muestro jsp para seleccionar equipos que participan en el fixture
			try
			{	
				preparaFormCreate(request, response);	
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error, vuelva a intentarlo.");
				request.getRequestDispatcher(administrar).forward(request, response);
			}	
			break;
		case "administrar": // muestro lista de jugadores para seleccionar si quiero editar/eliminar/agregar algunos 
			try
			{	
				listFixture(request, response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar el fixture, vuelva a intentarlo.");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}	
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("accion");	
		switch(action) {
		case "create": // muestro jsp para rellenar datos del nuevo jugador
			try
			{	
				createFixture(request, response);	
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error, vuelva a intentarlo.");
				request.getRequestDispatcher(administrar).forward(request, response);
			}	
			break;
		case "delete": // preparo el jugador a editar y muestro la pagina jsp con sus datos
			try
			{	
				deleteFixture(request,response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar el fixture, vuelva a intentarlo.");
				request.getRequestDispatcher(administrar).forward(request, response);
			}	
			break;	
		default: // falta agregar mensaje de error
			break;
		}
	}
	private void listFixture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PartidoLogic partidoL= new PartidoLogic();
		LinkedList<Partido> listFixture = partidoL.getFixture();
		if(listFixture.size()!=0) {
			// buscar todos los equipos ordenados por puntos, top goleadores, top asistencias, top tarjetas rojas y amarillas
			EquipoLogic equipoL= new EquipoLogic();
			JugadorLogic jugadorL=new JugadorLogic();
			LinkedList<Equipo>equiposOrdenPuntaje=equipoL.getAllByPuntaje(); // traigo todos los equipos del fixture ordenados por puntaje
			LinkedList<Jugador>jugadoresTopGoles=jugadorL.getTopByGoles(); // busco top 7 jugadores con mayores goles del fixture
			LinkedList<Jugador>jugadoresTopAsistencias=jugadorL.getTopByAsistencias();// busco top 7 jugadores con mayores asistencias del fixture
			LinkedList<Jugador>jugadoresTopTarjetaA=jugadorL.getTopByTarjetasA(); // busco top 7 jugadores con mayores tarjetas amarillas del fixture
			LinkedList<Jugador>jugadoresTopTarjetaR=jugadorL.getTopByTarjetasR(); // busco top 7 jugadores con mayores tarjetas rojas del fixture
			request.setAttribute("listFixture", listFixture);
			request.setAttribute("listEquiposPuntaje", equiposOrdenPuntaje);
			request.setAttribute("listTopGoles", jugadoresTopGoles);
			request.setAttribute("listTopAsistencias", listFixture);
			request.setAttribute("listTopTarjetasA", listFixture);
			request.setAttribute("listTopTarjetasR", listFixture);
		}
		request.getRequestDispatcher(administrar).forward(request, response);
	}
	private void createFixture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Equipo> equiposFixture= request.getParameter("listEquipos"); // considerar si veriticar aca la cantidad de equipos sea >5 o al clickear boton crearFixture
		// posible verificacion
		PartidoLogic partidoL= new PartidoLogic();
		LinkedList<Partido> partidosFixture =partidoL.createFixture(equiposFixture);
		partidoL.addFixture(partidosFixture); // guarda fixture y borra status de los jugadores y equipos como puntaje, goles, asistencias, ect
		// posible verificacion para saber si se guardo con exito el fixture
		listFixture(request, response);
	}
	private void preparaFormCreate(HttpServletRequest request, HttpServletResponse response) {
		EquipoLogic equipoL= new EquipoLogic();
		LinkedList<Equipo> equiposDisp= equipoL.getEquiposDispFixture(); // busca equipos disponibles para participar en un fixture que tengan entrenador, y almenos 5 jugadores
		request.setAttribute("equipos", equiposDisp);
		request.getRequestDispatcher(showFormCreate).forward(request, response);			
	}

}
