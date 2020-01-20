<%@ page import="java.util.HashMap" %><%@ page import="files.GeneralConfigFile" %><%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%  GeneralConfigFile file = new GeneralConfigFile(request.getServletContext());
	HashMap<String, String> configuration = file.getMap() ;%>
	<div class="before-nav">
		<div class="container">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/"><img src="/<% out.print(configuration.get("logo")); %>" alt="<% out.print(configuration.get("logoAlt")); %>" title="<% out.print(configuration.get("logoTitle")); %>"></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
	</div>
	<%--<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/sklep">Sklep</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/o-nas">O nas</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/kontakt">Kontakt</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/login">Zaloguj się</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/register">Zarejestruj się</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/koszyk"><i class="fas fa-shopping-cart"></i> koszyk</a>
				</li>
			</ul>
		</div>
	</nav>--%>
<nav class="navbar center navbar-expand-lg navbar-light bg-light">
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse navbar-justify" id="navbarSupportedContent">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/sklep">Sklep</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/o-nas">O nas</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/kontakt">Kontakt</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/login">Logowanie</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/register">Rejestracja</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/koszyk"><i class="fas fa-shopping-cart"></i></a>
			</li>
		</ul>
		<%--<form class="form-inline my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>--%>
	</div>
</nav>