package guru.springframework.sdjpa.wordpress.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;

/**
 * Created by sergei on 12/04/2025
 */
@Entity
@Table(name = "wp_posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_author")
    private User author;

    @Column(name = "post_date")
    private Timestamp createdDate;

    @Column(name = "post_date_gmt")
    private Timestamp createdDateGmt;

    @Column(name = "post_content", columnDefinition = "longtext")
    @NotNull
    @Lob
    private String content;

    @Column(name = "post_title", columnDefinition = "text")
    @NotNull
    @Lob
    private String title;

    @Column(name = "post_excerpt", columnDefinition = "text")
    @NotNull
    @Lob
    private String excerpt;

    @Column(name = "post_status")
    @Length(max = 20)
    @NotBlank
    private String status;

    @Column(name = "comment_status")
    @Length(max = 20)
    @NotBlank
    private String comment_status;

    @Column(name = "ping_status")
    @Length(max = 20)
    @NotBlank
    private String ping_status;

    @Column(name = "post_password")
    @Length(max = 255)
    @NotBlank
    private String password;

    @Column(name = "post_name")
    @Length(max = 200)
    @NotBlank
    private String name;

    @Column(name = "to_ping", columnDefinition = "text")
    @NotNull
    @Lob
    private String toPing;

    @Column(name = "pinged", columnDefinition = "text")
    @NotNull
    @Lob
    private String pinged;

    @Column(name = "post_modified")
    private Timestamp modifiedDate;

    @Column(name = "post_modified_gmt")
    private Timestamp modifiedDate_gmt;

    @Column(name = "post_content_filtered", columnDefinition = "longtext")
    @NotNull
    @Lob
    private String contentFiltered;

    @ManyToOne
    @JoinColumn(name = "post_parent")
    Post parent;

    @Length(max = 255)
    @NotBlank
    private String guid;

    private Integer menuOrder;

    @Column(name = "post_type")
    @Length(max = 20)
    @NotBlank
    private String type;

    @Column(name = "post_mime_type")
    @Length(max = 100)
    @NotBlank
    private String mimeType;

    private Long commentCount;

}
