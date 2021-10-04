package songlist.service.song;

import org.springframework.stereotype.Service;
import songlist.model.features.dance.Dance;
import songlist.model.features.mode.Mode;
import songlist.model.features.rhythm.Rhythm;
import songlist.model.song.Song;
import songlist.model.song.dto.NewSongDTO;
import songlist.service.dance.DanceService;
import songlist.service.mode.ModeService;
import songlist.service.rhythm.RhythmService;

@Service
public class SongBuilderService {

    RhythmService rhythmService;
    DanceService danceService;
    ModeService modeService;

    public SongBuilderService(RhythmService rhythmService, DanceService danceService, ModeService modeService) {
        this.rhythmService = rhythmService;
        this.danceService = danceService;
        this.modeService = modeService;
    }

    public Song build(NewSongDTO newSongDTO) {
        Song song = new Song();
        song.setName(newSongDTO.getName());
        song.setComments(newSongDTO.getComments());

        if (isNotNullOrEmpty(newSongDTO.getRhythmId())) {
            Rhythm rhythm = rhythmService.get(newSongDTO.getRhythmId());
            song.setRhythm(rhythm);
        }

        if (isNotNullOrEmpty(newSongDTO.getDanceId())) {
            Dance dance = danceService.get(newSongDTO.getDanceId());
            song.setDance(dance);
        }

        if (isNotNullOrEmpty(newSongDTO.getModeId())) {
            Mode mode = modeService.get(newSongDTO.getModeId());
            song.setMode(mode);
        }

        return song;
    }

    private static boolean isNotNullOrEmpty(String s) {
        return s != null && !s.isBlank();
    }
}
