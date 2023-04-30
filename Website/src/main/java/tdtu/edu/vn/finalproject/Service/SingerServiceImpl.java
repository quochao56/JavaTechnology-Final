package tdtu.edu.vn.finalproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Model.Genre;
import tdtu.edu.vn.finalproject.Model.Singer;
import tdtu.edu.vn.finalproject.Repository.SingerRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service
public class SingerServiceImpl implements SingerService{
    @Autowired
    SingerRepository singerRepository;
    @Override
    public List<Singer> getAllSinger() {
        return singerRepository.findAll();
    }

    @Override
    public Singer addSinger(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    public void removeSingerById(String id) {
        singerRepository.deleteById(id);
    }

    @Override
    public Optional<Singer> getSinger(String id) {
        return singerRepository.findById(id);
    }

}
