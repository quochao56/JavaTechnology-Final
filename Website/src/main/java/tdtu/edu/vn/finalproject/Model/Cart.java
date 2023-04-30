package tdtu.edu.vn.finalproject.Model;

import lombok.Builder;
import lombok.Data;

@Data
public class Cart {
    private String id;
    private String name;
    private double price;
    private String thumbnail;
    private int quantity;
    private double subtotal;

    @Builder
    public Cart(String id, String name, double price, String thumbnail, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.quantity = quantity;
        this.subtotal = quantity * price;
    }
}
