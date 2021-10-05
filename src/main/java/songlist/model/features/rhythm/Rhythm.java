package songlist.model.features.rhythm;

import songlist.model.song.Song;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

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

    @PreRemove
    private void preRemove() {
        songs.forEach( child -> child.setRhythm(null));
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

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }
}
