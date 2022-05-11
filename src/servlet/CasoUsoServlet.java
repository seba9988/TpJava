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

import entities.Arbitro;
import entities.Partido;
import logic.PartidoLogic;
@WebServlet("/CUControl")
public class CasoUsoServlet extends HttpServlet {
	String PartidoListar = "Partido_ListReprogramar.jsp";
	String asignaArbitro = "AsignarArbitro.jsp";
	String PartidoSinArbitroLista = "PartidoSinArbitro_Lista.jsp";
	private static final long serialVersionUID = 1L;

	public CasoUsoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acceso = "";
		String action = request.getParameter("accion");
		if (action.equalsIgnoreCase("ReprogramarPartido")) {
			preparalistP(request, response);
			acceso = PartidoListar;
		}
		if (action.equalsIgnoreCase("AsignarArbitro")) {
			preparalistaPSinArbitro(request, response);
			acceso = PartidoSinArbitroLista;
		}
		RequestDispatcher vista = request.getRequestDispatcher(acceso);
		vista.forward(request, response);
	}

	private void preparalistP(HttpServletRequest request, HttpServletResponse response) {
		PartidoLogic partidoL = new PartidoLogic();
		LinkedList<Partido> listP = partidoL.getAll();
		LinkedList<Partido> listPDisp = new LinkedList<>();
		for (Partido p : listP) {
			if (p.getResultado().isBlank() && p.getFecha().isAfter(LocalDate.now())) 
				listPDisp.add(p);
		}
		request.getSession().setAttribute("listaP", listPDisp); // muestra los partidos que no tengan resultado
	}

	private void preparalistaPSinArbitro(HttpServletRequest request, HttpServletResponse response) {
		PartidoLogic partidoL = new PartidoLogic();
		LinkedList<Partido> listP = partidoL.getAll();
		LinkedList<Partido> listPsinArbitro = new LinkedList<>();
		for (Partido p : listP) {
			if (p.getDniArbitro()==null && p.getFecha().isAfter(LocalDate.now()) && p.getResultado() != "Repro") // si el partido no tiene arbitro+ la fecha es despues de hoy+ no fue reprogramado entonces agrego partido a la lista
			{
				listPsinArbitro.add(p);
			}
		}
		request.getSession().setAttribute("listaPartido", listPsinArbitro);
	}

}
