<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Registrarse</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
	<div class="container mt-4">
	<h2>Registro</h2>
	<p>Complete el formulario para realizar el registro</p>
	<form method="post" action="register" class="bg-light mt-1">
	  <div class="col-md-4">
	    <label for="inputName" class="col-sm-2 col-form-label">Nombre</label>
	    <div class="col-sm-10">
      		<input type="text" name="name" class="form-control" id="inputName" required>
    	</div>
	  </div>
	  <div class="col-md-4">
	    <label for="inputApellido" class="col-sm-2 col-form-label">Apellido</label>
	    <div class="col-sm-10">
	    	<input type="text" name="surname" class="form-control" id="inputApellido" required>
    	</div>
	  </div>
	  <div class="col-md-4">
	    <label for="inputUser" class="form-label">Username</label>
	    <div class="col-sm-10">
	    	<input type="text" name="username" class="form-control" id="inputUser" required>
    	</div>
	  </div>
	  <div class="col-md-6">
	    <label for="inputMail" class="form-label">Email</label>
	    <div class="col-sm-10">
	    	<input type="email" name="mail" class="form-control" id="inputMail" placeholder="name@example.com" required>
    	</div>
	  </div>
	  <div class="col-md-3">
	    <label for="inputPassword" class="form-label">Contraseña</label>
	    <div class="col-sm-10">
	     	<input type="password" name="password" class="form-control" id="inputPassword" required>
     	</div>
	  </div>
	  <div class="col-12 mt-3">
	    <div class="form-check">
	      <input class="form-check-input" type="checkbox" value="" id="invalidCheck2" required>
	      <label class="form-check-label" for="invalidCheck2">
	        Acepto los <a href="#">términos y condiciones</a>
	      </label>
	    </div>
	  </div>
	  <div class="col-12 mt-3">
	    <button class="btn btn-primary" type="submit">Registrarse</button>
	  </div>
	  <%if(request.getAttribute("msg") != null){ %>
	  <div class="col-12 mt-3 text-danger">
	  	<label><%=request.getAttribute("msg") %>></label>
	  </div>
	  <%} %>
	</form>
	</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>