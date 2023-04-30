package tdtu.edu.vn.finalproject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    private String receiptID;
    private String userID;
    private double total;
    private String dateEstablished;
    private String state;

    @Builder
    public Receipt(String receiptID, String userID, double total, String dateEstablished, String state) {
        this.receiptID = receiptID;
        this.userID = userID;
        this.dateEstablished = dateEstablished;
        this.state = state;
    }
}
