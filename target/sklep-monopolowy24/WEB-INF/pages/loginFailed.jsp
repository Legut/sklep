<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Refresh" content="5;url=/login">
    <title>Login Success Page</title>
</head>
<body>
<h3>Logowanie nie powiodło się. Podałeś złe dane.</h3>
<h4>Zostaniesz automatycznie przekierowany do strony logowania</h4>
<br>
<a href="${pageContext.request.contextPath}/">Home</a>
</body>
</html>
