package login;

import dao.RegisterDAO;
import org.apache.commons.validator.routines.EmailValidator;
import util.ActivationEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_login = request.getParameter("userlogin");
        String user_pass = request.getParameter("pwd");
        String first_name = request.getParameter("firstname");
        String last_name = request.getParameter("lastname");
        String user_email = request.getParameter("email");
        String birth_date = request.getParameter("birthDate");

        if(!RegisterDAO.validateUserLogin(user_login)) {
            request.setAttribute("message", "Istnieje już uzytkownik o podanym loginie");
            doGet(request, response);
        } else if(!RegisterDAO.validateUserEmail(user_email)) {
            request.setAttribute("message", "Istnieje już uzytkownik o podanym emailu");
            doGet(request, response);
        } else if(!checkIfBirthDateIsValid(birth_date)) {
            request.setAttribute("message", "Podana data urodzenia nie spełnia wymogów rejestracji");
            doGet(request, response);
        } else {
            String activation_key = RegisterDAO.getAlphaNumericString(20);
            boolean done = RegisterDAO.addUser(user_login, user_pass, first_name, last_name, user_email, activation_key, birth_date);
            if(done) {
                ActivationEmail.sendActivationEmail(activation_key, user_email);
                response.sendRedirect("/register-success");
            } else {
                request.setAttribute("message", "Coś poszło nie tak w trakcie dodawania użytkownika do bazy. Spróbuj ponownie lub skontaktuj się z administratoracją sklepu.");
                doGet(request, response);
            }
        }
    }

    // Sprawdza czy osoba rejestrująca się jest pełnoletnia
    // true - pełnoletni, false - niepełnoletni
    public static boolean checkIfBirthDateIsValid(String birthDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.getDefault() );
        if(birthDate!=null){
            LocalDate birth_date = LocalDate.parse(birthDate, formatter);
            Period period = Period.between(birth_date, LocalDate.now());
            if(period.getYears()>=18 && period.getYears()<=200){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
