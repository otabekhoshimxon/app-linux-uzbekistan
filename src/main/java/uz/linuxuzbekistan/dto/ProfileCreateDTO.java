package uz.linuxuzbekistan.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class ProfileCreateDTO {

    private String name;
    private String surname;
    private String phone;
    private String password;
}
