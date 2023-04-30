package tdtu.edu.vn.finalproject.Service.AlbumServices;

import tdtu.edu.vn.finalproject.Model.Album;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AlbumService {

    Album addAlbum(Album album);

    List<Album> getAllAlbums();

    Optional<Album> getAlbumById(String id);

    void deleteAlbumById(String id);

    public Collection<Album> searchAlbumByName(String ar);
}
