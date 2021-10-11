package songlist.model.song;

import songlist.model.features.dance.Dance;
import songlist.model.features.mode.Mode;
import songlist.model.features.region.Region;
import songlist.model.features.rhythm.Rhythm;

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
    @JoinColumn(name="rhythm_id")
    private Rhythm rhythm;

    @ManyToOne
    @JoinColumn(name="dance_id")
    private Dance dance;

    @ManyToOne
    @JoinColumn(name="mode_id")
    private Mode mode;

    @ManyToOne
    @JoinColumn(name="region_id")
    private Region region;

    @Column
    private String comments;

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

    public Rhythm getRhythm() {
        return rhythm;
    }

    public void setRhythm(Rhythm rhythm) {
        this.rhythm = rhythm;
    }

    public Dance getDance() {
        return dance;
    }

    public void setDance(Dance dance) {
        this.dance = dance;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
