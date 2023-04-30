package tdtu.edu.vn.finalproject.Controller;

import com.google.common.base.Strings;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import tdtu.edu.vn.finalproject.DTO.AlbumDTO;
import tdtu.edu.vn.finalproject.Model.*;
import tdtu.edu.vn.finalproject.Service.AlbumServices.AlbumServiceImpl;
import tdtu.edu.vn.finalproject.Service.CategoryServices.CategoryServiceImpl;
import tdtu.edu.vn.finalproject.Service.GenreServices.GenreServiceImpl;
import tdtu.edu.vn.finalproject.Service.MailServices.MailServicesImpl;
import tdtu.edu.vn.finalproject.Service.ProductServices.ProductServiceImpl;
import tdtu.edu.vn.finalproject.Service.ReceiptLineServices.ReceiptLineServiceImpl;
import tdtu.edu.vn.finalproject.Service.ReceiptServices.ReceiptServicesImpl;
import tdtu.edu.vn.finalproject.Service.SingerServices.SingerServiceImpl;
import tdtu.edu.vn.finalproject.Service.UserServices.UserServicesImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController implements ErrorController {
    @Autowired
    AlbumServiceImpl albumService;
    @Autowired
    GenreServiceImpl genreService;
    @Autowired
    SingerServiceImpl singerService;
    @Autowired
    ReceiptLineServiceImpl receiptLineService;

    @Autowired
    ReceiptServicesImpl receiptServices;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    UserServicesImpl userServices;

    @Autowired
    MailServicesImpl mailServices;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("albums", getAlbumDTO(albumService.getAllAlbums()));
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("singers", singerService.getAllSingers());
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String showProduct(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("albumID").ascending());
        Page<Album> albumsPage = albumService.paginationAlbum(pageable);

        model.addAttribute("size", size);
        model.addAttribute("albums", getAlbumDTO(albumsPage.getContent()));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", albumsPage.getTotalPages());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("singers", singerService.getAllSingers());
        return "index";
    }

    private List<AlbumDTO> getAlbumDTO(List<Album> albums) {
        List<AlbumDTO> albumDTOS = new ArrayList<>();
        AlbumDTO albumDTO;
        for (Album i : albums) {
            albumDTO = new AlbumDTO();
            albumDTO.setAlbumID(i.getAlbumID());
            albumDTO.setName(i.getName());
            albumDTO.setAlbumDescription(i.getAlbumDescription());
            albumDTO.setPrice(i.getPrice());
            albumDTO.setThumbnail(i.getThumbnail());
            albumDTO.setSinger(singerService.getSingerById(i.getSingerID()).get());
            albumDTO.setNumberOfSongs(i.getNumberOfSongs());
            albumDTO.setGenre(genreService.getGenreById(i.getGenreID()).get());
            albumDTOS.add(albumDTO);
        }
        return albumDTOS;
    }

    @RequestMapping(value = "/home/search", method = RequestMethod.POST)
    public String searchProduct(@RequestParam("nameofalbum") String nameofalbum,
            @RequestParam("genreoption") String genreoption,
            @RequestParam("singeroption") String singeroption,
            @RequestParam("pricealbum") String price,
            Model model) {
        List<Album> albums;
        ;
        List<Album> temp = new ArrayList<>();
        Double priceAlbum = 0.0;
        if (!price.equals("")) {
            priceAlbum = Double.parseDouble(price);
        }
        if (nameofalbum.equals("") && genreoption.equals("all") && singeroption.equals("all") && price.equals("")) {
            return "redirect:/";
        }
        if (!nameofalbum.equals("")) {
            albums = (List<Album>) albumService.searchAlbumByName(nameofalbum);
        } else {
            albums = albumService.getAllAlbums();
        }
        if (!genreoption.equals("all")) {
            for (Album album : albums) {
                if (getGenre(album.getGenreID()).getGenreName().toLowerCase().equals(genreoption.toLowerCase())) {
                    temp.add(album);
                }
            }
        } else {
            for (Album album : albums) {
                temp.add(album);
            }
        }
        if (!singeroption.equals("all")) {
            for (Album album : albums) {
                if (!getSinger(album.getSingerID()).getSingerName().toLowerCase().equals(singeroption.toLowerCase())) {
                    temp.remove(album);
                }
            }
        }

        if (priceAlbum > 0) {
            for (Album album : albums) {
                if (album.getPrice() > priceAlbum) {
                    temp.remove(album);
                }
            }
        }

        model.addAttribute("albums", getAlbumDTO(temp));
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("singers", singerService.getAllSingers());
        return "index";
    }

    private Genre getGenre(String id) {
        for (Genre genre : genreService.getAllGenres()) {
            if (genre.getGenreID().equals(id)) {
                return genre;
            }
        }
        return null;
    }

    private Singer getSinger(String id) {
        for (Singer singer : singerService.getAllSingers()) {
            if (singer.getSingerID().equals(id)) {
                return singer;
            }
        }
        return null;
    }

    @GetMapping("/home/cart/remove/{id}")
    public String removeItem(@PathVariable String id) {
        receiptLineService.deleteReceiptLineByAlbumID(id);
        return "redirect:/home/cart";
    }

    @GetMapping("/home/cart/checkout/{id}")
    public String checkoutProduct(Model model, @PathVariable String id) {
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = securityUser.getUsername();

        List<Cart> cart = receiptLineService.getUserCart(username);
        double totalPrice = 0;

        for (Cart product : cart) {
            totalPrice += product.getSubtotal();
        }

        Context context = new Context();
        context.setVariable("order_id", id);
        context.setVariable("cart", cart);
        context.setVariable("totalPrice", totalPrice);
        String mailContent = templateEngine.process("order-confirmation", context);
        String subject = "[MusicShop] Confirm order";
        mailServices.sendEmailEmbedEmail(username, subject, mailContent);
        receiptServices.payment(id);
        return "thankyoupage";
    }

    @GetMapping("/home/cart")
    public String shoppingCart(Model model) {
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = securityUser.getUsername();
        List<Cart> cart = receiptLineService.getUserCart(username);
        double totalPrice = 0;

        for (Cart product : cart) {
            totalPrice += product.getSubtotal();
        }

        model.addAttribute("receipts", cart);
        model.addAttribute("total", totalPrice);
        model.addAttribute("receiptID", receiptServices.getCurrentReceiptId(username));
        return "shoppingcart";
    }

    @GetMapping("/home/product/detail/{id}")
    public String productDetail(@PathVariable String id, Model model, HttpSession session) {
        Album album = albumService.getAlbumById(id).get();
        model.addAttribute(album);
        model.addAttribute("genre", getGenre(album.getGenreID()));
        model.addAttribute("singer", getSinger(album.getSingerID()));
        model.addAttribute("isAddSuccess", session.getAttribute("isAddSuccess"));

        session.removeAttribute("isAddSuccess");
        return "productdetail";
    }

    @RequestMapping(value = "/home/cart/add/{id}", method = RequestMethod.POST)
    public String addProductToCart(HttpSession session, @PathVariable String id, @RequestParam("quantity") int quantity, HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = securityUser.getUsername();
        String userCurrentOrderId = receiptServices.getCurrentReceiptId(username);

        if (userCurrentOrderId == null || userCurrentOrderId.equals("")) {
            int currentIndex = Integer.parseInt(receiptServices.getLargestOrderId().substring(4)) + 1;
            String newOrderId = "RCPT" + Strings.padStart(String.valueOf(currentIndex), 3, '0');
            User user = userServices.getUserByUsername(username);

            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String strDate = dateFormat.format(date);

            Receipt order = Receipt.builder()
                                   .receiptID(newOrderId)
                                   .userID(username)
                                   .dateEstablished(strDate)
                                   .state("unpaid")
                                   .build();
            userCurrentOrderId = newOrderId;
            receiptServices.createNewOrder(order);
        }

        ReceiptLine orderLine = receiptLineService.insertNewOrderLine(userCurrentOrderId, id, quantity);

        session.setAttribute("isAddSuccess", true);
        return getPreviousPageByRequest(request).orElse("redirect:/");
    }

    private Optional<String> getPreviousPageByRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }


    @GetMapping("/error")
    public String handleError() {
        return "admin/error";
    }
}
