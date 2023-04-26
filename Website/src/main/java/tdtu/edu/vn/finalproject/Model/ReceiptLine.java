package tdtu.edu.vn.finalproject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@IdClass(ReceiptLinePrimaryKey.class)
public class ReceiptLine {
    @Id
    private String receiptID;
    @Id
    private String albumID;
    private int quantity;
    private double price;
}
