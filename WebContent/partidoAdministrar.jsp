<%@ page
	import="java.util.LinkedList"
	import="entities.Partido"
	import="entities.Equipo"
	import="java.util.Iterator"%>

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
  <link rel="stylesheet" type="text/css" href="css/main.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
  <%
  LinkedList<Partido>listP=(LinkedList<Partido>)request.getAttribute("listPartidos");
  %>
</head>
<body>
  <nav><%@ include file="/Include/Head.html"%></nav>
  <section class="container">
    <article>
      <h1 class="text-white text-center mt-4">Lista Partidos</h1>
      <form action="PartidoServlet" method=get>
    	  <button type="submit" class="btn btn-success float-end" name="accion" value="formFechaAdd"><i class="bi bi-plus-circle"></i> Agregar partido</i></button>
      </form>
      <table id="tableEquipo" class="table table-dark table-hover">
        <thead>
          <tr>
            <th>Fecha</th>
            <th>Hora</th>
            <th>Resultado</th>
            <th>Primer Equipo</th>
            <th>Segundo Equipo</th>
            <th>Cancha</th>
            <th></th>
          </tr> 
        </thead>
        <tbody>
          <%
          for(Partido P : listP) {	
          %>
          <tr>
            <th><%=P.getFecha()%></th>
            <th><%=P.getHora() %></th>
            <th><%=P.getResultado() %></th>
            <th><%=P.getEquipo1().getNombre() %></th>
            <th><%=P.getEquipo2().getNombre()  %></th>
            <th><%=P.getCancha().getNroCancha() %></th>
            <th>
              <form action="PartidoServlet" method=get style="display: inline-block;">
                <input type="hidden" name="Fecha" class="form-control" value="<%=P.getFecha() %>">	
                <input type="hidden" name="Hora" class="form-control" value="<%=P.getHora() %>">	
                <input type="hidden" name="nroC" class="form-control" value="<%=P.getCancha().getNroCancha() %>">	
                <button type="submit" class="btn btn-outline-primary" name="accion" value="formEdit"><i class="bi bi-pencil-fill"></i></button>
              </form>
              <form action="PartidoServlet" method=post style="display: inline-block;">
                <input type="hidden" name="Fecha" class="form-control" value="<%=P.getFecha() %>">	
                <input type="hidden" name="Hora" class="form-control" value="<%=P.getHora() %>">	
                <input type="hidden" name="nroC" class="form-control" value="<%=P.getCancha().getNroCancha() %>">
                <button type="submit" class="btn btn-outline-danger" name="accion" value="delete"><i class="bi bi-trash-fill"></i></button>
              </form>
              <form action="PartidoServlet" method=get style="display: inline-block;">
                <input type="hidden" name="Fecha" class="form-control" value="<%=P.getFecha() %>">	
                <input type="hidden" name="Hora" class="form-control" value="<%=P.getHora() %>">	
                <input type="hidden" name="nroC" class="form-control" value="<%=P.getCancha().getNroCancha() %>">
                <button type="submit" class="btn btn-primary" name="accion" value="formFechaReprogramar">Reprogramar</button>
              </form>
            </th>
          </tr> 
          <%}%>
        </tbody>
      </table>
    </article>  
 </section>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
      crossorigin="anonymous">
    </script>
</body>
</html>