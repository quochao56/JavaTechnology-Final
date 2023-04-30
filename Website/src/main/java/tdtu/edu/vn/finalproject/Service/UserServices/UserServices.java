package tdtu.edu.vn.finalproject.Service.UserServices;

import tdtu.edu.vn.finalproject.Model.User;

import java.util.Optional;

public interface UserServices {
    public boolean isExistedUser(String userID);

    public boolean isAuthUser(String userID, String password);

    public void registerUser(User user);

    public Optional<User> getUserInformation(String userID);
}
