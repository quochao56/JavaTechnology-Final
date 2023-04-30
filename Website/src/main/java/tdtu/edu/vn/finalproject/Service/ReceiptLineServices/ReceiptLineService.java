package tdtu.edu.vn.finalproject.Service.ReceiptLineServices;

import tdtu.edu.vn.finalproject.Model.Cart;
import tdtu.edu.vn.finalproject.Model.ReceiptLine;

import java.util.List;
import java.util.Optional;

public interface ReceiptLineService {

    void deleteReceiptLineByAlbumID(String albumID);

    List<ReceiptLine> getAllReceiptLine();

    ReceiptLine addReceiptLine(ReceiptLine receiptLine);

    void removeReceiptLineById(String id);

    Optional<ReceiptLine> getReceiptLineById(String id);

    ReceiptLine insertNewOrderLine(String receiptID, String albumID, int quantity);

    List<Cart> getUserCart(String username);
}
