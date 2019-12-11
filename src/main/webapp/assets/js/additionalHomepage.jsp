<%@ page import="java.util.HashMap"%><%@ page import="files.HomePageConfigFile"%><%@ page contentType="text/javascript;charset=UTF-8" %>
<%  HomePageConfigFile file = new HomePageConfigFile(request.getServletContext()); HashMap<String, String> configuration = file.getMap() ;%>
<% out.print(configuration.get("additionalHomepageJs")); %>
