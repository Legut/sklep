<%@ page import="java.util.ArrayList" %>
<%@ page import="objects.Category" %>
<%@ page import="dao.CategoryDAO" %>
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
            <form method="post" action="${pageContext.request.contextPath}/admin/product-manager/add-product">
                <div class="input-row" style="width: 100%">
                    <p class="input-element"><span>Nazwa produktu:</span> <br /> <span style="font-size: 8px">Nazwa produktu musi zawierać minimum 3 znaki.</span> <br />
                        <input type="text" name="product_name" pattern=".{3,}" title="Nazwa produktu musi zawierać minimum 3 znaki" required></p>
                    <% ArrayList<Category> categoryList = CategoryDAO.getCategoriesList(); %>
                    <p class="input-element"><span>Kategoria: </span> <br /> <span style="font-size: 8px">Kategorię można wybrać wyłącznie z listy utworzonych kategorii. Jeśli chcesz użyć kategorii, która nie znajduje się na liście przejdź do menadżera kategorii.</span> <br />
                        <select name="category" title="Kategorię można wybrać wyłącznie z listy utworzonych kategorii. Jeśli chcesz użyć kategorii, która nie znajduje się na liście przejdź do menadżera kategorii." required>
                            <% for (Category cat: categoryList) { %>
                                <option value="<% out.print(cat.getId()); %>"><% out.print(cat.getCategoryName()); %></option>
                            <% } %>
                        </select>
                    </p>
                </div>
                <div class="input-row">
                    <p class="input-element"><span>Ilość na stanie:</span> <br /> <input type="number" min="0" value="0" step="1" name="quantity" required></p>
                    <p class="input-element"><span>Ilość sprzedanych:</span> <br /> <input type="number" min="0" value="0" step="1" name="quantity_sold" required></p>
                    <p class="input-element"><span>Cena:</span> <br /> <input type="number" min="0" value="0.00" step="0.01"  name="price" required></p>
                </div>
                <div class="input-row">
                    <p class="input-element"><span>Cena po obniżce:</span> <br /> <input type="number" min="0" value="0.00" step="0.01" name="sale_price" required></p>
                    <p class="input-element"><span>Wyróżniony:</span> <br /> <select name="featured">
                        <option value="true">Tak</option>
                        <option value="false">Nie</option>
                    </select></p>
                    <p class="input-element"><span>Data dodania:</span> <br /> <input type="date" name="date_added" value="<% out.print(request.getAttribute("currentDate")); %>" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" min="1900-01-01" required></p>
                </div>
                <div class="input-row">
                    <p class="input-element" style="width:100%"><span>Opis:</span> <br /><textarea name="description"></textarea></p>
                </div>
                <div class="input-row">
                    <p class="input-element"><span>Obrazek wyróżniający:</span> <br /> <input type="text" id="PhotoLinkOne" name="photoLinkOne"> <input type="button" class="showButton" onclick="mediaManagerDisplay('PhotoLinkOne')" value="Wybierz"></p>
                </div>
                <div class="input-row">
                    <p class="input-element"><span>Obrazek drugi:</span> <br /> <input type="text" id="PhotoLinkTwo" name="photoLinkTwo"> <input type="button" class="showButton" onclick="mediaManagerDisplay('PhotoLinkTwo')" value="Wybierz"></p>
                </div>
                <div class="input-row">
                    <p class="input-element"><span>Obrazek trzeci:</span> <br /> <input type="text" id="PhotoLinkThree" name="photoLinkThree"> <input type="button" class="showButton" onclick="mediaManagerDisplay('PhotoLinkThree')" value="Wybierz"></p>
                </div>
                <div class="input-row">
                    <p class="input-element"><span>Obrazek czwarty:</span> <br /> <input type="text" id="PhotoLinkFour" name="photoLinkFour"> <input type="button" class="showButton" onclick="mediaManagerDisplay('PhotoLinkFour')" value="Wybierz"></p>
                </div>
                <div style="display: none" id="ImageChooseWindow">
                    <input type="button" class="hideButton showButton" onclick="mediaManagerHide()" value="Schowaj">
                    <iframe id="MediaFrame" src="${pageContext.request.contextPath}/admin/media-manager-frame"></iframe>
                </div>
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