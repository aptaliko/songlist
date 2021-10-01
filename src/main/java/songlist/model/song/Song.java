package songlist.model.song;

import lombok.Data;
import songlist.model.rhythm.Rhythm;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Song {

    @Id
    @Column(length = 16, unique = true, nullable = false)
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="rhythm_id")
    private Rhythm rhythm;

    @Column
    private String comments;

}
