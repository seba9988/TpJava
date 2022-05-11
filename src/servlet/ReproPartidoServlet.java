package servlet;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataEquipo;
import data.DataPartido;
import entities.Cancha;
import entities.Equipo;
import entities.Partido;
import logic.CanchaLogic;
import logic.PartidoLogic;
import logic.Validaciones;
/**
 * Servlet implementation class Controlador
 */
@WebServlet("/ControladorRePartido")


public class ReproPartidoServlet extends HttpServlet {
	String reprogramar="ReprogramarPartido.jsp";
	String listaCancha="CanchaDisp.jsp";
	String listar="Partido-Listar.jsp";
	Equipo e = new Equipo();
	String fp=null;// fecha del partido seleccionado
	LocalDate fechaP=null;
	LocalTime horaN=null;
	LocalDate fechaN=null;
	String hp=null; // hora del partido seleccionado
	LocalTime horaP=null;
	int nroC=0; // nro cancha del partido seleccionado
	private static final long serialVersionUID = 1L;
    public ReproPartidoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acceso="";
		String action=request.getParameter("accion");
		Partido p= new Partido();
	
		PartidoLogic pl= new PartidoLogic();	
		if(action.equalsIgnoreCase("editar")) {
			fp=request.getParameter("fechaP");
			fechaP=LocalDate.parse(fp);
			hp=request.getParameter("horaP");
			horaP=LocalTime.parse(hp);
			nroC=Integer.parseInt((String) request.getParameter("nroCP"));
			HttpSession session = request.getSession();
			session.setAttribute("FechaP",fechaP);
			session.setAttribute("HoraP",horaP);
			session.setAttribute("NroC",nroC);
			response.sendRedirect(reprogramar);}
		
		if(action.equalsIgnoreCase("verificar")) {
			String f=request.getParameter("Fecha"); // fecha nueva ingresada
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
			fechaN=LocalDate.parse(f); // probar si anda
			String h= request.getParameter("Hora"); // hora nueva ingresada
			horaN= LocalTime.parse(h);
			
			//surround below line with try catch block as below code throws checked exception
			/*try {
				//LocalDate fecha = sdf.parse(f);
				String h= request.getParameter("Hora");
				DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
				LocalDate d = dateFormat.parse(h); //paso el time en string a date para luego pasarlo a time
				LocalTime hora=(LocalTime) d; // probar no estoy seguro si anda sino habra que combinar fecha y hora
				preparalistC(request,response,hora,fecha);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			p=pl.getOne(fechaP, horaP, nroC);
			preparalistC(request,response,horaN,fechaN,p.getIdEquipo1(),p.getIdEquipo2());
			System.out.println(fechaP + " -- " +  horaP);
			System.out.println(fechaN + " -- " +  horaN);
			response.sendRedirect(listaCancha);		
			
		}
		if(action.equalsIgnoreCase("seleccion"))
		{
			LocalDate fechaP= LocalDate.parse(request.getParameter("FechaPar")); // puede ser que no haga falta junto con el session.getAtribute en canchaDisp al final
			//String fp=request.getParameter("fechaP"); // fecha del partido seleccionado
			//LocalDate fechaP=LocalDate.parse(fp);
			//String hp=request.getParameter("horaP"); // hora del partido seleccionado
			//LocalTime horaP=LocalTime.parse(hp);	
			//int nroC=Integer.parseInt((String) request.getParameter("nroCP"));
			Partido partido=pl.getOne(fechaP,horaP,nroC); // getOne con la fecha y hora del partido seleccionado, verificar si puedo pedir estos datos que deberian ser enviados en Partido_Listar			
			partido.setResultado("Repro");// una alternativa
			pl.Modif(partido); // modifico el resultado a reprogramado
			System.out.println(partido);
			Partido partidoNuevo= new Partido();
			/*String f=request.getParameter("Fecha"); // fecha nueva ingresada
			LocalDate fecha=LocalDate.parse(f); // probar si anda
			String h= request.getParameter("Hora"); // hora nueva ingresada
			LocalTime hora= LocalTime.parse(h);*/
			partidoNuevo.setFecha(fechaN);
			partidoNuevo.setHora(horaN);
			partidoNuevo.setIdEquipo1(partido.getIdEquipo1());
			partidoNuevo.setIdEquipo2(partido.getIdEquipo2());
			partidoNuevo.setResultado(" ");
//			partidoNuevo.setDniArbitro(partido.getDniArbitro());
			partidoNuevo.setNumCancha(Integer.parseInt((String) request.getParameter("nroCancha")));
			System.out.println(partidoNuevo);
			pl.alta(partidoNuevo);
			response.sendRedirect(listar);	
		}
				
		
				
		
	}
	private void preparalistC(HttpServletRequest request, HttpServletResponse response, LocalTime hora, LocalDate fecha,int id1,int id2) {
		CanchaLogic canchaL= new CanchaLogic();
		PartidoLogic partidoL= new PartidoLogic();
		Validaciones validar= new Validaciones();
		LinkedList<Partido> listP = partidoL.getAll();
		LinkedList<Cancha> listC = canchaL.getAll(); // como Partidos tiene visibilidad de canchas asumo que puedo hacerlo, verificar esto sino buscar otra forma
		LinkedList<Cancha> listCDisp= new LinkedList<>();
		if(validar.VerificarEquiposFecha(id1,id2,fecha,hora)) //si la lista sale vacia falta mostrar un mensaje diciendo que los equipos ya estan jguando aprtidos en esa fecha
		{		
			for(Cancha c: listC)
		{
			{
				if(validar.VerificarCanchaDisp(fecha,hora,c.getNroCancha()))
					listCDisp.add(c);
			}
		}
		}
		
		request.getSession().setAttribute("listaC", listCDisp);

}
}
