<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <h1>Rejestracja</h1>
        <form action="register" method="post">
            <p><span>Login:</span> <br /> <input type="text" name="userlogin"></p>
            <p><span>Hasło:</span> <br /> <input type="password" name="pwd"></p>
            <p><span>Imię:</span> <br /> <input type="text" name="firstname"></p>
            <p><span>Nazwisko:</span> <br /> <input type="text" name="lastname"></p>
            <p><span>Email:</span> <br /> <input type="text" name="email"></p>
            <p><input type="submit" value="Zarejestruj"></p>
        </form>
    </div>
</div>
</body>
</html>
