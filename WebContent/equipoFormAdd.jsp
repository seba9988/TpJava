
<%@ page language="java"
 import="java.util.*,java.sql.*"
 import="entities.Entrenador"%>
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
</head>

<body>
<%@ include file="/Include/Head.html" %>
   
    <br>

    <div class="container border text-center">
      <p class="h1">Cargar Datos del Equipo</p>
     
      <p> <% 
      
      %></p>
      <form action="EquipoServlet" method=post>
        <div class="row mt-3">
          <label for="nombre" class="col-form-label col-sm-2">Nombre Equipo: </label>
          <div class="col-sm-8">
            <input type="text" name="nombre" class="form-control" required>
          </div>
        </div>
        <div class="row mt-3">
          <label for="localidad" class="col-form-label col-sm-2">Localidad: </label>
          <div class="col-sm-8">
            <input type="text" name="localidad" class="form-control" required>
          </div>
        </div>
        <div class="row mt-3">
          <label for="puntaje" class="col-form-label col-sm-2">Puntaje: </label>
          <div class="col-sm-1">
            <input type="number" name="puntaje" class="form-control" required>
          </div>
        </div>
        <div class="row mt-3">
          <label for="difGol" class="col-form-label col-sm-2">Diferencia Goles: </label>
          <div class="col-sm-1">
            <input type="number" name="difGol" class="form-control" required> 
          </div>
        </div>
        <div class="row mt-3">
          <label for="entrenador" class="col-form-label col-sm-2">Entrenador: </label>
          <div class="col-sm-2">
      

     <select class="form-select" aria-label="Default select example" name="EntrenadorDni">
         <%
        LinkedList<Entrenador>listaE=(LinkedList<Entrenador>)request.getAttribute("listEntreDisp");
            for(Entrenador e : listaE) {
     %> 
        <option selected value="<%=e.getDni()%>"><%=e.getApellido()%></option>
    <%}%>
      </select>
          </div>
        </div>
 
        <div class="row mt-3 mb-3">
          <div class="offset-sm-1 col-sm-10">
            <button type="submit" class="btn btn-primary" name="accion" value="add"> Cargar</button>
          </div>
          
        </div>
      </form>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
      crossorigin="anonymous"></script>

</body>

</html>