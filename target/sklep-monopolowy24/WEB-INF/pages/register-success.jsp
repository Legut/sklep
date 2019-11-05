<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Rejestracja powiodła się</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/account-not-verified.css">
</head>
<body>
<div id="info-container">
    <div class="info-container-inner">
        <h1>Rejestracja przebiegła pomyślnie</h1>
        <p>Aby zalogować się do sklepu musisz jeszcze aktywować konto. Aby to zrobić
        należy kliknąć link aktywacyjny przesłany na maila podanego przy rejestracji.</p>
        <p>Jeśli mail z linkiem aktywacyjnym nie trafił do Twojej skrzynki pocztowej,
        spróbuj zalogować się na nieaktywne konto. Wówczas będziesz miał możliwość ponownego
        przesłania linka aktywacyjnego.</p>
        <div class="buttons-row">
            <a href="${pageContext.request.contextPath}/login" class="theme-button-link"><span class="theme-button">Login</span></a>
            <a href="${pageContext.request.contextPath}/faq" class="theme-button-link"><span class="theme-button">FAQ</span></a>
            <a href="${pageContext.request.contextPath}/kontakt" class="theme-button-link"><span class="theme-button">Kontakt</span></a>
        </div>
    </div>
</div>
</body>
</html>
