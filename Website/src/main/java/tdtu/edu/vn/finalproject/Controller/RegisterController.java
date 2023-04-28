package tdtu.edu.vn.finalproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tdtu.edu.vn.finalproject.DTO.RegisterDTO;
import tdtu.edu.vn.finalproject.Model.User;
import tdtu.edu.vn.finalproject.Service.UserServices.UserServicesImpl;

@Controller
public class RegisterController {
    @Autowired
    UserServicesImpl userServices;

    @GetMapping("/register")
    public String registerPage() {
        return "signup";
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public String register(Model model, RegisterDTO registerDTO) {
        String email = registerDTO.getEmail();
        String password = registerDTO.getPassword();
        String confirmPassword = registerDTO.getConfirmPassword();
        String message = "";
        boolean isRegisterFailed = true;
        if (!password.equals(confirmPassword)) {
            model.addAttribute("isRegisterFailed", isRegisterFailed);
            model.addAttribute("registerMessage", "Mật khẩu nhập lại không khớp");
            model.addAttribute("userEmail", email);

            return "signup";
        } else if (userServices.isExistedUser(email)) {
            model.addAttribute("isRegisterFailed", isRegisterFailed);
            model.addAttribute("registerMessage", "Email đã được đăng ký bởi một tài khoản khác");

            return "signup";
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashPassword = encoder.encode(password);
            User user = User.builder().userID(email)
                            .password(hashPassword)
                            .build();
            userServices.registerUser(user);
        }

        return "redirect:/index";
    }
}
