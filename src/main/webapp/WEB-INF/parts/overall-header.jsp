<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Jedyny taki - monopolowy sklep z dostawą do domu. Zamów co potrzebujesz! Z przyjemnością dostarczymy Ci trunki.">
    <meta name="author" content="Wojciech Legutowski, Paweł Osiwała, Sylwester Wrzesiński">
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700" rel="stylesheet">

    <title>Monopolowy24h - Internetowy sklep monopolowy z dostawą do domu</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Pliki CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fa/css/all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.css">
    <% String rola = null;
    if (session.getAttribute("user_role")!=null){
    	rola = session.getAttribute("user_role").toString();
    	if (rola.equals("ADMIN")) { %>
    		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/adminbar.css">
    	<%}
    }%>
  </head>
  <body>
  	<% if (session.getAttribute("user_role")!=null){
	    if (rola.equals("ADMIN")) { %>
	    	<jsp:include page="/WEB-INF/parts/adminbar.jsp"/>
	    <%}
	}%>
	<% if (session.getAttribute("user_role")!=null){
	    if (rola.equals("USER")) { %>
	    	<h1>TODO: userbar.jsp i userbar.css</h1>
	    <%}
	}%>