package tdtu.edu.vn.finalproject.Service.MailServices;

import org.springframework.mail.SimpleMailMessage;

public interface MailServices {
    public void sendMessage(SimpleMailMessage simpleMailMessage);
}
