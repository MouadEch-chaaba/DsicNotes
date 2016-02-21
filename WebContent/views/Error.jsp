<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>User not found</title>
		<link rel="stylesheet" type="text/css" href="../css/ionicons.min.css" />
		<link rel="stylesheet" type="text/css" href="../css/Error.css" />
	</head>
<body>
	<h1><%=  exception!=null?  exception.getMessage().toString():"" %></h1>
</body>
</html>