package guru.springframework.sdjpa.wordpress.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.CodePointLength;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;


import java.sql.Timestamp;

/**
 * Created by sergei on 11/04/2025
 */
@Entity
@Table(name = "wp_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_login", length = 60, nullable = false)
    @NotBlank
    @CodePointLength(min = 1, max = 60)
    private String login;

    @Column(name = "user_pass", length = 255, nullable = false)
    @NotNull
    @Length(min = 1, max = 255)
    private String password;

    @Column(name = "user_nicename", length = 50, nullable = false)
    @NotBlank
    @CodePointLength(min = 1, max = 50)
    private String niceName;

    @Column(name = "user_email", length = 100, nullable = false)
    @Email
    @NotNull
    @Length(max = 100)
    private String eMail;

    @Column(name = "user_url", length = 100, nullable = false)
    @URL
    @Length(max = 100)
    @NotNull
    private String url;

    @Column(name = "user_registered")
    private Timestamp registrationTimestamp;

    @Column(name = "user_activation_key", length = 255)
    @NotNull
    @Size(max = 255)
    private String activationKey;

    @Column(name = "user_status")
    private Integer status = 0;

    @Basic(optional = false)
    @NotNull
    @CodePointLength(max = 250)
    private String displayName;
}
