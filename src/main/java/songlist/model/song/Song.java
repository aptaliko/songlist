package songlist.model.song;

import lombok.Data;
import songlist.model.features.dance.Dance;
import songlist.model.features.mode.Mode;
import songlist.model.features.rhythm.Rhythm;

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

    @ManyToOne
    @JoinColumn(name="dance_id")
    private Dance dance;

    @ManyToOne
    @JoinColumn(name="mode_id")
    private Mode mode;

    @Column
    private String comments;

}
