package tdtu.edu.vn.finalproject.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tdtu.edu.vn.finalproject.Model.Product;

import java.util.Collection;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query("SELECT s FROM Product s WHERE s.name like %:key%")
    public Collection<Product> searchByName(@Param("key") String key);

}
