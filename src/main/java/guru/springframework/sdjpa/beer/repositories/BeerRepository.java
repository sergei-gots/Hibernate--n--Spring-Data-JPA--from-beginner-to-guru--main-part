package guru.springframework.sdjpa.beer.repositories;

import guru.springframework.sdjpa.beer.domain.Beer;
import guru.springframework.sdjpa.beer.enums.BeerStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by sergei on 11/05/2025
 */
public interface BeerRepository extends JpaRepository<Beer, UUID> {

    Page<Beer> findAllByBeerName(String beerName, Pageable pageable);

    Page<Beer> findAllByBeerStyle(BeerStyle beerStyle, Pageable pageable);

    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyle beerStyle, Pageable pageable);

    Beer findByUpc(String upc);
}
