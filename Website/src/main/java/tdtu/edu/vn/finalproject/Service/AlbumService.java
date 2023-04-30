package tdtu.edu.vn.finalproject.Service;

import tdtu.edu.vn.finalproject.Model.Album;
import tdtu.edu.vn.finalproject.Model.Product;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<Album> getAllAlbum();
    Album addAlbum(Album album);
    void removeAlbumById(String id);
    Optional<Album> getAlbumById(String id);
    Collection<Album> searchAlbumByName(String ar);
}
