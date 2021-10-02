package songlist.model.features.mode;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Mode {

    @Id
    @Column(length = 16, unique = true, nullable = false)
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(length = 16, unique = true, nullable = false)
    private String name;
}
