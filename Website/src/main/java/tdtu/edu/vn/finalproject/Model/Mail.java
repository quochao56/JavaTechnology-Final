package tdtu.edu.vn.finalproject.Model;

import lombok.Builder;
import lombok.Data;

@Data
public class Mail {
    private String recipient;
    private String subject;
    private String msgBody;
    private String attachment;

    @Builder
    public Mail(String recipient, String subject, String msgBody, String attachment) {
        this.recipient = recipient;
        this.subject = subject;
        this.msgBody = msgBody;
        this.attachment = attachment;
    }
}
