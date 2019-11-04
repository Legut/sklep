package login;

import util.ActivationEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/resend-activation-email")
public class ResendActivationEmail extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/resend-activation-email.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String authkey = request.getParameter("authkey");
        if(!email.isEmpty()){
            if(!authkey.isEmpty()){
                ActivationEmail.sendActivationEmail(authkey, email);
                request.setAttribute("message", "Wysłano email aktywacyjny ponownie!");
                request.setAttribute("desc", "Aby aktywować konto sprawdź skrzynkę pocztową podaną w trakcie rejestracji i kliknij w link aktywacyjny.");
            } else {
                request.setAttribute("message", "Brak kodu aktywacyjnego w bazie danych!");
                request.setAttribute("desc", "Spróbuj ponownie przejść przez formularz logowania, a później ponownie wciśnij guzik " +
                        "\"Wyślij ponownie link aktywacyjny\". Jeśli to nie rozwiąże problemu to skontaktuj się z administratorem.");
                System.out.println("Brak kodu aktywacyjnego w bazie danych!; ResendActivationEmail.doPost()");
            }
        } else {
            request.setAttribute("message", "Brak adresu email w bazie danych!");
            request.setAttribute("desc", "Spróbuj ponownie przejść przez formularz logowania, a później ponownie wciśnij guzik " +
                    "\"Wyślij ponownie link aktywacyjny\". Jeśli to nie rozwiąże problemu to skontaktuj się z administratorem.");
            System.out.println("Brak adresu email w bazie danych!; ResendActivationEmail.doPost()");
        }
        doGet(request, response);
    }
}
