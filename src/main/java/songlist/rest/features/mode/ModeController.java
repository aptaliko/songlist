package songlist.rest.features.mode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import songlist.exceptions.ValidationException;
import songlist.model.features.mode.dto.ModeDTO;
import songlist.model.features.mode.dto.NewModeDTO;
import songlist.service.features.mode.ModeService;

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

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateMode(@PathVariable String id, @Valid @NotNull @RequestBody NewModeDTO newModeDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(modeService.update(id, newModeDTO));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
