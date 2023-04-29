package tdtu.edu.vn.finalproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Model.Song;
import tdtu.edu.vn.finalproject.Repository.SongRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService{
    @Autowired
    SongRepository songRepository;

    @Override
    public void addSong(Song song) {
        songRepository.save(song);
    }

    @Override
    public List<Song> getAllSongs() {
        return (List<Song>) songRepository.findAll();
    }

    @Override
    public Optional<Song> getSongById(String id) {
        return songRepository.findById(id);
    }

    @Override
    public void deleteSongById(String id) {
        songRepository.deleteById(id);
    }

//    @Override
//    public Page<Song> paniSong(Pageable pageable) {
//        return songRepository.paniSong(pageable);
//    }
}
