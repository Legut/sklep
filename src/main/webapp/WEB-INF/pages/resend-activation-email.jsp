<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ponowne wysyłanie maila aktywującego</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/account-not-verified.css">
</head>
<body>
<div id="info-container">
    <div class="info-container-inner">
        <h1>
            <%
                if(request.getAttribute("message")!=null) {
                    out.println(request.getAttribute("message"));
                } else {
                    out.println("Spróbuj przeprowadzić procedurę ponownie");
                }
            %>
        </h1>
        <p>
            <%
                if(request.getAttribute("desc")!=null) {
                    out.println(request.getAttribute("desc"));
                }
            %>
        </p>
        <a href="${pageContext.request.contextPath}/login" class="theme-button-link"><span class="theme-button">Login</span></a>
        <a href="${pageContext.request.contextPath}/" class="theme-button-link"><span class="theme-button">Home</span></a>
        <a href="${pageContext.request.contextPath}/faq" class="theme-button-link"><span class="theme-button">FAQ</span></a>
        <a href="${pageContext.request.contextPath}/kontakt" class="theme-button-link"><span class="theme-button">Kontakt</span></a>
    </div>
</div>
</body>
</html>
