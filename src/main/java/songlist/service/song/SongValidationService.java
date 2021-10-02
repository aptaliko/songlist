package songlist.service.song;

import org.springframework.stereotype.Service;
import songlist.Exceptions.ValidationException;
import songlist.model.features.dance.Dance;
import songlist.model.features.mode.Mode;
import songlist.model.features.rhythm.Rhythm;
import songlist.model.song.Song;
import songlist.model.song.dto.NewSongDTO;
import songlist.repository.dance.DanceRepository;
import songlist.repository.mode.ModeRepository;
import songlist.repository.rhythm.RhythmRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class SongValidationService {

    private static final String DOES_NOT_EXIST = " does not exist.";

    RhythmRepository rhythmRepository;
    DanceRepository danceRepository;
    ModeRepository modeRepository;

    public SongValidationService(RhythmRepository rhythmRepository, DanceRepository danceRepository, ModeRepository modeRepository) {
        this.rhythmRepository = rhythmRepository;
        this.danceRepository = danceRepository;
        this.modeRepository = modeRepository;
    }

    public Song validateSong (NewSongDTO newSongDTO) throws ValidationException {
        Song song = new Song();
        song.setName(newSongDTO.getName());
        song.setComments(newSongDTO.getComments());

        if (isNotNullOrEmpty(newSongDTO.getRhythmId())) {
            Optional<Rhythm> rhythm = rhythmRepository.findById(UUID.fromString(newSongDTO.getRhythmId()));
            if (rhythm.isEmpty()) {
                throw new ValidationException("Rhythm with id " + newSongDTO.getRhythmId() + DOES_NOT_EXIST);
            }
            song.setRhythm(rhythm.get());
        }

        if (isNotNullOrEmpty(newSongDTO.getDanceId())) {
            Optional<Dance> dance = danceRepository.findById(UUID.fromString(newSongDTO.getDanceId()));
            if (dance.isEmpty()) {
                throw new ValidationException("Dance with id " + newSongDTO.getDanceId() + DOES_NOT_EXIST);
            }
            song.setDance(dance.get());
        }

        if (isNotNullOrEmpty(newSongDTO.getModeId())) {
            Optional<Mode> mode = modeRepository.findById(UUID.fromString(newSongDTO.getModeId()));
            if (mode.isEmpty()) {
                throw new ValidationException("Mode with id " + newSongDTO.getModeId() + DOES_NOT_EXIST);
            }
            song.setMode(mode.get());
        }

        return song;
    }

    private static boolean isNotNullOrEmpty(String s) {
        return s != null && !s.isBlank();
    }
}
