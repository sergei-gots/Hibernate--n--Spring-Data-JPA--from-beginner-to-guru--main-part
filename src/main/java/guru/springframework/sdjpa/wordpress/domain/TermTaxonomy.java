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
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Created by sergei on 12/04/2025
 */
@Entity
@Table(name = "wp_term_taxonomy")
public class TermTaxonomy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_taxonomy_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "term_id")
    private Term term;

    @Length(max = 32)
    @NotNull
    private String taxonomy;

    @Column(columnDefinition = "longtext")
    @Lob
    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent")
    private TermTaxonomy parent;

    private Long count;

}
