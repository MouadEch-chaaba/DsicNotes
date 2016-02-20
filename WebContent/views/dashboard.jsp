<%@ page language="java" contentType="text/html;" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Dashboard</title>
		<link rel="stylesheet" href="../css/ionicons.min.css" />
		<link href='https://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="../css/dashboard.css" />
	</head>

<body>
	<div class="wrapper">
		<div class="header">
			<span class="currentUser">Mouad Ech-chaaba</span>
			<a href="#" class="ion ion-power pushRight"></a>
		</div>
		
		<div class="notes">
			<div class="note hight-importance pushLeft">
				<div class="actions pushRight">
					<a href="#" class="ion ion-close-round pushRight"></a>
					<a href="#" data-id="1" class="ion ion-edit pushRight actionEdit"></a>
				</div>
				<p>Search the difference between MVC1 and MVC2 in JEE</p>
				<span class="ion ion-calendar creationDate">20/02/2016</span>
			</div>
			
			<div class="note addNote pushLeft">
				<a href="#" class="ion ion-plus-round actionAdd" ></a>
			</div>
			
			<div class="clear"></div>
		</div>
		
		<div class="footer">
			<p>Get the source code at</p>
			<a href="https://github.com/MouadEch-chaaba/DsicNotes" class="ion ion-social-github"></a>
		</div>
		
		<!-- Add / Edit note form -->
		<div class="form-wrapper">
			<div class="form">
				<div class="form-header"><span>Editer une note</span><i class="ion ion-close-round form-close"></i></div>
				
				<div class="form-body">
					<form action="#" method="post">
					<!-- note ID -->
					<input id="noteId" type="hidden" name="id" /> 
					
					<!-- Note content -->
					<div class="form-feild">
						<label for="content">Contenu: </label>
						<textarea name="content"></textarea>
						<div class="clear"></div>
					</div>
					
					<!-- Note content -->
					<div class="form-feild">
						<label for="importance">Importance: </label>
						<select name="importance">
							<option value="hight">Elevé</option>
							<option value="medium">Moyenne</option>
							<option value="low">basse</option>
						</select>
						<div class="clear"></div>
					</div>
					
					<!-- Submit button -->
					<div class="form-feild">
						<input type="submit" value="Editer" />
						<div class="clear"></div>
					</div>
				</form>
				</div>
			</div>
		</div>
		
	</div>
	
	<script src="../js/jquery.min.js"></script>
	<script src="../js/dashboard.js"></script>
</body>
</html>