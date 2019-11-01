<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="Navbar">
	<a href="${pageContext.request.contextPath}/admin/backend">
		<div class="Navbar__Link">
			Backend
		</div>
	</a>
	<nav class="Navbar__Items">
		<a>
			<div class="Navbar__Link">
	  			Dodaj
			</div>
		</a>
	</nav>
  	<nav class="Navbar__Items Navbar__Items--right">
	    <div class="Navbar__Link display-user-name">
		    <div>
		    	<% out.print(session.getAttribute("user_login").toString()); %>
		    </div>
		    <div class="dropdown-content">
				<p>Ustawienia profilu</p>
			</div>
    	</div>
  	</nav>
</div>