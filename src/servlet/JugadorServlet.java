package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataEquipo;
import data.DataJugador;
import entities.Equipo;
import entities.Jugador;

/**
 * Servlet implementation class Filtro
 */
@WebServlet("/JugadorControl")
public class JugadorServlet extends HttpServlet {
	
	
	String listar = "Jugador-Listar.jsp";
	String add="Jugador-Add.jsp";
	String modif="Jugador-Modif.jsp";
	String edit="Jugador-Edit.jsp";
	Jugador j = new Jugador();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JugadorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acceso="";
		String action=request.getParameter("accion");
		
		
		if (action.equalsIgnoreCase("listarj")){
			preparalist(request, response);
			response.sendRedirect(listar);
		}else if(action.equalsIgnoreCase("filtro")) {
		
	
		String posicion = request.getParameter("filtro");
		LinkedList<Jugador> filtro = new LinkedList<Jugador>();
		
		
		DataJugador jugador=new DataJugador();
		LinkedList<Jugador>list=jugador.getall();
		Jugador juga=null;
		
		if (posicion==null) {
			filtro.addAll(list);
		}else {
		if (posicion.equalsIgnoreCase("todos")) {
			filtro.addAll(list);
		}
		else {
		for(Jugador listJ : list) {
			if (listJ.getPosicion().equalsIgnoreCase(posicion)) {
			 filtro.add(listJ);
			}
		}}
		request.getSession().setAttribute("lista", filtro);
		response.sendRedirect(listar);}
		}
		
		if(action.equalsIgnoreCase("add")) {
			response.sendRedirect(add);
		}
		
		if(action.equalsIgnoreCase("agregar")) {
			String dni=request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			LocalDate fechaNac = LocalDate.parse(request.getParameter("fechaNac"));
			String	posicion = request.getParameter("posicion");
			String	goles = request.getParameter("goles");
			String	asistencias = request.getParameter("asistencias");
			String	tarjA = request.getParameter("tarjA");
			String	tarjR = request.getParameter("tarjR");
			String	partidosJ = request.getParameter("partidosJ");
			j.setDni(Integer.parseInt(dni));
			j.setNombre(nombre);
			j.setApellido(apellido);
			j.setFecha_nacimiento(fechaNac);
			j.setPosicion(posicion);
			j.setGoles(Integer.parseInt(goles));
			j.setAsistencias(Integer.parseInt(asistencias));
			j.setTarjetasA(Integer.parseInt(tarjA));
			j.setTarjetasR(Integer.parseInt(tarjR));
			j.setPartidosJugados(Integer.parseInt(partidosJ));
			if(DataJugador.add(j)) {
				preparalist(request, response);
				response.sendRedirect(listar);
			} else {
				request.setAttribute("msg", "No se pudo cargar Equipo Vuelva a intentarlo");
				RequestDispatcher rd= request.getRequestDispatcher("Jugador-Add.jsp");
				rd.forward(request, response);
			}
			
		}
		
		if(action.equalsIgnoreCase("modif")) {
			DataJugador Jugador = new DataJugador();
			LinkedList<Jugador> list = Jugador.getall();
			request.getSession().setAttribute("lista", list);
			response.sendRedirect(modif);
		}
		if(action.equalsIgnoreCase("editar")) {
			int dni=Integer.parseInt((String) request.getParameter("dni"));
			DataJugador djugador= new DataJugador();
			Jugador j=(Jugador)djugador.getOne(dni);
			request.getSession().setAttribute("jugador", j);
			response.sendRedirect(edit);
		}
		if(action.equalsIgnoreCase("Actualizar")) {
			int dni=Integer.parseInt(request.getParameter("dni"));
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			LocalDate fechaNac = LocalDate.parse(request.getParameter("fechaNac"));
			String	posicion = request.getParameter("posicion");
			String	goles = request.getParameter("goles");
			String	asistencias = request.getParameter("asistencias");
			String	tarjA = request.getParameter("tarjA");
			String	tarjR = request.getParameter("tarjR");
			String	partidosJ = request.getParameter("partidosJ");
			j.setDni(dni);
			j.setNombre(nombre);
			j.setApellido(apellido);
			j.setFecha_nacimiento(fechaNac);
			j.setPosicion(posicion);
			j.setGoles(Integer.parseInt(goles));
			j.setAsistencias(Integer.parseInt(asistencias));
			j.setTarjetasA(Integer.parseInt(tarjA));
			j.setTarjetasR(Integer.parseInt(tarjR));
			j.setPartidosJugados(Integer.parseInt(partidosJ));
			DataJugador.update(j);
			preparalist(request, response);
			response.sendRedirect(listar);
		}
		if(action.equalsIgnoreCase("eliminar")) {
			int dni=Integer.parseInt(request.getParameter("dni"));
			j.setDni(dni);
			DataJugador.delete(j);
			preparalist(request, response);
			response.sendRedirect(listar);
		}
		
		doGet(request, response);
	}
	
	private void preparalist(HttpServletRequest request, HttpServletResponse response) {
		DataJugador Jugador = new DataJugador();
		LinkedList<Jugador> list = Jugador.getall();
		request.getSession().setAttribute("lista", list);
	}

}
