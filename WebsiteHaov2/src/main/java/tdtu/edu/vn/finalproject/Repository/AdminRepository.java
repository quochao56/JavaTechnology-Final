package tdtu.edu.vn.finalproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tdtu.edu.vn.finalproject.Model.User;

import java.util.List;

public interface AdminRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.userID LIKE %?1%"
            + " OR u.username LIKE %?1%"
            + " OR u.role LIKE %?1%")
    public List<User> search(String keyword);

    @Query("SELECT DISTINCT u.role FROM User u")
    public List<String> getAllRole();


}
