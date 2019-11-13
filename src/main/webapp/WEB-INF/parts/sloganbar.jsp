<%@ page import="files.GeneralConfigFile" %><%@ page import="java.util.HashMap" %><%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  GeneralConfigFile file = new GeneralConfigFile(request.getServletContext());
    HashMap<String, String> configuration = file.getMap() ;%>
	<div id="pre-header">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <span><% out.print(configuration.get("sloganText")); %></span>
          </div>
        </div>
      </div>
    </div>