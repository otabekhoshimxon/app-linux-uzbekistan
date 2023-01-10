package uz.linuxuzbekistan.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArticleCreateDTO {
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String description;
    @NotNull
    private String categoryId;
    @NotNull
    private String imageId;



}
