package tdtu.edu.vn.finalproject.Service.MailServices;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Config.MailConfig;

@Service
public class MailServicesImpl implements MailServices {
    @Autowired
    public AmazonSimpleEmailService amazonSimpleEmailService;
    @Autowired
    private MailConfig mailConfig;
    @Autowired
    private MailSender mailSender;

    public void sendMessage(SimpleMailMessage simpleMailMessage) {
        this.mailSender.send(simpleMailMessage);
    }

    public void sendMail(String toMail, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailConfig.getFromMail());
        mailMessage.setTo(toMail);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        sendMessage(mailMessage);
    }

    public void sendEmailEmbedEmail(String toMail, String subject, String htmlBody) {
        try {
            SendEmailRequest sendEmailRequest = new SendEmailRequest()
                    .withDestination(new Destination().withToAddresses(toMail))
                    .withMessage(new Message()
                                         .withBody(new Body()
                                                           .withHtml(
                                                                   new Content()
                                                                           .withCharset("UTF-8")
                                                                           .withData(htmlBody)))
                                         .withSubject(new Content()
                                                              .withCharset("UTF-8")
                                                              .withData(subject)))
                    .withSource(mailConfig.getFromMail());
            SendEmailResult result = amazonSimpleEmailService.sendEmail(sendEmailRequest);
            System.out.println(result.getMessageId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
