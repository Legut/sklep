<%@ page import="objects.Product" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Nagłówek -->
<jsp:include page="/WEB-INF/admin/parts/overall-header.jsp"/>
<!-- Nawigacja sidebar -->
<jsp:include page="/WEB-INF/admin/parts/sidebar-menu.jsp"/>
<!-- Kontent -->
<div class="content product-manager">
    <div class="content-inside">
        <h1 class="backend-page-title"><i class="fas fa-shopping-bag"></i> Menadżer produktów - edytuj produkt</h1>
        <div class="form-container">
            <% if(request.getAttribute("singleProduct") != null) { Product singleProduct = (Product) request.getAttribute("singleProduct"); %>
            <form method="post" action="${pageContext.request.contextPath}/admin/product-manager/edit-product">
                <div class="input-row">
                    <p class="input-element"><span>ID:</span> <br /> <input type="text" name="productId" value="<% out.println(singleProduct.getId()); %>" title="Id nie może zostać zmienione" readonly></p>
                    <p class="input-element"><span>Nazwa produktu:</span> <br /> <span style="font-size: 8px">Nazwa produktu musi zawierać minimum 3 znaki.</span> <br />
                        <input type="text" name="product_name" pattern=".{3,}" value="<% out.println(singleProduct.getName()); %>" title="Nazwa produktu musi zawierać minimum 3 znaki" required></p></div>
                <div class="input-row">
                    <p class="input-element"><span>Kategoria: </span> <br /> <span style="font-size: 8px">---</span> <br /> <input type="text" name="category"
                        title="---" value="<% out.println(singleProduct.getCategory()); %>" required></p>
                    <p class="input-element"><span>Ilość na stanie:</span> <br /> <input type="text" name="quantity" value="<% out.println(singleProduct.getQuantity()); %>" required></p></div>
                <div class="input-row">
                    <p class="input-element"><span>Ilość sprzedanych:</span> <br /> <input type="text" name="quantity_sold" value="<% out.println(singleProduct.getQuantity_sold()); %>" required></p>
                    <p class="input-element"><span>Czy na wyprzedaży:</span> <br /> <input type="text" name="on_sale" value="<% out.println(singleProduct.getOn_sale()); %>" required></p>
                </div>
                <div class="input-row">
                    <p class="input-element"><span>Data dodania:</span> <br /> <input type="date" name="date_added" value="<% out.print(singleProduct.getDate_added()); %>" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" min="1900-01-01" required></p>
                    <p class="input-element"><span>Cena:</span> <br /> <input type="text" name="price" value="<% out.println(singleProduct.getPrice()); %>"></p></div>
                <div class="input-row">
                    <p class="input-element"><span>Opis:</span> <br /> <input type="text" name="description" value="<% out.println(singleProduct.getDescription()); %>"></p>
                    <p class="input-element"><span>Id galerii:</span> <br /> <input type="text" name="gallery_id" value="<% out.println(singleProduct.getGallery_id()); %>"></p>
                </div>
                <p class="input-element submit-element"><input type="submit" value="Zapisz zmiany"></p>
            </form>
            <% } %>
            <p class="info-msg"><% if(request.getAttribute("msg") != null){ out.println(request.getAttribute("msg")); request.setAttribute("msg", null); } %></p>
        </div>
    </div>
</div>
<!-- Stopka -->
<jsp:include page="/WEB-INF/admin/parts/overall-footer.jsp"/>