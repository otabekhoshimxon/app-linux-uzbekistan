package uz.linuxuzbekistan.entity;//Project name : app-linux-uzbekistan
//Time         : 10:52 PM


import lombok.*;
import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "attach_profile")
public class AttachProfileEntity {


    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @JoinColumn(name = "profile_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProfileEntity profile;

    @JoinColumn(name = "attach_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AttachEntity attach;

    private LocalDateTime createdDate=LocalDateTime.now();

}



