package songlist.repository.features;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import songlist.model.features.mode.Mode;

import java.util.UUID;

@Repository
public interface ModeRepository extends JpaRepository<Mode, UUID> {
}
