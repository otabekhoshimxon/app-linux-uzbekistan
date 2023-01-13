package uz.linuxuzbekistan.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uz.linuxuzbekistan.entity.CategoryEntity;

import java.util.Optional;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity ,String> {
    Optional<CategoryEntity> findByKey(String key);
}
