package songlist.service.song;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import songlist.Exceptions.ValidationException;
import songlist.model.song.Song;
import songlist.model.song.dto.NewSongDTO;
import songlist.model.song.dto.SongDTO;
import songlist.model.song.dto.SongSearchCriteria;
import songlist.predicates.SongWithRhythm;
import songlist.repository.song.SongRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SongService {

    SongRepository songRepository;
    SongValidationService songValidationService;

    public SongService(SongRepository songRepository, SongValidationService songValidationService) {
        this.songRepository = songRepository;
        this.songValidationService = songValidationService;
    }

    public List<SongDTO> getAllSongs() {
        return songRepository.findAll().stream().map(s -> new SongDTO(s.getId().toString(), s.getName(), s.getRhythm().getName(), s.getComments())).collect(Collectors.toList());
    }

    public List<SongDTO> getSongsOfCriteria(SongSearchCriteria criteria) {
        Specification<Song> activeSongs = Specification.where(new SongWithRhythm(criteria.getRhythmId()));

        return songRepository.findAll(activeSongs).stream().map(s -> new SongDTO(s.getId().toString(), s.getName(), s.getRhythm().getName(), s.getComments())).collect(Collectors.toList());
    }

    public SongDTO getSong(UUID id) {
        Song song = songRepository.getOne(id);

        return new SongDTO(song.getId().toString(), song.getName(), song.getRhythm().getName(), song.getComments());
    }

    public String createSong(NewSongDTO newSongDTO) throws ValidationException {
        Song song = songValidationService.validateSong(newSongDTO);

        return songRepository.save(song).getId().toString();
    }
}

