package tdtu.edu.vn.finalproject.Service.SingerServices;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tdtu.edu.vn.finalproject.Model.Singer;

import java.util.List;
import java.util.Optional;

public interface SingerService {
    void addSinger(Singer singer);

    List<Singer> getAllSingers();

    Optional<Singer> getSingerById(String id);

    void deleteSingerById(String id);

    Page<Singer> paniSinger(Pageable pageable);
}
