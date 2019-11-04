<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <div class="footer">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="logo">
              <img src="assets/images/monopolowy24h-logo.png" alt="">
            </div>
          </div>
          <div class="col-md-12">
            <div class="footer-menu">
              <ul>
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a href="#">Pomoc</a></li>
                <li><a href="#">Polityka prywatności</a></li>
                <li><a href="#">FAQ</a></li>
                <li><a href="#">Kontakt</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md-12">
            <div class="social-icons">
              <ul>
                <li><a href="#"><i class="fab fa-facebook-f"></i></a></li>
                <li><a href="#"><i class="fab fa-twitter"></i></a></li>
                <li><a href="#"><i class="fab fa-linkedin"></i></a></li>
                <li><a href="#"><i class="fa fa-rss"></i></a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Copyrights -->
	<jsp:include page="/WEB-INF/parts/copyrights.jsp"/>
    <!-- Załączone .js -->
    <jsp:include page="/WEB-INF/parts/footer-js.jsp"/>
    </body>
</html>