package tdtu.edu.vn.finalproject.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdtu.edu.vn.finalproject.Model.Receipt;


@Repository
public interface AdminOrderRepository extends JpaRepository<Receipt, String> {
    public Page<Receipt> findAll(Pageable pageable);
}
