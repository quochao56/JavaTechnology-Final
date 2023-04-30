package tdtu.edu.vn.finalproject.Service.ProdcutCartServices;


import tdtu.edu.vn.finalproject.Model.ProductCart;

import java.util.List;
import java.util.Optional;

public interface ProductCartService {
    List<ProductCart> getAllProductCart();

    ProductCart addProductCart(ProductCart productCart);

    void removeProductCartById(long id);

    Optional<ProductCart> getProductCartById(long id);

    void removeAllProductCart();
}
