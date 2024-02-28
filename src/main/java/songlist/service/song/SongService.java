package songlist.service.song;

import static songlist.utils.Constants.DOES_NOT_EXIST;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import songlist.exceptions.ValidationException;
import songlist.filters.SongWithDance;
import songlist.filters.SongWithMode;
import songlist.filters.SongWithRegion;
import songlist.filters.SongWithRhythm;
import songlist.mappers.entity.SongMapper;
import songlist.model.song.Song;
import songlist.model.song.dto.NewSongDTO;
import songlist.model.song.dto.SongDTO;
import songlist.model.song.dto.SongSearchCriteria;
import songlist.repository.impl.sql.SongRepositoryImpl;
import songlist.service.features.RegionService;

@Service
public class SongService {

    SongRepositoryImpl songRepository;
    SongBuilderService songBuilderService;
    RegionService regionService;

    public SongService(SongRepositoryImpl songRepository, SongBuilderService songBuilderService, RegionService regionService) {
        this.songRepository = songRepository;
        this.songBuilderService = songBuilderService;
        this.regionService = regionService;
    }

    public List<SongDTO> getAllSongs() {
        return songRepository.findAll().stream().map(SongMapper::toSongDTO).collect(Collectors.toList());
    }

    public List<SongDTO> getSongsOfCriteria(SongSearchCriteria criteria) {
        Specification<Song> activeSongs = Specification
            .where(new SongWithRhythm(criteria.getRhythmId()))
            .and(new SongWithDance(criteria.getDanceId()))
            .and(new SongWithMode(criteria.getModeId()))
            .and(new SongWithRegion(regionService.getAllChildrenRegionIds(criteria.getRegionId())));

        return songRepository.findAll(activeSongs).stream().map(SongMapper::toSongDTO).collect(Collectors.toList());
    }

    public SongDTO getDTO(String id) {
        Song song = songRepository.findById(UUID.fromString(id)).orElseThrow(
            NoSuchElementException::new);

        return SongMapper.toSongDTO(song);
    }

    public void create(NewSongDTO newSongDTO) throws ValidationException {
        songRepository.saveSong(newSongDTO);
    }

    public void delete(String id) {
        songRepository.deleteById(UUID.fromString(id));
    }

    public String update(String id, NewSongDTO newSongDTO) throws ValidationException {
        Optional<Song> song = songRepository.findById(UUID.fromString(id));

        if (song.isEmpty()) {
            throw new ValidationException("Song with id: " + id + DOES_NOT_EXIST);
        }
        Song s = song.get();
        songBuilderService.validateAndBuild(s, newSongDTO);

        return songRepository.save(s).getId().toString();
    }
}

