<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Nagłówek -->
<jsp:include page="/WEB-INF/admin/parts/overall-header.jsp"/>
<!-- Nawigacja sidebar -->
<jsp:include page="/WEB-INF/admin/parts/sidebar-menu.jsp"/>
<!-- Kontent -->
<div class="content product-manager">
    <div class="content-inside">
        <h1 class="backend-page-title"><i class="fas fa-shopping-cart"></i> Menadżer produktów - dodaj produkt</h1>
        <p class="info-msg"><% if(request.getAttribute("msg") != null){ out.println(request.getAttribute("msg")); request.setAttribute("msg", null); } %></p>
        <div class="form-container">
            <form method="post" action="${pageContext.request.contextPath}/admin/product-manager/add-product" enctype="multipart/form-data">
                <div class="input-row" style="width: 100%">
                    <p class="input-element"><span>Nazwa produktu:</span> <br /> <span style="font-size: 8px">Nazwa produktu musi zawierać minimum 3 znaki.</span> <br />
                        <input type="text" name="product_name" pattern=".{3,}" title="Nazwa produktu musi zawierać minimum 3 znaki" required></p>
                    <p class="input-element"><span>Kategoria: </span> <br /> <span style="font-size: 8px">---</span> <br /> <input type="text" name="category" title="---" required></p>
                </div>
                <div class="input-row">
                    <p class="input-element"><span>Ilość na stanie:</span> <br /> <input type="text" name="quantity" required></p>
                    <p class="input-element"><span>Ilość sprzedanych:</span> <br /> <input type="text" name="quantity_sold" required></p>
                </div>
                <div class="input-row">
                    <p class="input-element"><span>Czy na wyprzedaży:</span> <br /> <input type="text" name="sale_price" required></p>
                    <p class="input-element"><span>Data dodania:</span> <br /> <input type="date" name="date_added" value="2000-01-01" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" min="1900-01-01" required></p>
                </div>
                <div class="input-row">
                    <p class="input-element"><span>Cena:</span> <br /> <input type="text" name="price" required></p>
                    <p class="input-element"><span>Opis:</span> <br /> <input type="text" name="description" required></p>
                </div>
                <div class="input-row">
                    <p class="input-element"><span>Obrazek wyróżniający:</span> <br /> <input type="text" id="PhotoLinkOne" name="photoLinkOne" required> <input type="button" class="showHideButton" onclick="mediaManagerDisplay(this)" value="Wybierz"></p>
                </div>
                <iframe id="MediaFrame" style="width: 100%; height: 700px; display: none" src="${pageContext.request.contextPath}/admin/media-manager-frame"></iframe>
                <div class="input-row">
                    <p class="input-element submit-element"><input type="submit" value="Dodaj produkt"></p>
                </div>
            </form>
            <p style="color: red; font-weight: bold">
                <%
                    if(request.getAttribute("msg")!=null){
                        out.println(request.getAttribute("msg"));
                    }
                %>
            </p>
        </div>
    </div>
</div>
<!-- Stopka -->
<jsp:include page="/WEB-INF/admin/parts/overall-footer.jsp"/>