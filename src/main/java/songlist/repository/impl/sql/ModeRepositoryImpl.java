package songlist.repository.impl.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import songlist.model.features.mode.Mode;

import java.util.UUID;
import songlist.repository.abstraction.ModeRepository;

@Repository
public interface ModeRepositoryImpl extends JpaRepository<Mode, UUID>, ModeRepository {
}
