package songlist.model.features.dance;

import songlist.model.song.Song;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

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

    @PreRemove
    private void preRemove() {
        songs.forEach( child -> child.setDance(null));
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
