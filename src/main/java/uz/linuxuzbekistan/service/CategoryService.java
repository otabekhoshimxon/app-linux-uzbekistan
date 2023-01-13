package uz.linuxuzbekistan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.linuxuzbekistan.dto.CategoryCreateDTO;
import uz.linuxuzbekistan.entity.CategoryEntity;
import uz.linuxuzbekistan.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    public CategoryEntity getCategoryById(String categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElse(null);
        return categoryEntity;
    }

    public ResponseEntity create(CategoryCreateDTO categoryCreate) {

        Optional<CategoryEntity> byKey = categoryRepository.findByKey(categoryCreate.getKey());
        if (byKey.isEmpty()){
            return ResponseEntity.badRequest().body("Category key already exists");
        }
        CategoryEntity category = new CategoryEntity();
        category.setKey(categoryCreate.getKey());
        category.setNameEn(categoryCreate.getNameEn());
        category.setNameUz(categoryCreate.getNameUz());
        category.setNameRu(categoryCreate.getNameRu());
        categoryRepository.save(category);
        return ResponseEntity.ok("Category created");
    }
}
