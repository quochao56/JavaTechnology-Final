package tdtu.edu.vn.finalproject.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String artist;
    private String description;
    private String imageName;
    private long categoryId;
}
