package songlist.model.features.mode;

import songlist.model.song.Song;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Mode {

    @Id
    @Column(length = 16, unique = true, nullable = false)
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(length = 16, unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "mode")
    private Set<Song> songs;

    @PreRemove
    private void preRemove() {
        songs.forEach( child -> child.setMode(null));
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

    public Set<Song> getSongs() {
        return songs;
    }
}
