package tdtu.edu.vn.finalproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tdtu.edu.vn.finalproject.Model.Genre;
import tdtu.edu.vn.finalproject.Model.Singer;

import java.util.Collection;

public interface SingerRepository extends JpaRepository<Singer, String> {
}
