package uz.linuxuzbekistan.dto;

import lombok.Data;
@Data
public class ProfileCreateDTO {

    private String name;
    private String surname;
    private String phone;
    private String password;
}
