package songlist.repository.impl.nosql;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import songlist.model.song.dto.NewSongDTO;
import songlist.modelnosql.song.Song;
import songlist.repository.abstraction.SongRepository;

@Repository
public interface SongRepositoryImplNoSQL
    extends MongoRepository<Song, UUID>,
    SongRepository {

  @Override
  default void saveSong(NewSongDTO newSongDTO) {

  }
}
