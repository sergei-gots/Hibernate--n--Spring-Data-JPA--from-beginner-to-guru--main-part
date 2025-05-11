package guru.springframework.sdjpa.beer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by sergei on 11/05/2025
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    private UUID id;
    private String name;
}
