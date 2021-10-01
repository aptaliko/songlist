package songlist.rest.song;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import songlist.model.song.dto.NewSongDTO;
import songlist.model.song.dto.SongDTO;
import songlist.model.song.dto.SongSearchCriteria;
import songlist.service.song.SongService;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/songs")
public class SongController {

    SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping(value = "/")
    public List<SongDTO> getAllSongs() {
        return songService.getAllSongs();
    }

    @PostMapping(value = "/search")
    public List<SongDTO> getSongsBasedOnCriteria(@RequestBody SongSearchCriteria criteria) {
        return songService.getSongsOfCriteria(criteria);
    }

    @GetMapping(value = "/{id}")
    public SongDTO getSong(@PathVariable UUID id) {
        return songService.getSong(id);
    }

    @PostMapping(value = "/")
    public ResponseEntity createSong(@RequestBody NewSongDTO newSongDTO) {
        try {
            return ResponseEntity.of(songService.createSong(newSongDTO));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}


