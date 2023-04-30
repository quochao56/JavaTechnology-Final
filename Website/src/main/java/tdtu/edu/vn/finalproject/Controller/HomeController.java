package tdtu.edu.vn.finalproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.vn.finalproject.DTO.AlbumDTO;
import tdtu.edu.vn.finalproject.DTO.ReceiptLineDTO;
import tdtu.edu.vn.finalproject.Model.*;
import tdtu.edu.vn.finalproject.Service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
//    @Autowired
//    ProductServiceImpl productService;
//    @Autowired
//    CategoryServiceImpl categoryService;
    @Autowired
    AlbumServiceImpl albumService;
    @Autowired
    GenreServiceImpl genreService;
    @Autowired
    SingerServiceImpl singerService;
    @Autowired
    ReceiptLineServiceImpl receiptLineService;
    double TOTAL = 0.0;

    @GetMapping("/")
    public String showProduct(Model model) {
        model.addAttribute("albums", getAlbumDTO(albumService.getAllAlbum()));
        model.addAttribute("genres", genreService.getAllGenre());
        model.addAttribute("singers", singerService.getAllSinger());
        return "index";
    }
    private List<AlbumDTO> getAlbumDTO(List<Album> albums){
        List<AlbumDTO> albumDTOS = new ArrayList<>();
        AlbumDTO albumDTO;
        for(Album i: albums){
            albumDTO = new AlbumDTO();
            albumDTO.setAlbumID(i.getAlbumID());
            albumDTO.setName(i.getName());
            albumDTO.setAlbumDescription(i.getAlbumDescription());
            albumDTO.setPrice(i.getPrice());
            albumDTO.setThumbnail(i.getThumbnail());
            albumDTO.setSinger(singerService.getSinger(i.getSingerID()).get());
            albumDTO.setNumberOfSongs(i.getNumberOfSongs());
            albumDTO.setGenre(genreService.getGenre(i.getGenreID()).get());
            albumDTOS.add(albumDTO);
        }
        return albumDTOS;
    }

    @GetMapping("/admin")
    public String adminhome() {
        return "adminpage";
    }
    @RequestMapping(value = "/home/search", method = RequestMethod.POST)
    public String searchProduct(@RequestParam("nameofalbum") String nameofalbum,
                                @RequestParam("genreoption") String genreoption,
                                @RequestParam("singeroption") String singeroption,
                                @RequestParam("pricealbum") String price,
                                Model model) {
        List<Album> albums;;
        List<Album> temp = new ArrayList<>();
        Double priceAlbum = 0.0;
        if(!price.equals("")){
            priceAlbum = Double.parseDouble(price);
        }
        if( nameofalbum.equals("") && genreoption.equals("all") && singeroption.equals("all") && price.equals("")){
            return "redirect:/";
        }
        if (!nameofalbum.equals("")){
            albums = (List<Album>) albumService.searchAlbumByName(nameofalbum);
        }else{
            albums = albumService.getAllAlbum();
        }
        if(!genreoption.equals("all"))
        {
            for(Album album: albums){
                if(getGenre(album.getGenreID()).getGenreName().toLowerCase().equals(genreoption.toLowerCase())){
                    temp.add(album);
                }
            }
        }else {
            for(Album album: albums){
                temp.add(album);
            }
        }
        if(!singeroption.equals("all"))
        {
            for(Album album: albums){
                if(!getSinger(album.getSingerID()).getSingerName().toLowerCase().equals(singeroption.toLowerCase())){
                    temp.remove(album);
                }
            }
        }

        if(priceAlbum > 0){
            for(Album album: albums){
                if(album.getPrice() > priceAlbum){
                    temp.remove(album);
                }
            }
        }

        model.addAttribute("albums", getAlbumDTO(temp));
        model.addAttribute("genres", getListOfGenres(temp));
        model.addAttribute("singers", getListOfSingers(temp));
        return "index";
    }

    private List<Genre> getListOfGenres(List<Album> albums){
        List<Genre> genres = new ArrayList<>();
        for(Album i: albums){
            if(!genres.contains(getGenre(i.getGenreID()))){
                genres.add(getGenre(i.getGenreID()));
            }

        }
        return genres;
    }
    private List<Singer> getListOfSingers(List<Album> albums){
        List<Singer> singers = new ArrayList<>();
        for(Album i: albums){
            if(singers.contains(getSinger(i.getSingerID()))){
                singers.add(getSinger(i.getSingerID()));
            }

        }
        return singers;
    }

    @GetMapping("/home/product/detail/{id}")
    public String productDetail(@PathVariable String id, Model model) {
        Album album = albumService.getAlbumById(id).get();
        model.addAttribute(album);
        model.addAttribute("genre", getGenre(album.getGenreID()));
        model.addAttribute("singer", getSinger(album.getSingerID()));
        return "productdetail";
    }
    @RequestMapping(value = "/home/cart/add/{id}", method = RequestMethod.POST)
    public String addProductToCart(@PathVariable String id, @RequestParam("quantity")int quantity){
        ReceiptLine receiptLine = new ReceiptLine();
        Album album = albumService.getAlbumById(id).get();
        receiptLine.setAlbumID(id);
        receiptLine.setQuantity(quantity);
        receiptLine.setPrice(album.getPrice()*quantity);
        receiptLineService.addReceiptLine(receiptLine);
        return "redirect:/";
    }
    @GetMapping("/home/cart")
    public String shoppingCart(Model model){
        List<ReceiptLine> receiptLineList = receiptLineService.getAllReceiptLine();
        model.addAttribute("receipts", getReceiptLineDTO(receiptLineList));
        model.addAttribute("total", TOTAL);
        return "shoppingcart";
    }
    private List<ReceiptLineDTO> getReceiptLineDTO(List<ReceiptLine> receiptLineList){
        List<ReceiptLineDTO> receiptLineDTOS = new ArrayList<>();
        ReceiptLineDTO receiptLineDTO;
        Album album;
        for(ReceiptLine i : receiptLineList){
            receiptLineDTO = new ReceiptLineDTO();
            receiptLineDTO.setReceiptID(i.getReceiptID());
            album = new Album();
            album = albumService.getAlbumById(i.getAlbumID()).get();
            receiptLineDTO.setThumbnail(album.getThumbnail());
            receiptLineDTO.setPrice(album.getPrice());
            receiptLineDTO.setQuantity(i.getQuantity());
            receiptLineDTO.setAlbumName(album.getName());
            receiptLineDTO.setSubtotal(receiptLineDTO.getPrice());
            receiptLineDTO.setAlbumID(i.getAlbumID());
            receiptLineDTO.setReceiptID(i.getReceiptID());
            TOTAL += receiptLineDTO.getPrice()*receiptLineDTO.getQuantity();
            receiptLineDTOS.add(receiptLineDTO);
        }
        return receiptLineDTOS;
    }
    @GetMapping("/home/cart/remove/{id}")
    public String removeItem(@PathVariable String id){
        receiptLineService.removeReceiptLineById(id);
        return "shoppingcart";
    }
    @GetMapping("/home/cart/checkout")
    public String checkoutProduct(Model model){
//         if empty then customer cannot go the checkout page
        if(receiptLineService.getAllReceiptLine().isEmpty()){
            return "redirect:/home/cart";
        }
        return "thankyoupage";
    }
    private Genre getGenre(String id){
        for(Genre genre : genreService.getAllGenre()){
            if(genre.getGenreID().equals(id)){
                return genre;
            }
        }
        return null;
    }

    private Singer getSinger(String id){
        for(Singer singer : singerService.getAllSinger()){
            if(singer.getSingerID().equals(id)){
                return singer;
            }
        }
        return null;
    }


}
