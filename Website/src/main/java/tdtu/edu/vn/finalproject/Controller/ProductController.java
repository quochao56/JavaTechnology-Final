package tdtu.edu.vn.finalproject.Controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tdtu.edu.vn.finalproject.DTO.ProductDTO;
import tdtu.edu.vn.finalproject.Model.Product;
import tdtu.edu.vn.finalproject.Service.CategoryServiceImpl;
import tdtu.edu.vn.finalproject.Service.ProductServiceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProductController {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    CategoryServiceImpl categoryService;
    String uploadDir = System.getProperty("user.dir") + "/midterm2/src/main/resources/static/image";
    @GetMapping("/admin/product/list")
    public String getProduct(@NotNull Model model){
        model.addAttribute("products", productService.getAllproduct());
        return "listproduct";
    }
    @GetMapping("/admin/product/add")
    public String addProduct(@NotNull Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "addproduct";
    }
    @RequestMapping(value = "/admin/product/get", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("product")ProductDTO productDTO,
                             @RequestParam("image")MultipartFile file,
                             @RequestParam("imageName")String imgName)throws Exception
    {
        Product newProduct = new Product();
        newProduct.setId(productDTO.getId());
        newProduct.setName(productDTO.getName());
        newProduct.setArtist(productDTO.getArtist());
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setDescription(productDTO.getDescription());
        newProduct.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        String imageUUID = "";
        if(!file.isEmpty())
        {
            imageUUID = file.getOriginalFilename();
            Path fileNameandPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameandPath, file.getBytes());
        }else
        {
            imageUUID = imgName;
        }
        newProduct.setImage(imageUUID);
        productService.addProduct(newProduct);
        return "redirect:/admin/product/list";
    }
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id)
    {
        productService.removeProductById(id);
        return "redirect:/admin/product/list";
    }
    @GetMapping("/admin/product/edit/{id}")
    public String editProduct(@PathVariable long id, Model model)
    {
        Product product = productService.getProductById(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setId(product.getId());
        productDTO.setArtist(product.getArtist());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setImageName(product.getImage());
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("categories", categoryService.getAllCategory());
        return "addproduct";
    }
}
