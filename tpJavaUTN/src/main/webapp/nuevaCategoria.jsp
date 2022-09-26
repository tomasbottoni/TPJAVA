<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>	Categorias</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<form action="ABMCcategoria" method="get" autocomplete="off">
		
			<input type="hidden" name="accion" value="insert">
			
		    <div class="mb-3">
		    	<label class="form-label">Denominacion</label>
		    	<input id="denominacion" name="denominacion" type="text" class="form-control">
		    	<button class="btn btn-primary" type="submit">Guardar</button>
		  	</div>
		</form>
		</div>
</body>
</html>