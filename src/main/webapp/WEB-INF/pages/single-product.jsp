<%@ page import="objects.Product" %>
<%@ page import="dao.ProductDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="files.HomePageConfigFile" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/parts/overall-header.jsp"/>
<jsp:include page="/WEB-INF/parts/sloganbar.jsp"/>
<!-- Nawigacja -->
<jsp:include page="/WEB-INF/parts/navigation.jsp"/>

<!-- Początek zawartości strony -->
<%  HomePageConfigFile file = new HomePageConfigFile(request.getServletContext());
    HashMap<String, String> configuration = file.getMap();
    Product p = null;
    if(request.getAttribute("product")!=null) {
        p = (Product) request.getAttribute("product");
    } %>

<div class="single-product">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-heading">
                    <div class="line-dec"></div>
                    <h1><% out.print(p.getProduct_name());%></h1>
                  </div>
            </div>
            <div class="col-md-6">
                <div class="product-slider">
                    <div id="slider" class="flexslider">
                        <ul class="slides">
                            <% if(p.getImageOne() != null && p.getImageOne().length()>0) { %>
                                <li><img src="<%out.print(p.getImageOne());%>" alt="<% out.print(p.getProduct_name() + " - " + p.getCategory()); %>" /></li>
                            <% } else { %>
                                <li><img src="/assets/images/products/product-placeholder.jpg" alt="<% out.print(p.getProduct_name() + " - " + p.getCategory()); %>" /></li>
                            <% } %>
                            <% if(p.getImageTwo() != null && p.getImageTwo().length()>0) { %>
                                <li><img src="<%out.print(p.getImageTwo());%>" alt="<% out.print(p.getProduct_name() + " - " + p.getCategory()); %>" /></li>
                            <% } %>
                            <% if(p.getImageThree() != null && p.getImageThree().length()>0) { %>
                                <li><img src="<%out.print(p.getImageThree());%>" alt="<% out.print(p.getProduct_name() + " - " + p.getCategory()); %>" /></li>
                            <% } %>
                            <% if(p.getImageFour() != null && p.getImageFour().length()>0) { %>
                                <li><img src="<%out.print(p.getImageFour());%>" alt="<% out.print(p.getProduct_name() + " - " + p.getCategory()); %>" /></li>
                            <% } %>
                        </ul>
                    </div>
                    <div id="carousel" class="flexslider">
                        <ul class="slides">
                            <% if(p.getImageOne() != null && p.getImageOne().length()>0) { %>
                                <li><img src="<%out.print(p.getImageOne());%>" alt="<% out.print(p.getProduct_name() + " - " + p.getCategory()); %>" /></li>
                            <% } %>
                            <% if(p.getImageTwo() != null && p.getImageTwo().length()>0) { %>
                                <li><img src="<%out.print(p.getImageTwo());%>" alt="<% out.print(p.getProduct_name() + " - " + p.getCategory()); %>" /></li>
                            <% } %>
                            <% if(p.getImageThree() != null && p.getImageThree().length()>0) { %>
                                <li><img src="<%out.print(p.getImageThree());%>" alt="<% out.print(p.getProduct_name() + " - " + p.getCategory()); %>" /></li>
                            <% } %>
                            <% if(p.getImageFour() != null && p.getImageFour().length()>0) { %>
                                <li><img src="<%out.print(p.getImageFour());%>" alt="<% out.print(p.getProduct_name() + " - " + p.getCategory()); %>" /></li>
                            <% } %>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="right-content">
                    <h4><% out.print(p.getProduct_name()); %></h4>
                    <% if(p.getSale_price() > 0) { %>
                        <h6><span style="text-decoration: line-through; font-size: 12px; color: darkgrey"><% out.print(p.getPrice()); %> zł</span>
                        <span style="font-size: 24px"><% out.print(p.getSale_price()); %> zł</span></h6>
                    <% } else { %>
                        <h6><% out.print(p.getPrice()); %> zł</h6>
                    <% } %>
                    <p>Na stanie: <span id="inStock"><% out.print(p.getQuantity()); %></span> szt.</p>
                    <div class="add-to-cart-form">
                        <form action="/sklep/produkt?id=<% out.print(p.getId()); %>" method="post">
                            <label>ilość: <input id="quantity" name="amount" type="number" class="quantity-text" min="1" max="<% out.print(p.getQuantity()); %>"
                                    onfocusout="verifyQuantity(<% out.print(p.getQuantity()); %>);" value="<% if(p.getQuantity()==0){out.print(0);}else{out.print(1);} %>"
                                    <% if(p.getQuantity()==0){ out.print("disabled"); } %>></label>
                            <input type="submit" class="button" value="Zamów!">
                        </form>
                    </div>
                    <div class="down-content">
                        <div class="categories">
                            <h6>Kategoria: <span>
                                <% out.print("<a href=\"#\">" + p.getCategory() + "</a>"); %>
                            </span>
                            </h6>
                        </div>
                        <p><% out.print(p.getDescription()); %></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Koniec zawartości strony -->

<!-- Początek podobnych pozycji -->
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
                    <%  ArrayList<Product> lista = ProductDAO.getFeaturedProductsList(10);
                        for (Product produkt: lista) { %>
                    <a href="${pageContext.request.contextPath}/sklep/produkt?id=<% out.print(produkt.getId()); %>">
                        <div class="featured-item">
                            <img src="<% out.print(produkt.getImageOne()); %>" alt="Produkt - <% out.print(produkt.getProduct_name()); %>">
                            <h4><% out.print(produkt.getProduct_name()); %></h4>
                            <% if(produkt.getSale_price()>0){ %>
                            <h6><span style="text-decoration: line-through; font-size: 12px; color: darkgrey"><% out.print(produkt.getPrice()); %> zł</span>
                                <span style="font-size: 18px"><% out.print(produkt.getSale_price()); %> zł!</span></h6>
                            <% } else { %>
                            <h6><% out.print(produkt.getPrice()); %> zł</h6>
                            <% } %>
                        </div>
                    </a>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Koniec podobnych pozycji -->

<!-- Formularz do subskrypcji -->
<jsp:include page="/WEB-INF/parts/subscribe-form.jsp"/>
<!-- Stopka -->
<jsp:include page="/WEB-INF/parts/overall-footer.jsp"/>