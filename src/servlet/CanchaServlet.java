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

import data.DataCancha;
import entities.Cancha;


/**
 * Servlet implementation class CanchaControl
 */
@WebServlet("/CanchaControl")
public class CanchaServlet extends HttpServlet {

	String listar = "Cancha-Listar.jsp";
	String add="Cancha-Add.jsp";
	String modif="Cancha-Modif.jsp";
	String edit="Cancha-Edit.jsp";
	Cancha c = new Cancha();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CanchaServlet() {
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
			String numC=request.getParameter("NumC");
			String nombre = request.getParameter("nombre");
			
			c.setNroCancha(Integer.parseInt(numC));
			c.setNombre(nombre);
			
			if(DataCancha.add(c)) {
				preparalist(request, response);
				response.sendRedirect(listar);
			} else {
				request.setAttribute("msg", "No se pudo cargar Cancha Vuelva a intentarlo");
				RequestDispatcher rd= request.getRequestDispatcher(add);
				rd.forward(request, response);
			}
			
		}
		
		if(action.equalsIgnoreCase("modif")) {
			DataCancha Cancha = new DataCancha();
			LinkedList<Cancha> list = Cancha.getall();
			request.getSession().setAttribute("lista", list);
			response.sendRedirect(modif);
		}
		if(action.equalsIgnoreCase("editar")) {
			int numC=Integer.parseInt((String) request.getParameter("numC"));
			DataCancha dCancha= new DataCancha();
			Cancha c=(Cancha)dCancha.getOne(numC);
			request.getSession().setAttribute("Cancha", c);
			response.sendRedirect(edit);
		}
		if(action.equalsIgnoreCase("Actualizar")) {
			int numC=Integer.parseInt(request.getParameter("numC"));
			String nombre = request.getParameter("nombre");
			
			c.setNroCancha(numC);
			c.setNombre(nombre);
			
			
			DataCancha.update(c);
			preparalist(request, response);
			response.sendRedirect(listar);
		}
		if(action.equalsIgnoreCase("eliminar")) {
			int numC=Integer.parseInt(request.getParameter("numC"));
			c.setNroCancha(numC);
			DataCancha.delete(c);
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
		DataCancha Cancha = new DataCancha();
		LinkedList<Cancha> list = Cancha.getall();
		request.getSession().setAttribute("lista", list);
	}

}
