<%@ page import="logic.PartidoLogic"
	import="java.util.LinkedList"
	import="Entidades.Partido"
	import="Entidades.Equipo"
	import="Data.DataEquipo"
	import="Logic.EquipoLogic"
	import="java.util.Iterator"
	import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Seleccion partido para agregar arbitro</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
<%@ include file="/Include/Head.html" %>
    <br>

<div class="container-fluid">
    <br>
    <table id="tablePartido" class="table table-bordered ">
        <tr>
        <th>Fecha</th>
        <th>Hora</th>
        <th>Resultado</th>
        <th>Nombre equipo 1</th>
        <th>Nombre equipo 2</th>
        <th>Cancha</th>		
        </tr>
        <%
        LinkedList<Partido>listaP=(LinkedList<Partido>)session.getAttribute("listaPartido");			
			for(Partido listP : listaP) {
				Equipo e1=DataEquipo.getOne(listP.getIdEquipo1()); 
				Equipo e2=DataEquipo.getOne(listP.getIdEquipo2()); 
			
        %>
        <tr>
            <th><%=listP.getFecha() %></th>
            <th><%=listP.getHora() %></th>
            <th><%=listP.getResultado() %></th>
            <th><%=e1.getNombre() %></th>
            <th><%=e2.getNombre() %></th>
            <th><%=listP.getNumCancha() %></th>
             <th><form action="AsignarArbitroControl" method=post>
            	<input type="hidden" name="fechaP" class="form-control" value="<%=listP.getFecha()%>">	
            	<input type="hidden" name="horaP" class="form-control" value="<%=listP.getHora()%>">	
            	<input type="hidden" name="nroCP" class="form-control" value="<%=listP.getNumCancha()%>">	
            	<button type="submit" class="btn btn-primary" name="accion" value="agregar">Asignar</button>
           		</form>
            </th>
        </tr> 
        <%}
        
			
        %>
    </table>
</div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
      crossorigin="anonymous"></script>

</body>

</html>