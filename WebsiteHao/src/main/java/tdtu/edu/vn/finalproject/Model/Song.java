package tdtu.edu.vn.finalproject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="song")
public class Song {
    @Id
    private String songID;
    private String albumID;
    private String songName;
    private Long duration;
    private String thumbnail;
    private String songDescription;
}
