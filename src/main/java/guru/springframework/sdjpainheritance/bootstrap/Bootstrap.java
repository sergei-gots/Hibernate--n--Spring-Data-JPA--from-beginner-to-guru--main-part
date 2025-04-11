package guru.springframework.sdjpainheritance.bootstrap;

import guru.springframework.sdjpainheritance.domain.joinedtable.ElectricGuitar;
import guru.springframework.sdjpainheritance.domain.joinedtable.Instrument;
import guru.springframework.sdjpainheritance.repository.ElectricGuitarRepository;
import guru.springframework.sdjpainheritance.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sergei on 02/04/2025
 */
@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private ElectricGuitarRepository electricGuitarRepository;
    
    @Autowired
    private InstrumentRepository instrumentRepository;

    @Override
    public void run(String... args) throws Exception {

        ElectricGuitar electricGuitar = new ElectricGuitar();
        electricGuitar.setNumberOfStrings(6);
        electricGuitar.setNumberOfPickups(2);

        ElectricGuitar savedElectricGuitar = electricGuitarRepository.save(electricGuitar);

        System.out.println(savedElectricGuitar);
        
        List<Instrument> instruments = instrumentRepository.findAll();
        
        for (Instrument instrument: instruments) {
            System.out.println("instrument = " + instrument);
        }
        
        
    }
}
