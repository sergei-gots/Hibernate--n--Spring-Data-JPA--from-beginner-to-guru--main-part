package guru.springframework.sdjpainheritance.repository;

import guru.springframework.sdjpainheritance.domain.joinedtable.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sergei on 02/04/2025
 */
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
}
