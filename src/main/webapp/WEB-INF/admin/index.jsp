<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Backend - Home</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/backend.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fa/css/all.css">
</head>
<body>
<div class="sidebar-left">
    <div class="backend-navigation">
        <ul class="backend-navigation-list">
            <a href="${pageContext.request.contextPath}/" class="backend-navigation-link" title="Powrót do strony głównej">
                <li class="backend-navigation-element"><i class="fas fa-home"></i> Home</li>
            </a>
            <a href="${pageContext.request.contextPath}/admin" class="backend-navigation-link" title="Powrót do strony głównej backendu">
                <li class="backend-navigation-element"><i class="fas fa-igloo"></i> Backend Home</li>
            </a>
            <a href="${pageContext.request.contextPath}/admin/menadzer-uzytkownikow" class="backend-navigation-link" title="Przejdź do menadżera użytkowników">
                <li class="backend-navigation-element"><i class="fas fa-users"></i> Użytkownicy</li>
            </a>
        </ul>
    </div>
</div>
<div class="content"><h1>Backend Homepage</h1></div>
</body>
</html>
