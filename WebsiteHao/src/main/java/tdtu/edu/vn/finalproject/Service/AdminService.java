package tdtu.edu.vn.finalproject.Service;

import org.springframework.stereotype.Repository;
import tdtu.edu.vn.finalproject.Model.Receipt;
import tdtu.edu.vn.finalproject.Model.User;

import java.util.List;

@Repository
public interface AdminService {
     List<User> getAllUser();


     User save(User user) ;


     void deleteById(String id) ;

     User findByID(String id);

     List<User> ListAllUser(String keyword);

     List<Receipt> ListAllReceipt();
     void deleteOrderById(String id);
     Receipt saveReceipt(Receipt receipt);
     Receipt findOrderById(String id);

}
