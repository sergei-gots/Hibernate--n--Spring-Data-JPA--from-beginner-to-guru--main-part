package guru.springframework.sdjpa.wordpress.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Created by sergei on 12/04/2025
 */
@Entity
@Table(name = "wp_terms")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Column(name = "name", nullable = false)
    @Length(min = 1, max = 200)
    @NotBlank
    private String name;

    @Column(name = "slug", nullable = false)
    @Length(min = 1, max = 200)
    @NotBlank
    private String slug;

    @NotNull
    private Long termGroup;

}
