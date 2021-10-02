package songlist.rest.mode;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import songlist.model.features.mode.dto.ModeDTO;
import songlist.model.features.mode.dto.NewModeDTO;
import songlist.service.mode.ModeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/modes")
public class ModeController {

    ModeService modeService;

    public ModeController(ModeService modeService) {
        this.modeService = modeService;
    }

    @GetMapping(value = "/")
    public List<ModeDTO> getAllRhythms() {
        return modeService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ModeDTO getRhythm(@PathVariable UUID id) {
        return modeService.get(id);
    }

    @PostMapping()
    public ResponseEntity<UUID> createRhythm(@Validated @RequestBody NewModeDTO newModeDTO) {
        return ResponseEntity.of(modeService.create(newModeDTO));
    }

}
