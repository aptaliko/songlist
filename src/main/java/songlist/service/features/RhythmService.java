package songlist.service.features;

import org.springframework.stereotype.Service;
import songlist.exceptions.ValidationException;
import songlist.model.features.rhythm.Rhythm;
import songlist.model.features.rhythm.dto.NewRhythmDTO;
import songlist.model.features.rhythm.dto.RhythmDTO;
import songlist.repository.features.RhythmRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static songlist.utils.Constants.DOES_NOT_EXIST;

@Service
public class RhythmService {

    RhythmRepository rhythmRepository;

    public RhythmService(RhythmRepository rhythmRepository) {
        this.rhythmRepository = rhythmRepository;
    }

    public List<RhythmDTO> getAll() {
        return rhythmRepository.findAll().stream().map(s -> new RhythmDTO(s.getId().toString(), s.getName(), s.getMeter())).collect(Collectors.toList());
    }

    public RhythmDTO getDTO(String id) {
        Rhythm rhythm = rhythmRepository.getOne(UUID.fromString(id));
        return new RhythmDTO(rhythm.getId().toString(), rhythm.getName(), rhythm.getMeter());
    }

    public Rhythm get(String id) throws ValidationException {
        Optional<Rhythm> rhythm = rhythmRepository.findById(UUID.fromString(id));

        if (rhythm.isEmpty()) {
            throw new ValidationException("Rhythm with id" + id + DOES_NOT_EXIST);
        }
        return rhythm.get();
    }

    public String create(NewRhythmDTO newRhythmDTO) {
        Rhythm rhythm = new Rhythm();
        rhythm.setName(newRhythmDTO.getName());
        rhythm.setMeter(newRhythmDTO.getMeter());

        return rhythmRepository.save(rhythm).getId().toString();
    }

    public String update(String id, NewRhythmDTO newRhythmDTO) throws ValidationException {
        Optional<Rhythm> rhythm = rhythmRepository.findById(UUID.fromString(id));

        if (rhythm.isEmpty()) {
            throw new ValidationException("No rhythm with id: " + id);
        }
        Rhythm r = rhythm.get();
        r.setName(newRhythmDTO.getName());
        r.setMeter(newRhythmDTO.getMeter());

        return rhythmRepository.save(r).getId().toString();
    }

    public void delete(String id) {
        rhythmRepository.deleteById(UUID.fromString(id));
    }
}
