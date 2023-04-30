package tdtu.edu.vn.finalproject.Service.ReceiptServices;

import tdtu.edu.vn.finalproject.Model.Receipt;

public interface ReceiptServices {
    public String getCurrentReceiptId(String username);

    String getLargestOrderId();

    Receipt createNewOrder(Receipt order);

    boolean userCheckout(String orderId);

    void payment(String receiptID);
}
