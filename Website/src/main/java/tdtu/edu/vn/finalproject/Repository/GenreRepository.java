package tdtu.edu.vn.finalproject.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tdtu.edu.vn.finalproject.Model.Genre;

@Repository
public interface GenreRepository extends CrudRepository<Genre, String> {
    public Page<Genre> findAll(Pageable pageable);
}
