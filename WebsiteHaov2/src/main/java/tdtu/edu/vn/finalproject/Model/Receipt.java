package tdtu.edu.vn.finalproject.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    private String receiptID;
    private String userID;
    private double total;
    @Temporal(TemporalType.TIMESTAMP)
    private String dateEstablished;
    private String state;
}
