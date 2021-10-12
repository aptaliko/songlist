package songlist.filters;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import songlist.model.song.Song;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Set;
import java.util.UUID;

public class SongWithRegion implements Specification<Song> {

    private final Set<UUID> regionIds;

    public SongWithRegion(Set<UUID> regionIds) {
        this.regionIds = regionIds;
    }

    @Override
    public Predicate toPredicate(@NotNull Root<Song> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder builder) {
        if (this.regionIds.isEmpty()) {
            return builder.isTrue(builder.literal(true));
        }
        else {
            return builder.and(root.get("region").get("id").in(regionIds));
        }
    }
}
