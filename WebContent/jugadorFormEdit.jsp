
<%@ page language="java" import="java.util.*,java.sql.*"
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
</head>

<body>
<%@ include file="/Include/Head.html" %>
   
    <br>
	<%	
		Jugador J=(Jugador)request.getAttribute("jugador");
	%>
    <div class="container border text-center">
      <p class="h1">Modificar Jugador</p>
    <form action="JugadorServlet" method=post>
      	<div class="row mt-3">
          <div class="col-sm-8">
            <input type="hidden" name="dni" class="form-control" value="<%=J.getDni() %>"required>
          </div>
        </div>
        <div class="row mt-3">
          <label for="nombre" class="col-form-label col-sm-2">Nombre Jugador: </label>
          <div class="col-sm-8">
            <input type="text" name="nombre" class="form-control" Value="<%=J.getNombre()%>" required>
          </div>
        </div>
        <div class="row mt-3">
            <label for="apellido" class="col-form-label col-sm-2">Apellido Jugador: </label>
            <div class="col-sm-8">
              <input type="text" name="apellido" class="form-control" Value="<%=J.getApellido()%>" required>
            </div>
          </div>
        <div class="row mt-3">
          <label for="fechaNac" class="col-form-label col-sm-2">Fecha Nacimiento: </label>
          <div class="col-sm-8">
            <input type="date" name="fechaNac" class="form-control" Value="<%=J.getFecha_nacimiento()%>" required>
          </div>
        </div>
        <div class="row mt-3">
          <label for="posicion" class="col-form-label col-sm-2">Posicion: </label>
          <div class="col-sm-1">
            <input type="text" name="posicion" class="form-control" Value="<%=J.getPosicion()%>" >
          </div>
        </div>
        <div class="row mt-3">
          <label for="goles" class="col-form-label col-sm-2">Goles: </label>
          <div class="col-sm-1">
            <input type="number" name="goles" class="form-control" Value="<%=J.getGoles()%>" > 
          </div>
        </div>
        <div class="row mt-3">
            <label for="tarjA" class="col-form-label col-sm-2">Tarjetas Amarillas: </label>
            <div class="col-sm-1">
              <input type="number" name="tarjA" class="form-control" Value="<%=J.getTarjetasA()%>" > 
            </div>
          </div>
          <div class="row mt-3">
            <label for="tarjR" class="col-form-label col-sm-2">Tarjetas Rojas: </label>
            <div class="col-sm-1">
              <input type="number" name="tarjR" class="form-control" Value="<%=J.getTarjetasR()%>" > 
            </div>
        </div>
        <div class="row mt-3">
            <label for="partidosJ" class="col-form-label col-sm-2">Partidos Jugados: </label>
            <div class="col-sm-1">
              <input type="number" name="partidosJ" class="form-control" Value="<%=J.getPartidosJugados()%>" > 
            </div>
         </div>
          <div class="row mt-3">
            <label for="asistencias" class="col-form-label col-sm-2">Asistencias: </label>
            <div class="col-sm-1">
              <input type="number" name="asistencias" class="form-control" Value="<%=J.getAsistencias()%>" > 
            </div>
          </div>
        <div class="row mt-3 mb-3">
          <div class="offset-sm-1 col-sm-10">
          	<input type="hidden" name="idEquipo" class="form-control" value="<%=J.getEquipo().getIdEquipo()%>">	
            <button type="submit" class="btn btn-primary" name="accion" value="update"> Cargar</button>
          </div>
          
        </div>
      </form>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
      crossorigin="anonymous"></script>

</body>

</html>