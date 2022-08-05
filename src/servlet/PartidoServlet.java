package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Arbitro;
import entities.Cancha;
import entities.Equipo;
import entities.Partido;
import logic.ArbitroLogic;
import logic.CanchaLogic;
import logic.EquipoLogic;
import logic.PartidoLogic;
/**
 * Servlet implementation class PartidoControlador
 */
@WebServlet("/PartidoServlet")
public class PartidoServlet extends HttpServlet {
	String showFormAdd="partidoFormAdd.jsp";
	String administrar="partidoAdministrar.jsp"; //seleccion de partido editar o borrar
	String showFormEdit="partidoFormEdit.jsp"; // carga datos
	String showFormFecha="partidoFormFecha.jsp";
	String showFormReprog="reprogramarPartido.jsp";
	private static final long serialVersionUID = 1L;
    public PartidoServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("accion");
		switch(action) {
		case "formAdd": // muestro jsp para rellenar datos del nuevo jugador
			try
			{	
				addOReprogramacion(request, response);
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
				listPartidos(request, response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar la lista de Partidos, vuelva a intentarlo.");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}	
			break;
		case "formEdit": // preparo el jugador a editar y muestro la pagina jsp con sus datos
			try
			{	
				preparaPartidoEdit(request,response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar el partido seleccionado, vuelva a intentarlo.");
				request.getRequestDispatcher(administrar).forward(request, response);
			}	
			break;	
		case "formFechaAdd": // preparo el jugador a editar y muestro la pagina jsp con sus datos
			try
			{	
				formFechaAdd(request,response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al cargar el from fecha, vuelva a intentarlo.");
				request.getRequestDispatcher(administrar).forward(request, response);
			}	
			break;	
		case "formFechaReprogramar": // preparo el jugador a editar y muestro la pagina jsp con sus datos
			try
			{	
				formFechaReprogramar(request,response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al cargar el from fecha, vuelva a intentarlo.");
				request.getRequestDispatcher(administrar).forward(request, response);
			}	
			break;	
		default: // falta agregar mensaje de error
			break;
		}	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("accion");
		switch(action) {
		case "add": // muestro jsp para rellenar datos del nuevo jugador
			try
			{	
				addPartido(request, response);	
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
				updatePartido(request, response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al intentar actualizar el partido seleccionado, vuelva a intentarlo.");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}	
			break;
		case "delete": // preparo el jugador a editar y muestro la pagina jsp con sus datos
			try
			{	
				deletePartido(request,response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al intentar borrar el partido seleccionado, vuelva a intentarlo.");
				request.getRequestDispatcher(administrar).forward(request, response);
			}	
			break;	
		case "reprogramarPartido": // preparo el jugador a editar y muestro la pagina jsp con sus datos
			try
			{	
				partidoReprogramar(request,response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar el intetar reprogramar el partido seleccionado, vuelva a intentarlo.");
				request.getRequestDispatcher(administrar).forward(request, response);
			}	
			break;	
		default: // falta agregar mensaje de error
			break;
		}
	}
	private void listPartidos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PartidoLogic partidoL= new PartidoLogic();
		LinkedList<Partido> list = partidoL.getAll();
		request.setAttribute("listPartidos", list);
		request.getRequestDispatcher(administrar).forward(request, response);
	}
	private void preparaPartidoEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Partido partido=new Partido();
		Cancha cancha= new Cancha();
		EquipoLogic equipoL=new EquipoLogic();
		ArbitroLogic arbitroL=new ArbitroLogic();
		partido.setFecha(LocalDate.parse(request.getParameter("Fecha")));
		partido.setHora(LocalTime.parse(request.getParameter("Hora")));
		cancha.setNroCancha(Integer.parseInt(request.getParameter("nroC")));
		partido.setCancha(cancha);
		PartidoLogic partidoL= new PartidoLogic();
		partido=partidoL.getOne(partido);		
		LinkedList<Equipo>equiposDisp=equipoL.getEquiposDispPartido(partido); //lista de equipos que no juegen partidos en la fecha/hora ingresada, que tengan almenos 5 jugadores y un entrenador
		LinkedList<Arbitro>arbitrosDisp=arbitroL.getArbitrosDisp(partido);// lista de arbitros  que no estan ocupadoes en la fecha/hora ingresada	
		equiposDisp.add(partido.getEquipo1());// agrego equipo que ya estaba antes en el partido
		equiposDisp.add(partido.getEquipo2());// agrego equipo que ya estaba antes en el partido
		arbitrosDisp.add(partido.getArbitro());// agrego arbitro que yaestaba antes en el partido
		request.getSession().setAttribute("partido", partido);
		request.setAttribute("listEquipos",equiposDisp);
		request.setAttribute("listArbitros",arbitrosDisp);	
		request.getRequestDispatcher(showFormEdit).forward(request, response);
	}
	private void updatePartido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Partido partido= (Partido)request.getSession().getAttribute("partido");
		Equipo equipo1 =new Equipo();
		Equipo equipo2 =new Equipo();
		Arbitro arbitro=new Arbitro();
		PartidoLogic partidoL=new PartidoLogic();
		if(partido.getEquipo1().getIdEquipo()!=partido.getEquipo2().getIdEquipo())
		{
			partido.setIncidencias(request.getParameter("incidencias"));
			partido.setResultado(request.getParameter("resultado"));
			equipo1.setIdEquipo(Integer.parseInt(request.getParameter("equipoId1")));
			equipo2.setIdEquipo(Integer.parseInt(request.getParameter("equipoId2")));
			arbitro.setDni(request.getParameter("arbitroDni"));
			partido.setEquipo1(equipo1);
			partido.setEquipo2(equipo2);
			partido.setArbitro(arbitro);
			partidoL.update(partido);				
		}
		request.getSession().removeAttribute("partido");
		listPartidos(request, response);	
	}
	private void preparaPartidoAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EquipoLogic equipoL=new EquipoLogic();
		CanchaLogic canchaL=new CanchaLogic();
		ArbitroLogic arbitroL=new ArbitroLogic();
		Partido partido=(Partido)request.getSession().getAttribute("partido");
		partido.setFecha(LocalDate.parse(request.getParameter("Fecha")));
		partido.setHora(LocalTime.parse(request.getParameter("Hora")));
		LinkedList<Equipo>equiposDisp=equipoL.getEquiposDispPartido(partido); //lista de equipos que no juegen partidos en la fecha/hora ingresada, que tengan almenos 5 jugadores y un entrenador
		LinkedList<Cancha>canchasDisp=canchaL.getCanchasDisp(partido); //lista de canchas que no esten ocupadas en la fecha/hora ingresada
		LinkedList<Arbitro>arbitrosDisp=arbitroL.getArbitrosDisp(partido);// lista de arbitros  que no estan ocupadoes en la fecha/hora ingresada
		request.getSession().setAttribute("partido", partido); //fecha y hora del nuevo partido a agregar
		request.setAttribute("listEquipos",equiposDisp);
		request.setAttribute("listCanchas",canchasDisp);
		request.setAttribute("listArbitros",arbitrosDisp);		
		request.getRequestDispatcher(showFormAdd).forward(request, response);
	}
	private void addPartido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Partido partido=(Partido)request.getSession().getAttribute("partido");
		Equipo equipo1 =new Equipo();
		Equipo equipo2 =new Equipo();
		Cancha cancha=new Cancha();
		Arbitro arbitro=new Arbitro();
		PartidoLogic partidoL=new PartidoLogic();
		if(request.getParameter("equipoId1")!=request.getParameter("equipoId2"))
		{	
			cancha.setNroCancha(Integer.parseInt(request.getParameter("nroC")));
			equipo1.setIdEquipo(Integer.parseInt(request.getParameter("equipoId1")));
			equipo2.setIdEquipo(Integer.parseInt(request.getParameter("equipoId2")));
			arbitro.setDni(request.getParameter("arbitroDni"));
			partido.setCancha(cancha);
			partido.setEquipo1(equipo1);
			partido.setEquipo2(equipo2);
			partido.setArbitro(arbitro);	
			partidoL.add(partido);		
		}
		request.getSession().removeAttribute("partido");
		listPartidos(request, response);				
	}
	private void deletePartido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Partido partido= new Partido();
		Cancha cancha= new Cancha();
		PartidoLogic partidoL=new PartidoLogic();
		partido.setFecha(LocalDate.parse(request.getParameter("Fecha")));
		partido.setHora(LocalTime.parse(request.getParameter("Hora")));
		cancha.setNroCancha(Integer.parseInt(request.getParameter("nroC")));
		partido.setCancha(cancha);
		partidoL.delete(partido);
		listPartidos(request, response);
	}
	private void formFechaAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Partido partido= new Partido();
		partido.setResultado("");
		request.getSession().setAttribute("partido", partido);
		request.getRequestDispatcher(showFormFecha).forward(request, response);	
	}
	private void formFechaReprogramar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cancha cancha= new Cancha();
		Partido partido= new Partido();
		PartidoLogic partidoL=new PartidoLogic();
		partido.setFecha(LocalDate.parse(request.getParameter("Fecha")));
		partido.setHora(LocalTime.parse(request.getParameter("Hora")));
		cancha.setNroCancha(Integer.parseInt(request.getParameter("nroC")));
		partido.setCancha(cancha);
		partido=partidoL.getOne(partido);
		partido.setResultado("Reprogramado");
		request.getSession().setAttribute("partido", partido);
		request.getRequestDispatcher(showFormFecha).forward(request, response);	
	}
	private void preparaPartidoReprogramar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CanchaLogic canchaL=new CanchaLogic();
		ArbitroLogic arbitroL=new ArbitroLogic();
		EquipoLogic equipoL=new EquipoLogic();
		Partido partidoViejo=(Partido)request.getSession().getAttribute("partido");
		Partido partidoNuevo= new Partido();
		partidoNuevo.setFecha(LocalDate.parse(request.getParameter("Fecha")));
		partidoNuevo.setHora(LocalTime.parse(request.getParameter("Hora")));
		partidoNuevo.setEquipo1(partidoViejo.getEquipo1());
		partidoNuevo.setEquipo2(partidoViejo.getEquipo2());
		if(equipoL.dispParaReprogramar(partidoNuevo)) { // verifico si  equipo1 y equipo2 estan disponibles en la fecha/hora ingresada
			LinkedList<Cancha>canchasDisp=canchaL.getCanchasDisp(partidoNuevo); //lista de canchas que no esten ocupadas en la fecha/hora ingresada
			LinkedList<Arbitro>arbitrosDisp=arbitroL.getArbitrosDisp(partidoNuevo);// lista de arbitros  que no estan ocupadoes en la fecha/hora ingresada
			request.setAttribute("listcanchas",canchasDisp);
			request.setAttribute("listArbitros",arbitrosDisp);	
			request.getSession().setAttribute("PartidoNuevo", partidoNuevo); // fecha y hora pretendida para la reprogramacion
			request.getRequestDispatcher(showFormReprog).forward(request, response);
			return;
		}
		listPartidos(request, response);
	}
	private void partidoReprogramar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PartidoLogic  partidoL=new PartidoLogic();
		Cancha cancha= new Cancha();
		Partido partidoAReprogramar=(Partido)request.getSession().getAttribute("partido");
		Partido partidoNuevo= (Partido)request.getSession().getAttribute("PartidoNuevo");
		Arbitro arbitro=new Arbitro();
		cancha.setNroCancha(Integer.parseInt(request.getParameter("nroC")));
		arbitro.setDni(request.getParameter("arbitroDni"));
		partidoNuevo.setCancha(cancha);
		partidoNuevo.setArbitro(arbitro);
		partidoL.reprogramarPartido(partidoNuevo,partidoAReprogramar);
		request.getSession().removeAttribute("partido");
		request.getSession().removeAttribute("PartidoNuevo");
		listPartidos(request, response);
	}
	private void addOReprogramacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		Partido partido=(Partido)request.getSession().getAttribute("partido");
		if(partido.getResultado()=="Reprogramado") // verifica si estoy haciendo una reprogramacion o un add nuevo
			{preparaPartidoReprogramar(request,response);
			return;}
		preparaPartidoAdd(request,response);
	}
}