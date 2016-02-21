<%@ page language="java" contentType="text/html;" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="currentUser" class="com.dsic.beans.User" scope="session" />
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
			<span class="currentUser"><jsp:getProperty property="firstName" name="currentUser"/> <jsp:getProperty property="lastName" name="currentUser"/></span>
			<a href="../LogoutController" class="ion ion-power pushRight"></a>
		</div>
		
		<div class="notes">
			
			<c:forEach items="${currentUserNotes}" var="note">
				<div class="note ${note.importance}-importance pushLeft">
					<div class="actions pushRight">
						<a href="#" class="ion ion-close-round pushRight"></a>
						<a href="#" data-id="${note.identifier}" class="ion ion-edit pushRight actionEdit"></a>
					</div>
					<p>${note.content}</p>
					<span class="ion ion-calendar creationDate">${note.date}</span>
				</div>
			</c:forEach>
			
			<div class="note addNote pushLeft">
				<a href="AddNote.jsp" class="ion ion-plus-round actionAdd" ></a>
			</div>
			
			<div class="clear"></div>
		</div>
		
		<div class="footer">
			<p>Get the source code at</p>
			<a href="https://github.com/MouadEch-chaaba/DsicNotes" target="blank" class="ion ion-social-github"></a>
		</div>
		
		<!-- Add / Edit note form -->
		<div class="form-wrapper">
			<div class="form">
				<div class="form-header"><span>Editer une note</span><i class="ion ion-close-round form-close"></i></div>
				
				<div class="form-body">
					<form action="#" method="post">
					<!-- note ID -->
					<input id="noteId" type="hidden" name="identifier" /> 
					
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
							<option value="high">Elevé</option>
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