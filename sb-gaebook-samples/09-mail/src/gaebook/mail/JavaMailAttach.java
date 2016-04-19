package gaebook.mail;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class JavaMailAttach extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = "hello from App Engine! - こんにちは！";  // content 
        String subjectString = MimeUtility.encodeText("こんにちは", "ISO-2022-JP", "B");
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(user.getEmail(), user.getNickname()));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(user.getEmail(), user.getNickname()));
            msg.setSubject(subjectString);                  // subject 

            // 本文
            MimeBodyPart mbodyText = new MimeBodyPart();
            mbodyText.setText(msgBody);

            // 添付ファイル
            MimeBodyPart mbodyAttachment = new MimeBodyPart();
            mbodyAttachment.attachFile("WEB-INF/appEngine.png");

            // 本文と添付を一つのオブジェクトに入れる
            MimeMultipart mm = new MimeMultipart(); 
            mm.addBodyPart(mbodyText);
            mm.addBodyPart(mbodyAttachment); 
            
            // Messageに入れて送信
            msg.setContent(mm);
            Transport.send(msg);

        } catch (AddressException e) {
            throw new IOException(e);
        } catch (MessagingException e) {
            throw new IOException(e);
        }
        resp.setContentType("text/plain");
        resp.getWriter().println("mail sent!");
        
    }
}
