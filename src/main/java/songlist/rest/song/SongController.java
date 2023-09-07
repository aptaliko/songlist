package songlist.rest.song;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import songlist.exceptions.ValidationException;
import songlist.model.song.dto.NewSongDTO;
import songlist.model.song.dto.SongDTO;
import songlist.model.song.dto.SongSearchCriteria;
import songlist.service.metrics.CustomMetricService;
import songlist.service.song.SongService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/songs")
@Validated
@RequiredArgsConstructor
public class SongController {

    final SongService songService;

    final CustomMetricService customMetricService;

    @GetMapping(value = "/")
    public List<SongDTO> getAllSongs() {
        return songService.getAll();
    }

    @PostMapping(value = "/search")
    public List<SongDTO> getSongsBasedOnCriteria(@RequestBody SongSearchCriteria criteria) {
        customMetricService.incrementEndpointCallCounter();
        return songService.getSongsOfCriteria(criteria);
    }

    @GetMapping(value = "/{id}")
    public SongDTO getSong(@PathVariable String id) {
        return songService.getDTO(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value= HttpStatus.ACCEPTED)
    public void deleteSong(@PathVariable String id) {
        songService.delete(id);
    }

    @PostMapping(value = "/")
    public ResponseEntity<String> createSong(@Valid @NotNull @RequestBody NewSongDTO newSongDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(songService.create(newSongDTO));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateSong(@PathVariable String id, @Valid @NotNull @RequestBody NewSongDTO newSongDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(songService.update(id, newSongDTO));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}


