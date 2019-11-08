<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Nagłówek -->
<jsp:include page="/WEB-INF/admin/parts/overall-header.jsp"/>
<!-- Nawigacja sidebar -->
<jsp:include page="/WEB-INF/admin/parts/sidebar-menu.jsp"/>
<!-- Kontent -->
<div class="content">
    <div class="content-inside">
        <h1 class="backend-page-title"><i class="fas fa-users"></i> Menadżer użytkowników - dodaj użytkownika</h1>
        <p class="info-msg"><% if(request.getAttribute("msg") != null){ out.println(request.getAttribute("msg")); request.setAttribute("msg", null); } %></p>
        <div class="form-container">
            <form method="post" action="${pageContext.request.contextPath}/admin/user-manager/add-user">
                <p><span>Login:</span> <br /> <span style="font-size: 8px">Login musi zawierać minimum 6 znaków.</span> <br />
                    <input type="text" name="userlogin" pattern=".{6,}" title="Login musi zawierać minimum 6 znaków"></p>
                <p><span>Hasło </span> <br /> <span style="font-size: 8px">Hasło musi zawierać przynajmniej jedną cyfrę jedną wielką i jedną małą literę.<br>Dodatkowo hasło
                musi składać się z minimum 8 znaków.</span> <br /> <input type="password" name="pwd" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="
                Hasło musi zawierać przynajmniej jedną cyfrę jedną wielką i jedną małą literę. Dodatkowo hasło musi składać się z minimum 8 znaków."></p>
                <p><span>Imię:</span> <br /> <input type="text" name="firstname"></p>
                <p><span>Nazwisko:</span> <br /> <input type="text" name="lastname"></p>
                <p><span>Email:</span> <br /> <input type="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"></p>
                <p><input type="submit" value="Dodaj użytkownika"></p>
            </form>
            <p style="color: red; font-weight: bold">
                <%
                    if(request.getAttribute("msg")!=null){
                        out.println(request.getAttribute("msg"));
                    }
                %>
            </p>
        </div>
    </div>
</div>
<!-- Stopka -->
<jsp:include page="/WEB-INF/admin/parts/overall-footer.jsp"/>