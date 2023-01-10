package uz.linuxuzbekistan.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uz.linuxuzbekistan.entity.CategoryEntity;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity ,String> {
}
