package songlist.service.features;

import org.springframework.stereotype.Service;
import songlist.exceptions.ValidationException;
import songlist.mappers.SongMapper;
import songlist.model.features.region.Region;
import songlist.model.features.region.dto.NewRegionDTO;
import songlist.model.features.region.dto.RegionDTO;
import songlist.model.song.dto.SongDTO;
import songlist.repository.features.RegionRepository;

import java.util.*;
import java.util.stream.Collectors;

import static songlist.utils.Constants.DOES_NOT_EXIST;
import static songlist.utils.Utils.isNotNullOrEmpty;

@Service
public class RegionService {

    RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<RegionDTO> getAll() {
        return regionRepository.findAll().stream().map(r -> new RegionDTO(r.getId().toString(), r.getFullRegionName())).collect(Collectors.toList());
    }

    public Region get(String id) throws ValidationException {
        Optional<Region> region = regionRepository.findById(UUID.fromString(id));

        if (region.isEmpty()) {
            throw new ValidationException("Region with id: " + id  + DOES_NOT_EXIST);
        }
        return region.get();
    }

    public RegionDTO getDTO(String id) {
        Region region = regionRepository.findById(UUID.fromString(id)).orElseThrow(
            NoSuchElementException::new);
        return new RegionDTO(region.getId().toString(), region.getFullRegionName());
    }

    public String create(NewRegionDTO newRegionDTO) throws ValidationException {
        Region region = new Region();
        updateRegionObject(region, newRegionDTO);

        return regionRepository.save(region).getId().toString();
    }

    public String update(String id, NewRegionDTO newRegionDTO) throws ValidationException {
        Region region = get(id);
        updateRegionObject(region, newRegionDTO);

        return regionRepository.save(region).getId().toString();
    }

    public void delete(String id) {
        regionRepository.deleteById(UUID.fromString(id));
    }

    private void updateRegionObject(Region region, NewRegionDTO dto) throws ValidationException {
        region.setName(dto.getName());
        if (isNotNullOrEmpty(dto.getParentRegionId())) {
            region.setParentRegion(get(dto.getParentRegionId()));
        }
    }

    public Set<UUID> getAllChildrenRegionIds(String regionId) {
        return getAllChildrenRegions(regionId).stream().map(Region::getId).collect(Collectors.toSet());
    }

    public Set<Region> getAllChildrenRegions(String regionId) {
        try {
            return this.get(regionId).flattened().collect(Collectors.toSet());
        } catch (ValidationException e) {
            return Set.of();
        }
    }

    public List<SongDTO> getSongs(String id) {
        List<SongDTO> songs = new ArrayList<>();
        getAllChildrenRegions(id).forEach(region -> songs.addAll(region.getSongs().stream().map(SongMapper::toSongDTO).collect(Collectors.toList())));

        return songs;
    }
}
