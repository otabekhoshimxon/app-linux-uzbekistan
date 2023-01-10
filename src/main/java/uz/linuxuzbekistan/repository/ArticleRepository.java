package uz.linuxuzbekistan.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uz.linuxuzbekistan.entity.ArticleEntity;

public interface ArticleRepository extends PagingAndSortingRepository<ArticleEntity,String> {
}
