<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<jsp:include page="/WEB-INF/parts/overall-header.jsp"/>
    <jsp:include page="/WEB-INF/parts/sloganbar.jsp"/>
    <!-- Nawigacja -->
    <jsp:include page="/WEB-INF/parts/navigation.jsp"/>
    <!-- Początek zawartości strony -->
    <div class="about-page">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="section-heading">
              <div class="line-dec"></div>
              <h1>O nas</h1>
            </div>
          </div>
          <div class="col-md-6">
            <div class="left-image">
              <img src="assets/images/about-us.jpg" alt="">
            </div>
          </div>
          <div class="col-md-6">
            <div class="right-content">
              <h4>Phasellus vel interdum elit</h4>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Vulputate dignissim suspendisse in est ante in nibh. In iaculis nunc sed augue lacus viverra vitae congue eu. Porttitor massa id neque aliquam vestibulum morbi blandit cursus.</p>
                <br>
                <p>Diam in arcu cursus euismod quis viverra nibh cras. In nibh mauris cursus mattis molestie a iaculis at. At urna condimentum mattis pellentesque id nibh. Vel orci porta non pulvinar neque laoreet suspendisse interdum. Auctor neque vitae tempus quam pellentesque nec nam aliquam.</p>
                <br>
                <p>Semper auctor neque vitae tempus quam pellentesque nec nam aliquam. Sit amet luctus venenatis lectus magna fringilla urna porttitor. Urna et pharetra pharetra massa massa ultricies mi quis. Sapien faucibus et molestie ac feugiat sed lectus vestibulum mattis. Tempor orci eu lobortis elementum nibh tellus molestie nunc non. Sed arcu non odio euismod lacinia.</p>
                <div class="share">
                    <h6>Możesz się również z nami skontaktować przez: <span>
                          <a href="#"><i class="fab fa-facebook-f"></i></a>
                          <a href="#"><i class="fab fa-twitter"></i></a>
                          <a href="#"><i class="fab fa-instagram"></i></a>
                          <a href="#"><i class="fab fa-youtube"></i></a></span></h6>
                </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Koniec zawartości strony -->

    <!-- Formularz do subskrypcji -->
	<jsp:include page="/WEB-INF/parts/subscribe-form.jsp"/>
    <!-- Stopka -->
    <jsp:include page="/WEB-INF/parts/overall-footer.jsp"/>