<%@ page errorPage="../views/Error.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="currentUser" class="com.dsic.beans.User" scope="session" />
<jsp:setProperty property="*" name="currentUser"/>

<% 
if(currentUser.getLogin() == null || currentUser.getPassword() == null){ 
	throw new Exception("Username or password is empty");}
else{ %>
	<jsp:forward page="LoginController" />
<% } %>