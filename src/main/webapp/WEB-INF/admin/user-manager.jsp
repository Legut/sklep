<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="objects.User" %>
<%@ page import="java.util.ArrayList" %>
<!-- Nagłówek -->
<jsp:include page="/WEB-INF/admin/parts/overall-header.jsp"/>
<!-- Nawigacja sidebar -->
<jsp:include page="/WEB-INF/admin/parts/sidebar-menu.jsp"/>
<!-- Kontent -->
<div class="content user-manager">
    <div class="content-inside">
        <h1 class="backend-page-title"><i class="fas fa-users"></i> Menadżer użytkowników</h1>
        <p class="info-msg"><% if(request.getAttribute("msg") != null){ out.println(request.getAttribute("msg")); request.setAttribute("msg", null); } %></p>
        <div class="filters">
            <%
                int amountPerPage, currentPage, searchOption;
                String searchByUserName;
                if(request.getAttribute("amountOfUsers") != null) { out.println("<p>Ilość zarejestrowanych użytkowników spełniających wymagania: " + request.getAttribute("amountOfUsers") + "</p>"); }
                if(request.getAttribute("amountPerPage") != null){ amountPerPage = (int)((long)request.getAttribute("amountPerPage")); } else { amountPerPage = 0; }
                if(request.getAttribute("currentPage") != null){ currentPage = (int)((long)request.getAttribute("currentPage")); } else { currentPage = 0; }
                if(request.getAttribute("searchOption") != null){ searchOption = (int)request.getAttribute("searchOption"); } else { searchOption = 2; }
                if(request.getAttribute("searchByUserName") != null){ searchByUserName = (String) request.getAttribute("searchByUserName"); } else { searchByUserName = ""; }
            %>
            <form action="/admin/user-manager" method="post">
                <p>Ile użytkowników na jedną stronę:
                <select name="amountPerPage">
                    <option <% if(amountPerPage==5){ out.println("selected");} %>>5</option>
                    <option <% if(amountPerPage==10){ out.println("selected");} %>>10</option>
                    <option <% if(amountPerPage==20){ out.println("selected");} %>>20</option>
                    <option <% if(amountPerPage==50){ out.println("selected");} %>>50</option>
                    <option <% if(amountPerPage==100){ out.println("selected");} %>>100</option>
                        <% if(amountPerPage!=5 && amountPerPage!=10 && amountPerPage!=20 && amountPerPage!=50 && amountPerPage!=100){
                            out.println("<option selected>" + amountPerPage + "</option>");
                        } %>
                </select></p>
                <p>Wyszukaj login który
                    <select name="searchOption">
                        <option value="1" <% if(searchOption==1){ out.println("selected");} %>>zaczyna się na</option>
                        <option value="2" <% if(searchOption==2){ out.println("selected");} %>>zawiera</option>
                        <option value="3" <% if(searchOption==3){ out.println("selected");} %>>kończy się na</option>
                    </select>
                    <input type="text" name="searchByUserName" value="<% if(searchByUserName!=null){ out.println(searchByUserName);} %>"></p>
                <input type="submit" value="Zastosuj">
            </form>
        </div>
        <table class="data">
            <%
                if(request.getAttribute("list") != null){
                    ArrayList<User> list = (ArrayList<User>) request.getAttribute("list");
                    long i = 0;

                    out.println("<thead>" +
                            "<tr class=\"user-list-header\">" +
                            "<td class=\"user-list-header-item user-edit\">edytuj / usuń</td>" +
                            "<td class=\"user-list-header-item user-login\">Login</td>" +
                            "<td class=\"user-list-header-item user-pass\">Hasło</td>" +
                            "<td class=\"user-list-header-item user-name\">Imię</td>" +
                            "<td class=\"user-list-header-item user-last-name\">Nazwisko</td>" +
                            "<td class=\"user-list-header-item user-email\">Email</td>" +
                            "<td class=\"user-list-header-item user-role\">Rola</td>" +
                            "</tr>" +
                            "</thead>" +
                            "<tbody>");
                    if (!list.isEmpty()) {
                        for (User user : list) {
                            out.println("<tr class=\"user-row user-no-" + i + "\">" +
                                    "<td class=\"user-row-item user-edit\">" +
                                        "<a href=\"" + request.getContextPath() + "/admin/user-manager/edit-user?userId=" + user.getId() + "\">edytuj</a> / " +
                                        "<a href=\"" + request.getContextPath() + "/admin/user-manager?page=" + currentPage + "&amountPerPage=" + amountPerPage + "&searchOption=" + searchOption + "&searchByUserName=" + searchByUserName + "&deleteId=" + user.getId() + "\">usuń</a>" +
                                    "</td>" +
                                    "<td class=\"user-row-item user-login\">" + user.getUser_login() + "</td>" +
                                    "<td class=\"user-row-item user-pass\">" + user.getUser_pass() + "</td>" +
                                    "<td class=\"user-row-item user-name\">" + user.getFirst_name() + "</td>" +
                                    "<td class=\"user-row-item user-last-name\">" + user.getLast_name() + "</td>" +
                                    "<td class=\"user-row-item user-email\">" + user.getUser_email() + "</td>" +
                                    "<td class=\"user-row-item user-role\">" + user.getUser_role() + "</td>" +
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

                    if(currentPage<pagesToPrint){
                        %>

                        </table>
                        <div class="pages-list">

                        <% if (pagesToPrint>12) {
                            if (currentPage != 0) {
                                out.println("<a href=\"" + request.getContextPath() + "/admin/user-manager?page=" + (currentPage - 1) + "&amountPerPage=" + amountPerPage + "&searchOption=" + searchOption + "&searchByUserName=" + searchByUserName + "\">" +
                                        "<div class=\"link-no-0 previous-page\">Poprzednia</div>" +
                                        "</a>");
                            }

                            if(currentPage == 0){
                                out.println("<a href=\"" + request.getContextPath() + "/admin/user-manager?page=0&amountPerPage=" + amountPerPage + "&searchOption=" + searchOption + "&searchByUserName=" + searchByUserName + "\">" +
                                        "<div class=\"link-no-1 first-page pagination-active\">1</div>" +
                                        "</a>");
                            } else {
                                out.println("<a href=\"" + request.getContextPath() + "/admin/user-manager?page=0&amountPerPage=" + amountPerPage + "&searchOption=" + searchOption + "&searchByUserName=" + searchByUserName + "\">" +
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
                                    out.println("<a href=\"" + request.getContextPath() + "/admin/user-manager?page=" + j + "&amountPerPage=" + amountPerPage + "&searchOption=" + searchOption + "&searchByUserName=" + searchByUserName + "\">" +
                                            "<div class=\"link-no-" + (j + 1) + " pagination-active\">" + (j + 1) + "</div>" +
                                            "</a>");
                                } else {
                                    out.println("<a href=\"" + request.getContextPath() + "/admin/user-manager?page=" + j + "&amountPerPage=" + amountPerPage + "&searchOption=" + searchOption + "&searchByUserName=" + searchByUserName + "\">" +
                                            "<div class=\"link-no-" + (j + 1) + "\">" + (j + 1) + "</div>" +
                                            "</a>");
                                }
                            }

                            if(pagesToPrint-currentPage>4) {
                                out.println("<div class=\"three-dots-sep\">...</div>");
                            }

                            if(currentPage==pagesToPrint-1){
                                out.println("<a href=\"" + request.getContextPath() + "/admin/user-manager?page=" + (pagesToPrint - 1) + "&amountPerPage=" + amountPerPage + "&searchOption=" + searchOption + "&searchByUserName=" + searchByUserName + "\">" +
                                        "<div class=\"link-no-" + (pagesToPrint - 1) + " last-page pagination-active\">" + pagesToPrint + "</div>" +
                                        "</a>");
                            } else {
                                out.println("<a href=\"" + request.getContextPath() + "/admin/user-manager?page=" + (pagesToPrint - 1) + "&amountPerPage=" + amountPerPage + "&searchOption=" + searchOption + "&searchByUserName=" + searchByUserName + "\">" +
                                        "<div class=\"link-no-" + (pagesToPrint - 1) + " last-page\">" + pagesToPrint + "</div>" +
                                        "</a>");
                            }

                            if(currentPage!=pagesToPrint-1) {
                                out.println("<a href=\"" + request.getContextPath() + "/admin/user-manager?page=" + (currentPage + 1) + "&amountPerPage=" + amountPerPage + "&searchOption=" + searchOption + "&searchByUserName=" + searchByUserName + "\">" +
                                        "<div class=\"link-no-" + (pagesToPrint + 1) + " next-page\">Następna</div>" +
                                        "</a></div>");
                            }
                        } else {
                            if(currentPage!=0) {
                                out.println("<a href=\"" + request.getContextPath() + "/admin/user-manager?page=" + (currentPage - 1) + "&amountPerPage=" + amountPerPage + "&searchOption=" + searchOption + "&searchByUserName=" + searchByUserName + "\">" +
                                        "<div class=\"link-no-0 previous-page\">Poprzednia</div>" +
                                        "</a>");
                            }

                            for (int j=0; j<pagesToPrint; j++) {
                                if(currentPage==j) {
                                    out.println("<a href=\"" + request.getContextPath() + "/admin/user-manager?page=" + j + "&amountPerPage=" + amountPerPage + "&searchOption=" + searchOption + "&searchByUserName=" + searchByUserName + "\">" +
                                            "<div class=\"link-no-" + (j+1) + " pagination-active\">" + (j+1) + "</div></a>");
                                } else {
                                    out.println("<a href=\"" + request.getContextPath() + "/admin/user-manager?page=" + j + "&amountPerPage=" + amountPerPage + "&searchOption=" + searchOption + "&searchByUserName=" + searchByUserName + "\">" +
                                            "<div class=\"link-no-" + (j+1) + "\">" + (j+1) + "</div></a>");
                                }
                            }

                            if(currentPage!=pagesToPrint-1) {
                                out.println("<a href=\"" + request.getContextPath() + "/admin/user-manager?page=" + (currentPage + 1) + "&amountPerPage=" + amountPerPage + "&searchOption=" + searchOption + "&searchByUserName=" + searchByUserName + "\">" +
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