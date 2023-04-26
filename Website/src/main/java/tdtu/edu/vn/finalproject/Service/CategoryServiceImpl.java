package tdtu.edu.vn.finalproject.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Model.Category;
import tdtu.edu.vn.finalproject.Repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void removeCategoryById(long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> getCategoryById(long id) {
        return categoryRepository.findById(id);
    }
}
