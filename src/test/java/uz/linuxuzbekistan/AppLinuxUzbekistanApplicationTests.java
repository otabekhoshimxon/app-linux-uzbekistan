package uz.linuxuzbekistan;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.linuxuzbekistan.dto.AuthDTO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@SpringBootTest
class AppLinuxUzbekistanApplicationTests {

    @Test
    void contextLoads() {

        String serverUrl = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        System.out.println("serverUrl = " + serverUrl);
    }



}
