<%@ page import="java.util.HashMap" %><%@ page import="files.GeneralConfigFile" %><%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Nagłówek -->
<jsp:include page="/WEB-INF/admin/parts/overall-header.jsp"/>
<!-- Nawigacja sidebar -->
<jsp:include page="/WEB-INF/admin/parts/sidebar-menu.jsp"/>
<!-- Kontent -->
<%  GeneralConfigFile file = (GeneralConfigFile) request.getAttribute("file");
    HashMap<String, String> configuration = file.getMap() ;%>
<div class="content general-conf">
    <div class="content-inside">
        <h1 class="backend-page-title"><i class="fas fa-cogs"></i> Ustawienia główne</h1>

        <div class="activate-tab-bar">
            <button class="activate-tab-button active" onclick="openTab('Ogolne', this)">Ogólne</button>
            <button class="activate-tab-button" onclick="openTab('Typografia', this)">Typografia</button>
            <button class="activate-tab-button" onclick="openTab('Kolory', this)">Kolory</button>
            <button class="activate-tab-button" onclick="openTab('SocialMedia', this)">Social Media</button>
            <button class="activate-tab-button" onclick="openTab('Dodatkowy', this)">JS/CSS/HTML</button>
        </div>
        <form action="/admin/general-conf" method="post">
            <div id="Ogolne" class="tabName">
                <div class="input-section">
                    <h3 class="input-section-title">Logo</h3>
                    <p class="input-element">Url: <input type="text" name="logo" value="<% out.print(configuration.get("logo")); %>" /> *TODO: upload logo</p>
                    <p class="input-element">Alt: <input type="text" name="logoAlt" value="<% out.print(configuration.get("logoAlt")); %>" /></p>
                    <p class="input-element">Title: <input type="text" name="logoTitle" value="<% out.print(configuration.get("logoTitle")); %>" /></p>
                </div>
                <div class="input-section">
                    <h3 class="input-section-title">SEO</h3>
                    <p class="input-element">Tytuł SEO strony: <input type="text" name="seoTitle" value="<% out.print(configuration.get("seoTitle")); %>" /></p>
                    <p class="input-element">Opis SEO strony: <input type="text" name="seoDesc" value="<% out.print(configuration.get("seoDesc")); %>" /></p>
                </div>
                <div class="input-section">
                    <h3 class="input-section-title">Bez kategorii</h3>
                    <p class="input-element">Treść sloganu na górze strony: <input type="text" name="sloganText" value="<% out.print(configuration.get("sloganText")); %>" /></p>
                    <p class="input-element">Położenie panelu bocznego: <select name="sidebarAlignment">
                        <option value="1" <% if(configuration.get("sidebarAlignment").equals("1")){ out.print("selected"); }%>>Z lewej</option>
                        <option value="2" <% if(configuration.get("sidebarAlignment").equals("2")){ out.print("selected"); }%>>Z prawej</option>
                    </select> *TODO: sidebar na stronie sklepu</p>
                    <p class="input-element">Treść Copyrights: <input type="text" name="copyrights" value="<% out.print(configuration.get("copyrights")); %>" /></p>
                    <p class="input-element">Mail do formularza kontaktowego: <input type="email" name="contactMail" value="<% out.print(configuration.get("contactMail")); %>" /> *TODO: formularz kontaktowy na zakłdce /kontakt</p>
                </div>
            </div>
            <div id="Typografia" class="tabName" style="display:none">
                <div class="input-section">
                    <h3 class="input-section-title">Rozmiary nagłówków</h3>
                    <p class="input-element">Rozmiar czcionki H1 (font-size): <input type="number" name="h1FontSize" value="<% out.print(configuration.get("h1FontSize")); %>" /> px</p>
                    <p class="input-element">Rozmiar czcionki H2 (font-size): <input type="number" name="h2FontSize" value="<% out.print(configuration.get("h2FontSize")); %>" /> px</p>
                    <p class="input-element">Rozmiar czcionki H3 (font-size): <input type="number" name="h3FontSize" value="<% out.print(configuration.get("h3FontSize")); %>" /> px</p>
                    <p class="input-element">Rozmiar czcionki H4 (font-size): <input type="number" name="h4FontSize" value="<% out.print(configuration.get("h4FontSize")); %>" /> px</p>
                    <p class="input-element">Rozmiar czcionki H5 (font-size): <input type="number" name="h5FontSize" value="<% out.print(configuration.get("h5FontSize")); %>" /> px</p>
                    <p class="input-element">Rozmiar czcionki H6 (font-size): <input type="number" name="h6FontSize" value="<% out.print(configuration.get("h6FontSize")); %>" /> px</p>
                </div>
                <div class="input-section">
                    <h3 class="input-section-title">Grubości nagłówków</h3>
                    <p class="input-element">Grubość czcionki H1 (font-weight): <input type="number" name="h1FontWeight" step="100" value="<% out.print(configuration.get("h1FontWeight")); %>" /></p>
                    <p class="input-element">Grubość czcionki H2 (font-weight): <input type="number" name="h2FontWeight" step="100" value="<% out.print(configuration.get("h2FontWeight")); %>" /></p>
                    <p class="input-element">Grubość czcionki H3 (font-weight): <input type="number" name="h3FontWeight" step="100" value="<% out.print(configuration.get("h3FontWeight")); %>" /></p>
                    <p class="input-element">Grubość czcionki H4 (font-weight): <input type="number" name="h4FontWeight" step="100" value="<% out.print(configuration.get("h4FontWeight")); %>" /></p>
                    <p class="input-element">Grubość czcionki H5 (font-weight): <input type="number" name="h5FontWeight" step="100" value="<% out.print(configuration.get("h5FontWeight")); %>" /></p>
                    <p class="input-element">Grubość czcionki H6 (font-weight): <input type="number" name="h6FontWeight" step="100" value="<% out.print(configuration.get("h6FontWeight")); %>" /></p>
                </div>
                <div class="input-section">
                    <h3 class="input-section-title">Styl czcionek w paragrafach</h3>
                    <p class="input-element">Rozmiar czcionki w paragrafie (font-size): <input type="number" name="pFontSize" value="<% out.print(configuration.get("pFontSize")); %>" /> px</p>
                    <p class="input-element">Grubość czcionki w paragrafie (font-weight): <input type="number" name="pFontWeight" step="100" value="<% out.print(configuration.get("pFontWeight")); %>" /> px</p>
                </div>
            </div>
            <div id="Kolory" class="tabName" style="display:none">
                <p class="input-element">Tester1: <input type="color" name="tester" value="<% out.print(configuration.get("tester")); %>" /></p>
                <p class="input-element">Tester2: <input type="color" name="tester2" value="<% out.print(configuration.get("tester2")); %>" /></p>
            </div>
            <div id="SocialMedia" class="tabName" style="display:none">
                <div class="input-section">
                    <h3 class="input-section-title">Facebook</h3>
                    <p class="input-element">Widoczność:
                        <select name="fbVisibility">
                            <option value="1" <% if(configuration.get("fbVisibility").equals("1")){ out.print("selected"); }%>>Pokaż</option>
                            <option value="2" <% if(configuration.get("fbVisibility").equals("2")){ out.print("selected"); }%>>Ukryj</option>
                        </select>
                    </p>
                    <p class="input-element">Link: <input type="text" name="fbLink" value="<% out.print(configuration.get("fbLink")); %>" />
                        <select name="fbFollow">
                            <option value="1" <% if(configuration.get("fbFollow").equals("1")){ out.print("selected"); }%>>dofollow</option>
                            <option value="2" <% if(configuration.get("fbFollow").equals("2")){ out.print("selected"); }%>>nofollow</option>
                        </select>
                        <select name="fbTarget">
                            <option value="1" <% if(configuration.get("fbTarget").equals("1")){ out.print("selected"); }%>>_self</option>
                            <option value="2" <% if(configuration.get("fbTarget").equals("2")){ out.print("selected"); }%>>_blank</option>
                        </select>
                    </p>
                    <p class="input-element">Link title: <input type="text" name="fbTitle" value="<% out.print(configuration.get("fbTitle")); %>" /></p>
                </div>
                <h3 class="input-section-title">Twitter</h3>
                <div class="input-section">
                    <p class="input-element">Widoczność:
                        <select name="twitterVisibility">
                            <option value="1" <% if(configuration.get("twitterVisibility").equals("1")){ out.print("selected"); }%>>Pokaż</option>
                            <option value="2" <% if(configuration.get("twitterVisibility").equals("2")){ out.print("selected"); }%>>Ukryj</option>
                        </select>
                    </p>
                    <p class="input-element">Link: <input type="text" name="twitterLink" value="<% out.print(configuration.get("twitterLink")); %>" />
                        <select name="twitterFollow">
                            <option value="1" <% if(configuration.get("twitterFollow").equals("1")){ out.print("selected"); }%>>dofollow</option>
                            <option value="2" <% if(configuration.get("twitterFollow").equals("2")){ out.print("selected"); }%>>nofollow</option>
                        </select>
                        <select name="twitterTarget">
                            <option value="1" <% if(configuration.get("twitterTarget").equals("1")){ out.print("selected"); }%>>_self</option>
                            <option value="2" <% if(configuration.get("twitterTarget").equals("2")){ out.print("selected"); }%>>_blank</option>
                        </select>
                    </p>
                    <p class="input-element">Link title: <input type="text" name="twitterTitle" value="<% out.print(configuration.get("twitterTitle")); %>" /></p>
                </div>
                <div class="input-section">
                    <h3 class="input-section-title">Instagram</h3>
                    <p class="input-element">Widoczność:
                        <select name="igVisibility">
                            <option value="1" <% if(configuration.get("igVisibility").equals("1")){ out.print("selected"); }%>>Pokaż</option>
                            <option value="2" <% if(configuration.get("igVisibility").equals("2")){ out.print("selected"); }%>>Ukryj</option>
                        </select>
                    </p>
                    <p class="input-element">Link: <input type="text" name="igLink" value="<% out.print(configuration.get("igLink")); %>" />
                        <select name="igFollow">
                            <option value="1" <% if(configuration.get("igFollow").equals("1")){ out.print("selected"); }%>>dofollow</option>
                            <option value="2" <% if(configuration.get("igFollow").equals("2")){ out.print("selected"); }%>>nofollow</option>
                        </select>
                        <select name="igTarget">
                            <option value="1" <% if(configuration.get("igTarget").equals("1")){ out.print("selected"); }%>>_self</option>
                            <option value="2" <% if(configuration.get("igTarget").equals("2")){ out.print("selected"); }%>>_blank</option>
                        </select>
                    </p>
                    <p class="input-element">Link title: <input type="text" name="igTitle" value="<% out.print(configuration.get("igTitle")); %>" /></p>
                </div>
                <h3 class="input-section-title">YouTube</h3>
                <div class="input-section">
                    <p class="input-element">Widoczność:
                        <select name="ytVisibility">
                            <option value="1" <% if(configuration.get("ytVisibility").equals("1")){ out.print("selected"); }%>>Pokaż</option>
                            <option value="2" <% if(configuration.get("ytVisibility").equals("2")){ out.print("selected"); }%>>Ukryj</option>
                        </select>
                    </p>
                    <p class="input-element">Link: <input type="text" name="ytLink" value="<% out.print(configuration.get("ytLink")); %>" />
                        <select name="ytFollow">
                            <option value="1" <% if(configuration.get("ytFollow").equals("1")){ out.print("selected"); }%>>dofollow</option>
                            <option value="2" <% if(configuration.get("ytFollow").equals("2")){ out.print("selected"); }%>>nofollow</option>
                        </select>
                        <select name="ytTarget">
                            <option value="1" <% if(configuration.get("ytTarget").equals("1")){ out.print("selected"); }%>>_self</option>
                            <option value="2" <% if(configuration.get("ytTarget").equals("2")){ out.print("selected"); }%>>_blank</option>
                        </select>
                    </p>
                    <p class="input-element">Link title: <input type="text" name="ytTitle" value="<% out.print(configuration.get("ytTitle")); %>" /></p>
                </div>
            </div>
            <div id="Dodatkowy" class="tabName" style="display:none">
                <div class="input-section">
                    <h3 class="input-section-title">Dodatkowy CSS / JS</h3>
                    <p class="input-element"><span class="textarea-description">Dodatkowy css: </span><br /><textarea name="additionalCss" rows="10" cols="50"><% out.print(configuration.get("additionalCss")); %></textarea></p>
                    <p class="input-element"><span class="textarea-description">Dodatkowy js: </span><br /><textarea name="additionalJs" rows="10" cols="50"><% out.print(configuration.get("additionalJs")); %></textarea></p>
                </div>
                <div class="input-section">
                    <h3 class="input-section-title">Hooki</h3>
                    <p class="input-element"><span class="textarea-description">Przed &lt;/head> </span><br /><textarea name="hookBeforeHeadEnd" rows="10" cols="50"><% out.print(configuration.get("hookBeforeHeadEnd")); %></textarea></p>
                    <p class="input-element"><span class="textarea-description">Po &lt;body> </span><br /><textarea name="hookAfterBody" rows="10" cols="50"><% out.print(configuration.get("hookAfterBody")); %></textarea></p>
                    <p class="input-element"><span class="textarea-description">Po &lt;div class="footer"> </span><br /><textarea name="hookAfterFooter" rows="10" cols="50"><% out.print(configuration.get("hookAfterFooter")); %></textarea></p>
                    <p class="input-element"><span class="textarea-description">Przed końcem &lt;div class="footer"> </span><br /><textarea name="hookBeforeFooterEnd" rows="10" cols="50"><% out.print(configuration.get("hookBeforeFooterEnd")); %></textarea></p>
                </div>
            </div>
            <p class="input-element submit-element"><input type="submit" value="Zastosuj" /></p>
        </form>
    </div>
</div>
<!-- Stopka -->
<jsp:include page="/WEB-INF/admin/parts/overall-footer.jsp"/>