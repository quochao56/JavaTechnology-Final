package tdtu.edu.vn.finalproject.Model;

import lombok.Data;

@Data
public class Mail {
    private String recipient;
    private String subject;
    private String msgBody;
    private String attachment;
}
