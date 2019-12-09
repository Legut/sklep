<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/parts/overall-header.jsp"/>
<jsp:include page="/WEB-INF/parts/sloganbar.jsp"/>
<!-- Nawigacja -->
<jsp:include page="/WEB-INF/parts/navigation.jsp"/>
<!-- Początek zawartości strony -->
<div class="single-product">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-heading">
                    <div class="line-dec"></div>
                    <% out.println("<h1>" + request.getAttribute("name") + "</h1>"); %>
                  </div>
            </div>
            <div class="col-md-6">
                <div class="product-slider">
                    <div id="slider" class="flexslider">
                        <ul class="slides">
                            <li>
                                <img src="${pageContext.request.contextPath}<%out.println(request.getAttribute("photo_1"));%>"/>
                            </li>
                            <li>
                                <img src="${pageContext.request.contextPath}<%out.println(request.getAttribute("photo_2"));%>"/>
                            </li>
                            <li>
                                <img src="${pageContext.request.contextPath}<%out.println(request.getAttribute("photo_3"));%>"/>
                            </li>
                            <li>
                                <img src="${pageContext.request.contextPath}<%out.println(request.getAttribute("photo_4"));%>"/>
                            </li>
                            <!-- items mirrored twice, total of 12 -->
                        </ul>
                    </div>
                    <div id="carousel" class="flexslider">
                        <ul class="slides">
                            <li>
                                <img src="${pageContext.request.contextPath}<%out.println(request.getAttribute("photo_1"));%>"/>
                            </li>
                            <li>
                                <img src="${pageContext.request.contextPath}<%out.println(request.getAttribute("photo_2"));%>"/>
                            </li>
                            <li>
                                <img src="${pageContext.request.contextPath}<%out.println(request.getAttribute("photo_3"));%>"/>
                            </li>
                            <li>
                                <img src="${pageContext.request.contextPath}<%out.println(request.getAttribute("photo_4"));%>"/>
                            </li>
                            <!-- items mirrored twice, total of 12 -->
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="right-content">
                    <% out.println("<h4>" + request.getAttribute("name") + "</h4>" +
                    "<h6>" + request.getAttribute("price") + " zł" + "</h6>" +
                    "<p>" + request.getAttribute("description") + "</p>" +
                    "<span>" + request.getAttribute("quantity") + " egzemplarzy na stanie" +"</span>"
                    );
                    %>
                    <form action="" method="get">
                        <label for="quantity">ilość:</label>
                        <input name="quantity" type="quantity" class="quantity-text" id="quantity"
                               onfocus="if(this.value == '1') { this.value = ''; }"
                               onBlur="if(this.value == '') { this.value = '1';}"
                               value="1">
                        <input type="submit" class="button" value="Zamów!">
                    </form>
                    <div class="down-content">
                        <div class="categories">
                            <h6>Kategoria: <span>
                                <% out.println("<a href=\"#\">" + request.getAttribute("category") + "</a>"); %>
                            </span>
                            </h6>
                        </div>
                        <div class="share">
                            <h6>Udostępnij: <span><a href="#"><i class="fab fa-facebook"></i></a>
                                <a href="#"><i class="fab fa-twitter"></i></a>
                                <a href="#"><i class="fab fa-instagram"></i></a>
                                <a href="#"><i class="fab fa-youtube"></i></a></span>
                            </h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Koniec zawartości strony -->

<!-- Początek podobnych pozycji -->
<div class="featured-items">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-heading">
                    <div class="line-dec"></div>
                    <h1>Mogą Cię zainteresować:</h1>
                </div>
            </div>
            <div class="col-md-12">
                <div class="owl-carousel owl-theme">
                    <a href="${pageContext.request.contextPath}/product">
                        <div class="featured-item">
                            <img src="${pageContext.request.contextPath}/assets/images/item-01.jpg" alt="Item 1">
                            <h4>Proin vel ligula</h4>
                            <h6>$15.00</h6>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/product">
                        <div class="featured-item">
                            <img src="${pageContext.request.contextPath}/assets/images/item-02.jpg" alt="Item 2">
                            <h4>Erat odio rhoncus</h4>
                            <h6>$25.00</h6>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/product">
                        <div class="featured-item">
                            <img src="${pageContext.request.contextPath}/assets/images/item-03.jpg" alt="Item 3">
                            <h4>Integer vel turpis</h4>
                            <h6>$35.00</h6>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/product">
                        <div class="featured-item">
                            <img src="${pageContext.request.contextPath}/assets/images/item-04.jpg" alt="Item 4">
                            <h4>Sed purus quam</h4>
                            <h6>$45.00</h6>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/product">
                        <div class="featured-item">
                            <img src="${pageContext.request.contextPath}/assets/images/item-05.jpg" alt="Item 5">
                            <h4>Morbi aliquet</h4>
                            <h6>$55.00</h6>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/product">
                        <div class="featured-item">
                            <img src="${pageContext.request.contextPath}/assets/images/item-06.jpg" alt="Item 6">
                            <h4>Urna ac diam</h4>
                            <h6>$65.00</h6>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/product">
                        <div class="featured-item">
                            <img src="${pageContext.request.contextPath}/assets/images/item-04.jpg" alt="Item 7">
                            <h4>Proin eget imperdiet</h4>
                            <h6>$75.00</h6>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/product">
                        <div class="featured-item">
                            <img src="${pageContext.request.contextPath}/assets/images/item-05.jpg" alt="Item 8">
                            <h4>Nullam risus nisl</h4>
                            <h6>$85.00</h6>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/product">
                        <div class="featured-item">
                            <img src="${pageContext.request.contextPath}/assets/images/item-06.jpg" alt="Item 9">
                            <h4>Cras tempus</h4>
                            <h6>$95.00</h6>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Koniec podobnych pozycji -->

<!-- Formularz do subskrypcji -->
<jsp:include page="/WEB-INF/parts/subscribe-form.jsp"/>
<!-- Stopka -->
<jsp:include page="/WEB-INF/parts/overall-footer.jsp"/>