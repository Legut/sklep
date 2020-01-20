<%@ page import="java.util.HashMap" %><%@ page import="files.GeneralConfigFile" %><%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  GeneralConfigFile file = new GeneralConfigFile(request.getServletContext());
  HashMap<String, String> configuration = file.getMap() ;%>
    <div class="footer">
      <!-- Hook after <div class="footer"> -->
      <% out.print(configuration.get("hookAfterFooter")); %>

      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="logo">
              <a class="navbar-brand" href="${pageContext.request.contextPath}/"><img src="/<% out.print(configuration.get("logo")); %>" alt="<% out.print(configuration.get("logoAlt")); %>" title="<% out.print(configuration.get("logoTitle")); %>"></a>
            </div>
          </div>
          <div class="col-md-12">
            <div class="footer-menu">
              <ul>
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a href="#">Pomoc</a></li>
                <li><a href="#">Polityka prywatności</a></li>
                <li><a href="#">FAQ</a></li>
                <li><a href="${pageContext.request.contextPath}/kontakt">Kontakt</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md-12">
            <div class="social-icons">
              <ul>
                <% if(configuration.get("fbVisibility").equals("1")) { %>
                  <li><a href="<% out.print(configuration.get("fbLink")); %>" rel="<% if(configuration.get("fbLink").equals("1")){ out.print("dofollow"); }
                    else { out.print("nofollow"); } %>" target="<% if(configuration.get("fbTarget").equals("1")){ out.print("_self"); }
                    else { out.print("_blank"); } %>" title="<% out.print(configuration.get("fbTitle")); %>"><i class="fab fa-facebook-f"></i></a>
                  </li>
                <% } if(configuration.get("twitterVisibility").equals("1")) { %>
                  <li><a href="<% out.print(configuration.get("twitterLink")); %>" rel="<% if(configuration.get("twitterLink").equals("1")){ out.print("dofollow"); }
                    else { out.print("nofollow"); } %>" target="<% if(configuration.get("twitterTarget").equals("1")){ out.print("_self"); }
                    else { out.print("_blank"); } %>" title="<% out.print(configuration.get("twitterTitle")); %>"><i class="fab fa-twitter"></i></a>
                  </li>
                <% } if(configuration.get("igVisibility").equals("1")) { %>
                  <li><a href="<% out.print(configuration.get("igLink")); %>" rel="<% if(configuration.get("igLink").equals("1")){ out.print("dofollow"); }
                    else { out.print("nofollow"); } %>" target="<% if(configuration.get("igTarget").equals("1")){ out.print("_self"); }
                    else { out.print("_blank"); } %>" title="<% out.print(configuration.get("igTitle")); %>"><i class="fab fa-instagram"></i></a>
                  </li>
                <% } if(configuration.get("ytVisibility").equals("1")) { %>
                  <li><a href="<% out.print(configuration.get("ytLink")); %>" rel="<% if(configuration.get("ytLink").equals("1")){ out.print("dofollow"); }
                    else { out.print("nofollow"); } %>" target="<% if(configuration.get("ytTarget").equals("1")){ out.print("_self"); }
                    else { out.print("_blank"); } %>" title="<% out.print(configuration.get("ytTitle")); %>"><i class="fab fa-youtube"></i></a>
                  </li>
                <% } %>
              </ul>
            </div>
          </div>
        </div>
      </div>

      <!-- Hook before end of <div class="footer"> -->
      <% out.print(configuration.get("hookBeforeFooterEnd")); %>
    </div>
    <!-- Copyrights -->
	<jsp:include page="/WEB-INF/parts/copyrights.jsp"/>
    <!-- Załączone .js -->
    <jsp:include page="/WEB-INF/parts/footer-js.jsp"/>
    </body>
</html>