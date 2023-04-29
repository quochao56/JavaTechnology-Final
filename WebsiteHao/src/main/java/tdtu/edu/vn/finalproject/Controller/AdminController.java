package tdtu.edu.vn.finalproject.Controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.vn.finalproject.Model.Album;
import tdtu.edu.vn.finalproject.Model.Genre;
import tdtu.edu.vn.finalproject.Model.Singer;
import tdtu.edu.vn.finalproject.Model.Song;
import tdtu.edu.vn.finalproject.Service.*;

import java.util.Optional;

@Controller
public class AdminController {


    @Autowired
    SingerServiceImpl singerService;
    @Autowired
    SongServiceImpl songService;
    @Autowired
    GenServiceImpl genService;
    @Autowired
    AlbumServiceImpl albumService;
    String uploadDir = System.getProperty("user.dir") + "/midterm2/src/main/resources/static/image";

    //
    @RequestMapping(value = "/admin/singer", method = RequestMethod.GET)
    public String getAllSingers(Model model) {
        model.addAttribute("singer", singerService.getAllSingers());
        return "admin/singer";
    }

    @RequestMapping(value = "admin/singer/add", method = RequestMethod.GET)
    public String addSinger(@ModelAttribute("singer") Singer singer, Model model) {
        model.addAttribute("singerDTO", singer);
        return "admin/add-singer";
    }

    @RequestMapping(value = "admin/singer/save", method = RequestMethod.POST)
    public String saveSinger(@ModelAttribute("singer")
                             @RequestParam("singerID") String id,
                             @RequestParam("singerName") String name,
                             @RequestParam("singerDescription") String description,
                             Model model) {
        Singer singer = new Singer(id, name, description);
        singerService.addSinger(singer);
        return "redirect:/admin/singer";
    }

    @RequestMapping(value = "admin/singer/edit/{id}", method = RequestMethod.GET)
    public String updateSinger(@PathVariable("id") String id, Model model) {
        Singer singer = new Singer();
        Optional<Singer> singerget = singerService.getSingerById(id);
        singer.setSingerID(singerget.get().getSingerID());
        singer.setSingerName(singerget.get().getSingerName());
        singer.setSingerDescription(singerget.get().getSingerDescription());
        model.addAttribute("singerDTO", singer);
        return "admin/add-singer";
    }

    @RequestMapping(value = "admin/singer/delete", method = RequestMethod.POST)
    public String removeSinger(@RequestParam("singerID") String id) {
        singerService.deleteSingerById(id);
        return "redirect:/admin/singer";
    }

    @RequestMapping(value = "/admin/song", method = RequestMethod.GET)
    public String getAllSongs(Model model) {
        model.addAttribute("song", songService.getAllSongs());
        return "admin/song";
    }

    @RequestMapping(value = "admin/song/add", method = RequestMethod.GET)
    public String addSong(@ModelAttribute("song") Song song, Model model) {
        model.addAttribute("songDTO", song);
        return "admin/add-song";
    }

    @RequestMapping(value = "admin/song/save", method = RequestMethod.POST)
    public String saveSong(@ModelAttribute("songe")
                            @RequestParam("songID") String songID,
                            @RequestParam("albumID") String albumID,
                            @RequestParam("songName")  String songName,
                            @RequestParam("duration") String duration,
                            @RequestParam("thumbnail") String image,
                            @RequestParam("songDescription") String description,
                            Model model) {
        Song song = new Song(songID,albumID,songName,Long.valueOf(duration),image,description);
        songService.addSong(song);
        return "redirect:/admin/song";
    }


    @RequestMapping(value = "admin/song/edit/{id}", method = RequestMethod.GET)
    public String updateSong(@PathVariable("id") String id, Model model) {
        Song song = new Song();
        Optional<Song> songget = songService.getSongById(id);
        song.setSongID(songget.get().getSongID());
        song.setAlbumID(songget.get().getAlbumID());
        song.setSongName(songget.get().getSongName());
        song.setDuration(songget.get().getDuration());
        song.setThumbnail(songget.get().getThumbnail());
        song.setSongDescription(songget.get().getSongDescription());
        model.addAttribute("songDTO", song);
        return "admin/add-song";
    }

    @RequestMapping(value = "admin/song/delete", method = RequestMethod.POST)
    public String removeSong(@RequestParam("songID") String id) {
        songService.deleteSongById(id);
        return "redirect:/admin/song";
    }

    @RequestMapping(value = "/admin/genre", method = RequestMethod.GET)
    public String getAllGenres(Model model) {
        model.addAttribute("genre", genService.getAllGenres());
        return "admin/genre";
    }

    @RequestMapping(value = "admin/genre/add", method = RequestMethod.GET)
    public String addGenre(@ModelAttribute("genre") Genre genre, Model model) {
        model.addAttribute("genreDTO", genre);
        return "admin/add-genre";
    }

    @RequestMapping(value = "admin/genre/save", method = RequestMethod.POST)
    public String saveSinger(@ModelAttribute("genre")
                             @RequestParam("genreID") String id,
                             @RequestParam("genreName") String name,
                             Model model) {
        Genre genre = new Genre(id, name);
        genService.addGenre(genre);
        return "redirect:/admin/genre";
    }

    @RequestMapping(value = "admin/genre/edit/{id}", method = RequestMethod.GET)
    public String updateGenre(@PathVariable("id") String id, Model model) {
        Genre genre = new Genre();
        Optional<Genre> genreget = genService.getGenreById(id);
        genre.setGenreID(genreget.get().getGenreID());
        genre.setGenreName(genreget.get().getGenreName());
        model.addAttribute("genreDTO", genre);
        return "admin/add-genre";
    }

    @RequestMapping(value = "admin/genre/delete", method = RequestMethod.POST)
    public String removeGenre(@RequestParam("genreID") String id) {
        genService.deleteGenreById(id);
        return "redirect:/admin/genre";
    }


    @RequestMapping(value = {"/admin/album"}, method = RequestMethod.GET)
    public String getAllAlbums(Model model) {
        System.out.println(albumService.getAllAlbums());
        model.addAttribute("album", albumService.getAllAlbums());
        return "admin/album";
    }

    @RequestMapping(value = "admin/album/save", method = RequestMethod.POST)
    public String saveAlbum(@ModelAttribute("album")
                            @RequestParam("genreID") String genreID,
                            @RequestParam("albumID") String albumID,
                            @RequestParam("singerID")  String singerID,
                            @RequestParam("numberOfSongs") String numberOfSongs,
                            @RequestParam("thumbnail") String image,
                            @RequestParam("price") String price,
                            @RequestParam("albumDescription") String description,
                            Model model) {
        Album album = new Album(albumID,genreID,singerID,image,Integer.valueOf(numberOfSongs),description,Double.valueOf(price));
        albumService.addAblbum(album);
        return "redirect:/admin/album";
    }

    @RequestMapping(value = "admin/album/edit/{id}", method = RequestMethod.GET)
    public String updateAlbum(@PathVariable("id") String id, Model model) {
        Album album = new Album();
        Optional<Album> albumget = albumService.getAlbumById(id);
        album.setAlbumID(albumget.get().getAlbumID());
        album.setGenreID(albumget.get().getGenreID());
        album.setSingerID(albumget.get().getSingerID());
        album.setThumbnail(albumget.get().getThumbnail());
        album.setNumberOfSongs(albumget.get().getNumberOfSongs());
        album.setPrice(albumget.get().getPrice());
        album.setAlbumDescription(albumget.get().getAlbumDescription());

        model.addAttribute("albumDTO", album);
        return "admin/add-album";
    }

    @RequestMapping(value = "admin/album/delete", method = RequestMethod.POST)
    public String removeAlbum(@RequestParam("albumID") String id) {
        albumService.deleteAlbumById(id);
        return "redirect:/admin/album";
    }
//    @RequestMapping(value = "admin/album/{id}")
//    public String getAlbumId(@PathVariable("id") String id, Model model){
//        System.out.println(id);
//        model.addAttribute("album", albumService.getAlbumById(id));
//        return "redirect:/admin/album";
//    }

//    @RequestMapping(value = "/admin/singer", method = RequestMethod.GET)
//    public String getAllSingers(@NotNull Model model){
//        System.out.println(productService.getAllproduct());
//        return "redirect:/admin/album";
//    }

//    @GetMapping("/admin/product/list")
//    public String getProduct(@NotNull Model model){
//        System.out.println(productService.getAllproduct());
//        model.addAttribute("products", productService.getAllproduct());
//        return "admin/album";
//    }
//    @GetMapping("/admin/product/add")
//    public String addProduct(@NotNull Model model){
//        model.addAttribute("productDTO", new ProductDTO());
//        model.addAttribute("categories", categoryService.getAllCategory());
//        return "addproduct";
//    }
//    @RequestMapping(value = "/admin/product/get", method = RequestMethod.POST)
//    public String addProduct(@ModelAttribute("product")ProductDTO productDTO,
//                             @RequestParam("image") MultipartFile file,
//                             @RequestParam("imageName")String imgName)throws Exception
//    {
//        Product newProduct = new Product();
//        newProduct.setId(productDTO.getId());
//        newProduct.setName(productDTO.getName());
//        newProduct.setArtist(productDTO.getArtist());
//        newProduct.setPrice(productDTO.getPrice());
//        newProduct.setDescription(productDTO.getDescription());
//        newProduct.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
//        String imageUUID = "";
//        if(!file.isEmpty())
//        {
//            imageUUID = file.getOriginalFilename();
//            Path fileNameandPath = Paths.get(uploadDir, imageUUID);
//            Files.write(fileNameandPath, file.getBytes());
//        }else
//        {
//            imageUUID = imgName;
//        }
//        newProduct.setImage(imageUUID);
//        productService.addProduct(newProduct);
//        return "redirect:/admin/product/list";
//    }
//    @GetMapping("/admin/product/delete/{id}")
//    public String deleteProduct(@PathVariable long id)
//    {
//        productService.removeProductById(id);
//        return "redirect:/admin/product/list";
//    }
//    @GetMapping("/admin/product/edit/{id}")
//    public String editProduct(@PathVariable long id, Model model)
//    {
//        Product product = productService.getProductById(id).get();
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setName(product.getName());
//        productDTO.setPrice(product.getPrice());
//        productDTO.setId(product.getId());
//        productDTO.setArtist(product.getArtist());
//        productDTO.setDescription(product.getDescription());
//        productDTO.setCategoryId(product.getCategory().getId());
//        productDTO.setImageName(product.getImage());
//        model.addAttribute("productDTO", productDTO);
//        model.addAttribute("categories", categoryService.getAllCategory());
//        return "addproduct";
//    }
}
