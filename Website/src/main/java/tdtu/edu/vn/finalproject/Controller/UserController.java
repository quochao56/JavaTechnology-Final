package tdtu.edu.vn.finalproject.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import tdtu.edu.vn.finalproject.DTO.RegisterDTO;
import tdtu.edu.vn.finalproject.Model.User;
import tdtu.edu.vn.finalproject.Service.MailServices.MailServicesImpl;
import tdtu.edu.vn.finalproject.Service.UserServices.UserServicesImpl;

@Controller
public class UserController {
    @Autowired
    UserServicesImpl userServices;

    @Autowired
    MailServicesImpl mailServices;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @GetMapping("/register")
    public String registerPage() {
        return "signup";
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public String register(Model model, RegisterDTO registerDTO, HttpServletResponse response) {
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

        Context context = new Context();
        context.setVariable("name", email.replace("@gmail.com", ""));
        String mailContent = templateEngine.process("thank-you-register", context);
        String subject = "[MusicShop] Welcome to MusicShop";
        mailServices.sendEmailEmbedEmail(email, subject, mailContent);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "signin";
    }
}