package tdtu.edu.vn.finalproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tdtu.edu.vn.finalproject.Model.Album;
import tdtu.edu.vn.finalproject.Repository.AlbumRepository;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
@Service
public class AlbumServiceImpl implements AlbumService{
    @Autowired
    AlbumRepository albumRepository;

    @Override
    public Album addAblbum(Album album) {
        return albumRepository.save(album);
    }

    public void addAblbumCustom(MultipartFile multipartFile, String albumID,String genreID, String singerID, int numberOfSongs, double price, String description) {
        Album album = new Album();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if(fileName.contains(".."))
        {
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
        albumRepository.deleteById(id);
    }
}
