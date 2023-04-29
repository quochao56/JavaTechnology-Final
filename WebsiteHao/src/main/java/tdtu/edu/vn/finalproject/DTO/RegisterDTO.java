package tdtu.edu.vn.finalproject.DTO;

import lombok.Data;

@Data
public class RegisterDTO {
    private String email;
    private String password;
    private String confirmPassword;
}
