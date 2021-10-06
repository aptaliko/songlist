package songlist.service.dance;

import org.springframework.stereotype.Service;
import songlist.exceptions.ValidationException;
import songlist.model.features.dance.Dance;
import songlist.model.features.dance.dto.DanceDTO;
import songlist.model.features.dance.dto.NewDanceDTO;
import songlist.repository.dance.DanceRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DanceService {

    DanceRepository danceRepository;

    public DanceService(DanceRepository danceRepository) {
        this.danceRepository = danceRepository;
    }

    public List<DanceDTO> getAll() {
        return danceRepository.findAll().stream().map(s -> new DanceDTO(s.getId().toString(), s.getName())).collect(Collectors.toList());
    }

    public Optional<Dance> get(String id) {
        return danceRepository.findById(UUID.fromString(id));
    }

    public DanceDTO getDTO(String id) {
        Dance dance = danceRepository.getOne(UUID.fromString(id));

        return new DanceDTO(dance.getId().toString(), dance.getName());
    }

    public String create(NewDanceDTO newDanceDTO) {
        Dance dance = new Dance();
        dance.setName(newDanceDTO.getName());

        return danceRepository.save(dance).getId().toString();
    }

    public void delete(String id) {
        danceRepository.deleteById(UUID.fromString(id));
    }

    public String update(String id, NewDanceDTO newDanceDTO) throws ValidationException {
        Optional<Dance> dance = danceRepository.findById(UUID.fromString(id));

        if (dance.isEmpty()) {
            throw new ValidationException("No dance with id: " + id);
        }
        Dance d = dance.get();
        d.setName(newDanceDTO.getName());

        return danceRepository.save(d).getId().toString();
    }
}
