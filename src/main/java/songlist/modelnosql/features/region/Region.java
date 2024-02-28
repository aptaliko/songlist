package songlist.modelnosql.features.region;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import songlist.modelnosql.song.Song;

@Document(collection = "region")
public class Region {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Field
    private String name;

    @DBRef
    private Region parentRegion;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getFullRegionName() {
        if (this.getParentRegion() == null) {
            return this.getName();
        }
        return this.getParentRegion().getFullRegionName() + ":" + this.getName();
    }

//    public Stream<Region> flattened() {
//        return Stream.concat(
//                Stream.of(this),
//                this.getChildRegions().stream().flatMap(Region::flattened));
//    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getParentRegion() {
        return parentRegion;
    }

    public void setParentRegion(Region parentRegion) {
        this.parentRegion = parentRegion;
    }

}
