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
            <a href="${pageContext.request.contextPath}/admin/user-manager" class="backend-navigation-link" title="Przejdź do menadżera użytkowników">
                <li class="backend-navigation-element"><i class="fas fa-users"></i> Użytkownicy</li>
            </a>
        </ul>
    </div>
</div>