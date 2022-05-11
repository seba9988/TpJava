package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Arbitro;
import entities.Cancha;
import entities.Partido;
import logic.ArbitroLogic;
import logic.PartidoLogic;
import logic.Validaciones;
@WebServlet("/AsignarArbitroControl")
public class AsigArbitroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String AsignaArbitro="AsignarArbitro.jsp";
	String fp=null;// fecha del partido seleccionado
	String hp=null; // hora del partido seleccionado
	int nroC=0; // nro cancha del partido seleccionado
	LocalDate fechaP=null;
	LocalTime horaP=null;
	String PartidoListar = "Partido-Listar.jsp";
    public AsigArbitroServlet() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acceso="";
		String action=request.getParameter("accion");
		Partido p= new Partido();
	
		PartidoLogic pl= new PartidoLogic();	
		if(action.equalsIgnoreCase("agregar")) {
			fp=request.getParameter("fechaP");
			fechaP=LocalDate.parse(fp);
			hp=request.getParameter("horaP");
			horaP=LocalTime.parse(hp);
			nroC=Integer.parseInt((String) request.getParameter("nroCP"));
			HttpSession session = request.getSession();
			session.setAttribute("FechaP",fechaP);
			session.setAttribute("HoraP",horaP);
			session.setAttribute("NroC",nroC);
			preparalistA(request, response, horaP, fechaP);
			acceso=AsignaArbitro;}
		if(action.equalsIgnoreCase("seleccion"))
		{
			Partido partido=pl.getOne(fechaP,horaP,nroC); // getOne con la fecha y hora del partido seleccionado
			System.out.println(partido);
			System.out.println(request.getParameter("dniselec"));
			partido.setDniArbitro(request.getParameter("dniselec"));// una alternativa
			System.out.println(partido);
			pl.Modif(partido); // modifico el resultado a reprogramado		
			acceso=PartidoListar;
		}
		
		RequestDispatcher vista=request.getRequestDispatcher(acceso);
		vista.forward(request, response);
    }
    private void preparalistA(HttpServletRequest request, HttpServletResponse response, LocalTime hora, LocalDate fecha) {
		ArbitroLogic arbitroL= new ArbitroLogic();
		PartidoLogic partidoL= new PartidoLogic();
		Validaciones validar= new Validaciones();
		LinkedList<Partido> listP = partidoL.getAll();
		LinkedList<Arbitro> listA = arbitroL.getAll(); // como Partidos tiene visibilidad de canchas asumo que puedo hacerlo, verificar esto sino buscar otra forma
		LinkedList<Arbitro> listADisp= new LinkedList<>();
		Boolean disp=true;
			for(Arbitro a: listA)
		{
			disp=true;
			for(Partido p:listP)
			{
				if(p.getFecha()==fecha && p.getHora()==hora &&p.getDniArbitro()==a.getDni()) {
					disp=false;
				System.out.println("fecha no se puede");
				}
			}
			
			if (disp)
			{	
				
				listADisp.add(a);
			}
		}
			System.out.println(listADisp);
		request.getSession().setAttribute("listaA", listADisp);
		}

}
