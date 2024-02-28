package songlist.repository.impl.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import songlist.model.features.region.Region;

import java.util.UUID;
import songlist.repository.abstraction.RegionRepository;

@Repository
public interface RegionRepositoryImpl extends JpaRepository<Region, UUID>, RegionRepository {

}
