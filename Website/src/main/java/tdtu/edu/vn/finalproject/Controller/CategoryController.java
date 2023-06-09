package tdtu.edu.vn.finalproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tdtu.edu.vn.finalproject.Model.Category;
import tdtu.edu.vn.finalproject.Service.CategoryServices.CategoryServiceImpl;

import java.util.Optional;

@Controller
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping("/admin/category/list")
    public String getCategory(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "listcategory";
    }

    @GetMapping("/admin/category/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";
    }

    @PostMapping("/admin/category/get")
    public String addCategoryPost(@ModelAttribute("category") Category category) {
        category.setName(category.getName().toUpperCase());
        categoryService.addCategory(category);
        return "redirect:/admin/category/list";
    }

    @GetMapping("/admin/category/delete/{id}")
    public String deleteCategory(@PathVariable long id) {
        categoryService.removeCategoryById(id);
        return "redirect:/admin/category/list";
    }

    @GetMapping("/admin/category/edit/{id}")
    public String editCategory(@PathVariable long id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "addcategory";
        } else {
            return "error";
        }
    }
}
