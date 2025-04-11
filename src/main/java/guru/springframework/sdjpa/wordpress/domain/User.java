package guru.springframework.sdjpa.wordpress.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    @Column(name = "user_pass", nullable = false)
    private String password;

    @Column(name = "user_nicename", nullable = false)
    private String niceName;

    @Column(name = "user_email", nullable = false)
    private String eMail;

    @Column(name = "user_url", nullable = false)
    private String url;

    @Column(name = "user_registered")
    private Timestamp registrationTimestamp;

    @Column(name = "user_activation_key")
    private String activationKey;

    @Column(name = "user_status")
    private Integer status;

    @Basic(optional = false)
    private String displayName;
}
