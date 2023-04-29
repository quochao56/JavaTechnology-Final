package tdtu.edu.vn.finalproject.Service;

import tdtu.edu.vn.finalproject.Model.Album;
import tdtu.edu.vn.finalproject.Model.Genre;
import tdtu.edu.vn.finalproject.Model.Singer;

import java.util.List;
import java.util.Optional;

public interface GenreService{
    void addGenre(Genre genre);
    List<Genre> getAllGenres();

    Optional<Genre> getGenreById(String id);

    void deleteGenreById(String id);
}
