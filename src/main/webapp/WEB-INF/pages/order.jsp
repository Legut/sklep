<%@ page import="objects.CartProduct" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<jsp:include page="/WEB-INF/parts/overall-header.jsp"/>
    <jsp:include page="/WEB-INF/parts/sloganbar.jsp"/>
    <!-- Nawigacja -->
    <jsp:include page="/WEB-INF/parts/navigation.jsp"/>
    <!-- Początek koszyka -->

    <%
        double total = 0;
        DecimalFormat df2 = new DecimalFormat("#.##");
    %>

    <div class="featured-page">
      <div class="container">
        <div class="row">
          <div class="col-md-3 col-sm-12">
            <div class="section-heading">
              <div class="line-dec"></div>
              <h1 id="PageTitle">Zamówienie</h1>
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
            <%  ArrayList<CartProduct> orderContents = (ArrayList<CartProduct>) request.getAttribute("orderContents");
                if(orderContents!=null){
                    for(CartProduct orderProduct: orderContents) { %>
                        <div id="product-<% out.print(orderProduct.getProduct().getId()); %>" class="item col-md-12">
                            <div class="cart-item row">
                                <div class="col-md-1">
                                    <a href="${pageContext.request.contextPath}/sklep/produkt?id=<% out.print(orderProduct.getProduct().getId()); %>">
                                        <% if(!orderProduct.getProduct().getImageOne().isEmpty()) { %>
                                            <img src="<% out.print(orderProduct.getProduct().getImageOne()); %>" alt="<% out.print(orderProduct.getProduct().getProduct_name() + " - " + orderProduct.getProduct().getCategory()); %>">
                                        <% } else { %>
                                            <img src="/assets/images/products/product-placeholder.jpg" alt="<% out.print(orderProduct.getProduct().getProduct_name() + " - " + orderProduct.getProduct().getCategory()); %>">
                                        <% } %>
                                    </a>
                                </div>
                                <div class="product-info col-md-6">
                                    <h3><% out.print(orderProduct.getProduct().getProduct_name()); %></h3>
                                    <% if(orderProduct.getProduct().getSale_price()>0) { %>
                                        <h6>Cena za szt.: <% out.print(orderProduct.getProduct().getSale_price()); %> zł</h6>
                                    <% } else { %>
                                        <h6>Cena za szt.: <% out.print(orderProduct.getProduct().getPrice()); %> zł</h6>
                                    <% } %>
                                    <h6>Zamówiona ilość: <% out.print(orderProduct.getQuantity()); %></h6>
                                </div>
                                <div class="amount-and-price col-md-5">
                                    <% if(orderProduct.getProduct().getSale_price()>0) { %>
                                    <h3>Razem: <% out.print(orderProduct.getProduct().getSale_price()*orderProduct.getQuantity()); %> zł</h3>
                                    <% } else { %>
                                    <h3>Razem: <% out.print(orderProduct.getProduct().getPrice()*orderProduct.getQuantity()); %> zł</h3>
                                    <% } %>
                                </div>
                            </div>
                        </div>
                        <% if(orderProduct.getProduct().getSale_price()>0) {
                            total += orderProduct.getProduct().getSale_price() * orderProduct.getQuantity();
                        } else {
                            total += orderProduct.getProduct().getPrice() * orderProduct.getQuantity();
                        }
                    }
                } %>
        </div>
        <div id="Summary">
            <div>
                <% if(total>0) {
                    out.print("<h3 id=\"SummaryValue\">Koszt całego zmówienia: " + total + " zł</h3>");
                } else {
                    out.print("<div id=\"EmptyCartInfo\"><h3>Nie ma żadnych produktów w zamówieniu</h3><h6>Przejdź do zakładki <a href=\"/koszyk\" title=\"Koszyk\">Sklep</a>, aby zamówić produkty znajdujące się w koszyku.</h6></div>");
                } %>
            </div>
            <% if(orderContents!=null && orderContents.size()>0){ %>
                <div class="mb-10px">
                    <h6>Dane przesyłki:</h6>
                </div>
                <div class="mb-10px">
                    <form action="/koszyk" method="post">
                        <input name="orderId" type="hidden" value="<% out.print(request.getAttribute("orderId")); %>">
                        <input name="disclaimOrder" type="hidden" value="true">
                        <input type="submit" class="button" value="Zmień zmówienie">
                    </form>
                </div>
                <div class="mb-10px">
                    <form action="/zamowienie" method="post">
                        <select name="payment">
                            <option value="1">Przelew tradycyjny</option>
                        </select>
                        <input type="submit" class="button" value="Zaplac!">
                    </form>
                </div>
            <% } %>
        </div>
    </div>
    <!-- Koniec koszyka -->

    <!-- Formularz do subskrypcji -->
	<jsp:include page="/WEB-INF/parts/subscribe-form.jsp"/>
    <!-- Stopka -->
    <jsp:include page="/WEB-INF/parts/overall-footer.jsp"/>