<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Brak uprawnień!</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/activation-page.css">
</head>
<body class="no-access-body">
<div class="main">
    <div class="content">
        <h1>Nie masz uprawnień!</h1>
        <img class="email-image" src="${pageContext.request.contextPath}/assets/images/warning-image.png" />
        <p>Próbujesz dostać się do miejsca, gdzie nie powinno Cię być. Skorzystaj z jednego z poniższych guzików aby wrócić do sklepu.</p>
        <a href="${pageContext.request.contextPath}/" title="Naciśnij aby przejść do strony głównej">
            <div class="button-go-to-login">
                <span>Home</span>
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
</html>
