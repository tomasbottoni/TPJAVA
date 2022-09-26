<%@page import="entities.Producto"%>
<%@page import="entities.Categoria" %>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicCategoria"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Productos</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">

	<%LogicCategoria lc = new LogicCategoria();%>
	<%LinkedList<Categoria> list =  lc.getAll();%>
	<%if(list == null) {list = new LinkedList<Categoria>();}%>
</head>
<body>
	<div class="container">
		<form action="ABMCproducto" method="GET" autocomplete="off">
		
			<input type="hidden" name="accion" value="insert">
			
			<div class="mb-3">
		    	<label class="form-label">Descripcion</label>
		    	<input id="descripcion" name="descripcion" type="text" class="form-control">
		  	</div>
		  	<div class="mb-3">
		    	<label class="form-label">Precio</label>
		    	<input id="precio" name="precio" type="text" class="form-control">
		  	</div>
		  	<div class="mb-3">
		    	<label class="form-label">Stock</label>
		    	<input id="stock" name="stock" type="text" class="form-control">
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