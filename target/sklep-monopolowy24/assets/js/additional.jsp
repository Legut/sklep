<%@ page import="java.util.HashMap"%><%@ page import="files.GeneralConfigFile"%><%@ page contentType="text/javascript;charset=UTF-8" %>
<%  GeneralConfigFile file = new GeneralConfigFile(request.getServletContext()); HashMap<String, String> configuration = file.getMap() ;%>
<% out.print(configuration.get("additionalJs")); %>
