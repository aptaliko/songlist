package songlist.repository.song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import songlist.model.song.Song;

import java.util.UUID;

@Repository
public interface SongRepository extends JpaRepository<Song, UUID>, JpaSpecificationExecutor<Song> {

}
