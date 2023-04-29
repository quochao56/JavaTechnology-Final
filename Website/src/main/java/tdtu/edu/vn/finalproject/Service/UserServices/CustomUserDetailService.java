package tdtu.edu.vn.finalproject.Service.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Model.SecurityUser;
import tdtu.edu.vn.finalproject.Repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUserID(username)
                             .map(SecurityUser::new)
                             .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
