package tdtu.edu.vn.finalproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.vn.finalproject.Model.Product;
import tdtu.edu.vn.finalproject.Service.CategoryServiceImpl;
import tdtu.edu.vn.finalproject.Service.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping("/")
    public String showProduct(Model model) {
//        model.addAttribute("products", productService.getAllproduct());
//        model.addAttribute("categories", categoryService.getAllCategory());
//        model.addAttribute("artists", getArtistList());
        return "index";
    }

    @GetMapping("/admin")
    public String adminhome() {
        return "adminpage";
    }

    @RequestMapping(value = "/home/filter", method = RequestMethod.POST)
    public String filterProduct(@RequestParam("customRadio") String radioCheckedValues,
            @RequestParam("min") String minPrice,
            @RequestParam("max") String maxPrice,
            Model model) throws Exception {
        List<Product> listOfProduct = productService.getAllproduct();
        List<Product> products = new ArrayList<>();
        if (!radioCheckedValues.equals("")) {
            if (!radioCheckedValues.equals("all")) {
                for (Product product : listOfProduct) {
                    if (product.getArtist().equalsIgnoreCase(radioCheckedValues)) {
                        products.add(product);
                    }
                }
            } else {
                for (Product product : listOfProduct) {
                    products.add(product);
                }
            }
        }

        if (!minPrice.equals("") && !maxPrice.equals("")) {
            Double min = Double.parseDouble(minPrice);
            Double max = Double.parseDouble(maxPrice);
            for (Product product : listOfProduct) {
                if (product.getPrice() < min || product.getPrice() > max) {
                    products.remove(product);
                }
            }
        }
        model.addAttribute("products", products);
        model.addAttribute("artists", getArtistList());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "index";
    }

    private List<String> getArtistList() {
        List<String> brand = new ArrayList<>();
        for (Product product : productService.getAllproduct()) {
            if (!brand.contains(product.getArtist())) {
                brand.add(product.getArtist());
            }
        }
        return brand;
    }

    @PostMapping("/home/product/detail/{id}")
    public String productDetail(@PathVariable long id, Model model) {
        Product product = productService.getProductById(id).get();
        model.addAttribute(product);
        return "productdetail";
    }

    @RequestMapping(value = "/home/search", method = RequestMethod.POST)
    public String searchProduct(@RequestParam("nameofproduct") String nameproduct, Model model) {

        System.out.println(nameproduct);
        List<Product> products = (List<Product>) productService.searchProductByName(String.valueOf(nameproduct));
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", products);
        model.addAttribute("artists", getArtistList());
        return "index";
    }

}
