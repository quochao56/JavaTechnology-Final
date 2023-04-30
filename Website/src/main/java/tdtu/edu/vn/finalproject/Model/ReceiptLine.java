package tdtu.edu.vn.finalproject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "receiptLine")
@IdClass(ReceiptLinePrimaryKey.class)
public class ReceiptLine {
    @Id
    private String receiptID;
    @Id
    private String albumID;
    private int quantity;
    private double price;

    @Builder
    public ReceiptLine(String receiptID, String albumID, int quantity, double price) {
        this.receiptID = receiptID;
        this.albumID = albumID;
        this.quantity = quantity;
        this.price = price;
    }
}
