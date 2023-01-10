package uz.linuxuzbekistan.dto;

import lombok.Data;

@Data
public class ArticleCreateDTO {

    private String title;
    private String description;
    private String author;
    private String categoryId;


}
