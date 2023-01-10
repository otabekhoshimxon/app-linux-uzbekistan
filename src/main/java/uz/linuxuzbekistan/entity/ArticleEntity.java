package uz.linuxuzbekistan.entity;//Project name : app-linux-uzbekistan
//Time         : 5:04 PM
//Path         : src/main/java/uz/linuxuzbekistan/entity


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import uz.linuxuzbekistan.enums.ArticleStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = true, name = "view_count")
    private Integer viewCount;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ArticleStatus status=ArticleStatus.NOT_PUBLISHED;
    @Column(nullable = false)
    private Boolean visible = Boolean.TRUE;
    @Column(nullable = false, name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column(nullable = true, name = "publish_date")
    private LocalDateTime publishDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id",updatable = false,insertable = false)
    private ProfileEntity moderator;
    private String moderator_id;

    @JoinColumn(name = "publisher_id",insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ProfileEntity publisher;
    private String publisher_id;


    @JoinColumn(name = "image_id",insertable = false,updatable = false)
    @OneToOne
    private AttachEntity image;
    private String image_Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId",insertable = false, updatable = false)
    private CategoryEntity category;
    private String categoryId;






}



