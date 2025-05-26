package guru.springframework.beer.services;

import guru.springframework.beer.enums.BeerStyle;
import guru.springframework.beer.web.model.BeerDto;
import guru.springframework.beer.web.model.BeerPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Created by sergei on 24/05/2025
 */
public interface BeerService {

    BeerPagedList listBeers(String beerName, BeerStyle[] beerStyles, Pageable pageable, Boolean showInventoryOnHand);

    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerDto getByUpc(String upc);

    void deleteBeerById(UUID beerId);
}
