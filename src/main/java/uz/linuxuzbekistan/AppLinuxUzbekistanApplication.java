package uz.linuxuzbekistan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uz.linuxuzbekistan.entity.ProfileEntity;
import uz.linuxuzbekistan.enums.GeneralRole;
import uz.linuxuzbekistan.repository.ProfileRepository;
import uz.linuxuzbekistan.util.MD5PasswordGenerator;

@SpringBootApplication
public class AppLinuxUzbekistanApplication {


    @Autowired
    private ProfileRepository profileRepository;



    public static void main(String[] args) {


        SpringApplication.run(AppLinuxUzbekistanApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner() {

        return args -> {
            String phone="998908063341";
            if (!profileRepository.existsByPhone(phone)) {
                ProfileEntity profile = new ProfileEntity();
                profile.setPhone(phone);
                profile.setRole(GeneralRole.ADMIN);
                profile.setPassword(MD5PasswordGenerator.getMd5Password("1306"));
                profile.setSurname("Hoshimxon");
                profile.setName("Otabek");

                profileRepository.save(profile);
            }
        };

    }


}
