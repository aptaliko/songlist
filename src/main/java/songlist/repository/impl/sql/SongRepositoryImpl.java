package songlist.repository.impl.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import songlist.model.song.Song;

import java.util.UUID;
import songlist.model.song.dto.NewSongDTO;
import songlist.repository.abstraction.SongRepository;

@Repository
public interface SongRepositoryImpl extends JpaRepository<Song, UUID>, JpaSpecificationExecutor<Song>,
    SongRepository {

  @Override
  default void saveSong(NewSongDTO newSongDTO) {

  }

}
