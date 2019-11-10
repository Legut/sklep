<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="dao.ProductDAO" %>
<%@ page import="objects.Product" %>
<%@ page import="java.util.ArrayList" %>
<!-- Nagłówek -->
<jsp:include page="/WEB-INF/admin/parts/overall-header.jsp"/>
<!-- Nawigacja sidebar -->
<jsp:include page="/WEB-INF/admin/parts/sidebar-menu.jsp"/>
<!-- Kontent -->
<div class="content">
    <div class="content-inside">
        <h1 class="backend-page-title"><i class="fas fa-shopping-bag"></i> Menadżer produktów</h1>
        <div class="filters">
            <h1>
                <%
                    if(request.getAttribute("amountOfProducts") != null) {
                        out.println("Ilość produktów w bazie: " + request.getAttribute("amountOfProducts"));
                    }
                %>
            </h1>
            <form action="/admin/product-manager" method="post">
                <select name="amountPerPage">
                    <option>5</option>
                    <option>10</option>
                    <option selected>20</option>
                    <option>50</option>
                    <option>100</option>
                </select>
                <input type="submit" value="Zastosuj">
            </form>
        </div>
        <table class="data">
            <%
                if(request.getAttribute("list") != null){
                    ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("list");
                    long i = 0;

                    out.println("<thead>" +
                            "<tr class=\"product-list-header\">" +
                            "<td class=\"product-list-header-item product-name\">Nazwa</td>" +
                            "<td class=\"product-list-header-item product-category\">Kategoria</td>" +
                            "<td class=\"product-list-header-item product-quantity\">Dostępna ilość</td>" +
                            "<td class=\"product-list-header-item product-quantity-sold\">Sprzedana ilość</td>" +
                            "<td class=\"product-list-header-item product-on-sale\">Wyprzedaż</td>" +
                            "<td class=\"product-list-header-item product-date-added\">Data dodania</td>" +
                            "<td class=\"product-list-header-item product-price\">Cena</td>" +
                            "<td class=\"product-list-header-item product-description\">Opis</td>" +
                            "<td class=\"product-list-header-item product-product-gallery-id\">ID Galerii</td>" +
                            "</tr>" +
                            "</thead>" +
                            "<tbody>");
                    if (!list.isEmpty()) {
                        for (Product product : list) {
                            out.println("<tr class=\"product-row product-no-" + i + "\">" +
                                    "<td class=\"product-row-item product-name\">" + product.getName() + "</td>" +
                                    "<td class=\"product-row-item product-category\">" + product.getCategory() + "</td>" +
                                    "<td class=\"product-row-item product-quantity\">" + product.getQuantity() + "</td>" +
                                    "<td class=\"product-row-item product-quantity-sold\">" + product.getQuantity_sold() + "</td>" +
                                    "<td class=\"product-row-item product-on-sale\">" + product.getOn_sale() + "</td>" +
                                    "<td class=\"product-row-item product-date-added\">" + product.getDate_added() + "</td>" +
                                    "<td class=\"product-row-item product-price\">" + String.format( "%.2f", product.getPrice() ) + "</td>" +
                                    "<td class=\"product-row-item product-description\">" + product.getDescription() + "</td>" +
                                    "<td class=\"product-row-item product-gallery-id\">" + product.getGallery_id() + "</td>" +
                                    "</tr>");
                            i++;
                        }
                    }
                    out.println("</tbody>");
                } else {
                    out.println("Coś poszło nie tak przy odbieraniu danych z bazy...");
                }

                if(request.getAttribute("pagesToPrint") != null) {
                    int pagesToPrint = (int) request.getAttribute("pagesToPrint");
                    int amountPerPage, currentPage;

                    if(request.getAttribute("currentPage") != null){
                        currentPage = (int)((long) request.getAttribute("currentPage"));
                    } else {
                        currentPage = 0;
                    }

                    if(request.getAttribute("amountPerPage") != null){
                        amountPerPage = (int)((long)request.getAttribute("amountPerPage"));
                    } else {
                        amountPerPage = 0;
                    }

                    if(currentPage<pagesToPrint){
                        %>

                        </table>
                        <div class="pages-list">

                        <% if (pagesToPrint>12) {
                            if (currentPage != 0) {
                                out.println("<a href=\"/admin/product-manager?page=" + (currentPage - 1) + "&amountPerPage=" + amountPerPage + "\">" +
                                        "<div class=\"link-no-0 previous-page\">Poprzednia</div>" +
                                        "</a>");
                            }

                            if(currentPage == 0){
                                out.println("<a href=\"/admin/product-manager?page=0&amountPerPage=" + amountPerPage + "\">" +
                                        "<div class=\"link-no-1 first-page pagination-active\">1</div>" +
                                        "</a>");
                            } else {
                                out.println("<a href=\"/admin/product-manager?page=0&amountPerPage=" + amountPerPage + "\">" +
                                        "<div class=\"link-no-1 first-page\">1</div>" +
                                        "</a>");
                            }

                            if(currentPage>3) {
                                out.println("<div class=\"three-dots-sep\">...</div>");
                            }

                            //Korekcja paginacji
                            int a=2, b=2;
                            switch (currentPage){
                                case 2: a=1; b=3;
                                    break;
                                case 1: a=0; b=4;
                                    break;
                                case 0: a=-1; b=5;
                                    break;
                            }
                            switch (pagesToPrint-currentPage){
                                case 0: a=6; b=-2;
                                    break;
                                case 1: a=5; b=-1;
                                    break;
                                case 2: a=4; b=0;
                                    break;
                                case 3: a=3; b=1;
                                    break;
                            }

                            for(int j=currentPage-a; j<=currentPage+b; j++) {
                                if(j==currentPage) {
                                    out.println("<a href=\"/admin/product-manager?page=" + j + "&amountPerPage=" + amountPerPage + "\">" +
                                            "<div class=\"link-no-" + (j + 1) + " pagination-active\">" + (j + 1) + "</div>" +
                                            "</a>");
                                } else {
                                    out.println("<a href=\"/admin/product-manager?page=" + j + "&amountPerPage=" + amountPerPage + "\">" +
                                            "<div class=\"link-no-" + (j + 1) + "\">" + (j + 1) + "</div>" +
                                            "</a>");
                                }
                            }

                            if(pagesToPrint-currentPage>4) {
                                out.println("<div class=\"three-dots-sep\">...</div>");
                            }

                            if(currentPage==pagesToPrint-1){
                                out.println("<a href=\"/admin/product-manager?page=" + (pagesToPrint - 1) + "&amountPerPage=" + amountPerPage + "\">" +
                                        "<div class=\"link-no-" + (pagesToPrint - 1) + " last-page pagination-active\">" + pagesToPrint + "</div>" +
                                        "</a>");
                            } else {
                                out.println("<a href=\"/admin/product-manager?page=" + (pagesToPrint - 1) + "&amountPerPage=" + amountPerPage + "\">" +
                                        "<div class=\"link-no-" + (pagesToPrint - 1) + " last-page\">" + pagesToPrint + "</div>" +
                                        "</a>");
                            }

                            if(currentPage!=pagesToPrint-1) {
                                out.println("<a href=\"/admin/product-manager?page=" + (currentPage + 1) + "&amountPerPage=" + amountPerPage + "\">" +
                                        "<div class=\"link-no-" + (pagesToPrint + 1) + " next-page\">Następna</div>" +
                                        "</a></div>");
                            }
                        } else {
                            if(currentPage!=0) {
                                out.println("<a href=\"/admin/product-manager?page=" + (currentPage - 1) + "&amountPerPage=" + amountPerPage + "\">" +
                                        "<div class=\"link-no-0 previous-page\">Poprzednia</div>" +
                                        "</a>");
                            }

                            for (int j=0; j<pagesToPrint; j++) {
                                if(currentPage==j) {
                                    out.println("<a href=\"/admin/product-manager?page=" + j + "&amountPerPage=" + amountPerPage + "\">" +
                                            "<div class=\"link-no-" + (j+1) + " pagination-active\">" + (j+1) + "</div></a>");
                                } else {
                                    out.println("<a href=\"/admin/product-manager?page=" + j + "&amountPerPage=" + amountPerPage + "\">" +
                                            "<div class=\"link-no-" + (j+1) + "\">" + (j+1) + "</div></a>");
                                }
                            }

                            if(currentPage!=pagesToPrint-1) {
                                out.println("<a href=\"/admin/product-manager?page=" + (currentPage + 1) + "&amountPerPage=" + amountPerPage + "\">" +
                                        "<div class=\"link-no-" + (pagesToPrint + 1) + " next-page\">Następna</div>" +
                                        "</a>");
                            }
                        } %>
                        </div>
                    <% }
                    }%>
    </div>
</div>
<!-- Stopka -->
<jsp:include page="/WEB-INF/admin/parts/overall-footer.jsp"/>