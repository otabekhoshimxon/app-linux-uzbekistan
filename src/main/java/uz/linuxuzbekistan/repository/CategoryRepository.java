package uz.linuxuzbekistan.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uz.linuxuzbekistan.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity ,String> {
    Optional<CategoryEntity> findByKey(String key);

    @Query("from CategoryEntity  where visible=true ")
    List<CategoryEntity> getAllCategories();
}
