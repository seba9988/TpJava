package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Partido;
import logic.PartidoLogic;
import logic.Validaciones;

/**
 * Servlet implementation class PartidoControlador
 */
@WebServlet("/PartidoControl")
public class PartidoServlet extends HttpServlet {
	String listar = "Partido-Listar.jsp";
	String add="Partido-Add.jsp";
	String modif="Partido-Modif.jsp"; //seleccion de partido editar o borrar
	String edit="Partido-Edit.jsp"; // carga datos
	private static final long serialVersionUID = 1L;
    public PartidoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acceso = "";
		String action = request.getParameter("accion");
		if(action.equalsIgnoreCase("add")){
			
			acceso=add;			
		}
		if(action.equalsIgnoreCase("modif"))
			acceso=modif;
		if(action.equalsIgnoreCase("listarP"))
			
				acceso=listar;
		if(action.equalsIgnoreCase("Alta"))
		{
			LocalDate fecha=LocalDate.parse(request.getParameter("fecha"));
			LocalTime hora=LocalTime.parse(request.getParameter("hora"));
			int idEquipo1=Integer.parseInt(request.getParameter("equipoId1"));
			int idEquipo2=Integer.parseInt(request.getParameter("equipoId2"));
			int nroC=Integer.parseInt(request.getParameter("nroC"));
			Validaciones validar= new Validaciones();
			Boolean bandera=true; // para verificar si se puede hacer el alta
			if(validar.VerificarEquiposFecha(idEquipo1,idEquipo2, fecha, hora) && (idEquipo1!=idEquipo2)&& validar.VerificarCanchaDisp(fecha,hora,nroC)) // valida que los partidos no esten jugando ya en esa fecha/hora, que los ids de los equipos no sean iguales y que la cancha este disponible en esa fecha/hora
			{
				Partido p= new Partido();
				PartidoLogic partidoL= new PartidoLogic();
				p.setFecha(fecha);
				p.setHora(hora);
				p.setNumCancha(nroC);
				p.setIdEquipo1(idEquipo1);
				p.setIdEquipo2(idEquipo2);
				p.setResultado("");
				p.setDniArbitro("");
				partidoL.alta(p);		
				acceso=listar;
			}
			else {				
				acceso=add;		// pensar una forma de mostrar mensaje de error	
			}				
					}
		if(action.equalsIgnoreCase("editar"))
		{
			PartidoLogic partidoL= new PartidoLogic();
			LocalDate fecha= LocalDate.parse(request.getParameter("fecha"));
			System.out.println(fecha);
			LocalTime hora=LocalTime.parse(request.getParameter("hora"));
			int nroC=Integer.parseInt(request.getParameter("nroC"));
			System.out.println(nroC);
			Partido p=partidoL.getOne(fecha, hora, nroC);
			request.setAttribute("partido", p);
			acceso=edit;
		}
		if(action.equalsIgnoreCase("Actualizar"))
		{	PartidoLogic partidoL=new PartidoLogic();
			
			Validaciones validar= new Validaciones();
			LocalDate fecha= LocalDate.parse(request.getParameter("fecha"));
			
			LocalTime hora=LocalTime.parse(request.getParameter("hora"));
			int nroC=Integer.parseInt(request.getParameter("nroC"));
			
			int idEquipo1=Integer.parseInt(request.getParameter("equipo1")); // nuevo
			int idEquipo2=Integer.parseInt(request.getParameter("equipo2")); // nuevo
			String resultado=request.getParameter("resultado");
			String incidencias=request.getParameter("incidencias");
			System.out.println("Fecha"+ fecha);
			System.out.println("Hora" +hora);
			System.out.println(validar.VerificarEquiposFecha(idEquipo1,idEquipo2,fecha,hora));
			System.out.println(idEquipo1!=idEquipo2);
			Partido pviejo= partidoL.getOne(fecha, hora, nroC);
			boolean disp= false;
			if	((validar.DosEquiposIguales(pviejo.getIdEquipo1(),pviejo.getIdEquipo2(),idEquipo1,idEquipo2))) // verifica si los dos ids viejos son iguales a los nuevos validar.DosEquiposIguales(pviejo.getIdEquipo1(),pviejo.getIdEquipo2(),idEquipo1,idEquipo2)
			{
				Partido p= new Partido(fecha,hora,resultado,incidencias,idEquipo1,idEquipo2,nroC);  		
				System.out.println(p);
				partidoL.Modif(p);
				acceso=listar;
			}
			else {
				if(idEquipo1==pviejo.getIdEquipo1() || idEquipo1==pviejo.getIdEquipo2()) 
					{if(validar.VerificarUnEquipo(fecha,hora,idEquipo2))
						{Partido p= new Partido(fecha,hora,resultado,incidencias,idEquipo1,idEquipo2,nroC);  		
						System.out.println(p);
						partidoL.Modif(p);
						acceso=listar;}
					}
					else
						if (idEquipo2==pviejo.getIdEquipo2() || idEquipo2== pviejo.getIdEquipo1()) 
								if(validar.VerificarUnEquipo(fecha,hora,idEquipo1))
								{
									Partido p= new Partido(fecha,hora,resultado,incidencias,idEquipo1,idEquipo2,nroC);  		
									System.out.println(p);
									partidoL.Modif(p);
									acceso=listar;
								}			              
								else if((idEquipo1!=idEquipo2)&& validar.VerificarEquiposFecha(idEquipo1,idEquipo2,fecha,hora))
										{
											Partido p= new Partido(fecha,hora,resultado,incidencias,idEquipo1,idEquipo2,nroC);  		
											System.out.println(p);
											partidoL.Modif(p);
											acceso=listar;
										}				
									else
										
										{
											acceso=edit;
										}
								}						
		}
		if(action.equalsIgnoreCase("eliminar"))
		{
			LocalDate fecha= LocalDate.parse(request.getParameter("fecha"));
			LocalTime hora=LocalTime.parse(request.getParameter("hora"));
			int nroC=Integer.parseInt(request.getParameter("nroC"));
			PartidoLogic partidoL = new PartidoLogic();
			Partido p = partidoL.getOne(fecha, hora, nroC);
			partidoL.baja(p);
			acceso=listar;								
		}	
	    RequestDispatcher vista=request.getRequestDispatcher(acceso);
		vista.forward(request, response);		
	}

}