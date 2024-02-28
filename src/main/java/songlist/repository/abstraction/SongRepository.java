package songlist.repository.abstraction;

import org.springframework.stereotype.Repository;
import songlist.model.song.Song;
import songlist.model.song.dto.NewSongDTO;

@Repository
public interface SongRepository {

  void saveSong(NewSongDTO newSongDTO);
}
