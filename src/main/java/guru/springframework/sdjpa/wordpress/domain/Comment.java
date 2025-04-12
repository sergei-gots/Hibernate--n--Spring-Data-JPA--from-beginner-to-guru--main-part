package guru.springframework.sdjpa.wordpress.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.CodePointLength;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.sql.Timestamp;

/**
 * Created by sergei on 12/04/2025
 */
@Entity
@Table(name = "wp_comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "comment_author", length = 255, nullable = false)
    @NotBlank
    @CodePointLength(min = 1, max = 255)
    private String author;

    @Column(name = "comment_author_email", length = 100, nullable = false)
    @Email
    @NotNull
    @Length(max = 100)
    private String eMail;

    @Column(name = "comment_author_url", length = 200, nullable = false)
    @URL
    @Length(max = 200)
    @NotNull
    private String url;

    @Column(name = "comment_author_IP", length = 100, nullable = false)
    @Length(max = 100)
    @NotBlank
    private String ip;

    @Column(name = "comment_date")
    private Timestamp createdDate;

    @Column(name = "comment_date_gmt")
    private Timestamp createdDateGmt;

    @Column(name = "comment_content", nullable = false)
    @NotNull
    private String content;

    @Column(name = "comment_karma")
    @NotNull
    private Integer karma;

    @Column(name = "comment_approved")
    @Length(max = 20)
    @NotBlank
    private String approved;

    @Column(name = "comment_agent")
    @Length(max = 255)
    @NotBlank
    private String agent;

    @Column(name = "comment_type")
    @Length(max = 20)
    @NotBlank
    private String type;

    @ManyToOne
    @JoinColumn(name = "comment_parent")
    private Comment parent;
    //private Long parentId;

    @ManyToOne
    private User user;
    //private Long user Id;

}
