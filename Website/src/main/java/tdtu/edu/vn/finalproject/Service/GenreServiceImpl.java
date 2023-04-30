package tdtu.edu.vn.finalproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Model.Genre;
import tdtu.edu.vn.finalproject.Repository.GenreRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService{
    @Autowired
    GenreRepository genreRepository;
    @Override
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    @Override
    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void removeGenreById(String id) {
        genreRepository.deleteById(id);
    }

    @Override
    public Optional<Genre> getGenre(String id) {
        return genreRepository.findById(id);
    }

}
