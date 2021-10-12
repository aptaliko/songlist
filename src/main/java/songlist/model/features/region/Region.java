package songlist.model.features.region;

import songlist.model.song.Song;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Stream;

@Entity
public class Region {

    @Id
    @Column(length = 16, unique = true, nullable = false)
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(length = 16, unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="parent_id")
    private Region parentRegion;

    @OneToMany(mappedBy = "parentRegion")
    private Set<Region> childRegions;

    @OneToMany(mappedBy = "region")
    private Set<Song> songs;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.getName();
    }

    public String getFullRegionName() {
        if (this.getParentRegion() == null) {
            return this.getName();
        }
        return this.getParentRegion().getFullRegionName() + ":" + this.getName();
    }

    public Stream<Region> flattened() {
        return Stream.concat(
                Stream.of(this),
                this.getChildRegions().stream().flatMap(Region::flattened));
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getParentRegion() {
        return parentRegion;
    }

    public void setParentRegion(Region parentRegion) {
        this.parentRegion = parentRegion;
    }

    public Set<Region> getChildRegions() {
        return childRegions;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}
