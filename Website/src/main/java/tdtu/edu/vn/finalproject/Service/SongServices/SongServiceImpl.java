package tdtu.edu.vn.finalproject.Service.SongServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tdtu.edu.vn.finalproject.Model.Song;
import tdtu.edu.vn.finalproject.Repository.SongRepository;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    SongRepository songRepository;

    @Override
    public Song addSong(Song song) {
        return songRepository.save(song);
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
    public Page<Song> paniSong(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    @Override
    public void deleteSongById(String id) {
        songRepository.deleteById(id);
    }

    public List<Song> search(String keyword) {
        return songRepository.search(keyword);
    }


    public void addSongCustom(MultipartFile multipartFile, String songID, String albumID, String songName, String duration, String description) {
        Song song = new Song();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("File is not valid!");
        }
        try {
            song.setThumbnail(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        song.setSongName(songName);
        song.setSongID(songID);
        song.setAlbumID(albumID);
        song.setDuration(Long.valueOf(duration));
        song.setSongDescription(description);
        songRepository.save(song);
    }

//    @Override
//    public Page<Song> paniSong(Pageable pageable) {
//        return songRepository.paniSong(pageable);
//    }
}
