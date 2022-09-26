<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<title>Login</title> 
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" 
	rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	<link rel="stylesheet" href="css/Login_style.css">
</head>
<body>
	<main class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="login-form bg-light mt-4 p-4">
					<form method="post" action="login" class="row g3">
						<h4> Bienvenido</h4>
						<div class="col-12">
							<label> Usuario:</label>
							<input type="text" name="user" class="form-control" placeholder="Username">
						</div>
						<div class="col-12">
							<label> Contraseña:</label>
							<input type="password" name="pass" class="form-control" placeholder="Password">
						</div>
						<div class="col-12">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="rememberMe">
                                <label class="form-check-label" for="rememberMe"> Recuerdame</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <button type="submit" class="btn btn-dark float-end">Iniciar Sesión</button>
                        </div>
                        <% 
                        if(request.getAttribute("msg") != null){ %>
                        <div class="col-12 text-danger">
                        	<label><%=request.getAttribute("msg") %></label>
                        </div>
                        <%} %>
					</form>
					<hr class="mt-4">
                    <div class="col-12">
                        <p class="text-center mb-0">¿Todavia no tienes una cuenta? <a href="register.jsp">Registrate</a></p>
                    </div>
				</div>
			</div>
		</div>		
	</main>
</body>
</html>