package guru.springframework.sdjpa.wordpress.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.CodePointLength;
import org.hibernate.validator.constraints.Length;

/**
 * Created by sergei on 12/04/2025
 */
@Entity
@Table(name = "wp_options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long id;

    @Column(name = "option_name", nullable = false)
    @CodePointLength(min = 1, max = 191)
    @NotNull
    private String name;

    @Column(name = "option_value", nullable = false, columnDefinition = "longtext")
    @Lob
    @NotNull
    private String value;

    @Column(name = "autoload", nullable = false)
    @Length(max = 20)
    @NotBlank
    private String autoload;

}
