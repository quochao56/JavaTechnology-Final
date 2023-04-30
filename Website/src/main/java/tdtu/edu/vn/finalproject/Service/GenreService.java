package tdtu.edu.vn.finalproject.Service;

import tdtu.edu.vn.finalproject.Model.Album;
import tdtu.edu.vn.finalproject.Model.Genre;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<Genre> getAllGenre();
    Genre addGenre(Genre genre);
    void removeGenreById(String id);
    Optional<Genre> getGenre(String id);
}
