package songlist.rest.song;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import songlist.model.song.dto.NewSongDTO;
import songlist.model.song.dto.SongDTO;
import songlist.model.song.dto.SongSearchCriteria;
import songlist.service.song.SongService;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping(value = "/")
    public List<SongDTO> getAllSongs() {
        return songService.getAll();
    }

    @PostMapping(value = "/search")
    public List<SongDTO> getSongsBasedOnCriteria(@RequestBody SongSearchCriteria criteria) {
        return songService.getSongsOfCriteria(criteria);
    }

    @GetMapping(value = "/{id}")
    public SongDTO getSong(@PathVariable String id) {
        return songService.getDTO(id);
    }

    @PostMapping(value = "/")
    public ResponseEntity<String> createSong(@RequestBody NewSongDTO newSongDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(songService.create(newSongDTO));
    }
}


