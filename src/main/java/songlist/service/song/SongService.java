package songlist.service.song;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import songlist.model.song.Song;
import songlist.model.song.dto.NewSongDTO;
import songlist.model.song.dto.SongDTO;
import songlist.model.song.dto.SongSearchCriteria;
import songlist.predicates.SongWithRhythm;
import songlist.repository.song.SongRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SongService {

    SongRepository songRepository;
    SongBuilderService songBuilderService;

    public SongService(SongRepository songRepository, SongBuilderService songBuilderService) {
        this.songRepository = songRepository;
        this.songBuilderService = songBuilderService;
    }

    public List<SongDTO> getAll() {
        return songRepository.findAll().stream().map(s -> new SongDTO(s.getId().toString(), s.getName(), s.getRhythm().getName(), s.getComments())).collect(Collectors.toList());
    }

    public List<SongDTO> getSongsOfCriteria(SongSearchCriteria criteria) {
        Specification<Song> activeSongs = Specification.where(new SongWithRhythm(criteria.getRhythmId()));

        return songRepository.findAll(activeSongs).stream().map(s -> new SongDTO(s.getId().toString(), s.getName(), s.getRhythm().getName(), s.getComments())).collect(Collectors.toList());
    }

    public SongDTO getDTO(String id) {
        Song song = songRepository.getOne(UUID.fromString(id));

        return new SongDTO(song.getId().toString(), song.getName(), song.getRhythm().getName(), song.getComments());
    }

    public String create(NewSongDTO newSongDTO) {
        Song song = songBuilderService.build(newSongDTO);

        return songRepository.save(song).getId().toString();
    }
}

