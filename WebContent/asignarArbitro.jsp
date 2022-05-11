<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
   	import="java.util.LinkedList"
	import="Entidades.Arbitro"
	import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Listar Arbitros libres</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
  <%@ include file="/Include/Head.html" %>
    <br>
            
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLiveLabel">Seleccionar Arbitro</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <table id="tableCancha" class="table table-bordered ">
        <tr>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Dni</th>
        <th>Fecha nacimiento</th>
        </tr>
        <%
        LinkedList<Arbitro>list=(LinkedList<Arbitro>)session.getAttribute("listaA");
		for(Arbitro listA : list) {
			
        %>
        <tr>
            <th><%=listA.getNombre()%></th>
			<th><%=listA.getApellido()%></th>
            <th><%=listA.getDni()%></th>
            <th><%=listA.getFecha_nacimiento() %></th>          
               <th>
               	<form action="AsignarArbitroControl" method="post" >	
               	<input type="hidden" name="dniselec" class="form-control" value="<%=listA.getDni()%>"required>
            	<button type="submit" class="btn btn-primary" name="accion" value="seleccion">Seleccionar</button>
            	</form>
           	   </th>
        </tr> 
          <%}%>              
           </table>
           
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
      crossorigin="anonymous"></script>
</body>
</html>