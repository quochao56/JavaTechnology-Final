package tdtu.edu.vn.finalproject.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="singer")
public class Singer {
    @Id
    private String singerID;
    private String singerName;
    @Column(name = "singerDescription")
    private String singerDescription;
}
