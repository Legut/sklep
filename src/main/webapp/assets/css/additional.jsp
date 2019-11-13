<%@ page import="java.util.HashMap"%><%@ page import="files.GeneralConfigFile"%><%@ page contentType="text/css;charset=UTF-8" %>
<%  GeneralConfigFile file = new GeneralConfigFile(request.getServletContext()); HashMap<String, String> configuration = file.getMap() ;%>
h1 {
    font-size: <% out.print(configuration.get("h1FontSize")); %>px;
    font-weight: <% out.print(configuration.get("h1FontWeight")); %>!important;
}
h2 {
    font-size: <% out.print(configuration.get("h2FontSize")); %>px;
    font-weight: <% out.print(configuration.get("h2FontWeight")); %>!important;
}
h3 {
    font-size: <% out.print(configuration.get("h3FontSize")); %>px;
    font-weight: <% out.print(configuration.get("h3FontWeight")); %>!important;
}
h4 {
    font-size: <% out.print(configuration.get("h4FontSize")); %>px;
    font-weight: <% out.print(configuration.get("h4FontWeight")); %>!important;
}
h5 {
    font-size: <% out.print(configuration.get("h5FontSize")); %>px;
    font-weight: <% out.print(configuration.get("h5FontWeight")); %>!important;
}
h6 {
    font-size: <% out.print(configuration.get("h6FontSize")); %>px;
    font-weight: <% out.print(configuration.get("h6FontWeight")); %>!important;
}
p {
    font-size: <% out.print(configuration.get("pFontSize")); %>px;
    font-weight: <% out.print(configuration.get("pFontWeight")); %>!important;
}
.tester {
    background-color: <% out.print(configuration.get("tester")); %>;
}
.tester2 {
    background-color: <% out.print(configuration.get("tester2")); %>;
}
<% out.print(configuration.get("additionalCss")); %>

/* kolory zbiorczo */
/*.main-button a, .section-heading .line-dec, #pre-header, .banner .caption .line-dec,
.subscribe-form, .footer .social-icons a:hover, .single-product .right-content .button,
.single-product .right-content .down-content span a:hover i,
.about-page .right-content span a:hover i,
.contact-page .right-content span a:hover i,
.contact-page .right-content .button {
    background-color: #3a8bcd;
}
.navbar-dark .navbar-nav .nav-link:hover,
.navbar-dark .navbar-nav .active>.nav-link,
.navbar-dark .navbar-nav .nav-link.active,
.navbar-dark .navbar-nav .nav-link.show,
.navbar-dark .navbar-nav .show>.nav-link,
.featured-item:hover h4, .featured-item h6,
.subscribe-form .main-content form button,
.footer .footer-menu a:hover, .sub-footer a,
.single-product .right-content span,
.single-product .right-content .down-content span a:hover,
.featured .featured-item:hover h4,
.single-product .right-content h6 {
    color: #3a8bcd;
}
.navbar-dark .navbar-toggler, .page-navigation ul li a:hover,
.page-navigation ul li.current-page a {
    border-color: #3a8bcd;
    background-color: #3a8bcd;
}
.owl-theme .owl-dots .active span {
    background-color: #3a8bcd!important;
}
#filter .btn-primary.focus, .btn-primary:focus {
    color: #3a8bcd!important;
}*/



/* kolory osobno */
.main-button a {
    background-color: #3a8bcd;
}
.section-heading .line-dec {
    background-color: #3a8bcd;
}
#pre-header {
    background-color: #3a8bcd;
}
.navbar-dark .navbar-nav .nav-link:hover,
.navbar-dark .navbar-nav .active>.nav-link,
.navbar-dark .navbar-nav .nav-link.active,
.navbar-dark .navbar-nav .nav-link.show,
.navbar-dark .navbar-nav .show>.nav-link {
    color: #3a8bcd;
}
.navbar-dark .navbar-toggler {
    border-color: #3a8bcd;
    background-color: #3a8bcd;
}
.banner .caption .line-dec {
    background-color: #3a8bcd;
}
.featured-item:hover h4 {
    color: #3a8bcd;
}
.featured-item h6 {
    color: #3a8bcd;
}
.owl-theme .owl-dots .active span {
    background-color: #3a8bcd!important;
}
.subscribe-form {
    background-color: #3a8bcd;
}
.subscribe-form .main-content form button {
    color: #3a8bcd;
}
.footer .footer-menu a:hover {
    color: #3a8bcd;
}
.footer .social-icons a:hover {
    background-color: #3a8bcd;
}
.sub-footer a {
    color: #3a8bcd;
}
.featured .featured-item:hover h4 {
    color: #3a8bcd;
}
#filter .btn-primary.focus,
.btn-primary:focus {
    color: #3a8bcd!important;
}
.page-navigation ul li a:hover,
.page-navigation ul li.current-page a {
    background-color: #3a8bcd;
    border-color: #3a8bcd;
}
.single-product .right-content h6 {
    color: #3a8bcd;
}
.single-product .right-content span {
    color: #3a8bcd;
}
.single-product .right-content .button {
    background-color: #3a8bcd;
}
.single-product .right-content .down-content span a:hover {
    color: #3a8bcd;
}
.single-product .right-content .down-content span a:hover i {
    background-color: #3a8bcd;
}
.about-page .right-content span a:hover i {
    background-color: #3a8bcd;
}
.contact-page .right-content span a:hover i {
    background-color: #3a8bcd;
}
.contact-page .right-content .button {
    background-color: #3a8bcd;
}








