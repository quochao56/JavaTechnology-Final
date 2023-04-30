package tdtu.edu.vn.finalproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tdtu.edu.vn.finalproject.Model.Album;
import tdtu.edu.vn.finalproject.Model.Product;

import java.util.Collection;

public interface AlbumRepository extends JpaRepository<Album, String> {

    @Query("SELECT s FROM Album s WHERE s.name like %:key%")
    public Collection<Album> searchByName(@Param("key") String key);
}
