package songlist.repository.impl.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import songlist.model.features.rhythm.Rhythm;

import java.util.UUID;
import songlist.repository.abstraction.RhythmRepository;

@Repository
public interface RhythmRepositoryImpl extends JpaRepository<Rhythm, UUID>, RhythmRepository {
}
