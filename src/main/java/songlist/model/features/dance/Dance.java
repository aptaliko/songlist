package songlist.model.features.dance;

import lombok.Data;
import songlist.model.song.Song;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Dance {

    @Id
    @Column(length = 16, unique = true, nullable = false)
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(length = 16, unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "dance")
    private Set<Song> songs;
}
