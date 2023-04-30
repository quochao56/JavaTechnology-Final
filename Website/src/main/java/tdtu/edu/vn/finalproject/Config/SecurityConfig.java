package tdtu.edu.vn.finalproject.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tdtu.edu.vn.finalproject.Config.Filter.LoginHandler;
import tdtu.edu.vn.finalproject.Service.UserServices.CustomUserDetailService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final String[] NO_AUTH_ENDPOINTS = {
            "/js/**", "/images/**", "/favicon.ico",
            "/css/**", "/login", "/register", "/",
            "admin/css/**", "admin/lib/**", "admin/js/**", "admin/img/**",
            "/home/product/detail/**",
            "/home/search/**",
            "/products/**"
    };
    @Autowired
    LoginHandler successHandler;
    @Autowired
    private CustomUserDetailService userService;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                                .requestMatchers(NO_AUTH_ENDPOINTS).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .defaultSuccessUrl("/", true)
                .successHandler(successHandler)
                .failureUrl("/login?fail=true")
                .and()
                .logout().deleteCookies("JSESSIONID")
                .and()
                .rememberMe().key("uniqueAndSecret")
                .tokenValiditySeconds(60);
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        auth.userDetailsService(userService).passwordEncoder(pe);
    }
}
