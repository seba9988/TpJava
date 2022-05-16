package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Equipo;
import entities.Jugador;
import logic.JugadorLogic;

/**
 * Servlet implementation class Filtro
 */
@WebServlet("/JugadorServlet")
public class JugadorServlet extends HttpServlet {
	String showFormAdd="jugadorFormAdd.jsp";
	String administrar="jugadorAdministrar.jsp";
	String showFormEdit="jugadorFormEdit.jsp";
	private static final long serialVersionUID = 1L;      
    public JugadorServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("accion");
		switch(action) {
		case "formAdd": // muestro jsp para rellenar datos del nuevo jugador
			try
			{	
				request.getRequestDispatcher(showFormAdd).forward(request, response);	
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
				listJugadores(request, response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar la lista de Equipos, vuelva a intentarlo.");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}	
			break;
		case "formEdit": // preparo el jugador a editar y muestro la pagina jsp con sus datos
			try
			{	
				preparaJugadorEdit(request,response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar el Equipo seleccionado, vuelva a intentarlo.");
				request.getRequestDispatcher(administrar).forward(request, response);
			}	
			break;	
		case "filtro":
			try
			{
				listJugadoresByPos(request,response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar el Equipo seleccionado, vuelva a intentarlo.");
				request.getRequestDispatcher(administrar).forward(request, response);
			}	
			break;	
		default: // falta agregar mensaje de error
			break;
		}	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("accion");	
		switch(action) {
		case "add": // muestro jsp para rellenar datos del nuevo jugador
			try
			{	
				addJugador(request, response);	
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error, vuelva a intentarlo.");
				request.getRequestDispatcher(administrar).forward(request, response);
			}	
			break;
		case "update": // muestro lista de jugadores para seleccionar si quiero editar/eliminar/agregar algunos 
			try
			{	
				updateJugador(request, response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar la lista de Equipos, vuelva a intentarlo.");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}	
			break;
		case "delete": // preparo el jugador a editar y muestro la pagina jsp con sus datos
			try
			{	
				deleteJugador(request,response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar el Equipo seleccionado, vuelva a intentarlo.");
				request.getRequestDispatcher(administrar).forward(request, response);
			}	
			break;	
		default: // falta agregar mensaje de error
			break;
		}				
	}	
	private void listJugadores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JugadorLogic jugadorL= new JugadorLogic();
		LinkedList<Jugador> list = jugadorL.getAll();
		request.setAttribute("listJugadores", list);
		request.getRequestDispatcher(administrar).forward(request, response);
	}
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void listJugadoresByPos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Jugador jugador= new Jugador();
		JugadorLogic jugadorL= new JugadorLogic();
		jugador.setPosicion(request.getParameter("filtrobypos"));
		LinkedList<Jugador> list = jugadorL.getAllByPosicion(jugador);
		request.getSession().setAttribute("listJugadores", list);
		request.getRequestDispatcher(administrar).forward(request, response);
	}
	private void preparaJugadorEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Jugador jugador=new Jugador();
		jugador.setDni(request.getParameter("dni"));
		JugadorLogic jugadorL= new JugadorLogic();
		jugador=jugadorL.getOne(jugador);
		request.setAttribute("jugador", jugador);
		request.getRequestDispatcher(showFormEdit).forward(request, response);
	}
	private void addJugador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Jugador jugador= new Jugador();
		JugadorLogic jugadorL= new JugadorLogic();
		jugador.setDni(request.getParameter("dni"));
		jugador.setNombre(request.getParameter("nombre"));
		jugador.setApellido(request.getParameter("apellido"));
		jugador.setFecha_nacimiento(LocalDate.parse(request.getParameter("fechaNac")));
		jugador.setPosicion(request.getParameter("posicion"));
		jugador.setGoles(Integer.parseInt(request.getParameter("goles")));
		jugador.setAsistencias(Integer.parseInt(request.getParameter("asistencias")));
		jugador.setTarjetasA(Integer.parseInt( request.getParameter("tarjA")));
		jugador.setTarjetasR(Integer.parseInt(request.getParameter("tarjR")));
		jugador.setPartidosJugados(Integer.parseInt(request.getParameter("partidosJ")));
		jugadorL.add(jugador);
		listJugadores(request, response);
	}
	private void updateJugador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Jugador jugador= new Jugador();
		JugadorLogic jugadorL= new JugadorLogic();
		Equipo equipo= new Equipo();
		jugador.setDni(request.getParameter("dni"));
		jugador.setNombre(request.getParameter("nombre"));
		jugador.setApellido(request.getParameter("apellido"));
		jugador.setFecha_nacimiento(LocalDate.parse(request.getParameter("fechaNac")));
		jugador.setPosicion(request.getParameter("posicion"));
		jugador.setGoles(Integer.parseInt(request.getParameter("goles")));
		jugador.setAsistencias(Integer.parseInt(request.getParameter("asistencias")));
		jugador.setTarjetasA(Integer.parseInt( request.getParameter("tarjA")));
		jugador.setTarjetasR(Integer.parseInt(request.getParameter("tarjR")));
		jugador.setPartidosJugados(Integer.parseInt(request.getParameter("partidosJ")));
		equipo.setIdEquipo(Integer.parseInt(request.getParameter("idEquipo")));
		jugador.setEquipo(equipo);
		jugadorL.update(jugador);	
		listJugadores(request, response);
	}
	private void deleteJugador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Jugador jugador= new Jugador();
		JugadorLogic jugadorL=new JugadorLogic();
		jugador.setDni(request.getParameter("dni"));
		jugadorL.delete(jugador);
		listJugadores(request, response);
	}
}