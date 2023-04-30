package tdtu.edu.vn.finalproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Model.Album;
import tdtu.edu.vn.finalproject.Model.Product;
import tdtu.edu.vn.finalproject.Repository.AlbumRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service
public class AlbumServiceImpl implements AlbumService{
    @Autowired
    AlbumRepository albumRepository;
    @Override
    public List<Album> getAllAlbum() {
        return albumRepository.findAll();
    }

    @Override
    public Album addAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public void removeAlbumById(String id) {
        albumRepository.deleteById(id);
    }

    @Override
    public Optional<Album> getAlbumById(String id) {
        return albumRepository.findById(id);
    }

    @Override
    public Collection<Album> searchAlbumByName(String ar) {
        return albumRepository.searchByName(ar);
    }
}
