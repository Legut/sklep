<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Logowanie do panelu Admina</title>
	<link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700" rel="stylesheet">
	<link rel="stylesheet" href="/assets/css/login.css">
</head>
<body>
<div id="login-form-container">
	<div class="container-inner">
		<h1>Logowanie do Panelu Admina</h1>
		<form id="login" action="${pageContext.request.contextPath}/login" method="post">
			<div>
				<input type="hidden" name="redirectId" value="${param.redirectId}" />
				<input type="text" name="userName" class="form-control" placeholder="Login..." value= "${user.userName}" />
				<br><input type="text" name="password" class="form-control" placeholder="Hasło..." value= "${user.password}" />
			</div>
			<input type="submit" id="form-submit" value="Zaloguj" />
		</form>
		<p style="color: red;">${errorString}</p>
	</div>
</div>
</body>
</html>