package songlist.rest.features;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import songlist.exceptions.ValidationException;
import songlist.model.features.mode.dto.ModeDTO;
import songlist.model.features.mode.dto.NewModeDTO;
import songlist.model.song.dto.SongDTO;
import songlist.service.features.ModeService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

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
    public ResponseEntity<String> createMode(@Valid @NotNull @RequestBody NewModeDTO newModeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(modeService.create(newModeDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateMode(@PathVariable String id, @Valid @NotNull @RequestBody NewModeDTO newModeDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(modeService.update(id, newModeDTO));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMode(@PathVariable String id) {
        modeService.delete(id);
    }

    @GetMapping(value = "/{id}/songs")
    public Set<SongDTO> getSongs(@PathVariable String id) {
        return modeService.getSongs(id);
    }
}
