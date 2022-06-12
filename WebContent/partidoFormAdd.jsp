
<%@ page language="java" import="java.util.*,java.sql.*"
						 import="entities.*"					
						 import="java.util.LinkedList"%>
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
      <p class="h1">Cargar Datos del Partido</p>  
      <p> <%Partido partido=(Partido) request.getSession().getAttribute("partido");
      	System.out.println(partido.getFecha());%></p>
      <form action="PartidoServlet" method=post>
      	<div class="row mt-3">
          <label for="fecha" class="col-form-label col-sm-2">Fecha Partido: </label>
          <div class="col-sm-8">
            <input type="date" name="fecha" class="form-control" value="<%partido.getFecha();%>" readonly>
          </div>
        </div>
        <div class="row mt-3">
          <label for="hora" class="col-form-label col-sm-2">Hora Partido: </label>
          <div class="col-sm-8">
            <input type="time" name="hora" class="form-control" value="<%partido.getHora();%>"<%partido.getHora();%>readonly>
          </div>
        </div>
        <div class="row mt-3">
          <label for="equipo1" class="col-form-label col-sm-2">Primer Equipo: </label>
          <div class="col-sm-8">
            <select class="form-select" aria-label="Default select example" name="equipoId1">
                <%              
                LinkedList<Equipo>listaE=(LinkedList<Equipo>)request.getAttribute("listEquipos");                  
                    for(Equipo e : listaE) {
             %> 
                <option  value="<%=e.getIdEquipo()%>"><%=e.getNombre()%></option>
            <%}%>
            </select>
          </div>
        </div>
        <div class="row mt-3">
          <label for="equipo2" class="col-form-label col-sm-2">Segundo Equipo: </label>
          <div class="col-sm-3">
            <select class="form-select" aria-label="Default select example" name="equipoId2">
                <%                           
                    for(Equipo e : listaE) {
             %> 
                <option  value="<%=e.getIdEquipo()%>"><%=e.getNombre()%></option>     
            <%}%>
            </select>
          </div>
        </div>
        <div class="row mt-3">
          <label for="cancha" class="col-form-label col-sm-2">Cancha: </label>
          <div class="col-sm-3">
            <select class="form-select" aria-label="Default select example" name="nroC">
                <%
                LinkedList<Cancha>listaC=(LinkedList<Cancha>)request.getAttribute("listCanchas");                    
                    for(Cancha c : listaC) {
             %> 
                <option  value="<%=c.getNroCancha()%>"><%=c.getNombre()%></option>    
            <%}%>
            </select>
          </div>
        </div>
              <div class="row mt-3">
          <label for="equipo1" class="col-form-label col-sm-2">Arbitro: </label>
          <div class="col-sm-8">
            <select class="form-select" aria-label="Default select example" name="arbitroDni">
                <%              
                LinkedList<Arbitro>listaArb=(LinkedList<Arbitro>)request.getAttribute("listArbitros");                  
                    for(Arbitro arb : listaArb) {
             %> 
                <option  value="<%=arb.getDni()%>"><%=arb.getNombre()%></option>
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