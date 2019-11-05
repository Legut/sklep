<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Konto nie zostało aktywowane przez link aktywacyjny wysłany na maila</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/account-not-verified.css">
</head>
<body>
<div id="info-container">
    <div class="info-container-inner">
        <h1>Twoje konto jest nieaktywne</h1>
        <p>Aby aktywować konto, sprawdź skrzynkę mailową o podanym przy rejestracji adresie. Tam powinna się znajdować wiadomość z linkiem aktywacyjnym.
            Jeśli wiadomość do Ciebie nie dotarła spróbuj wysłać ją ponownie wciskając poniższy guzik. W razie dalszych problemów sprawdź FAQ lub skontaktuj się z nami.</p>
        <div class="buttons-row">
            <form action="${pageContext.request.contextPath}/resend-activation-email" method="post">
                <input type="hidden" name="email" value="<% out.println(session.getAttribute("user_email")); %>">
                <input type="hidden" name="authkey" value="<% out.println(session.getAttribute("user_activation_key")); %>">
                <input type="submit" class="submit-resend-activation-email" value="Wyślij ponownie link aktywacyjny">
            </form>
            <a href="${pageContext.request.contextPath}/faq" class="theme-button-link"><span class="theme-button">FAQ</span></a>
            <a href="${pageContext.request.contextPath}/kontakt" class="theme-button-link"><span class="theme-button">Kontakt</span></a>
        </div>
    </div>
</div>
</body>
</html>
