<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <div class="subscribe-form">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="section-heading">
              <div class="line-dec"></div>
              <h1>Zapisz się do Newslettera!</h1>
            </div>
          </div>
          <div class="col-md-8 offset-md-2">
            <div class="main-content">
              <p>Zapisując się do naszego Newslettera zgadzasz się na przetwarzanie Twoich danych na potrzeby marketingowe. Jednocześnie informujemy, że w każdym momencie możesz wypisać się z tej usługi. Administratorem danych osobowych jest firma Monopolowy 24h, ul. Anonimowa 6/7, 67-123, Miastowo. Więcej informacji znajdziesz w <a href="#" style="color: #ffffff">Polityce prywatności</a></p>
              <div class="container">
                <form id="subscribe" action="MyServlet" method="get">
                  <div class="row">
                    <div class="col-md-7">
                      <fieldset>
                        <input name="email" type="text" class="form-control" id="email" 
                        onfocus="if(this.value == 'Twój Email...') { this.value = ''; }" 
                    	onBlur="if(this.value == '') { this.value = 'Twój Email...';}"
                    	value="Twój Email..." required="">
                      </fieldset>
                    </div>
                    <div class="col-md-5">
                      <fieldset>
                        <button type="submit" id="form-submit" class="button">Zapisz się!</button>
                      </fieldset>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>