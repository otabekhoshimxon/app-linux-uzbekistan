package uz.linuxuzbekistan.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArticleUpdateDTO {
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String description;



}
