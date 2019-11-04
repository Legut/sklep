<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Aktywacja użytkownika</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/activation-page.css">
</head>
<%
    Boolean activ = (Boolean) request.getAttribute("activation");
    if(activ){
%>
<body class="activation-succes-body">
<div class="main">
    <div class="content">
        <h1>Aktywacja konta zakończona powodzeniem!</h1>
        <img class="email-image" src="${pageContext.request.contextPath}/assets/images/mail-activation-tick.png" />
        <p>Twój email został potwierdzony. Oznacza to, że możesz już bez problemu zalogować się na swoje nowe konto i rozpocząć zakupy. W razie pytań możesz skontaktować się z nami mailowo: <a href="mailto:kontakt@monopolowy24h.pl">kontakt@monopolowy24h.pl</a> lub telefonicznie: <a href="tel:+48123456789">+48 123 456 789</p>
        <a href="${pageContext.request.contextPath}/login" title="Naciśnij aby przejść do logowania">
            <div class="button-go-to-login">
                <span>Przejdź do logowania</span>
            </div>
        </a>
        <div class="bottom-links">
            <a href="${pageContext.request.contextPath}/polityka-prywatnosci">Polityka prywatności</a>
            <span>---</span>
            <a href="${pageContext.request.contextPath}/faq">FAQ</a>
            <span>---</span>
            <a href="${pageContext.request.contextPath}/kontakt">Kontakt</a>
        </div>
    </div>
</div>
</body>
<%
    } else {
%>
<body class="activation-failed-body">
<div class="main">
    <div class="content">
        <h1>Ups! Coś poszło nie tak...</h1>
        <img class="email-image" src="${pageContext.request.contextPath}/assets/images/mail-activation-cross.png" />
        <p>Weryfikacja maila nie przebiegła zgodnie z planem. Link aktywujący jest niepoprawny. Spróbuj wysłać link aktywujący ponownie. Aby to zrobić zaloguj się na swoje nieaktywne konto i kliknij przycisk "Wyślij ponownie". W razie pytań możesz skontaktować się z nami mailowo: <a href="mailto:kontakt@monopolowy24h.pl">kontakt@monopolowy24h.pl</a> lub telefonicznie: <a href="tel:+48123456789">+48 123 456 789</p>
        <a href="${pageContext.request.contextPath}/login" title="Naciśnij aby przejść do logowania">
            <div class="button-go-to-login">
                <span>Przejdź do logowania</span>
            </div>
        </a>
        <div class="bottom-links">
            <a href="${pageContext.request.contextPath}/polityka-prywatnosci">Polityka prywatności</a>
            <span>---</span>
            <a href="${pageContext.request.contextPath}/faq">FAQ</a>
            <span>---</span>
            <a href="${pageContext.request.contextPath}/kontakt">Kontakt</a>
        </div>
    </div>
</div>
</body>
<%
    }
%>
</html>