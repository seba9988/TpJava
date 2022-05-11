
<%@ page language="java" import="java.util.*,java.sql.*"
	import="Data.DataEntrenador"
	import="java.util.LinkedList"
	import="Entidades.Entrenador"
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
		Entrenador e=(Entrenador)session.getAttribute("Entrenador");
		System.out.println(e);
	%>
    <div class="container border text-center">
      <p class="h1">Modificar Entrenador</p>
    <form action="EntrenadorControl" method=post>
      	<div class="row mt-3">
          <div class="col-sm-8">
            <input type="hidden" name="dni" class="form-control" value="<%=e.getDni() %>"required>
          </div>
        </div>
        <div class="row mt-3">
          <label for="nombre" class="col-form-label col-sm-2">Nombre Entrenador: </label>
          <div class="col-sm-8">
            <input type="text" name="nombre" class="form-control" Value="<%=e.getNombre()%>" required>
          </div>
        </div>
        <div class="row mt-3">
            <label for="apellido" class="col-form-label col-sm-2">Apellido Entrenador: </label>
            <div class="col-sm-8">
              <input type="text" name="apellido" class="form-control" Value="<%=e.getApellido()%>" required>
            </div>
          </div>
        <div class="row mt-3">
          <label for="fechaNac" class="col-form-label col-sm-2">Fecha Nacimiento: </label>
          <div class="col-sm-8">
            <input type="date" name="fechaNac" class="form-control" Value="<%=e.getFecha_nacimiento()%>" required>
          </div>
        </div>
        <div class="row mt-3 mb-3">
          <div class="offset-sm-1 col-sm-10">
            <button type="submit" class="btn btn-primary" name="accion" value="Actualizar"> Cargar</button>
          </div>
          
        </div>
      </form>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
      crossorigin="anonymous"></script>

</body>

</html>