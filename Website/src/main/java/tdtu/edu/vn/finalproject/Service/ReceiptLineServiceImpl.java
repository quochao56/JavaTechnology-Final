package tdtu.edu.vn.finalproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Model.ReceiptLine;
import tdtu.edu.vn.finalproject.Repository.ReceiptLineRepository;

import java.util.List;
import java.util.Optional;
@Service
public class ReceiptLineServiceImpl implements ReceiptLineService{
    @Autowired
    ReceiptLineRepository receiptLineRepository;
    @Override
    public List<ReceiptLine> getAllReceiptLine() {
        return receiptLineRepository.findAll();
    }

    @Override
    public ReceiptLine addReceiptLine(ReceiptLine receiptLine) {
        return receiptLineRepository.save(receiptLine);
    }

    @Override
    public void removeReceiptLineById(String id) {
        receiptLineRepository.deleteById(id);
    }

    @Override
    public Optional<ReceiptLine> getReceiptLineById(String id) {
        return receiptLineRepository.findById(id);
    }
}
