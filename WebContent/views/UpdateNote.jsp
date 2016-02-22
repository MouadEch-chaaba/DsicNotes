<%@  page errorPage="../views/Error.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="currentNote" class="com.dsic.beans.Note" scope="session" />
<jsp:setProperty property="identifier" name="currentNote" />
<jsp:setProperty property="date" name="currentNote" />
<jsp:setProperty property="owner" name="currentNote" />
<jsp:setProperty property="importance" name="currentNote" />
<jsp:setProperty property="content" name="currentNote" />
<jsp:forward page="../UpdateNoteController" />

