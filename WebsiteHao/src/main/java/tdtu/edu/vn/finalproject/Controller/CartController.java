package tdtu.edu.vn.finalproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.vn.finalproject.Model.Product;
import tdtu.edu.vn.finalproject.Model.ProductCart;
import tdtu.edu.vn.finalproject.Service.*;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    ProductCartServiceImpl productCartService;
    @GetMapping("/home/cart")
    public String shoppingCart(Model model){
        List<ProductCart> productCartList = productCartService.getAllProductCart();
        model.addAttribute("total", getTotalPrice());
        model.addAttribute("cart", productCartList);
        return "shoppingcart";
    }

    private double getTotalPrice() {
        List<ProductCart> productCartList = productCartService.getAllProductCart();
        double total = 0;
        for(ProductCart productCart: productCartList){
            total += productCart.getSubtotal();
        }
        return total;
    }

    @RequestMapping(value = "/home/cart/add/{id}", method = RequestMethod.POST)
    public String addProductToCart(@PathVariable long id, @RequestParam("quantity")int quantity){
        ProductCart productCart = new ProductCart();
        Product product = productService.getProductById(id).get();
        double subtotal = product.getPrice()* quantity;
        productCart.setId(product.getId());
        productCart.setName(product.getName());
        productCart.setArtist(product.getArtist());
        productCart.setImageName(product.getImage());
        productCart.setPrice(product.getPrice());
        productCart.setDescription(product.getDescription());
        productCart.setQuantity(quantity);
        productCart.setSubtotal(subtotal);
        productCartService.addProductCart(productCart);
        return "redirect:/";
    }
    @GetMapping("/home/cart/remove/{id}")
    public String removeItem(@PathVariable int id){
        productCartService.removeProductCartById(id);
        return "redirect:/home/cart";
    }
    @GetMapping("/home/cart/checkout")
    public String checkoutProduct(Model model){
        // if empty then customer cannot go the checkout page
//        if(productCartService.getAllProductCart().isEmpty()){
//            return "redirect:/home/cart";
//        }
        return "checkoutproduct";
    }
    @RequestMapping(value = "/home/cart/checkout/thanks", method = RequestMethod.POST)
    public String addProductCart(){
        return "thankyoupage";
    }


}
