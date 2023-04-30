package tdtu.edu.vn.finalproject.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tdtu.edu.vn.finalproject.Model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService{

    Song addSong(Song song);
    List<Song> getAllSongs();

    Optional<Song> getSongById(String id);

    Page<Song> paniSong(Pageable pageable);

    void deleteSongById(String id);

//    Page<Song> paniSong(Pageable pageable);
}
