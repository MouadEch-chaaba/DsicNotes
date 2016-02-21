<%@ page errorPage="../views/Error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="currentNote" class="com.dsic.beans.Note" scope="session" />
<jsp:setProperty property="*" name="currentNote"/>
<jsp:getProperty property="content" name="currentNote"/>
<jsp:getProperty property="importance" name="currentNote"/>
<jsp:forward page="../AddNoteController" />