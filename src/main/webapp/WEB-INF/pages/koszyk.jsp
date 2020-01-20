<%@ page import="java.util.ArrayList" %>
<%@ page import="objects.Product" %>
<%@ page import="dao.ProductDAO" %>
<%@ page import="objects.Category" %>
<%@ page import="dao.CategoryDAO" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="shoppingLogic.ShoppingCart" %>
<%@ page import="objects.CartProduct" %>
<%@ page import="util.ConstantValues" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<jsp:include page="/WEB-INF/parts/overall-header.jsp"/>
    <jsp:include page="/WEB-INF/parts/sloganbar.jsp"/>
    <!-- Nawigacja -->
    <jsp:include page="/WEB-INF/parts/navigation.jsp"/>
    <!-- Początek koszyka -->

    <% double total = 0; %>

    <div class="featured-page">
      <div class="container">
        <div class="row">
          <div class="col-md-3 col-sm-12">
            <div class="section-heading">
              <div class="line-dec"></div>
              <h1 id="PageTitle">Koszyk</h1>
            </div>
          </div>
          <div class="col-md-9 col-sm-12">
              <h6><% if(request.getAttribute("msg")!=null) out.print(request.getAttribute("msg")); %></h6>
          </div>
        </div>
      </div>
    </div>

    <div class="featured cart container no-gutter">
        <div id="ListOfProducts" class="row">
            <%  ArrayList<CartProduct> cartProducts = (ArrayList<CartProduct>) request.getAttribute("cartProducts");
                if(cartProducts!=null){
                    for(CartProduct cartProduct: cartProducts) { %>
                        <div id="product-<% out.print(cartProduct.getProduct().getId()); %>" class="item col-md-12">
                            <div class="cart-item row">
                                <div class="col-md-2">
                                    <a href="${pageContext.request.contextPath}/sklep/produkt?id=<% out.print(cartProduct.getProduct().getId()); %>">
                                        <% if(!cartProduct.getProduct().getImageOne().isEmpty()) { %>
                                            <img src="<% out.print(cartProduct.getProduct().getImageOne()); %>" alt="<% out.print(cartProduct.getProduct().getProduct_name() + " - " + cartProduct.getProduct().getCategory()); %>">
                                        <% } else { %>
                                            <img src="/assets/images/products/product-placeholder.jpg" alt="<% out.print(cartProduct.getProduct().getProduct_name() + " - " + cartProduct.getProduct().getCategory()); %>">
                                        <% } %>
                                    </a>
                                </div>
                                <div class="product-info col-md-5">
                                    <h3><% out.print(cartProduct.getProduct().getProduct_name()); %></h3>
                                    <% if(cartProduct.getProduct().getSale_price()>0) { %>
                                        <h6>Cena za szt.: <span style="font-size: 10px; color: darkgrey; text-decoration: line-through"><% out.print(cartProduct.getProduct().getPrice()); %> zł</span> <% out.print(cartProduct.getProduct().getSale_price()); %> zł</h6>
                                    <% } else { %>
                                        <h6>Cena za szt.: <% out.print(cartProduct.getProduct().getPrice()); %> zł</h6>
                                    <% } %>
                                    <h6>Kategoria: <% out.print(cartProduct.getProduct().getCategory()); %></h6>
                                    <%  String[] desc = cartProduct.getProduct().getDescription().split(" ");
                                        if(desc.length>40) {
                                            int splitIndex = ConstantValues.ordinalIndexOf(cartProduct.getProduct().getDescription(), " ", 40);
                                            out.print("<p>" + cartProduct.getProduct().getDescription().substring(0, splitIndex) + "...</p>");
                                        } else {
                                            out.print("<p>" + cartProduct.getProduct().getDescription() + "</p>");
                                        } %>
                                </div>
                                <div class="amount-and-price col-md-5">
                                    <% if(cartProduct.getProduct().getSale_price()>0) { %>
                                    <h3><span style="font-size: 10px; color: darkgrey; text-decoration: line-through"><% out.print(cartProduct.getProduct().getPrice()*cartProduct.getQuantity()); %> zł</span> <% out.print(cartProduct.getProduct().getSale_price()*cartProduct.getQuantity()); %> zł</h3>
                                    <% } else { %>
                                    <h3><% out.print(cartProduct.getProduct().getPrice()*cartProduct.getQuantity()); %> zł</h3>
                                    <% } %>
                                    <h6 class="mb-10px">Ilość egzemplarzy na stanie: <% out.print(cartProduct.getProduct().getQuantity()); %></h6>
                                    <form action="/koszyk" method="post" class="mb-10px">
                                        <h6>Ilość w koszyku:
                                            <input name="productId" type="hidden" value="<% out.print(cartProduct.getProduct().getId()); %>">
                                            <input id="amount-<% out.print(cartProduct.getProduct().getId()); %>" name="amount" type="number" value="<% out.print(cartProduct.getQuantity()); %>" min="0" max="<% out.print(cartProduct.getProduct().getQuantity()); %>">
                                            <input type="submit" class="button" value="Zapisz">
                                        </h6>
                                    </form>
                                    <form action="/koszyk" method="post" class="mb-10px">
                                        <input name="productId" type="hidden" value="<% out.print(cartProduct.getProduct().getId()); %>">
                                        <input name="remove" type="hidden" value="true">
                                        <input type="submit" class="button" value="usuń pozycję z koszyka">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <% if(cartProduct.getProduct().getSale_price()>0) {
                            total += cartProduct.getProduct().getSale_price() * cartProduct.getQuantity();
                        } else {
                            total += cartProduct.getProduct().getPrice() * cartProduct.getQuantity();
                        }
                    }
                } %>
        </div>
        <% if(total>0) {
            out.print("<div id=\"Summary\">");
                out.print("<h3 id=\"SummaryValue\">Suma: " + total + " zł</h3>");
                out.print("<form action=\"/zamowienie\" method=\"post\">");
                    out.print("<input type=\"submit\" class=\"button\" value=\"Zamów!\">");
                out.print("</form>");
            out.print("</div>");
        } else {
            out.print("<div id=\"EmptyCartInfo\"><h3>Nie ma żadnych produktów w koszyku</h3><h6>Przejdź do zakładki <a href=\"/sklep\" title=\"Sklep\">Sklep</a>, aby dodać coś do koszyka.</h6></div>");
        } %>
    </div>
    <!-- Koniec koszyka -->

    <!-- Formularz do subskrypcji -->
	<jsp:include page="/WEB-INF/parts/subscribe-form.jsp"/>
    <!-- Stopka -->
    <jsp:include page="/WEB-INF/parts/overall-footer.jsp"/>