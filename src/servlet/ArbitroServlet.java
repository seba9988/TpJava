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

import data.DataArbitro;
import entities.Arbitro;


/**
 * Servlet implementation class ArbitroControl
 */
@WebServlet("/ArbitroControl")
public class ArbitroServlet extends HttpServlet {
	
	String listar = "Arbitro-Listar.jsp";
	String add="Arbitro-Add.jsp";
	String modif="Arbitro-Modif.jsp";
	String edit="Arbitro-Edit.jsp";
	Arbitro a = new Arbitro();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArbitroServlet() {
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
			
			a.setDni(dni);
			a.setNombre(nombre);
			a.setApellido(apellido);
			a.setFecha_nacimiento(fechaNac);
			
			if(DataArbitro.add(a)) {
				preparalist(request, response);
				response.sendRedirect(listar);
			} else {
				request.setAttribute("msg", "No se pudo cargar Equipo Vuelva a intentarlo");
				RequestDispatcher rd= request.getRequestDispatcher(add);
				rd.forward(request, response);
			}
			
		}
		
		if(action.equalsIgnoreCase("modif")) {
			DataArbitro Arbitro = new DataArbitro();
			LinkedList<Arbitro> list = Arbitro.getall();
			request.getSession().setAttribute("lista", list);
			response.sendRedirect(modif);
		}
		if(action.equalsIgnoreCase("editar")) {
			int dni=Integer.parseInt((String) request.getParameter("dni"));
			DataArbitro dArbitro= new DataArbitro();
			Arbitro a=(Arbitro)dArbitro.getOne(dni);
			request.getSession().setAttribute("Arbitro", a);
			response.sendRedirect(edit);
		}
		if(action.equalsIgnoreCase("Actualizar")) {
			String dni=request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			LocalDate fechaNac = LocalDate.parse(request.getParameter("fechaNac"));
			
			a.setDni(dni);
			a.setNombre(nombre);
			a.setApellido(apellido);
			a.setFecha_nacimiento(fechaNac);
			
			DataArbitro.update(a);
			preparalist(request, response);
			response.sendRedirect(listar);
		}
		if(action.equalsIgnoreCase("eliminar")) {
			String dni=request.getParameter("dni");
			a.setDni(dni);
			DataArbitro.delete(a);
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
		DataArbitro Arbitro = new DataArbitro();
		LinkedList<Arbitro> list = Arbitro.getall();
		request.getSession().setAttribute("lista", list);
	}

}
