package tdtu.edu.vn.finalproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Model.Singer;
import tdtu.edu.vn.finalproject.Repository.SingerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    SingerRepository singerRepository;

    @Override
    public void addSinger(Singer singer) {
        singerRepository.save(singer);
    }

    @Override
    public List<Singer> getAllSingers() {
        return (List<Singer>) singerRepository.findAll();
    }

    @Override
    public Optional<Singer> getSingerById(String id) {
        return singerRepository.findById(id);
    }

    @Override
    public void deleteSingerById(String id) {
        singerRepository.deleteById(id);
    }

    public List<Singer> getAllSingersne(){
        return singerRepository.getAllSingerne();
    }
}
