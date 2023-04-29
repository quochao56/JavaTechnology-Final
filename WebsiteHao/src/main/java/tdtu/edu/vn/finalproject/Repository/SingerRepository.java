package tdtu.edu.vn.finalproject.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tdtu.edu.vn.finalproject.Model.Singer;

import java.util.List;

@Repository
public interface SingerRepository extends CrudRepository<Singer, String> {

    @Query(value = "SELECT p from Singer p")
    public List<Singer> getAllSingerne();
}
