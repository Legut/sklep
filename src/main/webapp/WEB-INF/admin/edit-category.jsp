<%@ page import="objects.Category" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Nagłówek -->
<jsp:include page="/WEB-INF/admin/parts/overall-header.jsp"/>
<!-- Nawigacja sidebar -->
<jsp:include page="/WEB-INF/admin/parts/sidebar-menu.jsp"/>
<!-- Kontent -->
<div class="content category-manager">
    <div class="content-inside">
        <h1 class="backend-page-title"><i class="fas fa-tag"></i> Menadżer kategorii - edytuj kategorię</h1>
        <p class="info-msg"><% if(request.getAttribute("msg") != null){ out.println(request.getAttribute("msg")); request.setAttribute("msg", null); } %></p>
        <div class="form-container">
            <% if(request.getAttribute("singleCategory") != null) { Category singleCategory = (Category) request.getAttribute("singleCategory"); %>
            <form method="post" action="${pageContext.request.contextPath}/admin/category-manager/edit-category">
                <div class="input-row">
                    <p class="input-element"><span>ID:</span> <br /> <input style="max-width: 70px" type="text" name="categoryId" value="<% out.print(singleCategory.getId()); %>" title="Id nie może zostać zmienione" readonly></p>
                    <p class="input-element"><span>Nazwa kategorii:</span> <br /> <span style="font-size: 8px">Nazwa kategorii musi zawierać minimum 3 znaki.</span> <br />
                        <input type="text" name="category_name" pattern=".{3,}" value="<% out.print(singleCategory.getCategoryName()); %>" title="Nazwa kategorii musi zawierać minimum 3 znaki" required></p>
                    <p class="input-element"><span>URL kategorii: </span> <br /> <span style="font-size: 8px">URL kategorii musi zawierać minimum 3 znaki. Dozwolone są wyłącznie małe litery i cyfry. Nie należy korzystać ze spacji. Zamiastego należy używać znaku: '-'.</span> <br />
                        <input type="text" name="category_url" pattern="^(([a-z0-9-]){3,})$" value="<% out.print(singleCategory.getCategoryURL()); %>" title="URL kategorii musi zawierać minimum 3 znaki. Dozwolone są wyłącznie małe litery i cyfry. Nie należy korzystać ze spacji. Zamiastego należy używać znaku: '-'." required></p>
                </div>
                <p class="input-element submit-element"><input type="submit" value="Zapisz zmiany"></p>
            </form>
            <% } %>
        </div>
    </div>
</div>
<!-- Stopka -->
<jsp:include page="/WEB-INF/admin/parts/overall-footer.jsp"/>