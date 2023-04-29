package tdtu.edu.vn.finalproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.edu.vn.finalproject.Model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> getUserByUserID(String userID);
    public Optional<User> getUserByUserIDAndPassword(String userID, String password);
}
