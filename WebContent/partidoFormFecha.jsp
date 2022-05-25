<%@ page language="java" import="java.util.*,java.sql.*"
	import ="java.time.LocalDate"
	import ="java.time.LocalTime"
	%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>fecha</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<%@ include file="/Include/Head.html" %>
    <br>
    <div class="container border text-center">
      <p class="h1">Seleccionar Fecha</p>   
      <p></p>
      <form action="PartidoServlet" method=get>
      	<div class="row mt-3">
          <label for="Fecha" class="col-form-label col-sm-2">Fecha: </label>
          <div class="col-sm-8">
            <input type="Date" name="Fecha" class="form-control" required>
          </div>
        </div>
        <div class="row mt-3">
          <label for="Hora" class="col-form-label col-sm-2">Hora: </label>
          <div class="col-sm-8">
            <input type="Time" name="Hora" class="form-control" required>
          </div>
          </div>
          <div class="row mt-3 mb-3">
          <div class="offset-sm-1 col-sm-10">       	 
            <button type="submit" class="btn btn-light" value="formAdd" name="accion"> Siguiente  </button>       
          </div>     
        </div>
          </form>
          </div>      