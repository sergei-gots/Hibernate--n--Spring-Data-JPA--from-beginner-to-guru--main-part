package guru.springframework.beer.services;

import guru.springframework.beer.domain.Beer;
import guru.springframework.beer.enums.BeerStyle;
import guru.springframework.beer.repositories.BeerRepository;
import guru.springframework.beer.exceptions.NotFoundException;
import guru.springframework.beer.web.mappers.BeerMapper;
import guru.springframework.beer.web.model.BeerDto;
import guru.springframework.beer.web.model.BeerPagedList;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by sergei on 24/05/2025
 */
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;

    private final BeerMapper beerMapper;

    @Autowired
    public BeerServiceImpl(BeerRepository beerRepository, BeerMapper beerMapper) {
        this.beerRepository = beerRepository;
        this.beerMapper = beerMapper;
    }

    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
    @Override
    public BeerPagedList listBeers(String beerName, BeerStyle[] beerStyles, Pageable pageable, Boolean showInventoryOnHand) {

        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && beerStyles != null) {
            beerPage = beerRepository.findAllByBeerNameAndBeerStyleIn(beerName, beerStyles, pageable);
        }
        else if (!StringUtils.isEmpty(beerName) && beerStyles == null) {
            beerPage = beerRepository.findAllByBeerName(beerName, pageable);
        }
        else if (StringUtils.isEmpty(beerName) && beerStyles != null) {
            beerPage = beerRepository.findAllByBeerStyleIn(beerStyles, pageable);
        }
        else {
            beerPage = beerRepository.findAll(pageable);
        }

        Pageable beerPageable = beerPage.getPageable();
        if (showInventoryOnHand) {
            beerPagedList = new BeerPagedList(
                    beerPage
                            .getContent()
                            .stream()
                            .map(beerMapper::beerToBeerDtoWithInventory)
                            .collect(Collectors.toList()),
                    PageRequest.of(beerPageable.getPageNumber(), beerPageable.getPageSize()),
                    beerPage.getTotalElements()
            );
        }
        else {
            beerPagedList = new BeerPagedList(
                    beerPage
                            .getContent()
                            .stream()
                            .map(beerMapper::beerToBeerDto)
                            .collect(Collectors.toList()),
                    PageRequest.of(beerPageable.getPageNumber(), beerPageable.getPageSize()),
                    beerPage.getTotalElements()
            );
        }

        return beerPagedList;
    }

    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false")
    @Override
    public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {

        if (showInventoryOnHand) {
            return beerMapper.beerToBeerDtoWithInventory(
                    beerRepository.findByIdOrElseThrowNotFoundException(beerId)
            );
        } else {
            return  beerMapper.beerToBeerDto(
                    beerRepository.findByIdOrElseThrowNotFoundException(beerId)
            );
        }
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {

        Beer beer = beerRepository.findByIdOrElseThrowNotFoundException(beerId);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(BeerStyle.valueOf(beerDto.getBeerStyle()));
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return  beerMapper.beerToBeerDto(beerRepository.save(beer));
    }


    @Cacheable(cacheNames = "beerUpcCache")
    @Override
    public BeerDto getByUpc(String upc) {

        return beerMapper.beerToBeerDto(
                beerRepository.findByUpc(upc).orElseThrow(() -> new  NotFoundException(Beer.class, "upc", upc))
        );
    }

    @Override
    public void deleteBeerById(UUID beerId)  {
        beerRepository.delete(beerRepository.findByIdOrElseThrowNotFoundException(beerId));
    }
}
