<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Nagłówek -->
<jsp:include page="/WEB-INF/admin/parts/overall-header.jsp"/>
<!-- Nawigacja sidebar -->
<jsp:include page="/WEB-INF/admin/parts/sidebar-menu.jsp"/>
<!-- Kontent -->
<div class="content media-manager" id="MediaManager">
    <div class="content-inside">
        <h1 class="backend-page-title"><i class="fas fa-photo-video"></i> Menadżer mediów</h1>
        <%
            int amountPerPage = 0, currentPage = 0;
            long amountOfImages = 0;
            if(request.getAttribute("amountPerPage")!=null){
                amountPerPage = (int) request.getAttribute("amountPerPage");
            }
            if(request.getAttribute("amountOfImages")!=null){
                amountOfImages = (long) request.getAttribute("amountOfImages");
            }
            if(request.getAttribute("currentPage")!=null){
                currentPage = (int) request.getAttribute("currentPage");
            }
        %>
        <p class="info-msg" id="InfoMsg"><% if(request.getAttribute("msg") != null){ out.println(request.getAttribute("msg")); request.setAttribute("msg", null); } %></p>
        <div class="info">
            <p>Poniżej znajdą się wszystkie dodane obrazki. Aby dodać nowy obrazek skorzystaj z formularza. Aby zaznaczyć parę obrazków trzymaj CTRL (Windows).
                Obrazki po wysłaniu na serwer od razu są wysyłane podwójnie. Jedna wersja obrazka jest oryginalna druga zoptymalizowana.
                Po wybraniu obrazka należy zdefiniować szerokość zoptymalizowanego obrazka w pikselach. Jeśli dodawany obrazek jest mniejszy niż wybrana szerokość,
                to nie zostanie on roszerzony. Wysokość jest zmieniania proporcjonalnie do szerokości. Dodawane obrazki muszą spełniać wymagania:</p>
            <ul>
                <li>Maksymalny rozmiar pojedynczego obrazka to 5MB</li>
                <li>Maksymalny rozmiar wszystkich dodawanych obrazków to 15MB</li>
                <li>Obrazki muszą mieć rozszerzenie .jpg lub .png</li>
            </ul>
            <p>Poniższa galeria umożliwia kopiowanie linków. Po kliknięciu w obrazek link do niego zostanie skopiowany.</p>
        </div>
        <div class="form-container">
            <form method="post" action="${pageContext.request.contextPath}/admin/media-manager" style="margin-bottom: 0px" enctype="multipart/form-data">
                <div class="input-row">
                    <p class="input-element submit-element"><input type="submit" value="Dodaj"></p>
                    <p class="input-element"><span>Dodaj obrazek:</span> <br /> <input type="file" name="photoLink" id="AddPhoto" onchange="checkFileSize(this)" multiple required></p>
                    <p class="input-element"><span>Zmiana rozmiaru:</span> <br />
                        <select name="resizeValue">
                            <option value="640">640px</option>
                            <option value="1024" selected>1024px</option>
                            <option value="1600">1600px</option>
                        </select>
                    </p>
                    <p class="input-element"><input type="checkbox" name="onlyResizedImage"> Zapisz na serwerze tylko obrazek o zmienionych wymiarach (zoptymalizowany)</p>
                </div>
            </form>
            <form type="post" action="${pageContext.request.contextPath}/admin/media-manager">
                <div class="input-row">
                    <p class="input-element submit-element"><input type="submit" value="Wyświetl"></p>
                    <p class="input-element"><span>Ile obrazków na stronę:</span> <br />
                        <select name="amountPerPage">
                            <option value="10" <% if(amountPerPage==10) { out.print("selected"); } %>>10</option>
                            <option value="20" <% if(amountPerPage==20) { out.print("selected"); } %>>20</option>
                            <option value="35" <% if(amountPerPage==35) { out.print("selected"); } %>>35</option>
                            <option value="50" <% if(amountPerPage==50) { out.print("selected"); } %>>50</option>
                        </select>
                    </p>
                    <p class="input-element"><span>Którą stronę wyświetlić:</span> <br />
                        <select name="currentPage">
                            <%
                                int amountOfPages = (int) Math.ceil((double)amountOfImages / (double)amountPerPage);
                                for (int i = 1; i <= amountOfPages; i++) {
                            %>
                                <option <% if(currentPage==i) { out.print("selected"); } %>><% out.print(i); %></option>
                            <%
                                }
                            %>
                        </select>
                    </p>
                </div>
            </form>
            <div class="all-images-container" style="width: 100%">
                <%
                    if(request.getAttribute("imageLinksList") != null) {
                        List<String> imageLinkList = (List<String>) request.getAttribute("imageLinksList");
                        int end = amountPerPage * (currentPage);
                        for(int i = amountPerPage * (currentPage-1); i < imageLinkList.size(); i++){
                %>
                    <div class="image-container">
                        <img class="image fixed-image-size" src="<% out.print(imageLinkList.get(i)); %>" onclick="copyToClipboard(this);" />
                        <span class="image-text" id="image-text-<% out.print(i); %>" onclick="getWidthAndHeight(<% out.print(i); %>, '<% out.print(imageLinkList.get(i)); %>')"><label>0x0 px</label></span>
                    </div>
                <%
                            if(i==end){ break; }
                        }
                    } else { out.println("Nie ma żadnych obrazków na serwerze."); }
                %>
            </div>
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