package uz.linuxuzbekistan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.linuxuzbekistan.repository.ProfileRepository;

@Service
public class ProfileService {


    @Autowired
    private ProfileRepository profileRepository;


    public boolean existsById(String id) {
        return profileRepository.existsById(id);
    }
}
