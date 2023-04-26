package tdtu.edu.vn.finalproject.Service;



import tdtu.edu.vn.finalproject.Model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategory();
    Category addCategory(Category category);
    void removeCategoryById(long id);
    Optional<Category> getCategoryById(long id);
}
