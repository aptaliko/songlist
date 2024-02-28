package songlist.modelnosql.features.dance;

import java.util.UUID;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "dance")
@Getter
@Setter
public class Dance {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Field
    private String name;

}
