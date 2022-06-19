<%@ page 
	import="java.util.LinkedList"
	import="entities.Jugador"
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
  LinkedList<Jugador>list= (LinkedList<Jugador>)request.getAttribute("listJugadores");
  %>
</head>

<body>
  <nav><%@ include file="/Include/Head.html" %></nav>
  <section class="container">
    <article>
      <h1 class="text-white text-center mt-4">Lista Jugadores</h1>
      <form action="JugadorServlet" method=get>
        <button type="submit" class="btn btn-success float-end" name="accion" value="formAdd"><i class="bi bi-plus-circle"></i> Agregar jugador</i></button>
      </form>
      <table id="tableEquipo" class="table table-dark table-hover">
        <thead>
          <tr>
            <th>Dni</th>
            <th>Nombre </th>
            <th>Apellido</th>
            <th>Fecha Nacimiento</th>
            <th>Posicion</th>
            <th>Goles</th>
            <th>Asistencias</th>
            <th>Tarjetas Amarillas</th>
            <th>Tarjetas Rojas</th>
            <th>Partidos Jugados</th>
            <th>Equipo</th>
            <th></th>
          </tr>
        </thead>      
        <span style="color: red;"><%request.getAttribute("msg");%></span>
        <tbody>
          <%
          for(Jugador listJ : list) {		
          %>
          <tr>
            <th><%=listJ.getDni()%></th>
            <th><%=listJ.getNombre()%></th>
            <th><%=listJ.getApellido()%></th>
            <th><%=listJ.getFecha_nacimiento()%></th>
            <th><%=listJ.getPosicion()%></th>
            <th><%=listJ.getGoles()%></th>
            <th><%=listJ.getAsistencias()%></th>
            <th><%=listJ.getTarjetasA()%></th>
            <th><%=listJ.getTarjetasR()%></th>
            <th><%=listJ.getEquipo().getNombre()%></th>
            <th>
              <form action="JugadorServlet" method=get style="display: inline-block;">
                <input type="hidden" name="dni" class="form-control" value="<%=listJ.getDni()%>">	
                <button type="submit" class="btn btn-outline-primary" name="accion" value="formEdit"><i class="bi bi-pencil-fill"></i></button>
              </form>
              <form action="JugadorServlet" method=post style="display: inline-block;">
                <input type="hidden" name="dni" class="form-control" value="<%=listJ.getDni()%>">
                <button type="submit" class="btn btn-outline-danger" name="accion" value="delete"><i class="bi bi-trash-fill"></i></button>
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