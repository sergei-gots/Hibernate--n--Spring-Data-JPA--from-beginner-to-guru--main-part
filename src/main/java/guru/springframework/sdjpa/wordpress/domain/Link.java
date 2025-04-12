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
import org.hibernate.validator.constraints.URL;

import java.sql.Timestamp;

/**
 * Created by sergei on 12/04/2025
 */
@Entity
@Table(name = "wp_links")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    private Long id;

    @Column(name = "link_url", nullable = false)
    @URL
    @Length(max = 255)
    @NotNull
    private String url;

    @Column(name = "link_name", nullable = false)
    @Length(max = 255)
    @NotNull
    private String name;

    @Column(name = "link_image", nullable = false)
    @Length(max = 255)
    @NotNull
    private String image;

    @Column(name = "link_target", nullable = false)
    @Length(max = 25)
    @NotNull
    private String target;

    @Column(name = "link_description", nullable = false)
    @Length(max = 255)
    @NotNull
    private String description;

    @Column(name = "link_visible", nullable = false)
    @Length(max = 20)
    @NotBlank
    private String visible;


    @ManyToOne
    @JoinColumn(name = "link_owner", nullable = false)
    private User owner;
    //private Long linkOwnerId;

    @Column(name = "link_rating", nullable = false)
    private Integer rating = 0;

    @Column(name = "link_updated")
    private Timestamp modifiedDate;

    @Column(name = "link_rel", nullable = false)
    @Length(max = 255)
    @NotNull
    private String rel;

    @Column(name = "link_notes", nullable = false, columnDefinition = "mediumtext")
    @Lob
    @NotNull
    private String notes;

    @Column(name = "link_rss", nullable = false)
    @Length(max = 255)
    @NotNull
    private String rss;

}
