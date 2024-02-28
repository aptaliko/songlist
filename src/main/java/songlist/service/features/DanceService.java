package songlist.service.features;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import songlist.exceptions.ValidationException;
import songlist.model.features.dance.dto.DanceDTO;
import songlist.model.features.dance.dto.DanceInput;
import songlist.model.song.dto.SongDTO;
import songlist.repository.abstraction.DanceRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DanceService {

    DanceRepository danceRepository;

    public DanceService(@Qualifier("danceRepository") DanceRepository danceRepository) {
        this.danceRepository = danceRepository;
    }

    public List<DanceDTO> getAll() {
        return danceRepository.findAllDances().stream().map(s -> new DanceDTO(s.getId(), s.getName())).collect(Collectors.toList());
    }

    public DanceDTO getDTO(String id) {
        return danceRepository.findDanceById(id);
    }

    public void create(DanceInput danceInput) {
        danceRepository.createDance(danceInput);
    }

    public void update(String id, DanceInput danceInput) throws ValidationException {
        danceRepository.updateDance(id, danceInput);
    }

    public void delete(String id) {
        danceRepository.deleteDanceById(UUID.fromString(id));
    }

}
