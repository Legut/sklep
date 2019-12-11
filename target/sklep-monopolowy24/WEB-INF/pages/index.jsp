<%@ page import="files.HomePageConfigFile" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="dao.ProductDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="objects.Product" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/parts/overall-header.jsp"/>
<jsp:include page="/WEB-INF/parts/sloganbar.jsp"/>
<!-- Nawigacja -->
<jsp:include page="/WEB-INF/parts/navigation.jsp"/>
<%  HomePageConfigFile file = new HomePageConfigFile(request.getServletContext());
    HashMap<String, String> configuration = file.getMap() ;%>
<!-- Początek Bannera -->
<div class="banner" title="<% out.print(configuration.get("sliderBackgroundTitle")); %>">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="caption">
                    <h2><% out.print(configuration.get("sliderHeader")); %></h2>
                    <div class="line-dec"></div>
                    <p><% out.print(configuration.get("sliderText")); %></p>
                    <% if(configuration.get("sliderButton").equals("on")) { %>
                        <div class="main-button">
                            <a href="<% out.print(configuration.get("sliderButtonLink"));%>"
                               ref="<% out.print(configuration.get("sliderButtonLinkFollow"));%>"
                               target="<% out.print(configuration.get("sliderButtonLinkTarget"));%>"><% out.print(configuration.get("sliderButtonText"));%></a>
                        </div>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Koniec Bannera -->

<% out.print(configuration.get("hookBeforeHomepageFeatured")); %>

<% if(configuration.get("featuredSectionDisplay").equals("on")) { %>
<!-- Początek wyróżnionych produktów -->
<div class="featured-items">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-heading">
                    <div class="line-dec"></div>
                    <h1><% out.print(configuration.get("featuredHeader")); %></h1>
                </div>
            </div>
            <div class="col-md-12">
                <div class="owl-carousel owl-theme">
                    <%
                        ArrayList<Product> lista = ProductDAO.getFeaturedProductsList(10);
                        for (Product produkt: lista) {
                    %>
                    <a href="${pageContext.request.contextPath}/single-product">
                        <div class="featured-item">
                            <img src="<% out.print(produkt.getImageOne()); %>" alt="Produkt - <% out.print(produkt.getProduct_name()); %>">
                            <h4><% out.print(produkt.getProduct_name()); %></h4>
                            <h6><% out.print(produkt.getPrice()); %> zł</h6>
                        </div>
                    </a>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Koniec wyróżnionych produktów -->
<% } %>

<% out.print(configuration.get("hookAfterHomepageFeatured")); %>

<!-- Formularz do subskrypcji -->
<jsp:include page="/WEB-INF/parts/subscribe-form.jsp"/>
<!-- Stopka -->
<jsp:include page="/WEB-INF/parts/overall-footer.jsp"/>