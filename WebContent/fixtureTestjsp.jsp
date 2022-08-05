<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
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
      <h1 class="text-white text-center mt-4">Torneo</h1>
      <button class="btn btn-success" type="button" data-bs-toggle="collapse" data-bs-target="#p" aria-expanded="false" aria-controls="p">Proximos Partidos</button> 
      <button class="btn btn-success" type="button" data-bs-toggle="collapse" data-bs-target="#topJugadores" aria-expanded="false" aria-controls="TopJugadores">Top Jugadores</button>
      </article>   
    <article class="row ">
    
          <article class="collapse multi-collapse col" id="p">
 	 		<table id="tableTorneo" class="table table-dark table-hover">   
 	 		<c:forEach  var="nro" begin ="1" end= "${nroSemanas}">  
          	<tr>
            <th><c:out value="Semana ${nro}"/></th>
	        </tr> 
	        <tbody> 
	        	<c:forEach var="Partido" items="${Partidos.get(nro-1)}">
	          	<tr>
		        <th><c:out value="${Partido.equipo1.nombre} VS ${Partido.equipo2.nombre} " /></th>
		        </tr>
		      </c:forEach> 
		   </c:forEach> 
           </tbody>       
      	   </table>       		
      	  </article>
      	  <article class="collapse multi-collapse col" id="topJugadores">
      	  	<table id="tableJugadores" class="table table-dark table-hover">   	 	  
 	 		<thead>
          	<tr>
            <th><c:out value="Jugadores"/></th>
	        </tr> 
	        </thead>
	        <tbody> 
	        	<c:forEach items="${Jugadores}" var="jugador" begin ="0" end= "5">
	          	<tr>
		        <th><c:out value="${jugador.nombre}" /></th>
		        </tr>
		        </c:forEach> 
           </tbody>       
      	   </table>       		
      	  </article>	 
   </article>
   <article>
      <button class="btn btn-success" type="button" data-bs-toggle="collapse" data-bs-target="#p" aria-expanded="false" aria-controls="p">Proximos Partidos</button> 
      <button class="btn btn-success" type="button" data-bs-toggle="collapse" data-bs-target="#topJugadores" aria-expanded="false" aria-controls="TopJugadores">Top Jugadores</button>
      </article>   
    <article class="row ">
    
          <article class="collapse multi-collapse col" id="p">
 	 		<table id="tableTorneo" class="table table-dark table-hover">   
 	 		<c:forEach  var="nro" begin ="1" end= "${nroSemanas}">  
          	<tr>
            <th><c:out value="Semana ${nro}"/></th>
	        </tr> 
	        <tbody> 
	        	<c:forEach var="Partido" items="${Partidos.get(nro-1)}">
	          	<tr>
		        <th><c:out value="${Partido.equipo1.nombre} VS ${Partido.equipo2.nombre} " /></th>
		        </tr>
		      </c:forEach> 
		   </c:forEach> 
           </tbody>       
      	   </table>       		
      	  </article>
      	  <article class="collapse multi-collapse col" id="topJugadores">
      	  	<table id="tableJugadores" class="table table-dark table-hover">   	 	  
 	 		<thead>
          	<tr>
            <th><c:out value="Jugadores"/></th>
	        </tr> 
	        </thead>
	        <tbody> 
	        	<c:forEach items="${Jugadores}" var="jugador" begin ="0" end= "5">
	          	<tr>
		        <th><c:out value="${jugador.nombre}" /></th>
		        </tr>
		        </c:forEach> 
           </tbody>       
      	   </table>       		
      	  </article>	 
   </article>      
  </section>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
    crossorigin="anonymous"></script>
</body>
</html>
