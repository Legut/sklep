<%@ page import="java.util.HashMap" %>
<%@ page import="files.HomePageConfigFile" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Nagłówek -->
<jsp:include page="/WEB-INF/admin/parts/overall-header.jsp"/>
<!-- Nawigacja sidebar -->
<jsp:include page="/WEB-INF/admin/parts/sidebar-menu.jsp"/>
<!-- Kontent -->
<%  HomePageConfigFile file = (HomePageConfigFile) request.getAttribute("file");
    HashMap<String, String> configuration = file.getMap() ;%>
<div class="content homepage-editor">
    <div class="content-inside">
        <h1 class="backend-page-title"><i class="fas fa-cogs"></i> Homepage editor</h1>

        <div class="activate-tab-bar">
            <button class="activate-tab-button active" onclick="openTab('Slajder', this)">Slajder</button>
            <button class="activate-tab-button" onclick="openTab('Wyroznione', this)">Wyróżnione</button>
            <button class="activate-tab-button" onclick="openTab('Dodatkowy', this)">JS/CSS/HTML</button>
        </div>
        <form action="/admin/homepage-editor" method="post">
            <div id="Slajder" class="tabName">
                <div class="input-section">
                    <h3 class="input-section-title">Tło slajdera</h3>
                    <p class="input-element">Url (background-image): <input type="text" name="sliderBackgroundUrl" value="<% out.print(configuration.get("sliderBackgroundUrl")); %>" /> *TODO: upload background</p>
                    <p class="input-element">Title: <input type="text" name="sliderBackgroundTitle" value="<% out.print(configuration.get("sliderBackgroundTitle")); %>" /></p>
                </div>
                <div class="input-section">
                    <h3 class="input-section-title">Efekty tła slajdera - zaawansowane</h3>
                    <p class="input-element">Czy korzystać z efektów tła sjadera:  <select name="sliderEffectsUsage">
                        <option value="on" <% if(configuration.get("sliderEffectsUsage").equals("on")) out.print("selected"); %>>Tak</option>
                        <option value="off" <% if(configuration.get("sliderEffectsUsage").equals("off")) out.print("selected"); %>>Nie</option>
                    </select></p>
                    <p class="input-element">Kolor tła pod obrazkiem (background-color): <input type="color" name="sliderBackgroundColor" value="<% out.print(configuration.get("sliderBackgroundColor")); %>" /></p>
                    <p class="input-element">Tryb mieszania (background-blend-mode): <select name="sliderMixedBlendMode">
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("unset")) out.print("selected"); %>>unset</option>
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("normal")) out.print("selected"); %>>normal</option>
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("multiply")) out.print("selected"); %>>multiply</option>
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("screen")) out.print("selected"); %>>screen</option>
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("overlay")) out.print("selected"); %>>overlay</option>
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("darken")) out.print("selected"); %>>darken</option>
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("lighten")) out.print("selected"); %>>lighten</option>
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("color-dodge")) out.print("selected"); %>>color-dodge</option>
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("color-burn")) out.print("selected"); %>>color-burn</option>
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("hard-light")) out.print("selected"); %>>hard-light</option>
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("soft-light")) out.print("selected"); %>>soft-light</option>
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("difference")) out.print("selected"); %>>difference</option>
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("exclusion")) out.print("selected"); %>>exclusion</option>
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("hue")) out.print("selected"); %>>hue</option>
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("saturation")) out.print("selected"); %>>saturation</option>
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("color")) out.print("selected"); %>>color</option>
                        <option <% if(configuration.get("sliderMixedBlendMode").equals("luminosity")) out.print("selected"); %>>luminosity</option>
                    </select></p>
                </div>
                <div class="input-section">
                    <h3 class="input-section-title">Blok tekstu na slajderze</h3>
                    <p class="input-element">Nagłówek: <input type="text" name="sliderHeader" value="<% out.print(configuration.get("sliderHeader")); %>" /></p>
                    <p class="input-element">Tekst: <br/><textarea rows="10" cols="50" name="sliderText"><% out.print(configuration.get("sliderText")); %></textarea></p>
                    <p class="input-element">Kolor tła bloku (background-color): <input type="color" name="sliderBlockBackgroundColor" value="<% out.print(configuration.get("sliderBlockBackgroundColor")); %>" /></p>
                    <p class="input-element">Przezroczystość tła bloku (opacity): <input type="number" min="0" max="1" step="0.1" name="sliderBlockOpacity" value="<% out.print(configuration.get("sliderBlockOpacity")); %>" /></p>
                    <p class="input-element">Czy wyświetlać guzik: <select name="sliderButton">
                        <option value="on" <% if(configuration.get("sliderButton").equals("on")) out.print("selected"); %>>Pokaż</option>
                        <option value="off" <% if(configuration.get("sliderButton").equals("off")) out.print("selected"); %>>Ukryj</option>
                    </select></p>
                    <p class="input-element">Tekst guzika: <input type="text" name="sliderButtonText" value="<% out.print(configuration.get("sliderButtonText")); %>" /></p>
                    <p class="input-element">Link guzika: <input type="text" name="sliderButtonLink" value="<% out.print(configuration.get("sliderButtonLink")); %>" />
                        <select name="sliderButtonLinkFollow">
                            <option <% if(configuration.get("sliderButtonLinkFollow").equals("1")){ out.print("selected"); }%>>dofollow</option>
                            <option <% if(configuration.get("sliderButtonLinkFollow").equals("2")){ out.print("selected"); }%>>nofollow</option>
                        </select>
                        <select name="sliderButtonLinkTarget">
                            <option <% if(configuration.get("sliderButtonLinkTarget").equals("1")){ out.print("selected"); }%>>_self</option>
                            <option <% if(configuration.get("sliderButtonLinkTarget").equals("2")){ out.print("selected"); }%>>_blank</option>
                        </select>
                    </p>
                </div>
            </div>
            <div id="Wyroznione" class="tabName" style="display:none">
                <div class="input-section">
                    <h3 class="input-section-title">Sekcja wyróżnione</h3>
                    <p class="input-element">Czy wyświetlać sekcję: <select name="featuredSectionDisplay">
                        <option value="on" <% if(configuration.get("featuredSectionDisplay").equals("on")) out.print("selected"); %>>Pokaż</option>
                        <option value="off" <% if(configuration.get("featuredSectionDisplay").equals("off")) out.print("selected"); %>>Ukryj</option>
                    </select></p>
                    <p class="input-element">Nagłówek sekcji wyróżnione: <input type="text" name="featuredHeader" value="<% out.print(configuration.get("featuredHeader")); %>" /></p>
                    <p class="input-element">Ile produktów na raz: <input type="number" min="3" max="5" name="featuredProductsGrid" value="<% out.print(configuration.get("featuredProductsGrid")); %>" /></p>
                    <p class="input-element">Ile produktów w sumie: <input type="number" min="3" max="12" name="featuredProductsAmount" value="<% out.print(configuration.get("featuredProductsAmount")); %>" /></p>
                </div>
            </div>
            <div id="Dodatkowy" class="tabName" style="display:none">
                <div class="input-section">
                    <h3 class="input-section-title">Dodatkowy CSS / JS</h3>
                    <p class="input-element"><span class="textarea-description">Dodatkowy css: </span><br /><textarea name="additionalHomepageCss" rows="10" cols="50"><% out.print(configuration.get("additionalHomepageCss")); %></textarea></p>
                    <p class="input-element"><span class="textarea-description">Dodatkowy js: </span><br /><textarea name="additionalHomepageJs" rows="10" cols="50"><% out.print(configuration.get("additionalHomepageJs")); %></textarea></p>
                </div>
                <div class="input-section">
                    <h3 class="input-section-title">Hooki</h3>
                    <p class="input-element" style="margin-bottom: 30px"><span class="textarea-description">Przed sekcją wyróżnione produkty: </span><br /><textarea class="hook-tinymce" id="hookBeforeHomepageFeatured" name="hookBeforeHomepageFeatured" rows="10" cols="50"><% out.print(configuration.get("hookBeforeHomepageFeatured")); %></textarea></p>
                    <p class="input-element"><span class="textarea-description">Po sekcji wyróżnione produkty: </span><br /><textarea class="hook-tinymce" id="hookAfterHomepageFeatured" name="hookAfterHomepageFeatured" rows="10" cols="50"><% out.print(configuration.get("hookAfterHomepageFeatured")); %></textarea></p>
                </div>
            </div>
            <p class="input-element submit-element"><input type="submit" value="Zastosuj" /></p>
        </form>
    </div>
</div>
<!-- Stopka -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
    tinymce.init({
        selector: '.hook-tinymce',
        plugins: 'code',
        toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent | code'
    });
</script>
<jsp:include page="/WEB-INF/admin/parts/overall-footer.jsp"/>