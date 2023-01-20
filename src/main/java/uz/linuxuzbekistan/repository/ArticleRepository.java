package uz.linuxuzbekistan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import uz.linuxuzbekistan.entity.ArticleEntity;

import java.util.List;

public interface ArticleRepository extends PagingAndSortingRepository<ArticleEntity,String> {

    @Query(value = "select * from article where category_id=:iid and visible=true",nativeQuery = true)
    Page<ArticleEntity> getArticlesByCategoryId(@Param("iid") String id, Pageable pageable);

    @Query(value = " select * from article where visible=true order by view_count limit 4",nativeQuery = true)
    List<ArticleEntity> get4ArticlesByViewCount();
}
