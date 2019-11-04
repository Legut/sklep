<%@ page contentType="text/html;charset=UTF-8" %>
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
        <form action="${pageContext.request.contextPath}/register" method="post">
            <p><span>Login:</span> <br /> <span style="font-size: 8px">Login musi zawierać minimum 6 znaków.</span> <br />
                <input type="text" name="userlogin" pattern=".{6,}" title="Login musi zawierać minimum 6 znaków"></p>
            <p><span>Hasło </span> <br /> <span style="font-size: 8px">Hasło musi zawierać przynajmniej jedną cyfrę jedną wielką i jedną małą literę.<br>Dodatkowo hasło
                musi składać się z minimum 8 znaków.</span> <br /> <input type="password" name="pwd" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="
                Hasło musi zawierać przynajmniej jedną cyfrę jedną wielką i jedną małą literę. Dodatkowo hasło musi składać się z minimum 8 znaków."></p>
            <p><span>Imię:</span> <br /> <input type="text" name="firstname"></p>
            <p><span>Nazwisko:</span> <br /> <input type="text" name="lastname"></p>
            <p><span>Email:</span> <br /> <input type="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"></p>
            <p><input type="submit" value="Zarejestruj"></p>
        </form>
        <p style="color: red; font-weight: bold">
            <%
                if(request.getAttribute("message")!=null){
                    out.println(request.getAttribute("message"));
                }
            %>
        </p>
    </div>
</div>
</body>
</html>
