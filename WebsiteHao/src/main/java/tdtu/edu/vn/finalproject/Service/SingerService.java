package tdtu.edu.vn.finalproject.Service;

import tdtu.edu.vn.finalproject.Model.Singer;
import tdtu.edu.vn.finalproject.Model.Song;

import java.util.List;
import java.util.Optional;

public interface SingerService {
    void addSinger(Singer singer);

    List<Singer> getAllSingers();

    Optional<Singer> getSingerById(String id);

    void deleteSingerById(String id);

//    Page<Song> paniSinger(Pageable pageable);
}
