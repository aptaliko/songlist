package songlist.filters;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import songlist.model.song.Song;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.UUID;

public class SongWithRhythm implements Specification<Song> {

    private final String rhythmId;

    public SongWithRhythm(String rhythmId) {
        this.rhythmId = rhythmId;
    }

    @Override
    public Predicate toPredicate(@NotNull Root<Song> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder builder) {
        if (this.rhythmId == null) {
            return builder.isTrue(builder.literal(true));
        }
        else {
            return builder.equal(root.get("rhythm").get("id"), UUID.fromString(this.rhythmId));
        }
    }
}
