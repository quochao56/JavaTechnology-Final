package tdtu.edu.vn.finalproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Model.Album;
import tdtu.edu.vn.finalproject.Repository.AlbumRepository;

import java.util.List;
import java.util.Optional;
@Service
public class AlbumServiceImpl implements AlbumService{
    @Autowired
    AlbumRepository albumRepository;

    @Override
    public void addAblbum(Album album) {
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
