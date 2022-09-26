<%@page import="logic.LogicProducto"%>
<%@page import="entities.Producto"%>
<%@page import="entities.Categoria" %>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicCategoria"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Categoria</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	
</head>
<body>
	<%LogicCategoria lc = new LogicCategoria();%>
	<%Categoria cat = (Categoria)request.getAttribute("categoria");%>
	<div class="container">
		<form action="ABMCcategoria" method="get">
		  	
		  	<input type="hidden" name="accion" value="update">
		  	<input name="id" type="hidden" class="form-control" value="<%=cat.getId_categoria() %>">
			<div class="mb-3">
		    	<label class="form-label">Denominacion</label>
		    	<input name="denominacion" type="text" class="form-control" value="<%=cat.getDenominacion() %>">
		    </div>
	  		<button id="guardar" name="guardar" type="submit" class="btn btn-primary">Guardar</button>
		</form>
	</div>
</body>
</html>