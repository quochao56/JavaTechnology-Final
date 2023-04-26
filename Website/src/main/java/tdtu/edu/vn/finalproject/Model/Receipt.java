package tdtu.edu.vn.finalproject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Receipt {
    @Id
    private String receiptID;
    private String userID;
    private double total;
    private String dateEstablished;
    private String state;
}
