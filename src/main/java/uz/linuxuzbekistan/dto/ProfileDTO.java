package uz.linuxuzbekistan.dto;

import lombok.Data;
import uz.linuxuzbekistan.enums.GeneralRole;
import uz.linuxuzbekistan.enums.GeneralStatus;

import java.time.LocalDateTime;

@Data
public class ProfileDTO {
    private String id;
    private String name;
    private String surname;
    private String phone;
    private String password;
    private LocalDateTime createdDate;
    private GeneralStatus status;
    private GeneralRole role;
    private Boolean visible;


}
