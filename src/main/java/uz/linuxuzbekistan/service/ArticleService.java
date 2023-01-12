package uz.linuxuzbekistan.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.linuxuzbekistan.config.CustomUserDetails;
import uz.linuxuzbekistan.dto.ArticleCreateDTO;
import uz.linuxuzbekistan.dto.ArticleUpdateDTO;
import uz.linuxuzbekistan.entity.ArticleEntity;
import uz.linuxuzbekistan.entity.AttachEntity;
import uz.linuxuzbekistan.entity.CategoryEntity;
import uz.linuxuzbekistan.repository.ArticleRepository;
import uz.linuxuzbekistan.util.CurrentUserUtil;

import java.util.Objects;
import java.util.Optional;

@Service
public class ArticleService {


    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AttachService attachService;
    @Autowired
    private CurrentUserUtil currentUserUtil;

    public ResponseEntity create(ArticleCreateDTO articleCreate) {
        CategoryEntity categoryById = categoryService.getCategoryById(articleCreate.getCategoryId());
        AttachEntity attachById = attachService.getAttachById(categoryById.getId());
        CustomUserDetails currentUser = currentUserUtil.getCurrentUser();
        if (attachById == null) {
            return ResponseEntity.badRequest().body("Image not found");
        }
        if (categoryById == null) {
            return ResponseEntity.badRequest().body("Category not found");
        }
        ArticleEntity article = new ArticleEntity();
        article.setCategoryId(categoryById.getId());
        article.setTitle(articleCreate.getTitle());
        article.setContent(articleCreate.getContent());
        article.setDescription(articleCreate.getDescription());
        article.setImage_Id(articleCreate.getImageId());
        article.setPublisher_id(currentUser.getId());
        articleRepository.save(article);
        return ResponseEntity.ok("Article created successfully");
    }

    public ResponseEntity update(String id, ArticleUpdateDTO articleUpdate) {

        Optional<ArticleEntity> byId = articleRepository.findById(id);
        if (byId.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        ArticleEntity article = byId.get();
        article.setTitle(articleUpdate.getTitle());
        article.setContent(articleUpdate.getContent());
        article.setDescription(articleUpdate.getDescription());
        articleRepository.save(article);

        return ResponseEntity.ok("Article updated successfully");
    }


    public ResponseEntity delete(String id) {
        Optional<ArticleEntity> byId = articleRepository.findById(id);
        if (byId.isEmpty()){
            return ResponseEntity.badRequest().body("Article not found");
        }
        ArticleEntity article = byId.get();
        article.setVisible(false);
        articleRepository.save(article);
        return ResponseEntity.ok("Article deleted successfully");
    }


    public ResponseEntity increaseViewCount(String id) {

        ArticleEntity byId = getById(id);
        if (Objects.isNull(byId)){
            return ResponseEntity.badRequest().body("Article not found");
        }
        byId.setViewCount(byId.getViewCount()+1);
        articleRepository.save(byId);
        return ResponseEntity.ok("Article view count increased successfully");


    }

    public ArticleEntity getById(String id) {
        Optional<ArticleEntity> byId = articleRepository.findById(id);
        return byId.orElse(null);
    }
}
