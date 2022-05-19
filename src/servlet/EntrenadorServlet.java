package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Entrenador;
import logic.EntrenadorLogic;
/**
 * Servlet implementation class EntrenadorControl
 */
@WebServlet("/EntrenadorServlet")
public class EntrenadorServlet extends HttpServlet {
	String showFormAdd="entrenadorFormAdd.jsp";
	String administrar="entrenadorAdministrar.jsp";
	String showFormEdit="entrenadorFormEdit.jsp";
	Entrenador e = new Entrenador();
	private static final long serialVersionUID = 1L;

    public EntrenadorServlet() {
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
				listEntrenadores(request, response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar la lista de Entrenadores, vuelva a intentarlo.");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}	
			break;
		case "formEdit": // preparo el jugador a editar y muestro la pagina jsp con sus datos
			try
			{	
				preparaEntrenadorEdit(request,response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar el Entrenador seleccionado, vuelva a intentarlo.");
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
				addEntrenador(request, response);	
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
				updateEntrenador(request, response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar la lista de Entrenadores, vuelva a intentarlo.");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}	
			break;
		case "delete": // preparo el jugador a editar y muestro la pagina jsp con sus datos
			try
			{	
				deleteEntrenador(request,response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar el Entrenador seleccionado, vuelva a intentarlo.");
				request.getRequestDispatcher(administrar).forward(request, response);
			}	
			break;	
		default: // falta agregar mensaje de error
			break;
		}
	}
	private void listEntrenadores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntrenadorLogic entrenadorL= new EntrenadorLogic();
		LinkedList<Entrenador> list = entrenadorL.getAll();
		request.setAttribute("listEntrenadores", list);
		request.getRequestDispatcher(administrar).forward(request, response);
	}
	private void preparaEntrenadorEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Entrenador entrenador=new Entrenador();
		entrenador.setDni(request.getParameter("dni"));
		EntrenadorLogic entrenadorL= new EntrenadorLogic();
		entrenador=entrenadorL.getOne(entrenador);
		request.setAttribute("entrenador", entrenador);
		request.getRequestDispatcher(showFormEdit).forward(request, response);
	}
	private void addEntrenador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Entrenador entrenador= new Entrenador();
		EntrenadorLogic entrenadorL= new EntrenadorLogic();
		entrenador.setDni(request.getParameter("dni"));	
		entrenador.setNombre(request.getParameter("nombre"));
		entrenador.setApellido(request.getParameter("apellido"));
		entrenador.setFecha_nacimiento(LocalDate.parse(request.getParameter("fechaNac")));
		entrenadorL.add(entrenador);
		listEntrenadores(request, response);
	}
	private void updateEntrenador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Entrenador entrenador= new Entrenador();
		EntrenadorLogic entrenadorL= new EntrenadorLogic();
		entrenador.setDni(request.getParameter("dni"));
		entrenador.setNombre(request.getParameter("nombre"));
		entrenador.setApellido(request.getParameter("apellido"));
		entrenador.setFecha_nacimiento(LocalDate.parse(request.getParameter("fechaNac")));	
		entrenadorL.update(entrenador);
		listEntrenadores(request, response);
	}
	private void deleteEntrenador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Entrenador entrenador= new Entrenador();
		EntrenadorLogic entrenadorL=new EntrenadorLogic();
		entrenador.setDni(request.getParameter("dni"));
		entrenadorL.delete(entrenador);
		listEntrenadores(request, response);
	}
}