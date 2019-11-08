<%@ page import="objects.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Nagłówek -->
<jsp:include page="/WEB-INF/admin/parts/overall-header.jsp"/>
<!-- Nawigacja sidebar -->
<jsp:include page="/WEB-INF/admin/parts/sidebar-menu.jsp"/>
<!-- Kontent -->
<div class="content">
    <div class="content-inside">
        <h1 class="backend-page-title"><i class="fas fa-users"></i> Menadżer użytkowników - edytuj użytkownika</h1>
        <div class="form-container">
            <% if(request.getAttribute("singleUser") != null) { User singleUser = (User) request.getAttribute("singleUser"); %>
            <form method="post" action="${pageContext.request.contextPath}/admin/user-manager/edit-user">
                <p><span>ID:</span> <br /> <input type="text" name="userId" value="<% out.println(singleUser.getId()); %>" title="Id nie może zostać zmienione" readonly></p>
                <p><span>Login:</span> <br /> <span style="font-size: 8px">Login musi zawierać minimum 6 znaków.</span> <br />
                    <input type="text" name="userlogin" pattern=".{6,}" value="<% out.println(singleUser.getUser_login()); %>" title="Login musi zawierać minimum 6 znaków"></p>
                <p><span>Hasło: </span> <br /> <span style="font-size: 8px">Hasło musi zawierać przynajmniej jedną cyfrę jedną wielką i jedną małą literę.<br>Dodatkowo hasło
                musi składać się z minimum 8 znaków.</span> <br /> <input type="text" name="pwd" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="
                Hasło musi zawierać przynajmniej jedną cyfrę jedną wielką i jedną małą literę. Dodatkowo hasło musi składać się z minimum 8 znaków."
                value="<% out.println(singleUser.getUser_pass()); %>"></p>
                <p><span>Imię:</span> <br /> <input type="text" name="firstname" value="<% out.println(singleUser.getFirst_name()); %>"></p>
                <p><span>Nazwisko:</span> <br /> <input type="text" name="lastname" value="<% out.println(singleUser.getLast_name()); %>"></p>
                <p><span>Email:</span> <br /> <input type="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" value="<% out.println(singleUser.getUser_email()); %>"></p>
                <p><span>Email:</span> <br /> <input type="text" name="userActivationKey" maxlength="20" value="<% out.println(singleUser.getUser_activation()); %>"></p>
                <p><input type="submit" value="Zapisz zmiany"></p>
            </form>
            <% } %>
            <p class="info-msg"><% if(request.getAttribute("msg") != null){ out.println(request.getAttribute("msg")); request.setAttribute("msg", null); } %></p>
        </div>
    </div>
</div>
<!-- Stopka -->
<jsp:include page="/WEB-INF/admin/parts/overall-footer.jsp"/>