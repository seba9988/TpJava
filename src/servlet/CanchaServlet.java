package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cancha;
import logic.CanchaLogic;


/**
 * Servlet implementation class CanchaControl
 */
@WebServlet("/CanchaServlet")
public class CanchaServlet extends HttpServlet {
	String showFormAdd="canchaFormAdd.jsp";
	String administrar="canchaAdministrar.jsp";
	String showFormEdit="canchaFormEdit.jsp";
	
	private static final long serialVersionUID = 1L;
       
    public CanchaServlet() {
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
				listCanchas(request, response);
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
				preparaCanchaEdit(request,response);
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
				addCancha(request, response);	
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
				updateCancha(request, response);
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
				deleteCancha(request,response);
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
	private void listCanchas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CanchaLogic canchaL= new CanchaLogic();
		LinkedList<Cancha> list = canchaL.getAll();
		request.setAttribute("listaCancha", list);
		request.getRequestDispatcher(administrar).forward(request, response);
	}
	private void addCancha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cancha cancha= new Cancha();
		CanchaLogic canchaL= new CanchaLogic();
		cancha.setNombre(request.getParameter("nombre"));
		canchaL.add(cancha);
		listCanchas(request, response);	
	}
	private void preparaCanchaEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cancha cancha= new Cancha();
		CanchaLogic canchaL= new CanchaLogic();
		cancha.setNroCancha(Integer.parseInt(request.getParameter("numC")));
		cancha=canchaL.getOne(cancha);
		request.setAttribute("cancha", cancha);
		request.getRequestDispatcher(showFormEdit).forward(request, response);
	}
	private void updateCancha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cancha cancha= new Cancha();
		CanchaLogic canchaL= new CanchaLogic();	
		cancha.setNroCancha(Integer.parseInt(request.getParameter("numC")));
		cancha.setNombre(request.getParameter("nombre"));
		canchaL.update(cancha);	
		listCanchas(request, response);
	}
	private void deleteCancha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cancha cancha= new Cancha();
		CanchaLogic canchaL= new CanchaLogic();
		cancha.setNroCancha(Integer.parseInt(request.getParameter("numC")));
		canchaL.delete(cancha);
		listCanchas(request, response);
	}
}