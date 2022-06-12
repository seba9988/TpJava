<%@ page import="data.DataEntrenador"
	import="java.util.LinkedList"
	import="entities.Entrenador"
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
</head>
<body>
 <nav><%@ include file="/Include/Head.html" %></nav>
 <section class="container-fluid">
   <article>
      <h1 class="text-white text-center mt-4">Lista Entrenadores</h1>
      <form action="EntrenadorServlet" method=get>
        <button type="submit" class="btn btn-primary" name="accion" value="formAdd">Agregar</button>
      </form>
      <table id="tableEntrenador" class="table table-dark table-hover table-striped">
        <thead> 
          <tr>
            <th>Dni</th>
            <th>Nombre </th>
            <th>Apellido</th>
            <th>Fecha nacimiento</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <%
          LinkedList<Entrenador>list= (LinkedList<Entrenador>)request.getAttribute("listEntrenadores");
          for(Entrenador listE : list) {	
          %>
          <tr>
            <th><%=listE.getDni()%></th>
            <th><%=listE.getNombre()%></th>
            <th><%=listE.getApellido()%></th>
            <th><%=listE.getFecha_nacimiento()%></th>
            <th>
              <form action="EntrenadorServlet" method=get style="display: inline-block;">
                <input type="hidden" name="dni" class="form-control" value="<%=listE.getDni()%>">	
                <button type="submit" class="btn btn-primary" name="accion" value="formEdit">Editar</button>
              </form>
              <form action="EntrenadorServlet" method=post style="display: inline-block;">
                <input type="hidden" name="dni" class="form-control" value="<%=listE.getDni()%>">	
                <button type="submit" class="btn btn-primary" name="accion" value="delete">Eliminar</button>
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