package tdtu.edu.vn.finalproject.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tdtu.edu.vn.finalproject.Model.Album;

import java.util.Collection;

@Repository
@EnableJpaRepositories
public interface AlbumRepository extends CrudRepository<Album, String> {
    Page<Album> findAll(Pageable pageable);

    @Query("SELECT s FROM Album s WHERE s.name like %:key%")
    public Collection<Album> searchByName(@Param("key") String key);
}
