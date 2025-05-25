package guru.springframework.beer.web.mappers;

import org.mapstruct.Mapper;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Created by sergei on 25/05/2025
 */
@Mapper
public class DateMapper {

    public OffsetDateTime timestampToOffsetDateTime(Timestamp timestamp) {

        if (timestamp == null) {
            return null;
        }

        return OffsetDateTime.of(timestamp.toLocalDateTime(), ZoneOffset.UTC);
    }

}
