<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Dodatkowe skrypty -->
    <script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/owl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/additional.jsp"></script>
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