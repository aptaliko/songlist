package songlist.rest.rhythm;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import songlist.model.rhythm.dto.NewRhythmDTO;
import songlist.model.rhythm.dto.RhythmDTO;
import songlist.service.rhythm.RhythmService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rhythms")
public class RhythmController {

    RhythmService rhythmService;

    public RhythmController(RhythmService rhythmService) {
        this.rhythmService = rhythmService;
    }

    @GetMapping(value = "/")
    public List<RhythmDTO> getAllRhythms() {
        return rhythmService.getAllRhythms();
    }

    @GetMapping(value = "/{id}")
    public RhythmDTO getRhythm(@PathVariable UUID id) {
        return rhythmService.getRhythm(id);
    }

    @PostMapping()
    public ResponseEntity<UUID> createRhythm(@Validated @RequestBody NewRhythmDTO newRhythmDTO) {
        return ResponseEntity.of(rhythmService.createRhythm(newRhythmDTO));
    }

}
