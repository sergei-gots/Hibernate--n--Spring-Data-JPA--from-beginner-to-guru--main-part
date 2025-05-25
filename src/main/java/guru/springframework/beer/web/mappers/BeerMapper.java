package guru.springframework.beer.web.mappers;

import guru.springframework.beer.domain.Beer;
import guru.springframework.beer.web.model.BeerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by sergei on 24/05/2025
 */
@Mapper(uses = DateMapper.class)
//@DecoratedWith(LombokMapperConfig.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    BeerDto beerToBeerDtoWithInventory(Beer beer);

    @Mapping(target = "createdDate", ignore = true )
    @Mapping(target = "lastModifiedDate", ignore = true )
    Beer beerDtoToBeer(BeerDto beerDto);

}
