package guru.springframework.beer.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by sergei on 24/05/2025
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    @Null
    UUID id;

    @NotBlank
    @Size(min = 3, max = 100)
    String firstname;

    @NotBlank
    @Size(min = 3, max = 100)
    String lastname;
}
