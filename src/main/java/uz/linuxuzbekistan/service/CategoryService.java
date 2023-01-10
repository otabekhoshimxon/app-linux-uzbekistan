package uz.linuxuzbekistan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.linuxuzbekistan.entity.CategoryEntity;
import uz.linuxuzbekistan.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    public CategoryEntity getCategoryById(String categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElse(null);
        return categoryEntity;
    }
}
