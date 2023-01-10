package uz.linuxuzbekistan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.linuxuzbekistan.repository.ProfileRepository;

@Component
public class CustomUserDetailServise implements UserDetailsService {

    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {


        if (profileRepository.existsByPhone(phone)) {
            return new CustomUserDetails(profileRepository.getByPhone(phone).get());
        }

        return null;
    }
}
