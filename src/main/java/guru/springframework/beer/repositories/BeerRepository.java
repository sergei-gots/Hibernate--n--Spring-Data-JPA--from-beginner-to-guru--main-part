package guru.springframework.beer.repositories;

import guru.springframework.beer.domain.Beer;
import guru.springframework.beer.enums.BeerStyle;
import guru.springframework.beer.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by sergei on 11/05/2025
 */
public interface BeerRepository extends JpaRepository<Beer, UUID> {

    default Beer findByIdOrElseThrowNotFoundException(UUID beerId) {
        return findById(beerId).orElseThrow(() -> new NotFoundException(Beer.class, beerId));
    }

    Page<Beer> findAllByBeerName(String beerName, Pageable pageable);

    Page<Beer> findAllByBeerStyle(BeerStyle beerStyle, Pageable pageable);

    Page<Beer> findAllByBeerStyleIn(BeerStyle[] beerStyle, Pageable pageable);

    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyle beerStyle, Pageable pageable);

    Optional<Beer> findByUpc(String upc);
}
