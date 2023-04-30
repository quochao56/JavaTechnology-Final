package tdtu.edu.vn.finalproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.edu.vn.finalproject.Model.ReceiptLine;

public interface ReceiptLineRepository extends JpaRepository<ReceiptLine, String> {
}
