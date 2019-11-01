package util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ActivationEmail {
    public static void sendActivationEmail(String activation_key, String user_email) {
        final String username = "monopolowy24h@bcd.pl";
        final String password = "1dX3;21dfs3412davD!";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "bcd.home.pl");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(user_email)
            );
            //TODO: zastąpić sztywny link aktywacyjny takim co nie jest sztywny xd
            message.setSubject("Rejestracja w Monopolowy24h - Aktywacja konta");
            message.setText("Witaj! " +
                    "Jeśli otrzymałeś ten email, to znaczy że proces rejestracji przebiegł pomyślnie. " +
                    "Aby korzystać z utworzonego konta należy je aktywować, klikając w poniższy link." +
                    "\nhttp://localhost:8080/user-activation?key=" + activation_key +
                    "\nJeśli nie rejestrowałeś się w sklepie Monopolowy24h, to zignoruj tę wiadomość." +
                    "\n\n---------------------------------------" +
                    "\nWiadomość wygenerowana automatycznie. Prosimy nie odpowiadać na tę wiadomość.");

            Transport.send(message);
        } catch (MessagingException ex) {
            System.out.println("Sending activation email error; ActivationEmail.sendActivationEmail() -->" + ex.getMessage());
        }
    }
}
