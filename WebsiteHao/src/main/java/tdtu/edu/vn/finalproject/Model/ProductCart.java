package tdtu.edu.vn.finalproject.Model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "cart")
@Setter
@Getter
@NoArgsConstructor
@Data
public class ProductCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id", nullable = false)
    private long id;
    private String name;
    private Double price;
    private String artist;
    private String description;
    private String imageName;
    private double subtotal;
    private int quantity;

}
