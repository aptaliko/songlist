package songlist.modelnosql.features.rhythm;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Getter
@Setter
public class Rhythm {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Field
    private String name;

    @Field
    private String meter;

}
