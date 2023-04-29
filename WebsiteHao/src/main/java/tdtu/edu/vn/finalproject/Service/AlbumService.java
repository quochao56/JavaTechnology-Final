package tdtu.edu.vn.finalproject.Service;

import tdtu.edu.vn.finalproject.Model.Album;
import tdtu.edu.vn.finalproject.Model.Song;

import java.util.List;
import java.util.Optional;

public interface AlbumService {

    void addAblbum(Album album);
    List<Album> getAllAlbums();

    Optional<Album> getAlbumById(String id);

    void deleteAlbumById(String id);
}
