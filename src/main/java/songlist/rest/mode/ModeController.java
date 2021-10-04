package songlist.rest.mode;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import songlist.model.features.mode.dto.ModeDTO;
import songlist.model.features.mode.dto.NewModeDTO;
import songlist.service.mode.ModeService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/modes")
@Validated
public class ModeController {

    ModeService modeService;

    public ModeController(ModeService modeService) {
        this.modeService = modeService;
    }

    @GetMapping(value = "/")
    public List<ModeDTO> getAllModes() {
        return modeService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ModeDTO getMode(@PathVariable String id) {
        return modeService.getDTO(id);
    }

    @PostMapping()
    public ResponseEntity<UUID> createMode(@Valid @NotNull @RequestBody NewModeDTO newModeDTO) {
        return ResponseEntity.of(modeService.create(newModeDTO));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMode(@PathVariable String id) {
        modeService.delete(id);
    }
}
