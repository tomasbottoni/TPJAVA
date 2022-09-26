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
	<title>Producto</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	
	<%LogicCategoria lc = new LogicCategoria();%>
	<%LinkedList<Categoria> list =  (LinkedList<Categoria>)request.getAttribute("listaCat");%>
	<%if(list == null) {list = lc.getAll();}%>
</head>
<body>
	<%LogicProducto lp = new LogicProducto();%>
	<%Producto prod = (Producto)request.getAttribute("producto");%>
	<div class="container">
		<form action="ABMCproducto" method="get">
			
			<input type="hidden" name="accion" value="update">
			
			<div class="mb-3">
		    	<input name="id" type="hidden" class="form-control" value="<%=prod.getId() %>">
		  	</div>
		  	
			<div class="mb-3">
		    	<label class="form-label">Descripcion</label>
		    	<input name="descripcion" type="text" class="form-control" value="<%=prod.getDescripcion() %>">
		  	</div>
		  	<div class="mb-3">
		    	<label class="form-label">Precio</label>
		    	<input name="precio" type="text" class="form-control" value="<%=prod.getPrecio() %>">
		  	</div>
		  	<div class="mb-3">
		    	<label class="form-label">Stock</label>
		    	<input name="stock" type="text" class="form-control" value="<%=prod.getStock() %>">
		  	</div>
		  	
		   <label class="label px-1">Seleccione una categoria</label>
			<% for (Categoria c : list) {  %>	  
				<div class="form-check">
  					<input class="form-check-input" type="radio" name="tipoProducto" id="<%=c.getId_categoria()%>" value="<%=c.getDenominacion()%>" checked>
  					<label class="form-check-label" for="<%=c.getId_categoria()%>"> <%=c.getDenominacion()%> </label>
				</div>
			<% }%>	

	  		<button id="guardar" name="guardar" type="submit" class="btn btn-primary">Guardar</button>
		</form>
	</div>
</body>
</html>