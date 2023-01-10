package uz.linuxuzbekistan.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uz.linuxuzbekistan.entity.AttachEntity;

public interface AttachRepository extends PagingAndSortingRepository<AttachEntity,String> {
}
