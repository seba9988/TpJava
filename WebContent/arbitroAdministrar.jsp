<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>

<body>  
  <nav><%@ include file="/Include/Head.html" %></nav>
  <section class="container">
    <article>   
      <h1 class="text-white text-center mt-4">Lista Arbitros</h1>
      <form action="ArbitroServlet" method=get>
        <button type="submit" class="btn btn-success float-end" name="accion" value="formAdd"><i class="bi bi-plus-circle"></i> Agregar arbitro</i></button>
      </form>
      <table id="tableArbitro" class="table table-dark table-hover">
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
          <c:forEach items="${listArbitros}" var="arbitro">
          <tr>
            <th><c:out value="${arbitro.dni}" /></th>
            <th><c:out value="${arbitro.nombre}" /></th>
            <th><c:out value="${arbitro.apellido}" /></th>
            <th><c:out value="${arbitro.fechaNacimiento}" /></th>
            <th>
              <form action="ArbitroServlet" method=get style="display: inline-block;">
                <input type="hidden" name="dni" class="form-control" value="${arbitro.dni}">	
                <button type="submit" class="btn btn-outline-primary" name="accion" value="formEdit"><i class="bi bi-pencil-fill"></i></button>
              </form>
              <form action="ArbitroServlet" method=post style="display: inline-block;"> 
                <input type="hidden" name="dni" class="form-control" value="${arbitro.dni}">
                <button type="submit" class="btn btn-outline-danger" name="accion" value="delete"><i class="bi bi-trash-fill"></i></button>
              </form>
            </th>
          </tr> 
          </c:forEach>
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