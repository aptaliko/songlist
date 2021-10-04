package songlist.filters;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import songlist.model.song.Song;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.UUID;

public class SongWithDance implements Specification<Song> {

    private final String danceId;

    public SongWithDance(String danceId) {
        this.danceId = danceId;
    }

    @Override
    public Predicate toPredicate(@NotNull Root<Song> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder builder) {
        if (this.danceId == null) {
            return builder.isTrue(builder.literal(true));
        }
        else {
            return builder.equal(root.get("dance").get("id"), UUID.fromString(this.danceId));
        }
    }
}
