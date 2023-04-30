package tdtu.edu.vn.finalproject.Service.ReceiptServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Model.Receipt;
import tdtu.edu.vn.finalproject.Repository.ReceiptRepository;

@Service
public class ReceiptServicesImpl implements ReceiptServices {
    @Autowired
    ReceiptRepository receiptRepository;

    @Override
    public String getCurrentReceiptId(String username) {
        return receiptRepository.findOrderIdCurrentOrderOfUser(username);
    }

    @Override
    public String getLargestOrderId() {
        return receiptRepository.getLargestOrderId();
    }

    @Override
    public Receipt createNewOrder(Receipt order) {
        return receiptRepository.save(order);
    }

    @Override
    public boolean userCheckout(String orderId) {
        return receiptRepository.userPayCart(orderId) != 0;
    }

    @Override
    public void payment(String receiptID) {
        receiptRepository.userPayCart(receiptID);
    }
}
