package tdtu.edu.vn.finalproject.Service.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Model.User;
import tdtu.edu.vn.finalproject.Repository.UserRepository;

import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    UserRepository userRepository;


    @Override
    public boolean isExistedUser(String userID) {
        return userRepository.getUserByUserID(userID).isPresent();
    }

    @Override
    public boolean isAuthUser(String userID, String password) {
        return userRepository.getUserByUserIDAndPassword(userID, password).isPresent();
    }

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserInformation(String userID) {
        return Optional.empty();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username).orElse(null);
    }
}
