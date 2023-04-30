package tdtu.edu.vn.finalproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Model.Singer;
import tdtu.edu.vn.finalproject.Model.Song;
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

    @Override
    public Page<Singer> paniSinger(Pageable pageable) {
        return singerRepository.findAll(pageable);
    }

    public List<Singer> search(String keyword){ return singerRepository.search(keyword);}

}
