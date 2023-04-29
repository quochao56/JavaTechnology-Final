package tdtu.edu.vn.finalproject.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tdtu.edu.vn.finalproject.Model.Song;
@Repository
public interface SongRepository extends CrudRepository<Song, String> {
//    Page<Song> paniSong(Pageable pageable);
}
