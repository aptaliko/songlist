package songlist.rest.rhythm;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import songlist.model.features.rhythm.dto.NewRhythmDTO;
import songlist.model.features.rhythm.dto.RhythmDTO;
import songlist.service.rhythm.RhythmService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

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

    @DeleteMapping(value = "/{id}")
    public void deleteSong(@PathVariable String id) {
        rhythmService.delete(id);
    }

    @PostMapping()
    public ResponseEntity<UUID> createRhythm(@Valid @NotNull @RequestBody NewRhythmDTO newRhythmDTO) {
        return ResponseEntity.of(rhythmService.create(newRhythmDTO));
    }

}
