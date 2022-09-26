<%@page import="logic.LogicProducto"%>
<%@page import="entities.Flete"%>
<%@page import="entities.Categoria" %>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicFlete"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Flete</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	
</head>
<body>
	<%LogicFlete lf = new LogicFlete();%>
	<%Flete fle = (Flete)request.getAttribute("flete");%>
	<div class="container">
		<form action="ABMCadmin?accion=updateFlete" method="post">
		  	
			<div class="mb-3">
		    	<input name="id" type="hidden" class="form-control" value="<%=fle.getId_flete()%>">
		    	<label class="form-label">Nombre</label>
		    	<input name="nombre" type="text" class="form-control" value="<%=fle.getNombre() %>">
		    </div>
	  		<button id="guardar" name="guardar" type="submit" class="btn btn-primary">Guardar</button>
		</form>
	</div>
</body>
</html>