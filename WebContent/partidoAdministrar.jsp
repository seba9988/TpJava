<%@ page import="data.DataEquipo"
	import="java.util.LinkedList"
	import="Entidades.Partido"
	import="Entidades.Equipo"
	import="java.util.Iterator"
	import="Logic.PartidoLogic"
	import="Logic.EquipoLogic"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Inicio</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
<%@ include file="/Include/Head.html" %>
    <br>

.<div class="container-fluid">
    <br>
    <table id="tableEquipo" class="table table-bordered ">
        <tr>
        <th>Fecha</th>
        <th>Hora</th>
        <th>Resultado</th>
        <th>Primer Equipo</th>
        <th>Segundo Equipo</th>
        <th>Cancha</th>
        </tr>
        <%
        	PartidoLogic partidoL= new PartidoLogic();
       		LinkedList<Partido>listP=partidoL.getAll();
       		EquipoLogic equipoL=new EquipoLogic();
			for(Partido P : listP) {	
			Equipo e1 = equipoL.getOne(P.getIdEquipo1());
	        Equipo e2 = equipoL.getOne(P.getIdEquipo2());
        %>
        <tr>
        	<th><%=P.getFecha()%></th>
            <th><%=P.getHora() %></th>
            <th><%=P.getResultado() %></th>
            <th><%=e1.getNombre()%></th>
            <th><%=e2.getNombre() %></th>
            <th><%=P.getNumCancha() %></th>
            <th><form action="PartidoControl" method=post>
            	<input type="hidden" name="fecha" class="form-control" value="<%=P.getFecha() %>">	
                <input type="hidden" name="hora" class="form-control" value="<%=P.getHora() %>">	
                <input type="hidden" name="nroC" class="form-control" value="<%=P.getNumCancha() %>">	
            	<button type="submit" class="btn btn-primary" name="accion" value="editar">Editar</button>
            	<button type="submit" class="btn btn-primary" name="accion" value="eliminar">Eliminar</button>
           		</form>
            </th>
        </tr> 
        <%}%>
    </table>
   
</div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
      crossorigin="anonymous"></script>

</body>

</html>