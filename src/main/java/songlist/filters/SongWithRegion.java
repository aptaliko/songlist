package songlist.filters;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import songlist.model.song.Song;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.UUID;

public class SongWithRegion implements Specification<Song> {

    private final String regionId;

    public SongWithRegion(String regionId) {
        this.regionId = regionId;
    }

    @Override
    public Predicate toPredicate(@NotNull Root<Song> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder builder) {
        if (this.regionId == null) {
            return builder.isTrue(builder.literal(true));
        }
        else {
            return builder.equal(root.get("region").get("id"), UUID.fromString(this.regionId));
        }
    }
}
