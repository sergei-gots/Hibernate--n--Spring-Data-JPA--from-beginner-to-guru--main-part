package guru.springframework.beer.bootstrap;

import guru.springframework.beer.domain.Beer;
import guru.springframework.beer.enums.BeerStyle;
import guru.springframework.beer.repositories.BeerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

/**
 * Created by sergei on 11/05/2025
 */
@Component
@Slf4j
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "9122089364369";
    public static final String BEER_3_UPC = "0083783375213";
    public static final String BEER_4_UPC = "4666337557578";
    public static final String BEER_5_UPC = "8380495518610";
    public static final String BEER_6_UPC = "5677465691934";
    public static final String BEER_7_UPC = "5463533082885";
    public static final String BEER_8_UPC = "5339741428398";
    public static final String BEER_9_UPC = "1726923962766";
    public static final String BEER_10_UPC = "8484957731774";
    public static final String BEER_11_UPC = "6266328524787";
    public static final String BEER_12_UPC = "7490217802727";
    public static final String BEER_13_UPC = "8579613295827";
    public static final String BEER_14_UPC = "2318301340601";
    public static final String BEER_15_UPC = "9401790633828";
    public static final String BEER_16_UPC = "4813896316225";
    public static final String BEER_17_UPC = "3431272499891";
    public static final String BEER_18_UPC = "2380867498485";
    public static final String BEER_19_UPC = "4323950503848";
    public static final String BEER_20_UPC = "4006016803570";
    public static final String BEER_21_UPC = "9883012356263";
    public static final String BEER_22_UPC = "0583668718888";
    public static final String BEER_23_UPC = "9006801347604";
    public static final String BEER_24_UPC = "0610275742736";
    public static final String BEER_25_UPC = "6504219363283";
    public static final String BEER_26_UPC = "7245173761003";
    public static final String BEER_27_UPC = "0326984155094";
    public static final String BEER_28_UPC = "1350188843012";
    public static final String BEER_29_UPC = "0986442492927";
    public static final String BEER_30_UPC = "8670687641074";

    @Autowired
    private BeerRepository beerRepository;

    private Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        loadBeerObject();
    }

    private synchronized void loadBeerObject() {

        log.debug("Loading initial data. Count is:{}", beerRepository.count());

        if (beerRepository.count() > 0) {
            log.debug("Beer Records are already presented in the database");
            return;
        }

        beerRepository.save(buildBeer("Mango Bobs", BeerStyle.ALE, BEER_1_UPC));
        beerRepository.save(buildBeer("Galaxy Cat", BeerStyle.PALE_ALE, BEER_2_UPC));
        beerRepository.save(buildBeer("No Hammers On The Bar", BeerStyle.WHEAT, BEER_3_UPC));
        beerRepository.save(buildBeer("Blessed", BeerStyle.STOUT, BEER_4_UPC));
        beerRepository.save(buildBeer("Adjunct Trail", BeerStyle.STOUT, BEER_5_UPC));
        beerRepository.save(buildBeer("Very GGGreenn", BeerStyle.IPA, BEER_6_UPC));
        beerRepository.save(buildBeer("Double Barrel Hunahpu's", BeerStyle.STOUT, BEER_7_UPC));
        beerRepository.save(buildBeer("Very Hazy", BeerStyle.IPA, BEER_8_UPC));
        beerRepository.save(buildBeer("SR-71", BeerStyle.STOUT, BEER_9_UPC));
        beerRepository.save(buildBeer("Pliny the Younger", BeerStyle.IPA, BEER_10_UPC));
        beerRepository.save(buildBeer("Blessed", BeerStyle.STOUT, BEER_11_UPC));
        beerRepository.save(buildBeer("King Krush", BeerStyle.IPA, BEER_12_UPC));
        beerRepository.save(buildBeer("PBS Porter", BeerStyle.PORTER, BEER_13_UPC));
        beerRepository.save(buildBeer("Pinball Porter", BeerStyle.STOUT, BEER_14_UPC));
        beerRepository.save(buildBeer("Golden Buddha", BeerStyle.STOUT, BEER_15_UPC));
        beerRepository.save(buildBeer("Grand Central Red", BeerStyle.LAGER, BEER_16_UPC));
        beerRepository.save(buildBeer("Pac-man", BeerStyle.STOUT, BEER_17_UPC));
        beerRepository.save(buildBeer("Ro Sham Bo", BeerStyle.IPA, BEER_18_UPC));
        beerRepository.save(buildBeer("Summer Wheatly", BeerStyle.WHEAT, BEER_19_UPC));
        beerRepository.save(buildBeer("Java Jill", BeerStyle.LAGER, BEER_20_UPC));
        beerRepository.save(buildBeer("Bike Trail Pale", BeerStyle.PALE_ALE, BEER_21_UPC));
        beerRepository.save(buildBeer("N.Z.P.", BeerStyle.IPA, BEER_22_UPC));
        beerRepository.save(buildBeer("Strawberry Blond", BeerStyle.WHEAT, BEER_23_UPC));
        beerRepository.save(buildBeer("Loco", BeerStyle.PORTER, BEER_24_UPC));
        beerRepository.save(buildBeer("Spocktoberfest", BeerStyle.STOUT, BEER_25_UPC));
        beerRepository.save(buildBeer("Beach Blond Ale", BeerStyle.ALE, BEER_26_UPC));
        beerRepository.save(buildBeer("Bimini Twist IPA", BeerStyle.IPA, BEER_27_UPC));
        beerRepository.save(buildBeer("Rod Bender Red Ale", BeerStyle.ALE, BEER_28_UPC));
        beerRepository.save(buildBeer("Floating Dock", BeerStyle.SAISON, BEER_29_UPC));
        beerRepository.save(buildBeer("El Hefe", BeerStyle.WHEAT, BEER_30_UPC));

        log.debug("Beer Records loaded: {}", beerRepository.count());
    }

    private Beer buildBeer(String beerName, BeerStyle beerStyle, String upc) {

        return Beer.builder()
                .beerName(beerName)
                .beerStyle(beerStyle)
                .upc(upc)
                .price(new BigDecimal(BigInteger.valueOf(random.nextInt(10_000)), 2))
                .quantityOnHand(random.nextInt(5_000))
                .build();
    }
}
