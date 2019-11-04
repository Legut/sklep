<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Zaloguj się do sklepu</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>
<body>
<div id="login-form-container">
    <div class="container-inner">
        <h1>Zaloguj się do sklepu</h1>
        <form action="login" method="post">
            <p><span>Login:</span> <br /> <input type="text" name="user"></p>
            <p><span>Hasło:</span> <br /> <input type="password" name="pwd"></p>
            <p><input type="submit" value="Zaloguj"></p>
        </form>
    </div>
</div>
</body>
</html>