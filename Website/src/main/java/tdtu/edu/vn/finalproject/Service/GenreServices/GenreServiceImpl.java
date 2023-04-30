package tdtu.edu.vn.finalproject.Service.GenreServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Model.Genre;
import tdtu.edu.vn.finalproject.Repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    GenreRepository genreRepository;

    @Override
    public void addGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public List<Genre> getAllGenres() {
        return (List<Genre>) genreRepository.findAll();
    }

    @Override
    public Optional<Genre> getGenreById(String id) {
        return genreRepository.findById(id);
    }

    @Override
    public void deleteGenreById(String id) {
        genreRepository.deleteById(id);
    }

    @Override
    public Page<Genre> paginationGenre(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }
}
