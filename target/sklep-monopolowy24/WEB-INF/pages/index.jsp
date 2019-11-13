<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/parts/overall-header.jsp"/>
<jsp:include page="/WEB-INF/parts/sloganbar.jsp"/>
<!-- Nawigacja -->
<jsp:include page="/WEB-INF/parts/navigation.jsp"/>
<!-- Początek Bannera -->
<div class="banner">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="caption">
                    <h2>Wybierz coś dla siebie</h2>
                    <div class="line-dec"></div>
                    <p>Nasz sklep cechuje się dużą różnorodnością i wysoką jakością sprzedawanych towarów. Stąd każdy może znaleźć coś dla siebie.
                        <br><br>Regularnie wzbogacamy naszą ofertę o nowe pozycje. Zapraszamy do subskrybcji <a href="https://facebook.com" rel="nofollow noopener" target="_blank">naszej strony na Facebook'u</a>, na której zawsze informujemy o każdej nowej pozycji w naszej ofercie. Serdecznie zapraszamy do zakupów w naszym sklepie.</p>
                    <div class="main-button">
                        <a href="${pageContext.request.contextPath}/sklep">Sklep</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Koniec Bannera -->

<div class="tester">
    <h1>tester1</h1>
</div>
<div class="tester2">
    <h1>tester2</h1>
</div>

<!-- Początek wyróżnionych produktów -->
<div class="featured-items">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-heading">
                    <div class="line-dec"></div>
                    <h1>Wyróżnione pozycje</h1>
                </div>
            </div>
            <div class="col-md-12">
                <div class="owl-carousel owl-theme">
                    <a href="${pageContext.request.contextPath}/single-product">
                        <div class="featured-item">
                            <img src="assets/images/item-01.jpg" alt="Item 1">
                            <h4>Baileys 0,7 L</h4>
                            <h6>69,90 zł</h6>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/single-product">
                        <div class="featured-item">
                            <img src="assets/images/item-02.jpg" alt="Item 2">
                            <h4>Dalmore 0,7 L</h4>
                            <h6>208,00 zł</h6>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/single-product">
                        <div class="featured-item">
                            <img src="assets/images/item-03.jpg" alt="Item 3">
                            <h4>Golden Flakes Supreme 0,75 L</h4>
                            <h6>356,99 zł</h6>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/single-product">
                        <div class="featured-item">
                            <img src="assets/images/item-04.jpg" alt="Item 4">
                            <h4>Harnaś 0,5 L</h4>
                            <h6>2,50 zł</h6>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/single-product">
                        <div class="featured-item">
                            <img src="assets/images/item-05.jpg" alt="Item 5">
                            <h4>Morbi aliquet</h4>
                            <h6>$55.00</h6>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/single-product">
                        <div class="featured-item">
                            <img src="assets/images/item-06.jpg" alt="Item 6">
                            <h4>Urna ac diam</h4>
                            <h6>$65.00</h6>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/single-product">
                        <div class="featured-item">
                            <img src="assets/images/item-04.jpg" alt="Item 7">
                            <h4>Proin eget imperdiet</h4>
                            <h6>$75.00</h6>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/single-product">
                        <div class="featured-item">
                            <img src="assets/images/item-05.jpg" alt="Item 8">
                            <h4>Nullam risus nisl</h4>
                            <h6>$85.00</h6>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/single-product">
                        <div class="featured-item">
                            <img src="assets/images/item-06.jpg" alt="Item 9">
                            <h4>Cras tempus</h4>
                            <h6>$95.00</h6>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Koniec wyróżnionych produktów -->

<!-- Formularz do subskrypcji -->
<jsp:include page="/WEB-INF/parts/subscribe-form.jsp"/>
<!-- Stopka -->
<jsp:include page="/WEB-INF/parts/overall-footer.jsp"/>