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

import data.DataEntrenador;
import entities.Entrenador;


/**
 * Servlet implementation class EntrenadorControl
 */
@WebServlet("/EntrenadorControl")
public class EntrenadorServlet extends HttpServlet {
	
	String listar = "Entrenador-Listar.jsp";
	String add="Entrenador-Add.jsp";
	String modif="Entrenador-Modif.jsp";
	String edit="Entrenador-Edit.jsp";
	Entrenador e = new Entrenador();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EntrenadorServlet() {
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
		// TODO Auto-generated method stub
		
		String action=request.getParameter("accion");
		if(action.equalsIgnoreCase("add")) {
			response.sendRedirect(add);
		}
		
		if(action.equalsIgnoreCase("agregar")) {
			String dni=request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			LocalDate fechaNac = LocalDate.parse(request.getParameter("fechaNac"));
			
			e.setDni(Integer.parseInt(dni));
			e.setNombre(nombre);
			e.setApellido(apellido);
			e.setFecha_nacimiento(fechaNac);
			
			if(DataEntrenador.add(e)) {
				preparalist(request, response);
				response.sendRedirect(listar);
			} else {
				request.setAttribute("msg", "No se pudo cargar Equipo Vuelva a intentarlo");
				RequestDispatcher rd= request.getRequestDispatcher(add);
				rd.forward(request, response);
			}
			
		}
		
		if(action.equalsIgnoreCase("modif")) {
			DataEntrenador Entrenador = new DataEntrenador();
			LinkedList<Entrenador> list = Entrenador.getall();
			request.getSession().setAttribute("lista", list);
			response.sendRedirect(modif);
		}
		if(action.equalsIgnoreCase("editar")) {
			int dni=Integer.parseInt((String) request.getParameter("dni"));
			DataEntrenador dEntrenador= new DataEntrenador();
			Entrenador e=(Entrenador)dEntrenador.getOne(dni);
			request.getSession().setAttribute("Entrenador", e);
			response.sendRedirect(edit);
		}
		if(action.equalsIgnoreCase("Actualizar")) {
			int dni=Integer.parseInt(request.getParameter("dni"));
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			LocalDate fechaNac = LocalDate.parse(request.getParameter("fechaNac"));
			e=DataEntrenador.getOne(dni);
			System.out.println(e);
			e.setNombre(nombre);
			e.setApellido(apellido);
			e.setFecha_nacimiento(fechaNac);
			
			DataEntrenador.update(e);
			preparalist(request, response);
			response.sendRedirect(listar);
		}
		if(action.equalsIgnoreCase("eliminar")) {
			int dni=Integer.parseInt(request.getParameter("dni"));
			e.setDni(dni);
			DataEntrenador.delete(e);
			preparalist(request, response);
			response.sendRedirect(listar);
		}
		
		if(action.equalsIgnoreCase("listar")) {
			preparalist(request, response);
			response.sendRedirect(listar);
		}
		doGet(request, response);
	}
	private void preparalist(HttpServletRequest request, HttpServletResponse response) {
		DataEntrenador Entrenador = new DataEntrenador();
		LinkedList<Entrenador> list = Entrenador.getall();
		request.getSession().setAttribute("lista", list);
	}


}
