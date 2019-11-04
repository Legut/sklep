<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Zaloguj się do sklepu</title>
		<link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700" rel="stylesheet">
		<link rel="stylesheet" href="../assets/css/admin-login.css">
	</head>
	<body>
		<div id="login-form-container">
			<div class="container-inner">
				<h1>zły</h1>
				<h1>Zaloguj się do sklepu</h1>
				<form method="post" id="login" action="j_security_check">
					<p><span>Username:</span> <br /> <input type="text" placeholder="Login..." name="j_username"></p>
					<p><span>Password:</span> <br /> <input type="password" placeholder="Hasło..." name="j_password"></p>
					<p><input type="submit" id="form-submit" value="Zaloguj"></p>
				</form>
			</div>
		</div>
	</body>
</html>