package songlist.service.features;

import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import songlist.exceptions.ValidationException;
import songlist.mappers.SongMapper;
import songlist.model.features.dance.Dance;
import songlist.model.features.dance.dto.DanceDTO;
import songlist.model.features.dance.dto.NewDanceDTO;
import songlist.model.song.dto.SongDTO;
import songlist.repository.features.DanceRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static songlist.utils.Constants.DOES_NOT_EXIST;

@Service
public class DanceService {

    DanceRepository danceRepository;

    public DanceService(DanceRepository danceRepository) {
        this.danceRepository = danceRepository;
    }

    public List<DanceDTO> getAll() {
        return danceRepository.findAll().stream().map(s -> new DanceDTO(s.getId().toString(), s.getName())).collect(Collectors.toList());
    }

    public Dance get(String id) throws ValidationException {
        Optional<Dance> dance = danceRepository.findById(UUID.fromString(id));

        if (dance.isEmpty()) {
            throw new ValidationException("Dance with id" + id + DOES_NOT_EXIST);
        }
        return dance.get();
    }

    public DanceDTO getDTO(String id) {
        Dance dance = danceRepository.findById(UUID.fromString(id)).orElseThrow(
            NoSuchElementException::new);

        return new DanceDTO(dance.getId().toString(), dance.getName());
    }

    public String create(NewDanceDTO newDanceDTO) {
        Dance dance = new Dance();
        dance.setName(newDanceDTO.getName());

        return danceRepository.save(dance).getId().toString();
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

    public void delete(String id) {
        danceRepository.deleteById(UUID.fromString(id));
    }

    public Set<SongDTO> getSongs(String id) {
        try {
            return this.get(id).getSongs().stream().map(SongMapper::toSongDTO).collect(Collectors.toSet());
        } catch (ValidationException e) {
            return Set.of();
        }
    }
}
