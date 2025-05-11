package guru.springframework.sdjpa.beer.domain;

import guru.springframework.sdjpa.beer.enums.BeerStyle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by sergei on 11/05/2025
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36, updatable = false, nullable = false)
    private UUID id;

    @Version
    private Long version;

    private String beerName;

    @Enumerated(EnumType.STRING)
    private BeerStyle beerStyle;

    private String upc;

    private Integer quantityOnHand;
    private BigDecimal price;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp  createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedData;

}
