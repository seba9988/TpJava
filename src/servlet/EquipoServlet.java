package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import data.DataEquipo;
import entities.Entrenador;
import entities.Equipo;
import logic.EntrenadorLogic;
import logic.EquipoLogic;

/**
 * Servlet implementation class SrvEquipo
 */
@WebServlet("/EquipoServlet")
public class EquipoServlet extends HttpServlet {
	String showFormAdd="equipoFormAdd.jsp";
	String administrar="equipoAdministrar.jsp";
	String showFormEdit="equipoFormEdit.jsp";
	private static final long serialVersionUID = 1L;
    public EquipoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("accion");
		switch(action) {
		case "formAdd": // muestro jsp para rellenar datos del nuevo equipo
			try
			{	
				listEntreDisp(request, response);	
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error, vuelva a intentarlo.");
				request.getRequestDispatcher(administrar).forward(request, response);
			}	
			break;
		case "Administrar": // muestro lista de equipos para seleccionar si quiero editar/eliminar/agregar algunos cambiar nombre de modif por otro mejor
			try
			{	
				listEquipos(request, response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar la lista de Equipos, vuelva a intentarlo.");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}	
			break;
		case "formEdit": // preparo el equipo a editar y muestro la pagina jsp con sus datos
			try
			{	
				preparaEquipoEdit(request,response);
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
		switch (action) {
		case "add": // realizo el insert del equipo+dependencia de entrenador si se eligio alguno
			try
			{	
				addEquipo(request,response);		
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error, no se pudo agregar al equipo, vuelva a intentarlo");
				request.getRequestDispatcher(administrar).forward(request, response);
			}	
			break;
		case "update": // realizo el update
			try 
			{
				updateEquipo(request,response);
			}
			catch(Exception e )
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error, no se pudo actualizar al equipo, vuelva a intentarlo.");
				request.getRequestDispatcher(administrar).forward(request, response);
				
			}	
			break;
		case "delete": //realizo el delete de las dependencias de entrenador y jugador para luego eliminar a equipo
			try 
			{
				eliminarEquipo(request,response);
			}
			catch(SQLException e )
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error o el equipo todavia tiene partidos por jugar, vuelva a intentarlo.");
				System.out.println("Ocurrio un error o el equipo todavia tiene partidos por jugar, vuelva a intentarlo.");
				listEquipos(request, response);			
			}	
			break;
		default: // falta agregar mensaje de error
			break;
		}		
	}
private void listEquipos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataEquipo equipo = new DataEquipo();
		LinkedList<Equipo> list = equipo.getAll();
		request.setAttribute("listEquipos", list);
		request.getRequestDispatcher(administrar).forward(request, response);
	}
private void listEntreDisp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // lista de entrenadores que no tengan equipo
	EntrenadorLogic entrenadorL= new EntrenadorLogic();
	LinkedList <Entrenador>listEntrenadores=entrenadorL.getEntrenadoresDisp();
	request.setAttribute("listEntreDisp", listEntrenadores); // muestra los partidos que no tengan resultado
	request.getRequestDispatcher(showFormAdd).forward(request, response);
}
private void addEquipo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException { // deberia hacer un metodo updateDependency enentrenador para evitar el getOne?
	Equipo equipo = new Equipo();
	Entrenador entrenadorAbuscar= new Entrenador();	
	entrenadorAbuscar.setDni(request.getParameter("EntrenadorDni")); // comprobar si es nulo
	EntrenadorLogic entrenadorL=new EntrenadorLogic();	
	EquipoLogic equipoL= new EquipoLogic();
	Entrenador entrenador=entrenadorL.getOne(entrenadorAbuscar);
	equipo.setIdEquipo(Integer.parseInt(request.getParameter("id"))); // comrpobar si es nulo, podria guardarlo previamente en un wrapper Integar y ver si ese wrapper Integer es nulo
	equipo.setNombre(request.getParameter("nombre"));
	equipo.setLocalidad(request.getParameter("localidad"));
	equipo.setPuntaje(Integer.parseInt(request.getParameter("puntaje"))); // mismo que id
	equipo.setDifGoles(Integer.parseInt( request.getParameter("difGol"))); // mismo que id
	entrenador.setEquipo(equipo);	//Al entrenador le agrego el equipo al que ahora pertenece				
	equipoL.add(equipo);
	entrenadorL.update(entrenador);
	listEquipos(request, response);			
}
private void updateEquipo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	Equipo equipo = new Equipo();
	EquipoLogic equipoL=new EquipoLogic();
	equipo.setIdEquipo(Integer.parseInt(request.getParameter("id"))); // deberia comprobar si es nulo con un wrapper
	equipo.setNombre(request.getParameter("nombre"));
	equipo.setLocalidad(request.getParameter("localidad"));
	equipo.setPuntaje(Integer.parseInt(request.getParameter("puntaje")));
	equipo.setDifGoles(Integer.parseInt( request.getParameter("difGol")));
	equipoL.update(equipo);			
	listEquipos(request, response);
}
private void eliminarEquipo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException { //falta verificar que el equipo no este por jugar un partido antes de ser borradom en dicho caso cancelar la aliminacion hasta que juegue todos sus partidos
	Equipo equipo = new Equipo();
	EquipoLogic equipoL=new EquipoLogic();
	equipo.setIdEquipo(Integer.parseInt(request.getParameter("idEquipo")));	
	equipoL.delete(equipo);	 // una vez borradas las dependencias borro el equipo si no tiene partidos sin jugar
	listEquipos(request, response);
}
private void preparaEquipoEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Equipo equipo=new Equipo();
	equipo.setIdEquipo(Integer.parseInt(request.getParameter("idEquipo")));
	EquipoLogic equipoL= new EquipoLogic();
	equipo=equipoL.getOne(equipo);
	request.setAttribute("equipo", equipo);
	request.getRequestDispatcher(showFormEdit).forward(request, response);
}
}
