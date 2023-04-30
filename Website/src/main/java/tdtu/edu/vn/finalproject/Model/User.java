package tdtu.edu.vn.finalproject.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    private String userID;
    private String username;
    @Column(name = "pwd")
    private String password;
    @Column(name = "ROLE")
    private String role;

    @Builder
    public User(String userID, String password, String role) {
        this.userID = userID;
        this.username = userID;
        this.password = password;
        this.role = role;
    }
}
