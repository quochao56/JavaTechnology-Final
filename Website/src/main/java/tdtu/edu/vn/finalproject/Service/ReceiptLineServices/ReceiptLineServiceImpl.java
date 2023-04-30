package tdtu.edu.vn.finalproject.Service.ReceiptLineServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Model.Album;
import tdtu.edu.vn.finalproject.Model.Cart;
import tdtu.edu.vn.finalproject.Model.Receipt;
import tdtu.edu.vn.finalproject.Model.ReceiptLine;
import tdtu.edu.vn.finalproject.Repository.AlbumRepository;
import tdtu.edu.vn.finalproject.Repository.ReceiptLineRepository;
import tdtu.edu.vn.finalproject.Repository.ReceiptRepository;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReceiptLineServiceImpl implements ReceiptLineService {
    @Autowired
    ReceiptLineRepository receiptLineRepository;

    @Autowired
    ReceiptRepository receiptRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public void deleteReceiptLineByAlbumID(String albumID) {
        receiptLineRepository.deleteReceiptLineByAlbumID(albumID);
    }

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

    @Override
    public ReceiptLine insertNewOrderLine(String receiptID, String albumID, int quantity) {
        Receipt receipt = receiptRepository.findReceiptByReceiptID(receiptID);
        Album album = albumRepository.findById(albumID).get();
        ReceiptLine orderLineCurrent = receiptLineRepository.findReceiptLineByReceiptIDAndAlbumID(
                receipt.getReceiptID(), album.getAlbumID());
        if (orderLineCurrent != null) {
            quantity += orderLineCurrent.getQuantity();
        }

        ReceiptLine orderLine = ReceiptLine.builder()
                                           .receiptID(receipt.getReceiptID())
                                           .albumID(album.getAlbumID())
                                           .price(quantity * album.getPrice())
                                           .quantity(quantity).build();
        ReceiptLine savedReceiptLine = receiptLineRepository.save(orderLine);
        receipt.setTotal(receipt.getTotal() + quantity * album.getPrice());
        receiptRepository.save(receipt);
        return savedReceiptLine;
    }

    @Override
    public List<Cart> getUserCart(String username) {
        List<Map<String, Object>> result = receiptLineRepository.getCartOfUser(username);
        List<Cart> cart = new ArrayList<Cart>();
        ArrayList<String> setOfId = new ArrayList<>();
        DecimalFormat decfor = new DecimalFormat("0.00");
        for (Map<String, Object> row : result) {
            if (setOfId.contains(String.valueOf(row.get("albumID")))) {
                continue;
            }

            setOfId.add(String.valueOf(row.get("albumID")));
            cart.add(Cart.builder()
                         .id(String.valueOf(row.get("albumID")))
                         .name((String) row.get("name"))
                         .price(Double.parseDouble(decfor.format((Float) row.get("price"))))
                         .thumbnail((String) row.get("thumbnail"))
                         .quantity((Integer) row.get("quantity")).build()
            );
        }

        return cart;
    }
}
