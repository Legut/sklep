<%@ page import="objects.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Nagłówek -->
<jsp:include page="/WEB-INF/admin/parts/overall-header.jsp"/>
<!-- Nawigacja sidebar -->
<jsp:include page="/WEB-INF/admin/parts/sidebar-menu.jsp"/>
<!-- Kontent -->
<div class="content user-manager">
    <div class="content-inside">
        <h1 class="backend-page-title"><i class="fas fa-users"></i> Menadżer użytkowników - edytuj użytkownika</h1>
        <div class="form-container">
            <% if(request.getAttribute("singleUser") != null) { User singleUser = (User) request.getAttribute("singleUser"); %>
            <form method="post" action="${pageContext.request.contextPath}/admin/user-manager/edit-user">
                <div class="input-row">
                    <p class="input-element"><span>ID:</span> <br /> <input type="text" name="userId" value="<% out.println(singleUser.getId()); %>" title="Id nie może zostać zmienione" readonly></p>
                    <p class="input-element"><span>Login:</span> <br /> <span style="font-size: 8px">Login musi zawierać minimum 6 znaków.</span> <br />
                        <input type="text" name="userlogin" pattern=".{6,}" value="<% out.println(singleUser.getUser_login()); %>" title="Login musi zawierać minimum 6 znaków" required></p></div>
                <div class="input-row">
                    <p class="input-element"><span>Hasło: </span> <br /> <span style="font-size: 8px">Hasło musi zawierać przynajmniej jedną cyfrę jedną wielką i jedną małą literę.<br>Dodatkowo hasło
                        musi składać się z minimum 8 znaków.</span> <br /> <input type="text" name="pwd" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="
                        Hasło musi zawierać przynajmniej jedną cyfrę jedną wielką i jedną małą literę. Dodatkowo hasło musi składać się z minimum 8 znaków."
                        value="<% out.println(singleUser.getUser_pass()); %>" required></p>
                    <p class="input-element"><span>Imię:</span> <br /> <input type="text" name="firstname" value="<% out.println(singleUser.getFirst_name()); %>" required></p></div>
                <div class="input-row">
                    <p class="input-element"><span>Nazwisko:</span> <br /> <input type="text" name="lastname" value="<% out.println(singleUser.getLast_name()); %>" required></p>
                    <p class="input-element"><span>Email:</span> <br /> <input type="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" value="<% out.println(singleUser.getUser_email()); %>" required></p>
                </div>
                <div class="input-row">
                    <p class="input-element"><span>Klucz aktywacyjny:</span> <br /> <input type="text" name="userActivationKey" maxlength="20" value="<% out.println(singleUser.getUser_activation()); %>"></p>
                    <p class="input-element"><span>Data urodzenia:</span> <br /> <input type="date" name="birthDate" value="<% out.print(singleUser.getBirth_date()); %>" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" min="1900-01-01" required></p></div>
                <p class="input-element submit-element"><input type="submit" value="Zapisz zmiany"></p>
            </form>
            <% } %>
            <p class="info-msg"><% if(request.getAttribute("msg") != null){ out.println(request.getAttribute("msg")); request.setAttribute("msg", null); } %></p>
        </div>
    </div>
</div>
<!-- Stopka -->
<jsp:include page="/WEB-INF/admin/parts/overall-footer.jsp"/>