package uz.linuxuzbekistan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.linuxuzbekistan.dto.ProfileCreateDTO;
import uz.linuxuzbekistan.entity.ProfileEntity;
import uz.linuxuzbekistan.enums.GeneralRole;
import uz.linuxuzbekistan.repository.ProfileRepository;
import uz.linuxuzbekistan.util.MD5PasswordGenerator;

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
}
