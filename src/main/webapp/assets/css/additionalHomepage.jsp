<%@ page import="java.util.HashMap"%><%@ page import="files.HomePageConfigFile"%><%@ page contentType="text/css;charset=UTF-8" %>
<%  HomePageConfigFile file = new HomePageConfigFile(request.getServletContext()); HashMap<String, String> configuration = file.getMap() ;%>
/* Banner Style */
.banner {
    background-image: url(<% out.print(configuration.get("sliderBackgroundUrl")); %>);
    background-size: cover;
    background-repeat: no-repeat;
    padding: 150px 0px;
    background-position: center center;
    <% if(configuration.get("sliderEffectsUsage").equals("on")) { %>
        background-color: <% out.print(configuration.get("sliderBackgroundColor")); %>;
        background-blend-mode: <% out.print(configuration.get("sliderMixedBlendMode")); %>;
    <% } %>
}
.banner .caption {
    background-color: <% out.print(configuration.get("sliderBlockBackgroundColor")); %>;
    opacity: <% out.print(configuration.get("sliderBlockOpacity")); %>;
    padding: 30px;
    max-width: 450px;
}
.banner .caption h2 {
    margin-top: 0px;
    margin-bottom: 15px;
    font-weight: 700;
    color: #1e1e1e;
}
.banner .caption .line-dec {
    width: 30px;
    height: 5px;
}
.banner .caption p {
    margin-top: 15px;
    margin-bottom: 20px;
}
<% out.print(configuration.get("additionalHomepageCss")); %>