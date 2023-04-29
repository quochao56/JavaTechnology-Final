package tdtu.edu.vn.finalproject.Repository;

import org.springframework.data.repository.CrudRepository;
import tdtu.edu.vn.finalproject.Model.Album;

public interface AlbumRepository extends CrudRepository<Album, String> {
}
