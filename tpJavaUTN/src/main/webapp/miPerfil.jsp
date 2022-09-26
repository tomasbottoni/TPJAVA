<%@page import="entities.Usuario"%>
<%@page import="logic.LogicUsuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Mi Perfil</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<%Usuario us = (Usuario)session.getAttribute("user");%>

</head>
<body>
<!--CABECERA-->
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">
				<img src="img/java-logo.png" width="150px">
			</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      			<span class="navbar-toggler-icon"></span>
    		</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
    			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
    				<li class="nav-item">
    					<a class="nav-link" aria-current="page" href="#">Home</a>
    				</li>
    				<li class="nav-item">
    					<a class="nav-link" aria-current="page" href="./listProducts.jsp">Productos</a>
    				</li>
    				<li class="nav-item dropdown"> 
    					<a class="nav-link dropdown-toggle active" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" href="#">Mi cuenta</a>
    					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
    						<li><a class="dropdown-item" href="./miPerfil.jsp">Perfil</a></li>
    						<li><a class="dropdown-item" href="MainPage?accion=logout">Cerrar Sesión</a></li>
    					</ul>
    				</li>
    			</ul>
    			<ul class="navbar-nav me-4 mb-2 mb-lg-end">
    			<%if(us.getTipoUsuario().getId_TipoUsuario() == 1){ %>
                    <li class="nav-item dropdown"> 
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" href="#">Configuracion</a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="./Fletes.jsp">Fletes</a></li>
                            <li><a class="dropdown-item" href="./Localidades.jsp">Localidades</a></li>
                            <li><a class="dropdown-item" href="./Zonas.jsp">Zonas</a></li>
                        </ul>
                    </li>
                    <%} %>
    				<li class="nav-item">
    					<a class="nav-link" aria-current="page" href="./Carro_de_compras.jsp">Mi carrito</a>
    				</li>
    				<li class="nav-item">
    					<a class="nav-link" aria-current="page" href="./MisCompras.jsp">Mis Compras</a>
    				</li>
    			</ul>
    			<form class="d-flex">
        			<input class="form-control me-2" type="search" placeholder="¿Qué estas buscando?" aria-label="Search"
        			size="40">
        			<button class="btn btn-outline-success" type="submit">Buscar</button>
      			</form>
    		</div>
		</div>
	</nav>
	<!--CABECERA-->

<div class="col-lg-8 mx-auto p-3 py-md-5">
  <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
    <a href="#" class="d-flex align-items-center text-dark text-decoration-none">
      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
  		<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
  		<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
	  </svg>
      <span class="fs-4 mx-2">Mi Perfil</span>
    </a>
  </header>

  <main>

    <form method="post" action="UserMB?accion=update" class="bg-light mt-1">
    <div class="col-md-4">
	    <div class="col-sm-10">
      		<input type="hidden" name="id_user" class="form-control" value=<%=us.getId_usuario() %>>
    	</div>
	  </div>
	  <div class="col-md-4">
	    <div class="col-sm-10">
      		<input type="hidden" name="usertype" class="form-control" value=<%=us.getTipoUsuario().getId_TipoUsuario() %>>
    	</div>
	  </div>
	  <div class="col-md-4">
	    <label for="inputName" class="col-sm-2 col-form-label">Nombre</label>
	    <div class="col-sm-10">
      		<input type="text" name="nombre" class="form-control" value=<%=us.getNombre() %> required>
    	</div>
	  </div>
	  <div class="col-md-4">
	    <label for="inputApellido" class="col-sm-2 col-form-label">Apellido</label>
	    <div class="col-sm-10">
	    	<input type="text" name="apellido" class="form-control" value=<%=us.getApellido() %> required >
    	</div>
	  </div>
	  <div class="col-md-4">
	    <label for="inputUser" class="form-label">Username</label>
	    <div class="col-sm-10">
	    	<input type="text" name="username" class="form-control" value=<%=us.getNombreUsuario() %> required>
    	</div>
	  </div>
	  <div class="col-md-6">
	    <label for="inputMail" class="form-label">Email</label>
	    <div class="col-sm-10">
	    	<input type="email" name="mail" class="form-control" value=<%=us.getEmail() %> required>
    	</div>
	  </div>
	  <div class="col-md-3">
	    <label for="inputPassword" class="form-label">Contraseña</label>
	    <div class="col-sm-10">
	     	<input type="password" name="clave" class="form-control" value=<%=us.getClave() %> required>
     	</div>
	  </div>
	  <div class="col-12 mt-3">
	    <button class="btn btn-primary" type="submit">Modificar datos</button>
	  </div>
	  <div class="col-12 mt-3">
	    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">
		  Eliminar usuario
		</button>
	  </div>
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Atención</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        Confirmar eliminación de usuario (Se eliminarán todos sus datos)
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
		        <button type="submit" class="btn btn-primary" formaction="UserMB?accion=delete">Aceptar</button>
		      </div>
		    </div>
		  </div>
		</div>
	</form>

  </main>
  <footer class="pt-5 my-5 text-muted border-top">
    &copy; 2021
  </footer>
  </div>


</body>
</html>