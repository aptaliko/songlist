package songlist.rest.features;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import songlist.exceptions.ValidationException;
import songlist.model.features.dance.dto.DanceDTO;
import songlist.model.features.dance.dto.NewDanceDTO;
import songlist.service.features.DanceService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/dances")
@Validated
public class DanceController {

    DanceService danceService;

    public DanceController(DanceService danceService) {
        this.danceService = danceService;
    }

    @GetMapping(value = "/")
    public List<DanceDTO> getAllDances() {
        return danceService.getAll();
    }

    @GetMapping(value = "/{id}")
    public DanceDTO getDance(@PathVariable String id) {
        return danceService.getDTO(id);
    }

    @PostMapping()
    public ResponseEntity<String> createDance(@Valid @NotNull @RequestBody NewDanceDTO newDanceDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(danceService.create(newDanceDTO));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDance(@PathVariable String id) {
        danceService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateDance(@PathVariable String id, @Valid @NotNull @RequestBody NewDanceDTO newDanceDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(danceService.update(id, newDanceDTO));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}