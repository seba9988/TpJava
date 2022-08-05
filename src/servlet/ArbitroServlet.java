package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Arbitro;
import logic.ArbitroLogic;

@WebServlet("/ArbitroServlet")
public class ArbitroServlet extends HttpServlet {	
	String showFormAdd="arbitroFormAdd.jsp";
	String administrar="arbitroAdministrar.jsp";
	String showFormEdit="arbitroFormEdit.jsp";
	private static final long serialVersionUID = 1L;
       
    public ArbitroServlet() {
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
				listArbitros(request, response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar la lista de Arbitros, vuelva a intentarlo.");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}	
			break;
		case "formEdit": // preparo el jugador a editar y muestro la pagina jsp con sus datos
			try
			{	
				preparaArbitroEdit(request,response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar el Arbitro seleccionado, vuelva a intentarlo.");
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
				addArbitro(request, response);	
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
				updateArbitro(request, response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar la lista de Arbitros, vuelva a intentarlo.");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}	
			break;
		case "delete": // preparo el jugador a editar y muestro la pagina jsp con sus datos
			try
			{	
				deleteArbitro(request,response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("msg", "Ocurrio un error al buscar el Arbitro seleccionado, vuelva a intentarlo.");
				request.getRequestDispatcher(administrar).forward(request, response);
			}	
			break;	
		default: // falta agregar mensaje de error
			break;
		}
	}	
	private void listArbitros(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArbitroLogic arbitroL= new ArbitroLogic();
		LinkedList<Arbitro> list = arbitroL.getAll();
		request.setAttribute("listArbitros", list);
		request.getRequestDispatcher(administrar).forward(request, response);
	}
	private void preparaArbitroEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Arbitro arbitro=new Arbitro();
		arbitro.setDni(request.getParameter("dni"));
		ArbitroLogic arbitroL= new ArbitroLogic();
		arbitro=arbitroL.getOne(arbitro);
		request.setAttribute("arbitro", arbitro);
		request.getRequestDispatcher(showFormEdit).forward(request, response);
	}
	private void addArbitro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Arbitro arbitro= new Arbitro();
		ArbitroLogic arbitroL= new ArbitroLogic();
		arbitro.setDni(request.getParameter("dni"));	
		arbitro.setNombre(request.getParameter("nombre"));
		arbitro.setApellido(request.getParameter("apellido"));
		arbitro.setFechaNacimiento(LocalDate.parse(request.getParameter("fechaNac")));
		arbitroL.add(arbitro);
		listArbitros(request, response);
	}
	private void updateArbitro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Arbitro arbitro= new Arbitro();
		ArbitroLogic arbitroL= new ArbitroLogic();
		arbitro.setDni(request.getParameter("dni"));
		arbitro.setNombre(request.getParameter("nombre"));
		arbitro.setApellido(request.getParameter("apellido"));
		arbitro.setFechaNacimiento(LocalDate.parse(request.getParameter("fechaNac")));	
		arbitroL.update(arbitro);
		listArbitros(request, response);
	}
	private void deleteArbitro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Arbitro arbitro= new Arbitro();
		ArbitroLogic arbitroL=new ArbitroLogic();
		arbitro.setDni(request.getParameter("dni"));
		arbitroL.delete(arbitro);
		listArbitros(request, response);
	}
}