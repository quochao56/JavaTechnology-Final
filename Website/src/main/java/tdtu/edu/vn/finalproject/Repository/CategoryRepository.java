package tdtu.edu.vn.finalproject.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.edu.midterm2.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
