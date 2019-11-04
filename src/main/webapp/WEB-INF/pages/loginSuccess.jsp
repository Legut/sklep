<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Zalogowano pomyślnie</title>
</head>
<body>
<%
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user_login")) userName = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}
%>
<h3>Witaj <%=userName %>, Logowanie zakończone pomyślnie.</h3>
<h3>ID Twojej sesji=<%=sessionID %></h3>
<br>
<a href="${pageContext.request.contextPath}/">Home</a>
<form action="logout" method="post">
<input type="submit" value="Logout" >
</form>
</body>
</html>