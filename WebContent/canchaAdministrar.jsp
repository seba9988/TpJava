<%@ page
	import="java.util.LinkedList"
	import="entities.Cancha"
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
 		<form action="CanchaServlet" method=get>
    	<button type="submit" class="btn btn-primary" name="accion" value="formAdd">Agregar</button>
    	</form>
.<div class="container-fluid">
    <br>
    <table id="tableCancha" class="table table-bordered ">
        <tr>
        	<th>Numero de Cancha</th>
            <th>Nombre </th>
            <th></th>
        </tr>
        <%
       		LinkedList<Cancha>list= (LinkedList<Cancha>)request.getAttribute("listaCancha");
			for(Cancha listC : list) {			
        %>
        <tr>
        	<th><%=listC.getNroCancha()%></th>
            <th><%=listC.getNombre()%></th>
            <th><form action="CanchaServlet" method=get>
            	<input type="hidden" name="numC" class="form-control" value="<%=listC.getNroCancha()%>">	
            	<button type="submit" class="btn btn-primary" name="accion" value="formEdit">Editar</button>
            	</form>
            	<form action="CanchaServlet" method=post>
            	<input type="hidden" name="numC" class="form-control" value="<%=listC.getNroCancha()%>">
            	<button type="submit" class="btn btn-primary" name="accion" value="delete">Eliminar</button>
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