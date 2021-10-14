package songlist.service.song;

import org.springframework.stereotype.Service;
import songlist.exceptions.ValidationException;
import songlist.model.song.Song;
import songlist.model.song.dto.NewSongDTO;
import songlist.service.features.DanceService;
import songlist.service.features.ModeService;
import songlist.service.features.RegionService;
import songlist.service.features.RhythmService;

import static songlist.utils.Utils.isNotNullOrEmpty;

@Service
public class SongBuilderService {

    RhythmService rhythmService;
    DanceService danceService;
    ModeService modeService;
    RegionService regionService;

    public SongBuilderService(RhythmService rhythmService, DanceService danceService, ModeService modeService, RegionService regionService) {
        this.rhythmService = rhythmService;
        this.danceService = danceService;
        this.modeService = modeService;
        this.regionService = regionService;
    }

    public void validateAndBuild(Song song, NewSongDTO newSongDTO) throws ValidationException {
        song.setName(newSongDTO.getName());
        song.setComments(newSongDTO.getComments());

        if (isNotNullOrEmpty(newSongDTO.getRhythmId())) {
            song.setRhythm(rhythmService.get(newSongDTO.getRhythmId()));
        }

        if (isNotNullOrEmpty(newSongDTO.getDanceId())) {
            song.setDance(danceService.get(newSongDTO.getDanceId()));
        }

        if (isNotNullOrEmpty(newSongDTO.getModeId())) {
            song.setMode(modeService.get(newSongDTO.getModeId()));
        }

        if (isNotNullOrEmpty(newSongDTO.getRegionId())) {
            song.setRegion(regionService.get(newSongDTO.getRegionId()));
        }
    }
}
