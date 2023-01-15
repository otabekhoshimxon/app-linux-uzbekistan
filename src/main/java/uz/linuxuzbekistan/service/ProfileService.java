package uz.linuxuzbekistan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.linuxuzbekistan.dto.ProfileCreateDTO;
import uz.linuxuzbekistan.dto.ProfileDTO;
import uz.linuxuzbekistan.entity.ProfileEntity;
import uz.linuxuzbekistan.enums.GeneralRole;
import uz.linuxuzbekistan.repository.ProfileRepository;
import uz.linuxuzbekistan.util.MD5PasswordGenerator;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {


    @Autowired
    private ProfileRepository profileRepository;


    public boolean existsById(String id) {
        return profileRepository.existsById(id);
    }

    public ResponseEntity create(ProfileCreateDTO create,GeneralRole role) {
        if(profileRepository.existsByPhone(create.getPhone())){
            return ResponseEntity.badRequest().body("Phone already exists");
        }
        ProfileEntity profile = new ProfileEntity();
        profile.setName(create.getName());
        profile.setSurname(create.getSurname());
        profile.setPhone(create.getPhone());
        profile.setPassword(MD5PasswordGenerator.getMd5Password(create.getPassword()));
        profile.setRole(role);
        profileRepository.save(profile);
        return ResponseEntity.ok("SUCCESS CREATED "+role.name());

    }

    public ResponseEntity getAll(Integer page, Integer size) {
        Sort sort =Sort.by("name");
        Pageable pageable= PageRequest.of(page,size,sort);
        Page<ProfileEntity> all = profileRepository.findAll(pageable);
        List<ProfileDTO> list=new ArrayList<>();
        for (ProfileEntity profileEntity : all) {
            list.add(getProfile(profileEntity));
        }
        if (all.isEmpty()){
            return ResponseEntity.badRequest().body("No profile found");
        }
        return ResponseEntity.ok(new PageImpl<>(list,pageable,all.getTotalElements()));
    }

    @Cacheable(value = "profileCache", key = "#id")
    public ProfileDTO getProfile(ProfileEntity profile){
        ProfileDTO profileDTO=new ProfileDTO();
        profileDTO.setName(profile.getName());
        profileDTO.setSurname(profile.getSurname());
        profileDTO.setId(profile.getId());
        profileDTO.setRole(profile.getRole());
        profileDTO.setStatus(profile.getStatus());
        profileDTO.setVisible(profile.getVisible());
        profileDTO.setPassword(profile.getPassword());
        profileDTO.setCreatedDate(profile.getCreatedDate());
        profileDTO.setPhone(profile.getPhone());
        return profileDTO;
    }
}
