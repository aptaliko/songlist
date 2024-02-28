package songlist.repository.impl.nosql;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import songlist.modelnosql.features.mode.Mode;
import songlist.repository.abstraction.ModeRepository;

public interface ModeRepositoryImplNoSQL extends MongoRepository<Mode, UUID>, ModeRepository {
}
