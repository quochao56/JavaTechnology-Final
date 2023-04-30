package tdtu.edu.vn.finalproject.Service.AlbumServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tdtu.edu.vn.finalproject.Model.Album;
import tdtu.edu.vn.finalproject.Repository.AlbumRepository;
import tdtu.edu.vn.finalproject.Repository.SongRepository;

import java.io.IOException;
import java.util.*;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    SongRepository songRepository;

    public Page<Album> paginationAlbum(Pageable pageable) {
        return albumRepository.findAll(pageable);
    }

    public Album addAlbum(Album album) {
        return albumRepository.save(album);
    }

    public void addAlbumCustom(MultipartFile multipartFile, String albumID, String genreID, String singerID, int numberOfSongs, double price, String description) {
        Album album = new Album();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        if (fileName.contains("..")) {
            System.out.println("File is not valid!");
        }
        try {
            album.setThumbnail(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        album.setAlbumID(albumID);
        album.setSingerID(singerID);
        album.setGenreID(genreID);
        album.setNumberOfSongs(numberOfSongs);
        album.setPrice(price);
        album.setAlbumDescription(description);
        albumRepository.save(album);
    }

    @Override
    public List<Album> getAllAlbums() {
        return (List<Album>) albumRepository.findAll();
    }

    @Override
    public Optional<Album> getAlbumById(String id) {
        return albumRepository.findById(id);
    }

    @Override
    public void deleteAlbumById(String id) {
        songRepository.deleteSongsByAlbumID(id);
        albumRepository.deleteById(id);
    }

    @Override
    public Collection<Album> searchAlbumByName(String ar) {
        return albumRepository.searchByName(ar);
    }
}
