<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="Navbar">
	<a href="${pageContext.request.contextPath}/admin" title="Przejdź do backendu">
		<div class="Navbar__Link">
			<i class="fas fa-igloo"></i> Backend
		</div>
	</a>
	<nav class="Navbar__Items Navbar__Items--left">
		<div class="Navbar__Link">
			<a href="${pageContext.request.contextPath}/admin/user-manager" class="backend-navigation-link" title="Przejdź do menadżera użytkowników">
				<li class="backend-navigation-element"><i class="fas fa-users"></i> Użytkownicy</li>
			</a>
			<div class="dropdown-content">
				<ul>
					<a href="${pageContext.request.contextPath}/admin/user-manager/add-user" class="backend-navigation-link" title="Dodaj użytkownika">
						<li><i class="fas fa-user-plus"></i> Dodaj nowego użytkownika</li>
					</a>
				</ul>
			</div>
		</div>
		<a href="${pageContext.request.contextPath}/admin/general-conf" class="backend-navigation-link" title="Ustawienia główne">
			<div class="Navbar__Link">
				<li class="backend-navigation-element"><i class="fas fa-cogs"></i> Ustawienia główne</li>
			</div>
		</a>
		<div class="Navbar__Link">
			<a href="${pageContext.request.contextPath}/admin/product-manager" class="backend-navigation-link" title="Przejdź do menadżera produktów">
				<li class="backend-navigation-element"><i class="fas fa-shopping-cart"></i> Produkty</li>
			</a>
			<div class="dropdown-content">
				<ul>
					<a href="${pageContext.request.contextPath}/admin/product-manager/add-product" class="backend-navigation-link" title="Dodaj produkt">
						<li><i class="fas fa-cart-plus"></i> Dodaj nowy produkt</li>
					</a>
				</ul>
			</div>
		</div>
	</nav>
  	<nav class="Navbar__Items Navbar__Items--right">
	    <div class="Navbar__Link display-user-name">
		    <div>
				<i class="fas fa-user"></i> <% out.print(session.getAttribute("user_login").toString()); %>
		    </div>
		    <div class="dropdown-content">
				<ul>
					<a href="${pageContext.request.contextPath}/profil" title="Wyświetl ustawienia Twojego profilu"><li><i class="fas fa-user-cog"></i> Ustawienia</li></a>
					<a href="${pageContext.request.contextPath}/logout" title="Wyloguj się z konta"><li><i class="fas fa-sign-out-alt"></i> Wyloguj</li></a>
				</ul>
			</div>
    	</div>
  	</nav>
</div>