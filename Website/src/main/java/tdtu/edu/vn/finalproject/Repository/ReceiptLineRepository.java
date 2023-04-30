package tdtu.edu.vn.finalproject.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tdtu.edu.vn.finalproject.Model.ReceiptLine;

import java.util.List;
import java.util.Map;

public interface ReceiptLineRepository extends JpaRepository<ReceiptLine, String> {
    public ReceiptLine findReceiptLineByReceiptIDAndAlbumID(String receiptID, String albumID);

    @Query(value = "SELECT album.*, receiptLine.quantity FROM receipt, receiptLine, users, album\n" +
            "WHERE receipt.receiptID = receiptLine.receiptID\n" +
            "AND receipt.userID = :username\n" +
            "AND receipt.state = 'unpaid'\n" +
            "AND album.albumID = receiptLine.albumID;", nativeQuery = true)
    public List<Map<String, Object>> getCartOfUser(@Param("username") String username);

    @Transactional
    public void deleteReceiptLineByAlbumID(String albumID);
}
