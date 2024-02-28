package songlist.repository.impl.sql;

import java.util.List;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import songlist.mappers.entity.DanceMapper;
import songlist.model.features.dance.Dance;

import java.util.UUID;
import songlist.model.features.dance.dto.DanceDTO;
import songlist.model.features.dance.dto.DanceInput;
import songlist.repository.abstraction.DanceRepository;

@Repository
public interface DanceRepositoryImpl extends JpaRepository<Dance, UUID>, DanceRepository {

  DanceMapper mapper = Mappers.getMapper(DanceMapper.class);

  @Override
  default List<DanceDTO> findAllDances() {
    return mapper.entitiesToDtos(findAll());
  }

  @Override
  default DanceDTO findDanceById(String id) {
    return mapper.entityToDto(findById(UUID.fromString(id)).orElseThrow());
  }

  @Override
  default void createDance(DanceInput danceInput) {
    Dance dance = new Dance();
    dance.setName(danceInput.getName());
    save(dance);
  }

  @Override
  default void updateDance(String id, DanceInput danceInput) {
    Dance dance = findById(UUID.fromString(id)).orElseThrow();
    dance.setName(danceInput.getName());
    save(dance);
  }

  @Override
  default void deleteDanceById(UUID uuid) {
    deleteById(uuid);
  }
}
