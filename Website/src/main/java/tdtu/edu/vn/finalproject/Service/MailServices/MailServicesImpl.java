package tdtu.edu.vn.finalproject.Service.MailServices;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServicesImpl implements MailServices {
    @Autowired
    private MailSender mailSender;

    public void sendMessage(SimpleMailMessage simpleMailMessage) {
        this.mailSender.send(simpleMailMessage);
    }

    public void sendMail(String fromMail, String toMail, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromMail);
        mailMessage.setTo(toMail);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        sendMessage(mailMessage);
    }
}
