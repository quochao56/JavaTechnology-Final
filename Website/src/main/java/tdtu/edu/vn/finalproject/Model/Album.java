package tdtu.edu.vn.finalproject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Album {
    @Id
    private String albumID;
    private String genreID;
    private String singerID;
    private String thumbnail;
    private int numberOfSongs;
    private String albumDescription;
    private double price;
}
