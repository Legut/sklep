<%@ page import="java.util.HashMap" %><%@ page import="files.GeneralConfigFile" %><%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  GeneralConfigFile file = new GeneralConfigFile(request.getServletContext());
    HashMap<String, String> configuration = file.getMap() ;%>
    <div class="sub-footer">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="copyright-text">
              <p><% out.print(configuration.get("copyrights")); %> - Design: <a rel="nofollow" href="#">Wojciech Legutowski</a>, <a rel="nofollow" href="#">Paweł Osiwała</a>, <a rel="nofollow" href="#">Sylwester Wrzesiński</a></p>
            </div>
          </div>
        </div>
      </div>
    </div>