package songlist.repository.impl.nosql;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import songlist.modelnosql.features.region.Region;
import songlist.repository.abstraction.RegionRepository;

public interface RegionRepositoryImplNoSQL extends MongoRepository<Region, UUID>, RegionRepository {

}
