package songlist.repository.impl.nosql;

import java.util.List;
import java.util.UUID;
import org.mapstruct.factory.Mappers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import songlist.mappers.document.DanceMapper;
import songlist.model.features.dance.dto.DanceDTO;
import songlist.model.features.dance.dto.DanceInput;
import songlist.modelnosql.features.dance.Dance;
import songlist.repository.abstraction.DanceRepository;

@Repository
public interface DanceRepositoryImplNoSQL extends MongoRepository<Dance, UUID>, DanceRepository {

  DanceMapper mapper = Mappers.getMapper(DanceMapper.class);

  @Override
  default List<DanceDTO> findAllDances() {
    return mapper.documentsToDtos(findAll());
  }

  @Override
  default DanceDTO findDanceById(String id) {
    return mapper.documentToDto(findById(UUID.fromString(id)).orElseThrow());
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
