package music.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailUtil {

    public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML)
            throws MessagingException {
        
        // 1 - get a mail session
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", "rishigud@gmail.com");
        props.put("mail.smtp.password", "rishikumar@1992");
        props.put("mail.smtp.port", 587);    
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.quitwait", "false");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        // 2 - create a message
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }

        // 3 - address the message
        Address fromAddress = new InternetAddress(from);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        // 4 - send the message
      //Transport.send(message);
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com","rishigud@gmail.com", "rishikumar@1992");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();

    }
}
