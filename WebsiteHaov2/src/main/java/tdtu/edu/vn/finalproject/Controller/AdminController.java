package tdtu.edu.vn.finalproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tdtu.edu.vn.finalproject.Model.*;
import tdtu.edu.vn.finalproject.Service.*;

import java.util.ArrayList;
import java.util.List;
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

    String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/image";


    @RequestMapping(value = "/admin/singer")
    public String getAllSingers(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size,
                              @RequestParam(value = "keyword", required = false) String keyword,
                              Model model) {
        List<Singer> singers = new ArrayList<>();
        System.out.println(keyword);
        Pageable pageable = PageRequest.of(page, size, Sort.by("singerID").ascending());
        Page<Singer> singerPage = singerService.paniSinger(pageable);
        boolean flag = false;
        if (keyword != null) {
            singers = singerService.search(keyword);
            System.out.println(singers);
            flag = true;
        } else {
            singers = singerService.getAllSingers();
        }
        if (flag) {
            model.addAttribute("singer", singers);
        } else {
            model.addAttribute("singer", singerPage.getContent());
        }
        model.addAttribute("size", size);
        model.addAttribute("singerPage", singers.size());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", singerPage.getTotalPages());


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

    @RequestMapping(value = "/admin/song")
    public String getAllSongs(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size,
                              @RequestParam(value = "keyword", required = false) String keyword,
                              Model model) {
        List<Song> songs = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size, Sort.by("songID").ascending());
        Page<Song> songPage = songService.paniSong(pageable);
        boolean flag = false;
        if (keyword != null) {
            songs = songService.search(keyword);
            flag = true;
        } else {
            songs = songService.getAllSongs();
        }
        if (flag) {
            model.addAttribute("song", songs);
        } else {
            model.addAttribute("song", songPage.getContent());
        }
        model.addAttribute("size", size);
        model.addAttribute("songPage", songs.size());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", songPage.getTotalPages());
        return "admin/song";
    }

    @RequestMapping(value = "admin/song/add", method = RequestMethod.GET)
    public String addSong(@ModelAttribute("song") Song song, Model model) {
        model.addAttribute("songDTO", song);
        model.addAttribute("albums", albumService.getAllAlbums());
        return "admin/add-song";
    }

    @RequestMapping(value = "admin/song/save", method = RequestMethod.POST)
    public String saveSong(@ModelAttribute("song")
                           @RequestParam("image") MultipartFile multipartFile,
                           @RequestParam("songID") String songID,
                           @RequestParam("albumID") String albumID,
                           @RequestParam("songName") String songName,
                           @RequestParam("duration") String duration,
                           @RequestParam("songDescription") String description,
                           Model model) {
//        Song song = new Song(songID,albumID,songName,Long.valueOf(duration),image,description);
        songService.addSongCustom(multipartFile, songID, albumID, songName, duration, description);
        return "redirect:/admin/song";
    }


    @RequestMapping(value = "admin/song/edit/{id}", method = RequestMethod.GET)
    public String updateSong(@PathVariable("id") String id, Model model) {
        model.addAttribute("albums", albumService.getAllAlbums());
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
        model.addAttribute("album", albumService.getAllAlbums());
        return "admin/album";
    }

    @RequestMapping(value = {"/admin/album/add"}, method = RequestMethod.GET)
    public String addAlbums(@ModelAttribute("album") Album album, Model model) {
        model.addAttribute("albumDTO", album);
        model.addAttribute("genres", genService.getAllGenres());
        model.addAttribute("singers", singerService.getAllSingers());
        return "admin/add-album";
    }

    @RequestMapping(value = "admin/album/save", method = RequestMethod.POST)
    public String saveAlbum(@ModelAttribute("album")
                            @RequestParam("image") MultipartFile multipartFile,
                            @RequestParam("genreID") String genreID,
                            @RequestParam("albumID") String albumID,
                            @RequestParam("singerID") String singerID,
                            @RequestParam("numberOfSongs") int numberOfSongs,
                            @RequestParam("price") Double price,
                            @RequestParam("albumDescription") String description,
                            Model model) {

        albumService.addAblbumCustom(multipartFile, albumID, genreID, singerID, numberOfSongs, price, description);
        return "redirect:/admin/album";
    }

    @RequestMapping(value = "admin/album/edit/{id}", method = RequestMethod.GET)
    public String updateAlbum(@PathVariable("id") String id, Model model) {
        model.addAttribute("genres", genService.getAllGenres());
        model.addAttribute("singers", singerService.getAllSingers());
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

    @Autowired
    AdminServiceImpl adminService;


    @RequestMapping("/admin/user")
    public String showUser(Model model) {
        model.addAttribute("users", adminService.getAllUser());
        return "admin/user";
    }


    @RequestMapping("/admin/user/delete")
    public String deleteUser(@RequestParam("userID") String id) {
        System.out.println(id);
        adminService.deleteById(id);
        return "redirect:/admin/user";
    }


    @GetMapping("/admin/users/edit/{id}")
    public String editUsers(@PathVariable String id, Model model) {
        model.addAttribute("user", adminService.findByID(id));
        model.addAttribute("roles", adminService.getAllRoles());
        return "admin/add-user";
    }

    @PostMapping("/admin/edit/{id}")
    public String editProduct(@PathVariable(name = "id") String id,
                              @ModelAttribute("user") User user, Model model) {
        model.addAttribute("pageTitle", user.getUserID());
        User UserExisting = adminService.findByID(id);
        UserExisting.setUserID(id);
        UserExisting.setUsername(user.getUsername());
        UserExisting.setRole(user.getRole());


        adminService.save(UserExisting);
        model.addAttribute("user", user);
        return "redirect:/admin/user";
    }

    @PostMapping("/admin/editOrder/{id}")
    public String editOrders(@PathVariable(name = "id") String id,
                             @ModelAttribute("receipt") Receipt receipt, Model model) {
        model.addAttribute("pageTitle", receipt.getReceiptID());
        Receipt ReceiptExisting = adminService.findOrderById(id);
        ReceiptExisting.setUserID(receipt.getUserID());
        ReceiptExisting.setReceiptID(id);
        ReceiptExisting.setTotal(receipt.getTotal());
        ReceiptExisting.setState(receipt.getState());
        ReceiptExisting.setDateEstablished(receipt.getDateEstablished());


        adminService.saveReceipt(ReceiptExisting);

        return "redirect:/admin/order";
    }

    @RequestMapping(value = "/admin/editUser", method = RequestMethod.POST)
    public String update(@ModelAttribute User user) {
        adminService.save(user);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/users/search")
    public String SearchUser(Model model, @Param("keyword") String keyword) {
        List<User> listUser = adminService.ListAllUser(keyword);
        model.addAttribute("users", listUser);
        model.addAttribute("keyword", keyword);

        return "admin/user";
    }


    @RequestMapping("/admin/order")
    public String showReceipt(Model model) {
        model.addAttribute("receipts", adminService.ListAllReceipt());
        return "admin/order";
    }

    @RequestMapping("/admin/order/delete/{id}")
    public String deleteOrder(@PathVariable(name = "id") String id) {
        adminService.deleteOrderById(id);
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/edit/{id}")
    public String editOrder(@PathVariable String id, Model model) {
        model.addAttribute("user", adminService.findOrderById(id));
        model.addAttribute("states", adminService.getStatusPay());
        return "admin/add-order";
    }

    @RequestMapping("/admin/changePay/{id}")
    public String changePay(@PathVariable(name = "id") String id, Model model) {
        Receipt receipt = adminService.findOrderById(id);
        if (receipt.getState().equals("Paid")) {
            receipt.setState("Unpaid");
        } else {
            receipt.setState("Paid");
        }
        adminService.saveReceipt(receipt);
        return "redirect:/admin/order";
    }


    @RequestMapping(value = "/admin/editOrder", method = RequestMethod.POST)
    public String updateOrder(@ModelAttribute Receipt receipt) {
        adminService.saveReceipt(receipt);
        return "admin/order";
    }
}
