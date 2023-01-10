package uz.linuxuzbekistan.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.linuxuzbekistan.config.CustomUserDetails;
import uz.linuxuzbekistan.dto.ArticleCreateDTO;
import uz.linuxuzbekistan.entity.ArticleEntity;
import uz.linuxuzbekistan.entity.AttachEntity;
import uz.linuxuzbekistan.entity.CategoryEntity;
import uz.linuxuzbekistan.repository.ArticleRepository;
import uz.linuxuzbekistan.util.CurrentUserUtil;

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
        return ResponseEntity.ok(article);
    }
}
