package tdtu.edu.vn.finalproject.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tdtu.edu.vn.finalproject.Model.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, String> {
    @Query(value = "SELECT DISTINCT receipt.receiptID FROM receipt, receiptLine, users\n" +
            "WHERE receipt.receiptID = receiptLine.receiptID\n" +
            "AND receipt.userID = :username\n" +
            "AND receipt.state = 'unpaid'", nativeQuery = true)
    public String findOrderIdCurrentOrderOfUser(@Param("username") String username);

    public Receipt findReceiptByReceiptID(String orderId);

    @Query(value = "SELECT receiptID FROM receipt ORDER BY receiptID DESC LIMIT 1", nativeQuery = true)
    public String getLargestOrderId();

    @Transactional
    @Modifying
    @Query(value = "UPDATE receipt " +
            "SET state = 'Paid' " +
            "WHERE receiptID = :orderId",
            nativeQuery = true)
    public int userPayCart(@Param("orderId") String orderId);

}
