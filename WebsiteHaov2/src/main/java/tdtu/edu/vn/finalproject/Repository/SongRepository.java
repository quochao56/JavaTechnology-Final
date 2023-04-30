package tdtu.edu.vn.finalproject.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tdtu.edu.vn.finalproject.Model.Song;

import java.util.List;

@Repository
public interface SongRepository extends CrudRepository<Song, String> {
    Page<Song> findAll(Pageable pageable);

    @Query("SELECT s FROM Song s where s.albumID like %:keyword% or s.songID like %:keyword% or s.songName like %:keyword% or s.songDescription like %:keyword% ")
    List<Song> search(@Param("keyword") String keyword);
}
