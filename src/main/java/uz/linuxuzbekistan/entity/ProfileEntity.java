package uz.linuxuzbekistan.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import uz.linuxuzbekistan.enums.GeneralRole;
import uz.linuxuzbekistan.enums.GeneralStatus;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String phone;
    @Column
    @Size(min = 5,max = 250)
    private String password;
    @Column
    private LocalDateTime createdDate=LocalDateTime.now();
    @Column
    @Enumerated(EnumType.STRING)
    private GeneralStatus status=GeneralStatus.ACTIVE;
    @Column
    @Enumerated(EnumType.STRING)
    private GeneralRole role=GeneralRole.STUDENT;

    @Column
    private Boolean visible=Boolean.TRUE;






}



