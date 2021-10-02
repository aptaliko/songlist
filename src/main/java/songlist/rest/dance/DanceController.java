package songlist.rest.dance;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import songlist.model.features.dance.dto.DanceDTO;
import songlist.model.features.dance.dto.NewDanceDTO;
import songlist.service.dance.DanceService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dances")
public class DanceController {

    DanceService danceService;

    public DanceController(DanceService danceService) {
        this.danceService = danceService;
    }

    @GetMapping(value = "/")
    public List<DanceDTO> getAllRhythms() {
        return danceService.getAll();
    }

    @GetMapping(value = "/{id}")
    public DanceDTO getRhythm(@PathVariable UUID id) {
        return danceService.get(id);
    }

    @PostMapping()
    public ResponseEntity<UUID> createRhythm(@Validated @RequestBody NewDanceDTO newDanceDTO) {
        return ResponseEntity.of(danceService.create(newDanceDTO));
    }

}
