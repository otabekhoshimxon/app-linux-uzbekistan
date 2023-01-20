package uz.linuxuzbekistan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.linuxuzbekistan.dto.CategoryCreateDTO;
import uz.linuxuzbekistan.dto.CategoryDTO;
import uz.linuxuzbekistan.dto.CategoryUpdateDTO;
import uz.linuxuzbekistan.entity.CategoryEntity;
import uz.linuxuzbekistan.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
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
        if (!byKey.isEmpty()){
            return ResponseEntity.badRequest().body("Category key already exists");
        }
        CategoryEntity category = new CategoryEntity();
        category.setKey(categoryCreate.getKey());
        category.setNameUz(categoryCreate.getNameUz());
        categoryRepository.save(category);
        return ResponseEntity.ok("Category created");
    }
    @CachePut(value = "categoryCache", key = "#id")
    public ResponseEntity update(String id, CategoryUpdateDTO categoryUpdate) {

        Optional<CategoryEntity> byId = categoryRepository.findById(id);
        if (byId.isEmpty()){
            return ResponseEntity.badRequest().body("Category does not exist");
        }
        CategoryEntity category = new CategoryEntity();
        category.setNameUz(categoryUpdate.getNameUz());
        categoryRepository.save(category);
        return ResponseEntity.ok("Category updated");
    }

    public ResponseEntity delete(String id) {

        Optional<CategoryEntity> byId = categoryRepository.findById(id);
        if (byId.isEmpty()){
            return ResponseEntity.ok("Category not found");

        }
        CategoryEntity category = byId.get();
        category.setVisible(false);
        categoryRepository.save(category);
        return ResponseEntity.ok("Category deleted");
    }

    public ResponseEntity getAllCategories() {


        List<CategoryEntity> allCategories = categoryRepository.getAllCategories();

        List<CategoryDTO> list=new ArrayList<>();

        allCategories.forEach(category -> {
            if (category.getVisible()){
                CategoryDTO categoryDTO=new CategoryDTO();
                categoryDTO.setId(category.getId());
                categoryDTO.setKey(category.getKey());
                categoryDTO.setNameUz(category.getNameUz());
                list.add(categoryDTO);
            }
        });


        if (list.isEmpty()){
            return ResponseEntity.badRequest().body("Categories not found");
        }

        return ResponseEntity.ok(list);

    }
    @Cacheable(value = "categoryCache", key = "#key")
    public ResponseEntity getByKey(String key) {
        Optional<CategoryEntity> byKey = categoryRepository.findByKey(key);
        if (byKey.isEmpty()){
            return ResponseEntity.badRequest().body("Category not found");
        }

        CategoryEntity category = byKey.get();
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setNameUz(category.getNameUz());

        return ResponseEntity.ok(categoryDTO);
    }
}
