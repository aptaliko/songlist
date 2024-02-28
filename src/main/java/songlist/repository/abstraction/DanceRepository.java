package songlist.repository.abstraction;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import songlist.model.features.dance.dto.DanceDTO;
import songlist.model.features.dance.dto.DanceInput;

@Repository
public interface DanceRepository {
  List<DanceDTO> findAllDances();

  DanceDTO findDanceById(String id);

  void updateDance(String id, DanceInput danceInput);

  void createDance(DanceInput danceInput);

  void deleteDanceById(UUID uuid);
}
