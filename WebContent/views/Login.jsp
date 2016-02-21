<%@ page errorPage="../views/Error.jsp" language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<jsp:useBean id="currentUser" class="com.dsic.beans.User" scope="session" />
<jsp:setProperty name="currentUser" property="*" />

<% 
if(currentUser.getLogin() == null || currentUser.getPassword() == null){ 
	throw new Exception("Username or pass is empty");}
else{ %>
	<jsp:forward page="../LoginController" />
<% } %>

