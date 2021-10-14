package songlist.rest.features;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import songlist.exceptions.ValidationException;
import songlist.model.features.region.dto.NewRegionDTO;
import songlist.model.features.region.dto.RegionDTO;
import songlist.model.song.dto.SongDTO;
import songlist.service.features.RegionService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/regions")
@Validated
public class RegionController {

    RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping(value = "/")
    public List<RegionDTO> getAllRegions() {
        return regionService.getAll();
    }

    @GetMapping(value = "/{id}")
    public RegionDTO getRegion(@PathVariable String id) {
        return regionService.getDTO(id);
    }

    @PostMapping()
    public ResponseEntity<String> createRegion(@Valid @NotNull @RequestBody NewRegionDTO newRegionDTO) {
       try{
           return ResponseEntity.status(HttpStatus.CREATED).body(regionService.create(newRegionDTO));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateRegion(@PathVariable String id, @Valid @NotNull @RequestBody NewRegionDTO newRegionDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(regionService.update(id, newRegionDTO));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRegion(@PathVariable String id) {
        regionService.delete(id);
    }

    @GetMapping(value = "/{id}/songs")
    public List<SongDTO> getSongs(@PathVariable String id) {
        return regionService.getSongs(id);
    }

}
