<%@ page contentType="text/html;charset=UTF-8" %>
<div class="sidebar-left">
    <div class="backend-navigation">
        <ul class="backend-navigation-list">
            <a href="${pageContext.request.contextPath}/" class="backend-navigation-link" title="Powrót do strony głównej">
                <li class="backend-navigation-element"><i class="fas fa-home"></i> Home</li>
            </a>
            <a href="${pageContext.request.contextPath}/admin" class="backend-navigation-link" title="Powrót do strony głównej backendu">
                <li class="backend-navigation-element"><i class="fas fa-igloo"></i> Backend Home</li>
            </a>
            <div class="users-link">
                <a href="${pageContext.request.contextPath}/admin/user-manager" class="backend-navigation-link" title="Przejdź do menadżera użytkowników">
                    <li class="backend-navigation-element"><i class="fas fa-users"></i> Użytkownicy</li>
                </a>
                <ul class="dropdown users-dropdown">
                    <a href="${pageContext.request.contextPath}/admin/user-manager/add-user" class="backend-navigation-link" title="Dodaj nowego użytkownika">
                        <li><i class="fas fa-user-plus"></i> Dodaj nowego użytkownika</li>
                    </a>
                </ul>
            </div>
            <a href="${pageContext.request.contextPath}/admin/general-conf" class="backend-navigation-link" title="Ustawienia główne">
                <li class="backend-navigation-element"><i class="fas fa-cogs"></i> Ustawienia główne</li>
            </a>
            <a href="${pageContext.request.contextPath}/admin/media-manager" class="backend-navigation-link" title="Menadżer mediów">
                <li class="backend-navigation-element"><i class="fas fa-photo-video"></i> Menadżer mediów</li>
            </a>
            <div class="products-link">
                <a href="${pageContext.request.contextPath}/admin/product-manager" class="backend-navigation-link" title="Przejdź do menadżera produktów">
                    <li class="backend-navigation-element"><i class="fas fa-shopping-cart"></i> Produkty</li>
                </a>
                <ul class="dropdown product-dropdown">
                    <a href="${pageContext.request.contextPath}/admin/product-manager/add-product" class="backend-navigation-link" title="Dodaj nowy produkt">
                        <li><i class="fas fa-cart-plus"></i> Dodaj nowy produkt</li>
                    </a>
                </ul>
            </div>
            <a href="${pageContext.request.contextPath}/admin/homepage-editor" class="backend-navigation-link" title="Edytor strony głównej">
                <li class="backend-navigation-element"><i class="fas fa-cogs"></i> Homepage Editor</li>
            </a>
            <div class="category-link">
                <a href="${pageContext.request.contextPath}/admin/category-manager" class="backend-navigation-link" title="Menadżer kategorii">
                    <li class="backend-navigation-element"><i class="fa fa-tag" aria-hidden="true"></i> Menadżer kategorii</li>
                </a>
                <ul class="dropdown category-dropdown">
                    <a href="${pageContext.request.contextPath}/admin/category-manager/add-category" class="backend-navigation-link" title="Dodaj nową kategorię">
                        <li><i class="fas fa-tags"></i> Dodaj nową kategorię</li>
                    </a>
                </ul>
            </div>
        </ul>
    </div>
</div>