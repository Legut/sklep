<%@ page import="java.util.HashMap" %>
<%@ page import="files.GeneralConfigFile" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%  GeneralConfigFile file = new GeneralConfigFile(request.getServletContext());
    HashMap<String, String> configuration = file.getMap() ;%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="author" content="Wojciech Legutowski, Paweł Osiwała, Sylwester Wrzesiński">
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700" rel="stylesheet">

    <title><% out.print(configuration.get("seoTitle")); %></title>
    <meta name="description" content="<% out.print(configuration.get("seoDesc")); %>">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Pliki CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fa/css/all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/additional.jsp">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/flex-slider.css">
    <% String rola = null;
    if (session.getAttribute("user_role")!=null){
    	rola = session.getAttribute("user_role").toString();
    	if (rola.equals("ADMIN")) { %>
    		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/adminbar.css">
    	<%}
    }%>
    <% if(request.getAttribute("isHomepage")=="is") { %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/additionalHomepage.jsp">
    <% } %>

    <!-- Hook before </head> -->
    <% out.print(configuration.get("hookBeforeHeadEnd")); %>
  </head>
  <body>
    <!-- Hook after <body> -->
    <% out.print(configuration.get("hookAfterBody")); %>

  	<%  if (session.getAttribute("user_role")!=null){
            if (rola.equals("ADMIN")) { %>
                <jsp:include page="/WEB-INF/parts/adminbar.jsp"/>
            <%}
	    }%>
	<%  if (session.getAttribute("user_role")!=null){
            if (rola.equals("USER")) { %>
                <h1>TODO: userbar.jsp i userbar.css</h1>
            <%}
	    }%>