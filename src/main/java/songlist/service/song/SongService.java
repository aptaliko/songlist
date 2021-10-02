package songlist.service.song;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import songlist.model.features.dance.Dance;
import songlist.model.features.mode.Mode;
import songlist.model.features.rhythm.Rhythm;
import songlist.model.song.Song;
import songlist.model.song.dto.NewSongDTO;
import songlist.model.song.dto.SongDTO;
import songlist.model.song.dto.SongSearchCriteria;
import songlist.predicates.SongWithRhythm;
import songlist.repository.dance.DanceRepository;
import songlist.repository.mode.ModeRepository;
import songlist.repository.rhythm.RhythmRepository;
import songlist.repository.song.SongRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SongService {

    SongRepository songRepository;
    RhythmRepository rhythmRepository;
    DanceRepository danceRepository;
    ModeRepository modeRepository;

    public SongService(SongRepository songRepository, RhythmRepository rhythmRepository, DanceRepository danceRepository, ModeRepository modeRepository) {
        this.songRepository = songRepository;
        this.rhythmRepository = rhythmRepository;
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

    public Optional<UUID> createSong(NewSongDTO newSongDTO) throws Exception {
        if (newSongDTO == null) {
            return Optional.empty();
        }

        Song song = new Song();
        song.setName(newSongDTO.getName());
        song.setComments(newSongDTO.getComments());

        Optional<Rhythm> rhythm = rhythmRepository.findById(UUID.fromString(newSongDTO.getRhythmId()));
        if (rhythm.isEmpty()) {
            throw new Exception("Rhythm with id " + newSongDTO.getRhythmId() + " does not exist.");
        }
        song.setRhythm(rhythm.get());

        Optional<Dance> dance = danceRepository.findById(UUID.fromString(newSongDTO.getDanceId()));
        if (dance.isEmpty()) {
            throw new Exception("Dance with id " + newSongDTO.getDanceId() + " does not exist.");
        }
        song.setDance(dance.get());

        Optional<Mode> mode = modeRepository.findById(UUID.fromString(newSongDTO.getModeId()));
        if (mode.isEmpty()) {
            throw new Exception("Mode with id " + newSongDTO.getModeId() + " does not exist.");
        }
        song.setMode(mode.get());

        return Optional.of(songRepository.save(song).getId());
    }
}

