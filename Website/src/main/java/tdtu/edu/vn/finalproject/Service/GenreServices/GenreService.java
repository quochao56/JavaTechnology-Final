package tdtu.edu.vn.finalproject.Service.GenreServices;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tdtu.edu.vn.finalproject.Model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    void addGenre(Genre genre);

    List<Genre> getAllGenres();

    Optional<Genre> getGenreById(String id);

    void deleteGenreById(String id);

    Page<Genre> paginationGenre(Pageable pageable);
}
