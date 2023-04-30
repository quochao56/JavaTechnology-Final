package tdtu.edu.vn.finalproject.Service;

import tdtu.edu.vn.finalproject.Model.Album;
import tdtu.edu.vn.finalproject.Model.ReceiptLine;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReceiptLineService {
    List<ReceiptLine> getAllReceiptLine();
    ReceiptLine addReceiptLine(ReceiptLine receiptLine);
    void removeReceiptLineById(String id);
    Optional<ReceiptLine> getReceiptLineById(String id);
}
