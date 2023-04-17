package tdtu.edu.vn.finalproject.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.edu.midterm2.model.ProductCart;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
}
