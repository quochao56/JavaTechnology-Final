package tdtu.edu.vn.finalproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdtu.edu.vn.finalproject.Model.Receipt;


@Repository
public interface AdminOrderRpository extends JpaRepository<Receipt,String> {

}
