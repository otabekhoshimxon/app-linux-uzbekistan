package uz.linuxuzbekistan.entity;//Project name : app-linux-uzbekistan
//Time         : 10:51 PM


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
@Table(name = "attach")
public class AttachEntity {


    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column
    private String originName;
    @Column
    private String extention;
    @Column
    private Long size;
    @Column
    private String path;

    @Column
    private LocalDateTime createdDate = LocalDateTime.now();


}



