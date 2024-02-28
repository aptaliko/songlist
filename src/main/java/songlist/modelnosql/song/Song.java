package songlist.modelnosql.song;

import java.util.UUID;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import songlist.modelnosql.features.dance.Dance;
import songlist.modelnosql.features.mode.Mode;
import songlist.modelnosql.features.region.Region;
import songlist.modelnosql.features.rhythm.Rhythm;

@Document(collection = "song")
@Getter
@Setter
public class Song {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Field
    private String name;

    @DBRef
    private Rhythm rhythm;

    @DBRef
    private Dance dance;

    @DBRef
    private Mode mode;

    @DBRef
    private Region region;

    @Field
    private String comments;

}
