package uz.linuxuzbekistan.dto;

import lombok.Data;
import uz.linuxuzbekistan.enums.ArticleStatus;

import java.time.LocalDateTime;

@Data
public class ArticleDTO {
    private String id;
    private String title;
    private String content;
    private String description;
    private Integer viewCount;
    private ArticleStatus status;
    private Boolean visible;
    private LocalDateTime createdDate;
    private LocalDateTime publishDate;
    private String image_Id;
    private String categoryId;
}
