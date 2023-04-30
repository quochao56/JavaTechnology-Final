package tdtu.edu.vn.finalproject.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tdtu.edu.vn.finalproject.Model.Singer;
import tdtu.edu.vn.finalproject.Model.Song;

import java.util.List;

@Repository
public interface SingerRepository extends CrudRepository<Singer, String> {

    Page<Singer> findAll(Pageable pageable);
    @Query("SELECT s FROM Singer s where s.singerID like %:keyword% or s.singerDescription like %:keyword% or s.singerName like %:keyword%")
    List<Singer> search(@Param("keyword") String keyword);
}
