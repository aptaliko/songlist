package songlist.repository.impl.nosql;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import songlist.modelnosql.features.rhythm.Rhythm;
import songlist.repository.abstraction.RhythmRepository;

public interface RhythmRepositoryImplNoSQL extends MongoRepository<Rhythm, UUID>, RhythmRepository {
}
