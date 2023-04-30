package tdtu.edu.vn.finalproject.Service;

import tdtu.edu.vn.finalproject.Model.Genre;
import tdtu.edu.vn.finalproject.Model.Singer;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SingerService {
    List<Singer> getAllSinger();
    Singer addSinger(Singer singer);
    void removeSingerById(String id);
    Optional<Singer> getSinger(String id);
}
