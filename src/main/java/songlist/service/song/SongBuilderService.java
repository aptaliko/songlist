package songlist.service.song;

import org.springframework.stereotype.Service;
import songlist.exceptions.ValidationException;
import songlist.model.features.dance.Dance;
import songlist.model.features.mode.Mode;
import songlist.model.features.rhythm.Rhythm;
import songlist.model.song.Song;
import songlist.model.song.dto.NewSongDTO;
import songlist.service.dance.DanceService;
import songlist.service.mode.ModeService;
import songlist.service.rhythm.RhythmService;

import java.util.Optional;

@Service
public class SongBuilderService {

    RhythmService rhythmService;
    DanceService danceService;
    ModeService modeService;

    private static final String DOES_NOT_EXIST = " does not exist.";

    public SongBuilderService(RhythmService rhythmService, DanceService danceService, ModeService modeService) {
        this.rhythmService = rhythmService;
        this.danceService = danceService;
        this.modeService = modeService;
    }

    public Song validateAndBuild(NewSongDTO newSongDTO) throws ValidationException {
        Song song = new Song();
        song.setName(newSongDTO.getName());
        song.setComments(newSongDTO.getComments());

        if (isNotNullOrEmpty(newSongDTO.getRhythmId())) {
            Optional<Rhythm> rhythm = rhythmService.get(newSongDTO.getRhythmId());
            if (rhythm.isEmpty()) {
                throw new ValidationException("Rhythm with id" + newSongDTO.getRhythmId() + DOES_NOT_EXIST);
            }
            song.setRhythm(rhythm.get());
        }

        if (isNotNullOrEmpty(newSongDTO.getDanceId())) {
            Optional<Dance> dance = danceService.get(newSongDTO.getDanceId());
            if (dance.isEmpty()) {
                throw new ValidationException("Dance with id" + newSongDTO.getDanceId() + DOES_NOT_EXIST);
            }
            song.setDance(dance.get());
        }

        if (isNotNullOrEmpty(newSongDTO.getModeId())) {
            Optional<Mode> mode = modeService.get(newSongDTO.getModeId());
            if (mode.isEmpty()) {
                throw new ValidationException("MOde with id" + newSongDTO.getModeId() + DOES_NOT_EXIST);
            }
            song.setMode(mode.get());
        }

        return song;
    }

    private static boolean isNotNullOrEmpty(String s) {
        return s != null && !s.isBlank();
    }
}
