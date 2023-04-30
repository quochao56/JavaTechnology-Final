package tdtu.edu.vn.finalproject.DTO;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter @Getter
public class ReceiptLineDTO {
    private String receiptID;
    private String albumID;
    private String albumName;
    private String thumbnail;
    private double price;
    private int quantity;
    private double subtotal;
}
