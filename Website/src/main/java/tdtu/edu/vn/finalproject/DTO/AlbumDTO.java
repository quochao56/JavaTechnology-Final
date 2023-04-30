package tdtu.edu.vn.finalproject.DTO;

import lombok.Data;
import tdtu.edu.vn.finalproject.Model.Genre;
import tdtu.edu.vn.finalproject.Model.Singer;

@Data
public class AlbumDTO {
    private String albumID;
    private String name;
    private Genre genre;
    private Singer singer;
    private String thumbnail;
    private int numberOfSongs;
    private String albumDescription;
    private double price;
}
