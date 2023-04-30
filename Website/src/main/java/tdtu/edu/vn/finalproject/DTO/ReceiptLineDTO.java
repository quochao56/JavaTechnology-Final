package tdtu.edu.vn.finalproject.DTO;

import lombok.Data;

@Data
public class ReceiptLineDTO {
    private String receiptID;
    private String albumID;
    private String albumName;
    private String thumbnail;
    private double price;
    private int quantity;
    private double subtotal;
}
