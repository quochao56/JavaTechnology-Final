package tdtu.edu.vn.finalproject.Model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReceiptLinePrimaryKey implements Serializable {
    private String receiptID;
    private String albumID;

    public ReceiptLinePrimaryKey(String receiptID, String albumID) {
        this.albumID = albumID;
        this.receiptID = receiptID;
    }
}
