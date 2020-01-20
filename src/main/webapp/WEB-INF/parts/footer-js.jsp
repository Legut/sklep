<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.form.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Dodatkowe skrypty -->
    <script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/owl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/additional.jsp"></script>
    <% if(request.getAttribute("isHomepage")=="is") { %>
        <script src="${pageContext.request.contextPath}/assets/js/additionalHomepage.jsp"></script>
    <% } %>

    <!-- Skrypty tylko na single product -->
    <script src="${pageContext.request.contextPath}/assets/js/isotope.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/additional-js-for-single-product.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/flex-slider.js"></script>

    <!-- Dodatkowe inline skrypty -->
    <script language = "text/Javascript">
      cleared[0] = cleared[1] = cleared[2] = 0;
      function clearField(t){
      if(! cleared[t.id]){
          cleared[t.id] = 1;
          t.value='';
          t.style.color='#fff';
          }
      }
    </script>
    <% if(request.getRequestURL().toString().endsWith("/sklep.jsp")){ %>
        <script>sendAjax()</script>
    <% } %>