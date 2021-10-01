package songlist.model.song;

import songlist.model.rhythm.Rhythm;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Song {

    @Id
    @Column(length = 16, unique = true, nullable = false)
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="rhythm_id", nullable = false)
    private Rhythm rhythm;

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rhythm getRhythm() {
        return rhythm;
    }

    public void setRhythm(Rhythm rhythm) {
        this.rhythm = rhythm;
    }
}
