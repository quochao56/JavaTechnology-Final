package tdtu.edu.vn.finalproject.Service;


import tdtu.edu.vn.finalproject.Model.Product;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Iterable<Product> getAllproduct();
    Product addProduct(Product product);
    void removeProductById(long id);
    Optional<Product> getProductById(long id);
    Collection<Product> searchProductByName(String ar);

}
