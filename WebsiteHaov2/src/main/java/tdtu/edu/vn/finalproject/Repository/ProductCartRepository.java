package tdtu.edu.vn.finalproject.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.edu.vn.finalproject.Model.ProductCart;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
}
