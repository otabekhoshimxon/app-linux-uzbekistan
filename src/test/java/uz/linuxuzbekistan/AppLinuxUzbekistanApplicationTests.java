package uz.linuxuzbekistan;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@SpringBootTest
class AppLinuxUzbekistanApplicationTests {

    @Test
    void contextLoads() {

        String serverUrl = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        System.out.println("serverUrl = " + serverUrl);
    }

}
