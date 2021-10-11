package songlist.rest.features;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import songlist.exceptions.ValidationException;
import songlist.model.features.rhythm.dto.NewRhythmDTO;
import songlist.model.features.rhythm.dto.RhythmDTO;
import songlist.service.features.RhythmService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/rhythms")
@Validated
public class RhythmController {

    RhythmService rhythmService;

    public RhythmController(RhythmService rhythmService) {
        this.rhythmService = rhythmService;
    }

    @GetMapping(value = "/")
    public List<RhythmDTO> getAllRhythms() {
        return rhythmService.getAll();
    }

    @GetMapping(value = "/{id}")
    public RhythmDTO getRhythm(@PathVariable String id) {
        return rhythmService.getDTO(id);
    }

    @PostMapping()
    public ResponseEntity<String> createRhythm(@Valid @NotNull @RequestBody NewRhythmDTO newRhythmDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rhythmService.create(newRhythmDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateRhythm(@PathVariable String id, @Valid @NotNull @RequestBody NewRhythmDTO newRhythmDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(rhythmService.update(id, newRhythmDTO));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteSong(@PathVariable String id) {
        rhythmService.delete(id);
    }

}
