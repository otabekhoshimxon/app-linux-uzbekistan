package uz.linuxuzbekistan.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uz.linuxuzbekistan.entity.ProfileEntity;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends PagingAndSortingRepository<ProfileEntity,String> {


    boolean existsByPhone(String phone);

    @Override
    Optional<ProfileEntity> findById(String s);

    Optional<ProfileEntity> getByPhone(String phone);
}
