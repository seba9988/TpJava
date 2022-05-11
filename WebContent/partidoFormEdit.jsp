
<%@ page language="java" import="java.util.*,java.sql.*"
	import="Data.DataEntrenador"
	import="java.util.LinkedList"
	import="Entidades.Partido"
	import="Entidades.Equipo"
	import="java.util.Iterator"
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
	<%	
		Partido p=(Partido)request.getAttribute("partido");
		
	%>
    <div class="container border text-center">
      <p class="h1">Modificar Partido</p>
    <form action="PartidoControl" method=post>
      	<div class="row mt-3">
          <div class="col-sm-8">
            <input type="hidden" name="fecha" class="form-control" value="<%=p.getFecha()%>">
            <input type="hidden" name="hora" class="form-control" value="<%=p.getHora() %>">
            <input type="hidden" name="nroC" class="form-control" value="<%=p.getNumCancha() %>">
          </div>
        </div>
        <div class="row mt-3">
          <label for="resultado" class="col-form-label col-sm-2">Resultado Partido: </label>
          <div class="col-sm-8">
            <input type="text" name="resultado" class="form-control" Value="<%=p.getResultado()%>" required>
          </div>
        </div>
        <div class="row mt-3">
            <label for="incidencias" class="col-form-label col-sm-2">Incidencias Partido: </label>
            <div class="col-sm-8">
              <input type="text" name="incidencias" class="form-control" Value="<%=p.getIncidencias()%>" required>
            </div>
          </div>
        <div class="row mt-3">
          <label for="equipo2" class="col-form-label col-sm-2">Primer Equipo: </label>
          <div class="col-sm-8">
            <select class="form-select" aria-label="Default select example" name="equipo1">
                 <%EquipoLogic equipoL=new EquipoLogic(); 
                 Equipo e1=equipoL.getOne(p.getIdEquipo1());
                 Equipo e2=equipoL.getOne(p.getIdEquipo2());%>
            <option Selected value="<%=e1.getIdEquipo()%>"><%=e1.getNombre()%> </option>         
                <%                
                LinkedList<Equipo>listaE=equipoL.getAll();
                    
                    for(Equipo e : listaE) {
             %> 
                <option  value="<%=e.getIdEquipo()%>"><%=e.getNombre()%></option>
          
            <%}%>
            </select>
          </div>
        </div>
        <div class="row mt-3">
            <label for="equipo2" class="col-form-label col-sm-2">Segundo Equipo: </label>
            <div class="col-sm-8">
                <select class="form-select" aria-label="Default select example" name="equipo2">
                <option Selected value="<%=e2.getIdEquipo()%>" ><%=e2.getNombre()%> </option>
                    <%                       
                        for(Equipo e : listaE) {
                 %> 
                    <option  value="<%=e.getIdEquipo()%>"><%=e.getNombre()%></option>
              
                <%}%>
                </select>
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