package songlist.model.rhythm;

import lombok.Data;
import songlist.model.song.Song;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Rhythm {

    @Id
    @Column(length = 16, unique = true, nullable = false)
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(length = 16, unique = true, nullable = false)
    private String name;

    @Column
    private String meter;

    @OneToMany(mappedBy = "rhythm")
    private Set<Song> songs;

}
