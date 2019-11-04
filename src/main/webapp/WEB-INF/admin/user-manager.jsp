<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="dao.UserDAO" %>
<%@ page import="objects.User" %>
<%@ page import="java.util.ArrayList" %>
<!-- Nagłówek -->
<jsp:include page="/WEB-INF/admin/parts/overall-header.jsp"/>
<!-- Nawigacja sidebar -->
<jsp:include page="/WEB-INF/admin/parts/sidebar-menu.jsp"/>
<!-- Kontent -->
<div class="content">
    <div class="content-inside">
        <h1 class="backend-page-title"><i class="fas fa-users"></i> Menadżer użytkowników</h1>
        <div class="filters">
            <h1>
                <%
                    if(request.getAttribute("amountOfUsers") != null) {
                        out.println("Ilość zarejestrowanych użytkowników: " + request.getAttribute("amountOfUsers"));
                    }
                %>
            </h1>
        </div>
        <table class="data">
            <%
                if(request.getAttribute("list") != null){
                    ArrayList<User> list = (ArrayList<User>) request.getAttribute("list");
                    long i = 0;

                    out.println("<thead>" +
                            "<tr class=\"user-list-header\">" +
                            "<td class=\"user-list-header-item user-login\">Login</td>" +
                            "<td class=\"user-list-header-item user-pass\">Hasło</td>" +
                            "<td class=\"user-list-header-item user-name\">Imię</td>" +
                            "<td class=\"user-list-header-item user-last-name\">Nazwisko</td>" +
                            "<td class=\"user-list-header-item user-email\">Email</td>" +
                            "<td class=\"user-list-header-item user-role\">Rola</td>" +
                            "<td class=\"user-list-header-item user-activation-key\">Klucz aktywacyjny</td>" +
                            "</tr>" +
                            "</thead>" +
                            "<tbody>");
                    if (!list.isEmpty()) {
                        for (User user : list) {
                            out.println("<tr class=\"user-row user-no-" + i + "\">" +
                                    "<td class=\"user-row-item user-login\">" + user.getUser_login() + "</td>" +
                                    "<td class=\"user-row-item user-pass\">" + user.getUser_pass() + "</td>" +
                                    "<td class=\"user-row-item user-name\">" + user.getFirst_name() + "</td>" +
                                    "<td class=\"user-row-item user-last-name\">" + user.getLast_name() + "</td>" +
                                    "<td class=\"user-row-item user-email\">" + user.getUser_email() + "</td>" +
                                    "<td class=\"user-row-item user-role\">" + user.getUser_role() + "</td>" +
                                    "<td class=\"user-row-item user-activation-key\">" + user.getUser_activation() + "</td>" +
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

                    %>

                    </table>
                    <div class="pages-list">

                    <% if (pagesToPrint>8 && currentPage>2){
                        // TODO: Do poprawki potem
                        if(currentPage!=0) {
                            out.println("<a href=\"/admin/user-manager?page=" + (currentPage - 1) + "&amountPerPage=" + amountPerPage + "\">" +
                                    "<div class=\"link-no-0 previous-page\">Poprzednia</div>" +
                                    "</a>");

                            out.println("<a href=\"/admin/user-manager?page=0&amountPerPage=" + amountPerPage + "\">" +
                                    "<div class=\"link-no-1 first-page\">1</div>" +
                                    "</a>");
                        }

                        for (int j=currentPage-1; j<=pagesToPrint+1; j++) {
                            out.println("<a href=\"/admin/user-manager?page=" + j + "&amountPerPage=" + amountPerPage + "\">" +
                                    "<div class=\"link-no-" + j + "\">" + j + "</div></div>" +
                                    "</a>");
                        }

                        if(currentPage!=pagesToPrint) {
                            out.println("<a href=\"/admin/user-manager?page=" + pagesToPrint + "&amountPerPage=" + amountPerPage + "\">" +
                                    "<div class=\"link-no-" + (pagesToPrint + 2) + " last-page\">Ostatnia</div>" +
                                    "</a>");

                            out.println("<a href=\"/admin/user-manager?page=" + (currentPage + 1) + "&amountPerPage=" + amountPerPage + "\">" +
                                    "<div class=\"link-no-" + (pagesToPrint + 3) + " next-page\">Następna</div>" +
                                    "</a></div>");
                        }
                    } else {
                        if(currentPage!=0) {
                            out.println("<a href=\"/admin/user-manager?page=" + (currentPage - 1) + "&amountPerPage=" + amountPerPage + "\">" +
                                    "<div class=\"link-no-0 previous-page\">Poprzednia</div>" +
                                    "</a>");
                        }

                        for (int j=1; j<=pagesToPrint; j++) {
                            out.println("<a href=\"/admin/user-manager?page=" + (j-1) + "&amountPerPage=" + amountPerPage + "\">" +
                                    "<div class=\"link-no-" + j + "\">" + j + "</div></a>");
                        }

                        if(currentPage!=pagesToPrint-1) {
                            out.println("<a href=\"/admin/user-manager?page=" + (currentPage + 1) + "&amountPerPage=" + amountPerPage + "\">" +
                                    "<div class=\"link-no-" + (pagesToPrint + 1) + " next-page\">Następna</div>" +
                                    "</a>");
                        }
                    } %>


                    </div>



                <% } %>
    </div>
</div>
<!-- Stopka -->
<jsp:include page="/WEB-INF/admin/parts/overall-footer.jsp"/>